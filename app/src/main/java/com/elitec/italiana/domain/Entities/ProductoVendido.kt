package com.elitec.italiana.domain.Entities


data class ProductoVendido(
	override val id:Int,
	var cantidad: Int=0,
	var costoUnitario:Double=0.0
): IEntity {
	fun DevolverCostoTotal():Double = cantidad*costoUnitario
}
