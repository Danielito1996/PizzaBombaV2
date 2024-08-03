package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.domain.Adapters.CategoriaAdapter
import com.elitec.italiana.domain.Adapters.ICategoriasAdapter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AdaptersModule {
	@Binds
	@Singleton
	abstract fun bindAsapter(adapt:CategoriaAdapter):ICategoriasAdapter
}