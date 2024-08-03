package com.elitec.italiana.data.Repositories.BDRepository.NotificationDB

import com.elitec.italiana.domain.Entities.Notificaciones
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class DAONotificaciones @Inject constructor(
	private val realm: Realm
): NotificationRepo {
	override fun set(notificacion:Notificaciones) {

		CoroutineScope(Dispatchers.IO).launch {
			realm.write {
				val notify = Notificaciones().apply {
					descripcion=notificacion.descripcion
					fecha=notificacion.fecha
					icon=notificacion.icon
				}
				copyToRealm(notify, updatePolicy = UpdatePolicy.ALL)
			}
		}
	}

	override fun getAsFlow(): Flow<MutableList<Notificaciones>> = realm.query<Notificaciones>().
			asFlow().
			map {
				result-> result.list.toMutableList()
			}
}