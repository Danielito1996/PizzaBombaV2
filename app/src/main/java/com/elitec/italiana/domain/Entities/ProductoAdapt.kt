package com.elitec.italiana.domain.Entities

data class ProductoAdapt(
	override val id: Int,
	var nombre: String?="",
	var precio: Double?=0.0,
	var descripcion: String?="",
): IEntity {
	// Función para generar la URL de la foto a partir del nombre
}
