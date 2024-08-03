package com.elitec.italiana.data.ImageAPIService

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import com.elitec.italiana.domain.Entities.ElementTypes

interface IGetImageAPI {
	suspend fun GetImagesAPI(tipo: ElementTypes, nombreImagen: String,context: Context): ImageBitmap?
}