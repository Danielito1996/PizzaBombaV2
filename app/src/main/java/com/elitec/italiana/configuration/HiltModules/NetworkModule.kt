package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.configuration.NetInterface.IPAddress
import com.elitec.italiana.configuration.NetworkLogs.LogginInterceptor
import com.elitec.italiana.data.APIServices.ApiServices
import com.elitec.italiana.data.URL.IUrlDictionary
import com.elitec.italiana.data.Repositories.NetworkRepository.INetworkRepository
import com.elitec.italiana.data.Repositories.NetworkRepository.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

	private val ip:IPAddress=IPAddress()
	@Provides
	@Singleton
	fun provideOkHttpClient(logginfInterceptor: LogginInterceptor): OkHttpClient{
		//Esto evita el error de SSL Desconocido
		try {
			// Create a trust manager that does not validate certificate chains
			val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
				override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) = Unit
				override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) = Unit
				override fun getAcceptedIssuers() = arrayOf<X509Certificate>()
			})

			// Install the all-trusting trust manager
			val sslContext = SSLContext.getInstance("SSL")
			sslContext.init(null, trustAllCerts, java.security.SecureRandom())

			// Create an ssl socket factory with our all-trusting manager
			val sslSocketFactory = sslContext.socketFactory

			return OkHttpClient.Builder()
				.addInterceptor(logginfInterceptor)
				.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
				.hostnameVerifier { _, _ -> true }
				.build()
		} catch (e: Exception) {
			throw RuntimeException(e)
		}
	}

	@Singleton
	@Provides
	fun provideInterceptor(): LogginInterceptor {
		return LogginInterceptor()//Permite cjeqear la URL de Retrofit
	}
	@Singleton
	@Provides
	fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder().baseUrl(ip.ResolveIP())  //Con resolution de IP
			.addConverterFactory(GsonConverterFactory.create())
			.client(okHttpClient)
			.build()
		/*return Retrofit.Builder().baseUrl("https://51q0r5rn-7062.use2.devtunnels.ms/api/")//Con Dev Tunnel
			.addConverterFactory(GsonConverterFactory.create())
			.client(okHttpClient)
			.build()*/
	}

	@Singleton
	@Provides
	fun provideINetworkRepository(
		apiServices: ApiServices,
		urlDictionary: IUrlDictionary
	): INetworkRepository {
		return NetworkRepository(apiServices, urlDictionary)
	}

	@Singleton
	@Provides
	fun provideApiServices(retrofit: Retrofit):ApiServices {
		return retrofit.create(ApiServices::class.java)
	}
}