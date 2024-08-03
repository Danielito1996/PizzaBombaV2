package com.elitec.italiana.data.Repositories.BDRepository

import com.elitec.italiana.data.Repositories.BDRepository.NotificationDB.NotificationRepo
import com.elitec.italiana.data.Repositories.BDRepository.PreferedDB.PreferidosRepo

interface IRealmDataBaseRepository {
	fun RealmNotificationDataBase(): NotificationRepo
	fun RealmPreferidosDataBase(): PreferidosRepo
}