package com.elitec.italiana.data.Repositories.CacheRepository.Data

import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.CategoriaAdapt
import retrofit2.Response

interface IDataCache {
	val size:Int
 	suspend fun get ():List<Categoria>
}