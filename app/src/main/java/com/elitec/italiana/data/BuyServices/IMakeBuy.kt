package com.elitec.italiana.data.BuyServices

import android.content.Context
import com.elitec.italiana.domain.Entities.Compra

interface IMakeBuy {
	suspend fun BuyOrder(compra: Compra, context: Context):Boolean
}