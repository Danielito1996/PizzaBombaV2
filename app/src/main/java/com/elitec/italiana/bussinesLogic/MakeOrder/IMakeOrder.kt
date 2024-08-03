package com.elitec.italiana.bussinesLogic.MakeOrder

import com.elitec.italiana.domain.Entities.Compra

interface IMakeOrder {
	suspend fun OrderNow (compra: Compra):Boolean
}