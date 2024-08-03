package com.elitec.italiana

import android.app.Application
import com.elitec.italiana.domain.Entities.Notificaciones
import com.elitec.italiana.domain.Entities.ProductosPreferidos
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Configuration
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@HiltAndroidApp
class ItalianaAPP:Application() {
}