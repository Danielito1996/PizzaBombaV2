package com.elitec.italiana.data.Repositories.NetworkRepository

import com.elitec.italiana.data.APIServices.ApiServices
import com.elitec.italiana.data.URL.IUrlDictionary
import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.Compra
import com.elitec.italiana.domain.Entities.ElementTypes
import com.elitec.italiana.domain.Entities.IEntity
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val apiServices:ApiServices,
val urlDictionary: IUrlDictionary
):INetworkRepository {
	override suspend fun <T: IEntity> getEndpointData(types: ElementTypes): List<Categoria> {
		return apiServices.getData<Categoria>(urlDictionary.DevolverURLParcial(types))
	}

	override suspend fun postOrder(compra:Compra) {
		val url = urlDictionary.DevolverURLParcial(ElementTypes.Compras)
		apiServices.postData(urlDictionary.DevolverURLParcial(ElementTypes.Compras),compra)
	}
}