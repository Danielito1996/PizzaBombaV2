package com.elitec.italiana.domain.Adapters

import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.CategoriaAdapt
import com.elitec.italiana.domain.Entities.Oferta
import retrofit2.Response

interface ICategoriasAdapter {
	fun Adapt(response: Response<String>):List<Categoria>
}