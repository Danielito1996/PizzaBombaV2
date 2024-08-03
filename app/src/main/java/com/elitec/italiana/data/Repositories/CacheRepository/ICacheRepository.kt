package com.elitec.italiana.data.Repositories.CacheRepository

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import com.elitec.italiana.domain.Entities.Categoria
import com.elitec.italiana.domain.Entities.CategoriaAdapt
import com.elitec.italiana.domain.Entities.ElementTypes

interface ICacheRepository {
	suspend fun get(): MutableList<Categoria>
	suspend fun get(key:String, types: ElementTypes, context: Context): ImageBitmap
}