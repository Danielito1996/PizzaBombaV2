package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.bussinesLogic.Filters.Filters
import com.elitec.italiana.bussinesLogic.Filters.IFilters
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FiltersModule {
	@Binds
	@Singleton
	abstract fun bindFilters(filters: Filters): IFilters
}