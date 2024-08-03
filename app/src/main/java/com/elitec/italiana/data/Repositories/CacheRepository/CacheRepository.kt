package com.elitec.italiana.data.Repositories.CacheRepository

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import com.elitec.italiana.data.Repositories.CacheRepository.Data.IDataCache
import com.elitec.italiana.data.Repositories.CacheRepository.Image.IImageCache
import com.elitec.italiana.domain.Adapters.ICategoriasAdapter
import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.CategoriaAdapt
import com.elitec.italiana.domain.Entities.ElementTypes
import javax.inject.Inject

class CacheRepository @Inject constructor(
	private val imageCache: IImageCache,
	private val dataCache:IDataCache
):ICacheRepository {
 	override suspend fun get():MutableList<Categoria>{
		val response = dataCache.get()
		return response.toMutableList()
	 }
	override suspend fun get(
		key:String,
		types: ElementTypes,
		context: Context
	):ImageBitmap = imageCache.get(key,types,context)
}