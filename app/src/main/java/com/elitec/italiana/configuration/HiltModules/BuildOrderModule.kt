package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.bussinesLogic.BuildOrders.BuildOrder
import com.elitec.italiana.bussinesLogic.BuildOrders.IBuildOrder
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BuildOrderModule {
	@Binds
	@Singleton
	abstract fun bindBuildOrden(order: BuildOrder): IBuildOrder
}