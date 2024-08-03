package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.configuration.SecureId.ISecureID
import com.elitec.italiana.configuration.SecureId.SecureID
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SecureIDModule {
	@Binds
	@Singleton
	abstract fun bindSecureID(secureId:SecureID):ISecureID
}