package com.elitec.italiana.data.URL

import com.elitec.italiana.domain.Entities.ElementTypes
import javax.inject.Inject

class UrlDictionary @Inject constructor(): IUrlDictionary {
	override fun DevolverURLParcial(tipo: ElementTypes):String = when (tipo) {
			ElementTypes.Categorias-> "Categorias"
			ElementTypes.Productos->"Productos"
			ElementTypes.Compras-> "Ventas"
			ElementTypes.ImagenCategoria->"ImagesCategories"
			ElementTypes.ImagenProducto->"ImagesProduct"
			else-> throw  Exception("No existe implementacion de peticion web para esta Entidad aun")
		}
}