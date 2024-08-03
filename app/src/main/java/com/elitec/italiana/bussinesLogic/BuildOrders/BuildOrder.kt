package com.elitec.italiana.bussinesLogic.BuildOrders

import android.content.res.Resources.NotFoundException
import com.elitec.italiana.domain.Entities.Producto
import com.elitec.italiana.domain.Entities.ProductoVendido
import com.elitec.italiana.domain.Factory.IGenericFactory
import javax.inject.Inject

class BuildOrder @Inject constructor(
	private val factory: IGenericFactory
): IBuildOrder {

   private val order = mutableListOf<ProductoVendido>()

	override fun getOrder():List<ProductoVendido> = order
	override fun addProductToOrder(producto: Producto):Boolean {
		try{
			order.forEach {
				if (it.id==producto.id) {
					it.cantidad++
					return true
				}
			}
			val build = factory.buildEntity(producto.id)
			build.cantidad=1
			build.costoUnitario=producto.precio
			order.add(build)
			return true
		}
		catch (e:Exception){
			return false
		}
	}

	override fun removeProductToOrder(producto: Producto):Boolean {
		order.forEach {
			if (it.id==producto.id) {
				it.cantidad--
				if (it.cantidad==0) {
					order.remove(it)
				}
				return true
			}
		}
		throw NotFoundException("Producto no encontrado entre sus pedidos")
	}
}