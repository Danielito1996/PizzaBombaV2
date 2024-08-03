package com.elitec.italiana.data.Repositories.BDRepository.PreferedDB

import com.elitec.italiana.domain.Entities.ProductosPreferidos
import kotlinx.coroutines.flow.Flow

interface PreferidosRepo {
	fun getAsFlow(): Flow<MutableList<ProductosPreferidos>>
	fun set(producto: ProductosPreferidos)
	fun delete(producto: ProductosPreferidos)
}