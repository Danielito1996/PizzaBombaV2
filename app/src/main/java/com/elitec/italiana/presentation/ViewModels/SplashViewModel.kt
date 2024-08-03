package com.elitec.italiana.presentation.ViewModels

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.elitec.italiana.configuration.NetInterface.IIPAddress
import com.elitec.italiana.data.Repositories.CacheRepository.ICacheRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
 	val dataAPI:ICacheRepository,
	private val ip:IIPAddress
):ViewModel() {

	var cargandoDatos by mutableStateOf(true)
	var textoInformativo by mutableStateOf("")

	suspend fun GetDataAPI() {

			val ipAddress = ip.ResolveIP()
			textoInformativo="Configurando servicios : $ipAddress"
			delay(1000)
			textoInformativo="Cargando Datos"
			dataAPI.get()


	}
}