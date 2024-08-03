package com.elitec.italiana.presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elitec.italiana.presentation.Error404
import com.elitec.italiana.presentation.FirstScreen
import com.elitec.italiana.presentation.OfertaScreen
import com.elitec.italiana.presentation.SplashScreen

@Composable
fun Navigator() {
	val navController = rememberNavController()
	NavHost(navController = navController, startDestination = AppScreens.SplashScreen.route) {
		composable(route=AppScreens.SplashScreen.route) {
			SplashScreen(navController)
		}
		composable(route=AppScreens.FirstScreen.route) {
			FirstScreen(navController)
		}
		composable(route=AppScreens.OfertasScreen.route) {
			OfertaScreen(navController)
		}
		composable(route=AppScreens.Error404.route) {
			Error404(navController)
		}
	}
}