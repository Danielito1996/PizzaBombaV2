package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.bussinesLogic.BuyOrders.IBuyOrder
import com.elitec.italiana.bussinesLogic.MakeOrder.IMakeOrder
import com.elitec.italiana.bussinesLogic.MakeOrder.MakeOrder
import com.elitec.italiana.data.BuyServices.IMakeBuy
import com.elitec.italiana.data.BuyServices.MakeBuy
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BuyModules {
	@Binds
	@Singleton
	abstract fun bindsBuyOrderModule(buyOptions:IBuyOrder):IBuyOrder

	@Binds
	@Singleton
	abstract fun bindUpBuyToServer(postBuy:MakeBuy):IMakeBuy

	@Binds
	@Singleton
	abstract fun bindMakeOrder (makeOrder:MakeOrder):IMakeOrder
}