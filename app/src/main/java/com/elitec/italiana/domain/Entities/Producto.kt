package com.elitec.italiana.domain.Entities

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

data class Producto(
     val descripcion: String,
    override val id: Int,
    val nombre: String,
    val precio: Double
):IEntity