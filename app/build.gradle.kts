plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	id ("kotlin-kapt")
	id ("com.google.devtools.ksp")
	id("com.google.dagger.hilt.android")
	id("io.realm.kotlin")
}

android {
	namespace = "com.elitec.italiana"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.elitec.italiana"
		minSdk = 28
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.1"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	testImplementation(libs.junit)
	testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
	testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
	//Cosumo de APIS
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	// Kotlin serialization
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
	//Navegacion
	implementation("androidx.navigation:navigation-compose:2.7.7")
	//Consumo asincrono de Imagenes en red
	implementation("io.coil-kt:coil-compose:2.6.0")
	//Inyeccion de dependencias con Dagger Hilt
	implementation("com.google.dagger:hilt-android:2.50")
	implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
	kapt("com.google.dagger:hilt-android-compiler:2.50")
	kapt("androidx.hilt:hilt-compiler:1.2.0")

	//Realm Persistencia
	implementation("io.realm.kotlin:library-base:1.16.0")
	implementation("io.realm.kotlin:library-sync:1.16.0")
	// If using coroutines with the SDK
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
/*
	//Uso de WebSockets con retrofit
	implementation("com.tinder.scarlet:scarlet:0.1.12")
	implementation("com.tinder.scarlet:websocket-okhttp:0.1.12")
	implementation("com.tinder.scarlet:message-adapter-gson:0.1.12")
	implementation("com.tinder.scarlet:stream-adapter-coroutines:0.1.12")
	implementation("com.tinder.scarlet:lifecycle-android:0.1.12")
	*/
 

}
// Allow references to generated code
kapt {
	correctErrorTypes = true
}