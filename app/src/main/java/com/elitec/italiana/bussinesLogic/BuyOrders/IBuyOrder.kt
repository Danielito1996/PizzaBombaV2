package com.elitec.italiana.bussinesLogic.BuyOrders

import com.elitec.italiana.domain.Entities.Compra

interface IBuyOrder {
	fun newBuy (compra: Compra):Boolean
	fun getBuyHistory(): List<Compra>
}