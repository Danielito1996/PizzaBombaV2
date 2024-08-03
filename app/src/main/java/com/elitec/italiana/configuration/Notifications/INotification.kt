package com.elitec.italiana.configuration.Notifications

import android.content.Context

interface INotification {
	fun sendNotifications(
		context: Context,
		message:String
	)
	fun onlySounds(context: Context)
}