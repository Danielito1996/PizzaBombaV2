package com.elitec.italiana.bussinesLogic.MakeOrder

import com.elitec.italiana.configuration.NetworkLogs.LogginInterceptor
import com.elitec.italiana.data.Repositories.NetworkRepository.INetworkRepository
import com.elitec.italiana.domain.Entities.Compra
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MakeOrder @Inject constructor(
	private val api:INetworkRepository
):IMakeOrder {
	override suspend fun OrderNow (compra:Compra):Boolean {
		return try {
			api.postOrder(compra)
			true
		} catch (ext:Exception){
			false
		}
	}
}