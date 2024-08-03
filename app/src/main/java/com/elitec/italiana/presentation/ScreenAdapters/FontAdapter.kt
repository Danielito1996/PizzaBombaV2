package com.elitec.italiana.presentation.ScreenAdapters

import android.content.Context
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.elitec.italiana.presentation.UIHelpers.IScreenSizeHelper
import javax.inject.Inject

class FontAdapter @Inject constructor(
	private val screenSize:IScreenSizeHelper
):IFontAdapter {
	val smart = 14.sp
	val medium = 17.sp
	val large = 20.sp
	val extraLarge = 23.sp
	override fun getFontSizeByScreen(context: Context):TextUnit {
		val sizeScreen = screenSize.getScreenWidth(context)
		return when {
			sizeScreen<720 -> smart
			sizeScreen<1080 -> medium
			sizeScreen<1420 -> large
			else -> extraLarge
		}
	}
}