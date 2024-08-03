package com.elitec.italiana.data.APIServices

import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.Compra
import com.elitec.italiana.domain.Entities.IEntity
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
	//Son esta variante puedo controlar la ruta de la solicitudes , montandose en la API base definida en los modulos
	@GET("{endpoint}")
	suspend fun getImage(@Path("endpoint") endpoint: String, @Query("fileName") fileName:String): ResponseBody

	@GET("{endpoint}")
	suspend fun <T: IEntity>getData(@Path("endpoint") endpoint: String): List<Categoria>

	@POST("{endpoint}")
	suspend fun postData(@Path("endpoint") endpoint: String, @Body data: Compra)

	@PUT("{endpoint}")
	suspend fun <T: IEntity> putData(@Path("endpoint") endpoint: String, @Body data: T): Response<String>

	@DELETE("{endpoint}")
	suspend fun <T: IEntity> deleteData(@Path("endpoint") endpoint: String): Response<String>
}