package com.elitec.italiana.data.Repositories.BDRepository

import com.elitec.italiana.data.Repositories.BDRepository.NotificationDB.NotificationRepo
import com.elitec.italiana.data.Repositories.BDRepository.PreferedDB.PreferidosRepo
import javax.inject.Inject

class BDRepository @Inject constructor(
	private val notificacion : NotificationRepo,
	private val preferidos: PreferidosRepo
):IRealmDataBaseRepository {
	override fun RealmNotificationDataBase(): NotificationRepo = notificacion
	override fun RealmPreferidosDataBase(): PreferidosRepo = preferidos
}