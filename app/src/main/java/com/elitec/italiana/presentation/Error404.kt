package com.elitec.italiana.presentation

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.elitec.italiana.R
import com.elitec.italiana.presentation.Navigation.AppScreens

@Composable
fun Error404(navHostController: NavHostController) {
	val isHorizontal = LocalConfiguration.current.orientation==Configuration.ORIENTATION_LANDSCAPE
	if (isHorizontal) {
		Screem404Horizontal(navHostController = navHostController)
	}
	else {
		Screem404Vertical(navHostController = navHostController)
	}
}

@Composable
fun Screem404Vertical(navHostController: NavHostController) {
	Box(contentAlignment = Alignment.Center,
		modifier = Modifier.fillMaxSize()) {
		Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxWidth()) {
			Image(painter = painterResource(id = R.drawable.image404),
				contentScale = ContentScale.FillHeight,
				contentDescription = "404 Image",
				modifier = Modifier
					.padding(bottom = 25.dp))
			Text(text = "Lo sentimos", fontSize = 22.sp, fontFamily = FontFamily.Serif
				, modifier = Modifier.padding(bottom = 15.dp))
			Text(text = "FUERA DE SERVICIO",
				textAlign = TextAlign.Center,fontSize = 30.sp, fontFamily = FontFamily.Serif)
			Surface(onClick = { navHostController.navigate(AppScreens.SplashScreen.route) },
				shape = RoundedCornerShape(10.dp),
				color = Color(0xFF5DDC54),
				modifier = Modifier.padding(top = 30.dp)) {
				Row(verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier.padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end =10.dp)) {
					Text(text = "REINTENTAR",
						color = Color.Black,
						fontSize = 20.sp,
						fontFamily = FontFamily.Serif)
					Icon(imageVector = Icons.Filled.Refresh,
						tint = Color.Black,
						contentDescription = "ReLoad",
						modifier = Modifier
							.size(45.dp)
							.padding(start = 15.dp))
				}
			}
		}
	}
}

@Composable
fun Screem404Horizontal(navHostController: NavHostController) {
	Box(contentAlignment = Alignment.Center,
		modifier = Modifier.fillMaxSize()) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxSize()) {
			Column(modifier = Modifier.weight(3f).fillMaxSize()) {

			}
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier.weight(7f).fillMaxSize()
			) {
				Image(
					painter = painterResource(id = R.drawable.image404),
					contentDescription = "404 Image",
					modifier = Modifier
						.size(200.dp)
						.padding(bottom = 20.dp)
				)
				Text(
					text = "Lo sentimos",
					fontSize = 22.sp,
					fontFamily = FontFamily.Serif,
					modifier = Modifier.padding(bottom = 10.dp)
				)
				Text(
					text = "FUERA DE SERVICIO",
					textAlign = TextAlign.Center, fontSize = 30.sp, fontFamily = FontFamily.Serif
				)
				Surface(
					onClick = { navHostController.navigate(AppScreens.SplashScreen.route) },
					shape = RoundedCornerShape(10.dp),
					color = Color(0xFF5DDC54),
					modifier = Modifier.padding(top = 25.dp)
				) {
					Row(
						verticalAlignment = Alignment.CenterVertically,
						modifier = Modifier.padding(
							start = 10.dp,
							top = 5.dp,
							bottom = 5.dp,
							end = 10.dp
						)
					) {
						Text(
							text = "REINTENTAR",
							color = Color.Black,
							fontSize = 20.sp,
							fontFamily = FontFamily.Serif
						)
						Icon(
							imageVector = Icons.Filled.Refresh,
							tint = Color.Black,
							contentDescription = "ReLoad",
							modifier = Modifier
								.size(45.dp)
								.padding(start = 10.dp)
						)
					}
				}
			}
		}
	}
}
@Preview
@Composable
fun Error404Preview() {
	val navHostController = rememberNavController()
	Error404(navHostController = navHostController)
}