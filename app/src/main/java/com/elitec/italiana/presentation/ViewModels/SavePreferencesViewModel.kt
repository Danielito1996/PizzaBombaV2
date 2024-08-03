package com.elitec.italiana.presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elitec.italiana.data.Repositories.BDRepository.IRealmDataBaseRepository
import com.elitec.italiana.domain.Entities.Notificaciones
import com.elitec.italiana.domain.Entities.ProductosPreferidos
import com.elitec.italiana.domain.Factory.IGenericFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavePreferencesViewModel @Inject constructor(
	private val bd:IRealmDataBaseRepository,
	private val factory:IGenericFactory
):ViewModel() {

	private var _listaPreferidos: Flow<MutableList<ProductosPreferidos>> = flowOf(mutableListOf())
	var listaPreferidos: StateFlow<MutableList<ProductosPreferidos>>

	init {
		_listaPreferidos = bd.RealmPreferidosDataBase().getAsFlow()
		listaPreferidos = _listaPreferidos.stateIn(
			viewModelScope,
			SharingStarted.Eagerly,
			mutableListOf())
	}

	suspend fun isPrefered(id:Int):Boolean {
		var isPreferred = false
			_listaPreferidos.first { preferidos ->
				isPreferred = preferidos.any { it.PreferidoId == id }
				true

		}
		return isPreferred
	}

	fun SavePreferences(id:Int) {
		val item = factory.buildEntity()
		item.PreferidoId=id
		bd.RealmPreferidosDataBase().set(item)
	}

	fun DeletePreferences(id:Int) {
		val item = factory.buildEntity()
		item.PreferidoId=id
		bd.RealmPreferidosDataBase().delete(item)
	}
}