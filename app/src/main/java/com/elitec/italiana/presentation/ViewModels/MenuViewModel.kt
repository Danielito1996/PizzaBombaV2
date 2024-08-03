package com.elitec.italiana.presentation.ViewModels

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.elitec.italiana.bussinesLogic.Filters.IFilters
import com.elitec.italiana.configuration.PersonalException.NoProductException
import com.elitec.italiana.data.Repositories.CacheRepository.ICacheRepository
import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.Compra
import com.elitec.italiana.domain.Entities.Producto
import com.elitec.italiana.domain.Entities.ProductoVendido
import com.elitec.italiana.domain.Factory.IGenericFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
	val cache:ICacheRepository,
	private val filter: IFilters,
	val factory: IGenericFactory
):ViewModel() {

	var noPedidos by mutableStateOf(0)
	//var listaCategorias by mutableStateOf(mutableListOf<Categoria>())
	private val _listaCategorias = MutableStateFlow(mutableListOf<Categoria>())
	val listaCategorias: StateFlow<List<Categoria>> = _listaCategorias

	private var _listaProducto = MutableStateFlow(mutableListOf<Producto>())
	var listaProducto:StateFlow<MutableList<Producto>> = _listaProducto

	init {
		CoroutineScope(Dispatchers.IO).launch {
			val categorias = cache.get() //Los estados no se modifican directamente en corrutinas, ya que se van de contexto
			withContext(Dispatchers.Main) {
				_listaCategorias.value = categorias
				_listaProducto.value = _listaCategorias.value[0].productos.toMutableList()
			}
		}
	}

	fun getProductOfCategory(id:Int,context: Context) {
	  try {
	  	_listaProducto.value = listaCategorias.value.first{it.id==id}.productos.toMutableList()
	  } catch (ex:Exception) {
		Toast.makeText(
			context,
			"Los datos de la categoria ${listaCategorias.value.first { it.id==id}.descripcion} no se cargaron correctamente",
		   Toast.LENGTH_SHORT
		).show()
	  }
	}

	fun getProductsByName(name:String):Producto =
		filter.getProductByName(name,listaCategorias.value)

	fun getProductById(id: Int):String? {
		return filter.getProductById(id,listaCategorias.value)?.nombre

	}

}