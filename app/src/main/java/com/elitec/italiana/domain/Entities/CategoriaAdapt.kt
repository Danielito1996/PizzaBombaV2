package com.elitec.italiana.domain.Entities

data class CategoriaAdapt(
	override val id: Int,
	var descripcion: String?=null,
	var productoAdapts: List<ProductoAdapt>?): IEntity {

	}
