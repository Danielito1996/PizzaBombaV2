package com.elitec.italiana.presentation.ScreenAdapters

import android.content.Context
import androidx.compose.ui.unit.TextUnit

interface IFontAdapter {
	fun getFontSizeByScreen(context: Context): TextUnit
}