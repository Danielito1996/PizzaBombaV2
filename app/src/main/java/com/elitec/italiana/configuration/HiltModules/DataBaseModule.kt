package com.elitec.italiana.configuration.HiltModules

import com.elitec.italiana.data.Repositories.BDRepository.BDRepository
import com.elitec.italiana.data.Repositories.BDRepository.IRealmDataBaseRepository
import com.elitec.italiana.data.Repositories.BDRepository.PreferedDB.DAOPreferidos
import com.elitec.italiana.data.Repositories.BDRepository.NotificationDB.DAONotificaciones
import com.elitec.italiana.data.Repositories.BDRepository.NotificationDB.NotificationRepo
import com.elitec.italiana.data.Repositories.BDRepository.PreferedDB.PreferidosRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBaseModule {
	@Binds
	@Singleton
	abstract fun bindDataBaseRepository (database:BDRepository):IRealmDataBaseRepository

	@Binds
	@Singleton
	abstract fun bindNotificationBD(dataBase: DAONotificaciones): NotificationRepo

	@Binds
	@Singleton
	abstract fun bindPreferidosBD(dataBase:DAOPreferidos):PreferidosRepo
}