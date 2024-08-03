package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.data.ImageAPIService.GetImageAPI
import com.elitec.italiana.data.ImageAPIService.IGetImageAPI
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ImageAPIModule {
	@Binds
	@Singleton
	abstract fun bindsGetImageModule(getImage:GetImageAPI):IGetImageAPI
}