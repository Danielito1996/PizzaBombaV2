package com.elitec.italiana.bussinesLogic.Filters

import com.elitec.italiana.configuration.PersonalException.NoProductException
import com.elitec.italiana.configuration.PersonalException.NoProductFindException
import com.elitec.italiana.data.Repositories.CacheRepository.ICacheRepository
import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.Producto
import javax.inject.Inject

class Filters @Inject constructor(private val cache:ICacheRepository): IFilters {

	override fun getProductsByCategory(id:Int, categoriaAdapts:List<Categoria>): List<Producto> =
		categoriaAdapts.firstOrNull{it.id==id}?.productos?:throw NoProductException(
			"No existe productos en oferta para esta categoria"
		)

	override fun getProductByName(name:String, categoria:List<Categoria>):Producto = getProductsAll(categoria).firstOrNull {
		it.nombre==name
	}?:throw NoProductFindException("El producto" +
			" ${name} no se encuentra en la " +
			"oferta por el momento")

	private fun getProductsAll(categoriaAdapts:List<Categoria>):List<Producto> {
		val productoAdapt = mutableListOf<Producto>()
		categoriaAdapts.forEach {it.productos?.forEach{ it2 -> productoAdapt.add(it2)}}
		return productoAdapt
	}

	override fun getProductById(id: Int,categoria: List<Categoria>):Producto? {
		val listaProducto = getProductsAll(categoria)
		return listaProducto.firstOrNull{it.id==id}
	}
}