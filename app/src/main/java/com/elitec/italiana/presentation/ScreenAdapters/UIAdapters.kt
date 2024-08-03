package com.elitec.italiana.presentation.ScreenAdapters

import android.content.Context
import androidx.compose.ui.unit.TextUnit
import javax.inject.Inject

class UIAdapters @Inject constructor(
	private val fontAdapter: IFontAdapter
) {
	fun getFontBySize(context: Context):TextUnit {
		return fontAdapter.getFontSizeByScreen(context)
	}
}