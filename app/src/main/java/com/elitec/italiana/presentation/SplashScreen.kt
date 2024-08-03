package com.elitec.italiana.presentation

import android.widget.Toast
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.elitec.italiana.R
import com.elitec.italiana.configuration.PersonalException.DataSynchronizationException
import com.elitec.italiana.configuration.PersonalException.NoIpResolveException
import com.elitec.italiana.presentation.Navigation.AppScreens
import com.elitec.italiana.presentation.ViewModels.SplashViewModel

@Composable
fun SplashScreen(navController: NavController) {
	val viewModel:SplashViewModel= hiltViewModel()
	val context = LocalContext.current
	LaunchedEffect(true) {
		try {
			viewModel.GetDataAPI()
			navController.popBackStack()
			navController.navigate(AppScreens.FirstScreen.route)
		} catch (noIP: NoIpResolveException) {
			val toast = Toast.makeText(context, noIP.message, Toast.LENGTH_SHORT)
			toast.show()
		} catch (noData: DataSynchronizationException) {
			val toast = Toast.makeText(context, noData.message, Toast.LENGTH_SHORT)
			toast.show()
		}catch (e:Exception) {
			val toast= Toast.makeText(context,e.message, Toast.LENGTH_SHORT)
			toast.show()
			navController.navigate(AppScreens.Error404.route)
		}
	}
	val color = if (isSystemInDarkTheme()) {
		Color(0xFF2a2a2a)
	}
	else {
		Color.White
	}
	Surface(color = color ,modifier = Modifier.fillMaxSize()) {
		Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxSize()) {
			Column {
				Box(contentAlignment = Alignment.BottomCenter,modifier = Modifier
					.weight(4.5f)
					.fillMaxSize()) {
					Image(
						painter = painterResource(id = R.drawable.splash),
						contentDescription = "Splash Screen",
						modifier = Modifier.size(120.dp)
					)
				}
				Box(contentAlignment = Alignment.Center,modifier = Modifier
					.weight(1.5f)
					.fillMaxSize()) {
					if (viewModel.cargandoDatos) {
						RotateTransition(modifier = Modifier.padding(10.dp))
					}
				}
				Box(contentAlignment = Alignment.BottomCenter,
					modifier = Modifier
						.weight(4f)
						.fillMaxSize()) {
					Text(text = viewModel.textoInformativo,
						textAlign = TextAlign.Center,
						fontFamily = FontFamily.Serif,
						modifier = Modifier.padding(bottom = 10.dp))
				}
			}
		}
	}
}

@Composable
fun RotateTransition(modifier: Modifier) {
	val updates = rememberInfiniteTransition(label = "")
	val rotate = updates.animateFloat(
		initialValue = 0f,
		targetValue = 360f,
		animationSpec = infiniteRepeatable(
			animation = tween(
				durationMillis = 1500,
				easing = EaseInOutSine
			),
			repeatMode = RepeatMode.Restart
		),
		label = ""
	)
	Canvas(
		modifier = Modifier
			.size(70.dp)
			.padding(10.dp)
	) {
		rotate(
			degrees = rotate.value,
			pivot = Offset(size.width / 2f, size.height / 2f)
		) {
			drawArc(
				brush = Brush.linearGradient(
					listOf(
						Color(
							90,
							87,
							90,
							252),
						Color(
							194,
							191,
							194,
							252),
					)
				),
				startAngle = 0f,
				sweepAngle = 360f,
				topLeft = Offset(
					0f,
					0f),
				useCenter = false,
				style = Stroke(
					15.dp.toPx() ,
					cap = StrokeCap.Butt,
					pathEffect = PathEffect
						.dashPathEffect(
							floatArrayOf(10f, 35f , 50f),
							0f)
				)
			)
		}
	}
}



@Preview
@Composable
fun SplashPreview() {
	val navController = rememberNavController()
	SplashScreen(navController = navController)
}