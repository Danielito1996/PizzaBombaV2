package com.elitec.italiana.presentation.UIHelpers

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import javax.inject.Inject

class ScreenSizeHelper @Inject constructor():IScreenSizeHelper {
	override fun getScreenWidth(context: Context): Int {
		val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
		val displayMetrics = DisplayMetrics()
		windowManager.defaultDisplay.getMetrics(displayMetrics)
		return displayMetrics.widthPixels
	}
}