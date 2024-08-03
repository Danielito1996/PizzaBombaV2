package com.elitec.italiana.presentation.Navigation

sealed class AppScreens(val route:String) {
	object SplashScreen: AppScreens("SplashScreen")
	object FirstScreen: AppScreens("FirstScreen")
	object MenuScreen: AppScreens("MenuScreen")
	object OfertasScreen: AppScreens("OfertasScreen")
	object PedidosScreen: AppScreens("PedidosScreen")
	object ConfiguracionScreen: AppScreens("ConfiguracionScreen")
	object Error404: AppScreens("Error404")
}