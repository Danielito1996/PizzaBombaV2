package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.data.URL.IUrlDictionary
import com.elitec.italiana.data.URL.UrlDictionary
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UrlModule {
	@Binds
	@Singleton
	abstract fun bindIUrlDictionary(url: UrlDictionary): IUrlDictionary
}