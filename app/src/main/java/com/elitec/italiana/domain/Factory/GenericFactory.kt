package com.elitec.italiana.domain.Factory

import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.Compra
import com.elitec.italiana.domain.Entities.ElementTypes
import com.elitec.italiana.domain.Entities.IEntity
import com.elitec.italiana.domain.Entities.Oferta
import com.elitec.italiana.domain.Entities.Producto
import com.elitec.italiana.domain.Entities.ProductoAdapt
import com.elitec.italiana.domain.Entities.ProductoVendido
import com.elitec.italiana.domain.Entities.ProductosPreferidos
import javax.inject.Inject

class GenericFactory @Inject constructor(): IGenericFactory {
	override fun buildEntity(
		descripcion: String,
		id: Int,
		productos: List<Producto>):Categoria = Categoria(
			id = id,
			descripcion = descripcion,
			productos = productos
		)
	override fun buildEntity(
		descripcion: String,
		id: Int,
		nombre: String,
		precio: Double):Producto =Producto(
			id=id,
			descripcion = descripcion,
			nombre = nombre,
			precio = precio
		)
	override fun buildEntity(
		id: Int):ProductoVendido = ProductoVendido(id)

	override fun buildEntity():ProductosPreferidos= ProductosPreferidos()


	override fun buildEntity (
		id:Int, fecha:String): Compra = Compra(id,fecha)
}