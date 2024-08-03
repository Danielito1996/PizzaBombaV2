package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.configuration.NetInterface.IIPAddress
import com.elitec.italiana.configuration.NetInterface.IPAddress
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class IPModule {
	@Binds
	@Singleton
	abstract fun bindIPAddress(ip:IPAddress):IIPAddress
}