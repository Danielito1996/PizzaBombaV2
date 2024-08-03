package com.elitec.italiana.data.Repositories.BDRepository.NotificationDB

import com.elitec.italiana.domain.Entities.Notificaciones
import kotlinx.coroutines.flow.Flow

interface NotificationRepo {
	fun getAsFlow(): Flow<MutableList<Notificaciones>>
	fun set(notificacion: Notificaciones)
}