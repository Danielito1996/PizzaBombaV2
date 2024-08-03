package com.elitec.italiana.configuration.SecureId

import android.content.Context
import android.provider.Settings
import android.provider.Settings.Secure
import javax.inject.Inject

class SecureID @Inject constructor():ISecureID {
	override fun obtenerIDUnico(context: Context):String {
		return Settings.Secure.getString(context.contentResolver,Settings.Secure.ANDROID_ID)
	}
}