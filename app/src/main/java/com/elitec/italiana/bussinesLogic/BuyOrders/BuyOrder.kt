package com.elitec.italiana.bussinesLogic.BuyOrders

import com.elitec.italiana.domain.Entities.Compra
import javax.inject.Inject

class BuyOrder @Inject constructor(): IBuyOrder {
	private val buys = mutableListOf<Compra>()

	override fun newBuy (compra: Compra):Boolean {
		if(compra.productos?.size!!<=0) {
			return false
		}
		buys.add(compra)
		return true
	}
	override fun getBuyHistory(): List<Compra> = buys.sortedBy { it.fecha }
}