package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.domain.Entities.Notificaciones
import com.elitec.italiana.domain.Entities.ProductosPreferidos
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RealmModule {

	@Provides
	@Singleton
	fun provideRealmConfiguration ():RealmConfiguration {
		return RealmConfiguration.create(
			schema = setOf(
				ProductosPreferidos::class,
				Notificaciones::class
			)
		)
	}
	@Provides
	fun provideRealm(): Realm{
		val configuration = provideRealmConfiguration()
		return Realm.open(configuration)
	}
}