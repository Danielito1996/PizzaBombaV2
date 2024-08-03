package com.elitec.italiana.configuration.Notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.elitec.italiana.R
import javax.inject.Inject

class NotificationServices @Inject constructor():INotification {
	private val channelId = "pizzaBomba"
	private val channelName = "notificacionesInternas"
	private var notificationId = 1
	override fun sendNotifications(
		context: Context,
		message: String
	) {
		// Crear un canal de notificación para Android Oreo y versiones superiores
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val importance = NotificationManager.IMPORTANCE_DEFAULT
			val channel = NotificationChannel(channelId, channelName, importance).apply {
				description = "Pizza Bomba: "
			}
			// Registrar el canal en el sistema
			val notificationManager: NotificationManager =
				context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
			notificationManager.createNotificationChannel(channel)
		}
		// Definir el sonido de la notificación
		val soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
		// Construir la notificación
		val builder = NotificationCompat.Builder(context, channelId)
			.setSmallIcon(R.drawable.icon)
			.setContentTitle("Pizza Bomba notification")
			.setContentText(message)
			.setPriority(NotificationCompat.PRIORITY_DEFAULT)
			.setSound(soundUri) // Establecer el sonido personalizado aquí
		// Mostrar la notificación
		with(NotificationManagerCompat.from(context)) {
			if (ActivityCompat.checkSelfPermission(
					context,
					Manifest.permission.POST_NOTIFICATIONS
				) != PackageManager.PERMISSION_GRANTED
			) {
				// TODO: Consider calling
				//    ActivityCompat#requestPermissions
				// here to request the missing permissions, and then overriding
				//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
				//                                          int[] grantResults)
				// to handle the case where the user grants the permission. See the documentation
				// for ActivityCompat#requestPermissions for more details.
				return
			}
			notify(notificationId, builder.build())
		}
	}

	override fun onlySounds(context: Context) {
		// Definir el sonido de la notificación
		val soundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
		val mediaPlayer =MediaPlayer.create(context, soundUri)
		mediaPlayer.start()
	}
}