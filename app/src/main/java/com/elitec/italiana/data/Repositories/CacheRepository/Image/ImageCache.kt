package com.elitec.italiana.data.Repositories.CacheRepository.Image

import android.content.Context
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import com.elitec.italiana.R
import com.elitec.italiana.data.ImageAPIService.IGetImageAPI
import com.elitec.italiana.domain.Entities.ElementTypes
import dagger.hilt.android.internal.modules.ApplicationContextModule
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ImageCache @Inject constructor(private val imageNetwork:IGetImageAPI): IImageCache {
	private val cache = HashMap<String, ImageBitmap>()

	override val size:Int
		get() = cache.size

	override suspend fun get (key:String,types: ElementTypes, context: Context): ImageBitmap {
		return cache[key] ?: getImageOutside(key, types, context) }

	private fun set (key: String,image:ImageBitmap) {
		cache[key]=image
	}

	private suspend fun getImageOutside (
		key: String,types: ElementTypes,
		context: Context
	):ImageBitmap {
		var image = imageNetwork.GetImagesAPI(types,key,context)?:throw NullPointerException()
		set(key,image)
		return image
	}

	private fun webpToImageBitmap(context: Context): ImageBitmap {
		val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.iconopizza)
		return bitmap.asImageBitmap()
	}


}