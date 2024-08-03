package com.elitec.italiana.presentation.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elitec.italiana.R
import com.elitec.italiana.bussinesLogic.MakeOrder.IMakeOrder
import com.elitec.italiana.configuration.Notifications.INotification
import com.elitec.italiana.configuration.SecureId.ISecureID
import com.elitec.italiana.data.Repositories.BDRepository.IRealmDataBaseRepository
import com.elitec.italiana.data.Repositories.CacheRepository.ICacheRepository
import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.Notificaciones
import com.elitec.italiana.domain.Entities.Producto
import com.elitec.italiana.domain.Entities.ProductoVendido
import com.elitec.italiana.domain.Factory.IGenericFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PedidosViewModel @Inject constructor(
	val cache: ICacheRepository,
	private val _bd: IRealmDataBaseRepository,
	val factory: IGenericFactory,
	val DeviceId: ISecureID,
	val makeOrder:IMakeOrder,
	private val showNotificacion:INotification

):ViewModel() {
	//Estados de animaciones
	var showModalBottomSheet by  mutableStateOf(false)
	val animarNotificacion by mutableStateOf(false)
	val cantidadNotificaciones by mutableStateOf(0)

	//Pedido
	val pedido by mutableStateOf(mutableListOf<ProductoVendido>())
	var cantidadDeProductosAnadidos by mutableStateOf(0)
	var saldoAPagar by mutableStateOf(0.0)
	var nuevoPedido by mutableStateOf(false)

	//Notificaciones
	var notificationDialog by mutableStateOf(false)
	var notificacionesPendientes by mutableStateOf(0)
	//var listaCategorias by mutableStateOf(mutableListOf<Categoria>())
	private var _listaNotificaciones: Flow<MutableList<Notificaciones>> = flowOf(mutableListOf())
	var listaNotificaciones: StateFlow<MutableList<Notificaciones>>

	init {

		_listaNotificaciones = _bd.RealmNotificationDataBase().getAsFlow()
		listaNotificaciones = _listaNotificaciones.stateIn(
			viewModelScope,
			SharingStarted.Eagerly,
			mutableListOf())
	}

	fun AddProductToListOfOrder(productoVendido: ProductoVendido): Boolean {
		try {
			val contain = pedido.contains(
				pedido.firstOrNull{ it.id==productoVendido.id }
			)
			if (contain) {
				val index= pedido.indexOf(pedido.firstOrNull{ it.id==productoVendido.id})
				val productoEncontrado = pedido[index]
				productoEncontrado.cantidad+=productoVendido.cantidad
				pedido[index]=productoEncontrado
			}
			else {
				pedido.add(productoVendido)
				cantidadDeProductosAnadidos++
			}
			saldoAPagar=computeTheAllMoneyToBy(pedido)
			nuevoPedido = true
			return  true
		}
		catch (ex:Exception) {
			return false
		}
	}

	fun RemoveProductToOrder(context: Context,productoVendido: ProductoVendido) {
		try {
			val index= pedido.indexOf(pedido.firstOrNull{ it.id==productoVendido.id})
			val productoEncontrado = pedido[index]
			if (productoEncontrado.cantidad==0)
				throw Exception()
			productoEncontrado.cantidad--
			pedido[index]=productoEncontrado
			saldoAPagar=computeTheAllMoneyToBy(pedido)
		}
		catch (ex:Exception) {
			Toast.makeText(
				context,
				"Este producto ya no se puede reducir mas",
				Toast.LENGTH_SHORT).show()
		}
	}
	private fun computeTheAllMoneyToBy(
		mutableList: MutableList<ProductoVendido>
	):Double {
		var saldo = 0.0
		mutableList.forEach {
			saldo+= it.cantidad*it.costoUnitario
		}
		return saldo
	}

	fun SendOrderToServer (context: Context) {
		val sdf = SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US
		)
		val fecha = sdf.format(Date())
		viewModelScope.launch {
			try {

				val compra = factory.buildEntity(0, fecha)
				compra.device = DeviceId.obtenerIDUnico(context)
				if (saldoAPagar == 0.0) {
					throw Exception("No ha agregado nada al pedido")
				}
				compra.monto = saldoAPagar
				compra.productos = pedido.toList()

				if (makeOrder.OrderNow(compra)) {
					cantidadDeProductosAnadidos = 0
					showModalBottomSheet = false
					showNotificacion.onlySounds(context)

					notificacionesPendientes++
					pedido.clear()
					MakeSaveNotification(
						mensaje = "Su pedido ha sido entregado satisfactoriamente, su monto total ha sido $ $saldoAPagar",
						fecha = fecha,
						icon = R.drawable.suscefull
					)
					Toast.makeText(
						context,
						"Se ha enviado su pedido correctamente",
						Toast.LENGTH_LONG
					)
					saldoAPagar = 0.0
				}
				else {
					throw Exception("El pedido no se pudo procesar")
				}
			}
			catch (ex:Exception) {
				MakeSaveNotification(
					mensaje = "Su pedido ha tenido problemas en la entrega, no se ha concretado correctamente",
					fecha = fecha,
					icon = R.drawable.wrong
				)
				Toast.makeText(context,ex.message,Toast.LENGTH_SHORT).show()

			}
		}
	}

	private fun MakeSaveNotification(icon:Int,mensaje:String,fecha:String) {
		val notificaciones = Notificaciones()
		notificaciones.fecha=fecha
		notificaciones.descripcion= mensaje
		notificaciones.icon= icon
		_bd.RealmNotificationDataBase().set(notificaciones)
	}

}