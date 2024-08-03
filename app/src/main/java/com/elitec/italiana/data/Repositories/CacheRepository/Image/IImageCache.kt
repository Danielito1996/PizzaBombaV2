package com.elitec.italiana.data.Repositories.CacheRepository.Image

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import com.elitec.italiana.domain.Entities.ElementTypes

interface IImageCache {
	val size:Int
 	suspend fun get (key:String,types: ElementTypes,context: Context): ImageBitmap
}