package com.elitec.italiana.bussinesLogic.BuildOrders

import com.elitec.italiana.domain.Entities.Producto
import com.elitec.italiana.domain.Entities.ProductoVendido

interface IBuildOrder {
	fun addProductToOrder(producto: Producto):Boolean
	fun removeProductToOrder(producto: Producto):Boolean
	fun getOrder():List<ProductoVendido>
}