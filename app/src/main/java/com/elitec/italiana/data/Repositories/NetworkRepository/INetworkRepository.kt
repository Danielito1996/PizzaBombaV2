package com.elitec.italiana.data.Repositories.NetworkRepository

import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.Compra
import com.elitec.italiana.domain.Entities.ElementTypes
import com.elitec.italiana.domain.Entities.IEntity
import retrofit2.Response

interface INetworkRepository {
	suspend fun <T: IEntity> getEndpointData(types: ElementTypes): List<Categoria>
	suspend fun postOrder(compra: Compra)
}