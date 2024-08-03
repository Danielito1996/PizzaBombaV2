package com.elitec.italiana.configuration.SecureId

import android.content.Context

interface ISecureID {

	fun obtenerIDUnico(context: Context):String
}