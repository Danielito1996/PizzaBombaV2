package com.elitec.italiana.domain.Adapters

import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.CategoriaAdapt
import com.elitec.italiana.domain.Entities.ElementTypes
import com.elitec.italiana.domain.Entities.Oferta
import com.elitec.italiana.domain.Entities.ProductoAdapt
import com.elitec.italiana.domain.Factory.IGenericFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.json.Json
import retrofit2.Response
import javax.inject.Inject

class CategoriaAdapter @Inject constructor(
	private val factory:IGenericFactory
):ICategoriasAdapter{

	override fun Adapt(response: Response<String>):List<Categoria> {
		val json = response.body()!!
		val categorias: List<Categoria> = Json.decodeFromString(json)
		return categorias
	}
/*
	private fun AdaptStepByStep(lista:MutableList<CategoriaAdapt>):MutableList<CategoriaAdapt> {
		val listaAdapter = mutableListOf<CategoriaAdapt>()
		var listaparcialProductoAdapts= mutableListOf<ProductoAdapt>()
		lista.forEach {
			var categoriaAdapt = factory.ConstruirElemento<CategoriaAdapt>(ElementTypes.Categorias,it.id)
			categoriaAdapt.descripcion=it.descripcion
			it.productoAdapts?.forEach {
				var producto = it
				listaparcialProductoAdapts.add(producto)
			}
			categoriaAdapt.productoAdapts=listaparcialProductoAdapts
			listaparcialProductoAdapts.clear()
			listaAdapter.add(categoriaAdapt)
		}
		return listaAdapter
	}*/
}