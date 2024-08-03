package com.elitec.italiana.configuration.NetInterface

import com.elitec.italiana.configuration.PersonalException.NoIpResolveException
import java.net.Inet4Address
import java.net.NetworkInterface
import javax.inject.Inject

class IPAddress @Inject constructor():IIPAddress {
	override fun ResolveIP(): String {
		val ip = getIPHostAddress()
		if (ip.isNotEmpty()) {
			val modifiedIP = ip.substringBeforeLast(".") + ".220"
			return "https://$modifiedIP:7062/api/"
		}
		return ""
	}
	private fun getIPHostAddress(): String {
		NetworkInterface.getNetworkInterfaces()?.toList()?.map { networkInterface ->
			networkInterface.inetAddresses?.toList()?.find { !it.isLoopbackAddress && it is Inet4Address }?.let {
				return it.hostAddress
			}
		}
		return "127.0.0.1"
	}
}