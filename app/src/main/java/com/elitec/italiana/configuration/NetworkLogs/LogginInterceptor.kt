package com.elitec.italiana.configuration.NetworkLogs

import okhttp3.Interceptor
import okhttp3.Response

class LogginInterceptor:Interceptor {
	var requestUrl: String?=null
		private set

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()
		val response = chain.proceed(request)
		requestUrl = response.request.url.toString()
		return response
	}
}