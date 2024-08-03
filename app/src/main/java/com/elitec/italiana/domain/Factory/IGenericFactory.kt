package com.elitec.italiana.domain.Factory

import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.Compra
import com.elitec.italiana.domain.Entities.ElementTypes
import com.elitec.italiana.domain.Entities.IEntity
import com.elitec.italiana.domain.Entities.Producto
import com.elitec.italiana.domain.Entities.ProductoVendido
import com.elitec.italiana.domain.Entities.ProductosPreferidos

interface IGenericFactory {
	fun buildEntity(): ProductosPreferidos

	fun buildEntity(
		descripcion: String,
		id: Int,
		productos: List<Producto>): Categoria

	fun buildEntity(
		descripcion: String,
		id: Int,
		nombre: String,
		precio: Double):Producto

	fun buildEntity(
		id: Int): ProductoVendido

	fun buildEntity (
		id:Int, fecha:String): Compra
}