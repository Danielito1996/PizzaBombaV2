package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.data.Repositories.CacheRepository.CacheRepository
import com.elitec.italiana.data.Repositories.CacheRepository.Data.DataCache
import com.elitec.italiana.data.Repositories.CacheRepository.Data.IDataCache
import com.elitec.italiana.data.Repositories.CacheRepository.ICacheRepository
import com.elitec.italiana.data.Repositories.CacheRepository.Image.IImageCache
import com.elitec.italiana.data.Repositories.CacheRepository.Image.ImageCache
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Suppress("unused", "unused", "unused", "unused")
@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {
	@Binds
	@Singleton
 	abstract fun bindImageCache(imageCache: ImageCache):IImageCache

	@Binds
	@Singleton
	abstract fun bindDataCache(dataCache: DataCache):IDataCache

	@Binds
	@Singleton
	abstract fun bindCache(cache: CacheRepository):ICacheRepository
}