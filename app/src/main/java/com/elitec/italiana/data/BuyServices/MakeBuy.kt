package com.elitec.italiana.data.BuyServices

import android.content.Context
import android.widget.Toast
import com.elitec.italiana.bussinesLogic.BuyOrders.IBuyOrder
import com.elitec.italiana.configuration.NetworkLogs.LogginInterceptor
import com.elitec.italiana.data.APIServices.ApiServices
import com.elitec.italiana.data.URL.IUrlDictionary
import com.elitec.italiana.domain.Entities.Compra
import com.elitec.italiana.domain.Entities.ElementTypes
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MakeBuy @Inject constructor(
	private val api:ApiServices,
	private val urlendpoint:IUrlDictionary,
	private val logginInterceptor: LogginInterceptor,
	private val buyOrder:IBuyOrder
):IMakeBuy {
	override suspend fun BuyOrder(compra: Compra,context:Context):Boolean {
		try {
			api.postData(urlendpoint.DevolverURLParcial(ElementTypes.Compras),compra)
			return buyOrder.newBuy(compra)
		}
		catch (e:Exception) {
			// Manejar el error
			val urlPeticion = logginInterceptor.requestUrl
			val message:String
			when (e) {

				is HttpException -> {
					// Un error no 2xx HTTP fue recibido del servidor
					message= "Error HTTP: ${e.code()}"
				}

				is IOException -> {
					// Un error de red ocurrió mientras se realizaba la llamada
					message="Error de red"
				}

				else -> {
					// Un error desconocido ocurrió
					message="Error desconocido"
				}
			}
			Toast.makeText(context,message,Toast.LENGTH_SHORT)
			return false
		}
	}
}