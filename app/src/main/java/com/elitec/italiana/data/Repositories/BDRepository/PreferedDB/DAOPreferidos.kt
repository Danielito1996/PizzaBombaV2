package com.elitec.italiana.data.Repositories.BDRepository.PreferedDB

import com.elitec.italiana.domain.Entities.ProductosPreferidos
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class DAOPreferidos @Inject constructor(
	private val realm: Realm
): PreferidosRepo {
	override fun set(producto: ProductosPreferidos) {
		CoroutineScope(Dispatchers.IO).launch {
			realm.write {
				val preferido = ProductosPreferidos().apply {
					PreferidoId=producto.PreferidoId
				}
				copyToRealm(preferido, updatePolicy = UpdatePolicy.ALL)
			}
		}
	}

	override fun getAsFlow(): Flow<MutableList<ProductosPreferidos>> = realm.query<ProductosPreferidos>().
	asFlow().
	map {
			result-> result.list.toMutableList()
	}
	override fun delete(producto: ProductosPreferidos) {
		CoroutineScope(Dispatchers.IO).launch {
			realm.write {
				val frozenPreferido = realm.query<ProductosPreferidos>("PreferidoId == $0", producto.PreferidoId).first().find()
				frozenPreferido?.let {
					val livePreferido = findLatest(it)
					livePreferido?.let { delete(it) }
				}
			}
		}
		val id:Int
	}
}