package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.configuration.Notifications.INotification
import com.elitec.italiana.configuration.Notifications.NotificationServices
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotificationModules {
	@Binds
	@Singleton
	abstract fun bindNotificationServices(
		notificationServices: NotificationServices
	): INotification
}