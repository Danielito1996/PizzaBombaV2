package com.elitec.italiana.bussinesLogic.Filters

import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.CategoriaAdapt
import com.elitec.italiana.domain.Entities.Producto
import com.elitec.italiana.domain.Entities.ProductoAdapt

interface IFilters {
	fun getProductsByCategory(id:Int, categoriaAdapts:List<Categoria>): List<Producto>
	fun getProductByName(name:String, categoriaAdapts:List<Categoria>):Producto
	fun getProductById(id: Int,categoria: List<Categoria>):Producto?
}