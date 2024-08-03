package com.elitec.italiana.domain.Entities

data class Compra(
	override var id: Int,
	val fecha: String?="",
	var device:String="",
	var monto: Double=0.0,
	var productos: List<ProductoVendido>?= null
): IEntity
