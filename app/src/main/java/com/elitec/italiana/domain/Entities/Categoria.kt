package com.elitec.italiana.domain.Entities


data class Categoria(
    val descripcion: String,
    override val id: Int,
    val productos: List<Producto>
):IEntity