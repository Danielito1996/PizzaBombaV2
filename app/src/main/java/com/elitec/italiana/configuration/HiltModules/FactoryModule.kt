package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.domain.Factory.GenericFactory
import com.elitec.italiana.domain.Factory.IGenericFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class FactoryModule {
	@Binds //Permite especificar que clase concreta implementara una interfaz
	@Singleton
	abstract fun bindIGenericFactory(factory: GenericFactory): IGenericFactory
}