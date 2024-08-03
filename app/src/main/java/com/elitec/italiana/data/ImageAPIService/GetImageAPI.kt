package com.elitec.italiana.data.ImageAPIService

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.elitec.italiana.R
import com.elitec.italiana.configuration.NetworkLogs.LogginInterceptor
import com.elitec.italiana.data.APIServices.ApiServices
import com.elitec.italiana.data.URL.IUrlDictionary
import com.elitec.italiana.domain.Entities.ElementTypes
import javax.inject.Inject

class GetImageAPI @Inject constructor(
	private val apiServices:ApiServices,
	private val getURL: IUrlDictionary,
	private val logginfInterceptor: LogginInterceptor
) : IGetImageAPI {
	// Función para generar la URL de la foto a partir del nombre
	private fun resolverNombre(nombre: String): String = if (nombre != null) {
		nombre.lowercase().replace(" ", "")
	} else {
		"" // URL por defecto si el nombre es nulo
	}
	override suspend fun GetImagesAPI(tipo: ElementTypes, nombreImagen: String,context: Context): ImageBitmap? {
		try {
			val endPoint = getURL.DevolverURLParcial(tipo)
			val responseBody = apiServices.getImage(endPoint,resolverNombre(nombreImagen) )
			return responseBody.bytes().toImageBitmap()
		} catch (e: Exception) {
			// Manejar el error
			val urlPeticion = logginfInterceptor.requestUrl
			return webpToImageBitmap(context)
		}
	}

	private fun webpToImageBitmap(context: Context): ImageBitmap {
		val drawableId = R.drawable.iconopizza
		val source=ImageDecoder.createSource(context.resources,drawableId)
		val bitmap = ImageDecoder.decodeBitmap(source)
		return bitmap.asImageBitmap()
	}

	private fun ByteArray.toImageBitmap(): ImageBitmap =
		BitmapFactory.decodeByteArray(this,0,size).asImageBitmap()
}