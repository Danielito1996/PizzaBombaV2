package com.elitec.italiana.data.Repositories.CacheRepository.Data

import com.elitec.italiana.data.Repositories.NetworkRepository.INetworkRepository
import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.CategoriaAdapt
import com.elitec.italiana.domain.Entities.ElementTypes
import retrofit2.Response
import javax.inject.Inject

class DataCache @Inject constructor(
	private val network:INetworkRepository
) : IDataCache {
	private val cache = HashMap<ElementTypes,List<Categoria>>()
	override val size: Int
		get() = cache.size

	override suspend fun get(): List<Categoria> = cache[ElementTypes.Categorias]?:getDataOutside()

	private fun set(data: List<Categoria>) {
		this.cache[ElementTypes.Categorias]=data
	}

	private suspend fun getDataOutside():List<Categoria> {
		val response=network.getEndpointData<Categoria>(ElementTypes.Categorias)
		set(response)
		return response
	}

}