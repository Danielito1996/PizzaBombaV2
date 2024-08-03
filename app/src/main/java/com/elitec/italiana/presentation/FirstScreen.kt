package com.elitec.italiana.presentation

import android.content.res.Configuration
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.elitec.italiana.R
import com.elitec.italiana.presentation.Navigation.AppScreens
import com.elitec.italiana.presentation.theme.NaranjaBeich
import com.elitec.italiana.presentation.theme.NaranjaClaro
import com.elitec.italiana.presentation.theme.NaranjaMiel

@Composable
fun FirstScreen(navController: NavController) {
	//Inicializacion de animacion de borde
	val borderAnimation by rememberInfiniteTransition().animateFloat(
		initialValue = 0f,
		targetValue = 1000f,
		animationSpec = infiniteRepeatable(
			animation = tween(
				durationMillis = 1000,
				easing = LinearEasing
			)
		)
	)
	val colors = listOf(
		NaranjaMiel,
		NaranjaClaro,
		NaranjaBeich
	)
	val gradientBrush by remember {
		mutableStateOf(
			Brush.horizontalGradient(
				colors = colors,
				startX = -10.0f,
				endX = 400.0f,
				tileMode = TileMode.Repeated
			)
		)
	}
	//Inicializacion de animacion de sacudida de Logo
	val value by rememberInfiniteTransition().animateFloat(
		initialValue = 5f,
		targetValue = -5f,
		animationSpec = infiniteRepeatable(
			animation = tween(
				durationMillis = 600,
				easing = LinearEasing
			),
			repeatMode = RepeatMode.Reverse
		)
	)
	val isHorizontal = LocalConfiguration.current.orientation== Configuration.ORIENTATION_LANDSCAPE
	Box(modifier = Modifier
		.fillMaxSize()
		.padding(top = 10.dp)){
		if (isHorizontal) {
			Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier.fillMaxSize()) {
				Box(contentAlignment = Alignment.TopCenter,modifier = Modifier
					.weight(2.5f)
					.fillMaxSize()) {
					Box(contentAlignment = Alignment.Center,modifier = Modifier
						.fillMaxSize()) {
						Image(
							painter = painterResource(id = R.drawable.foto3),
							contentDescription = "Banner",
							contentScale = ContentScale.Crop,
							modifier = Modifier.clip(shape = RoundedCornerShape(15.dp))
						)
					}
					Surface(shape = CircleShape, color = NaranjaBeich,
						modifier = Modifier
							.drawBehind {
								rotate(borderAnimation) {
									drawCircle(
										gradientBrush
									)
								}
							}
							.drawBehind {
								rotate(value) {
									drawCircle(
										gradientBrush, style = Stroke(width = 12.dp.value)
									)
								}
							}
							.padding(5.dp)
					) {
						Image(painter = painterResource(id = R.drawable.icon),
							contentDescription = null,
							modifier = Modifier
								.size(130.dp)
								.padding(bottom = 18.dp))
					}
				}
				Column(verticalArrangement = Arrangement.SpaceAround, horizontalAlignment = Alignment.CenterHorizontally,
					modifier = Modifier
						.weight(6f)
						.fillMaxSize()) {
					Box(modifier = Modifier
						.weight(3f)
						.fillMaxSize()) {
						TitulosPersonalizados()
					}
					Box(modifier = Modifier
						.weight(7f)
						.fillMaxSize()
						.padding(top = 10.dp, start = 15.dp, end = 15.dp, bottom = 5.dp)) {
						Promociones(navController)
					}
				}
				Box(modifier = Modifier
					.weight(1.5f)
					.fillMaxSize()) {
					Footer(isHorizontal = isHorizontal, modifier = Modifier.fillMaxSize())
				}

			}
		}
		else {
			Column(verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier.padding(10.dp)) {
				Box(contentAlignment = Alignment.BottomCenter,
					modifier = Modifier
						.weight(3f)
						.fillMaxWidth()) {
					BannerInicial()
					Surface(shape = CircleShape, color = NaranjaBeich,
						modifier = Modifier
							.drawBehind {
								rotate(borderAnimation) {
									drawCircle(
										gradientBrush
									)
								}
							}
							.drawBehind {
								rotate(value) {
									drawCircle(
										gradientBrush, style = Stroke(width = 12.dp.value)
									)
								}
							}
					) {
						Image(painter = painterResource(id = R.drawable.icon),
							contentDescription = null,
							modifier = Modifier
								.size(90.dp)
								.padding(bottom = 10.dp))
					}
				}
				Box(modifier = Modifier
					.weight(2f)
					.fillMaxWidth()) {
					TitulosPersonalizados()
				}
				Box(modifier = Modifier
					.weight(3f)
					.fillMaxWidth()) {
					Promociones(navController)
				}
				Box(contentAlignment = Alignment.BottomCenter,modifier = Modifier
					.weight(2f)
					.fillMaxWidth()) {
					Footer(isHorizontal = isHorizontal)
				}
			}
		}
		}

}

@Composable
fun BannerLateral(gradientBrush:Brush,modifier: Modifier?=Modifier) {
	//Inicializacion de animacion de borde
	val borderAnimation by rememberInfiniteTransition().animateFloat(
		initialValue = 0f,
		targetValue = 1000f,
		animationSpec = infiniteRepeatable(
			animation = tween(
				durationMillis = 1000,
				easing = LinearEasing
			)
		)
	)
	//Inicializacion de animacion de sombras del boton ver Ofertas
	val value by rememberInfiniteTransition().animateFloat(
		initialValue = 1.dp.value,
		targetValue = 5.dp.value,
		animationSpec = infiniteRepeatable(animation = tween(durationMillis =  1000),
			repeatMode = RepeatMode.Reverse)
	)
	Box(contentAlignment = Alignment.TopCenter,modifier = Modifier
		.fillMaxSize()) {
		Box(contentAlignment = Alignment.Center,modifier = Modifier
			.fillMaxSize()) {
			Image(
				painter = painterResource(id = R.drawable.foto3),
				contentDescription = "Banner",
				contentScale = ContentScale.Crop,
				modifier = Modifier.clip(shape = RoundedCornerShape(15.dp))
			)
		}
		Surface(shape = CircleShape, color = NaranjaBeich,
			modifier = Modifier
				.drawBehind {
					rotate(borderAnimation) {
						drawCircle(
							gradientBrush
						)
					}
				}
				.drawBehind {
					rotate(value) {
						drawCircle(
							gradientBrush, style = Stroke(width = 12.dp.value)
						)
					}
				}
				.padding(5.dp)
		) {
			Image(painter = painterResource(id = R.drawable.icon),
				contentDescription = null,
				modifier = Modifier
					.size(130.dp)
					.padding(bottom = 18.dp))
		}
	}
}
@Composable
fun TitulosPersonalizados() {
	Column(verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier.fillMaxWidth()) {
		Text(text = "PIZZA BOMBA",
			fontSize = 45.sp,
			fontFamily = FontFamily.Serif)
		Text(text = "...donde el sabor es simplemente explosivo...",
			fontFamily = FontFamily.Cursive,
			fontSize = 20.sp)
		Row {
			Icon(imageVector = Icons.Filled.Star, contentDescription = null)
			Icon(imageVector = Icons.Filled.Star, contentDescription = null)
			Icon(imageVector = Icons.Filled.Star, contentDescription = null)
			Icon(imageVector = Icons.Filled.Star, contentDescription = null)
			Icon(imageVector = Icons.Filled.Star, contentDescription = null)
		}
	}
}


@Composable
fun BannerInicial(modifier: Modifier?=Modifier) {
	Image(contentScale = ContentScale.Crop,
		painter = painterResource(id = R.drawable.foto1),
		contentDescription = null,
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 50.dp)
			.clip(shape = RoundedCornerShape(15.dp)))
}

@Composable
fun Footer(isHorizontal:Boolean, modifier: Modifier?=Modifier) {
	if (isHorizontal){
		Surface(shape = RoundedCornerShape(15.dp),
			color = NaranjaBeich,modifier= Modifier
				.fillMaxSize()
				.padding(5.dp)) {
			Row(modifier = Modifier.fillMaxSize()) {
				Column(
					horizontalAlignment = Alignment.CenterHorizontally,
					verticalArrangement = Arrangement.SpaceAround,
					modifier = Modifier
						.fillMaxHeight()
						.padding(5.dp)
				) {
					Image(
						painter = painterResource(id = R.drawable.telegram),
						contentDescription = null,
						modifier = Modifier.size(50.dp)
					)
					Image(
						painter = painterResource(id = R.drawable.email),
						contentDescription = null,
						modifier = Modifier.size(60.dp)
					)
					Image(
						painter = painterResource(id = R.drawable.facebook),
						contentDescription = null,
						modifier = Modifier.size(65.dp)
					)
					Image(
						painter = painterResource(id = R.drawable.web),
						contentDescription = null,
						modifier = Modifier.size(50.dp)
					)

					Text(
						text = "Design by: ELITEC",
						textAlign = TextAlign.Center,
						color = Color.Black,
						fontSize = 10.sp,
						fontFamily = FontFamily.Serif,
						modifier = Modifier.padding(5.dp)
					)
				}
			}
		}
	}else {
		Column {
			Divider(
				Modifier
					.fillMaxWidth()
					.padding(top = 20.dp, bottom = 20.dp, start = 5.dp, end = 5.dp))
			Column(verticalArrangement = Arrangement.Bottom,
				horizontalAlignment = Alignment.CenterHorizontally) {
				Row(verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.SpaceEvenly,
					modifier = Modifier
						.fillMaxWidth()
						.padding(5.dp)) {
					Image(painter = painterResource(id = R.drawable.telegram),
						contentDescription = null,
						modifier = Modifier.size(50.dp))
					Image(painter = painterResource(id = R.drawable.email),
						contentDescription = null,
						modifier = Modifier.size(60.dp))
					Image(painter = painterResource(id = R.drawable.facebook),
						contentDescription = null,
						modifier = Modifier.size(65.dp))
					Image(painter = painterResource(id = R.drawable.web),
						contentDescription = null,
						modifier = Modifier.size(50.dp))
				}
				Text(text = "Design by: ELITEC",
					fontFamily = FontFamily.Serif,
					modifier = Modifier.padding(5.dp))
			}
		}
	}
}

@Composable
fun Promociones(navController: NavController) {

	//Inicializacion de animacion de sombras del boton ver Ofertas
	val value by rememberInfiniteTransition().animateFloat(
		initialValue = 1.dp.value,
		targetValue = 5.dp.value,
		animationSpec = infiniteRepeatable(animation = tween(durationMillis =  1000),
			repeatMode = RepeatMode.Reverse)
	)

	Surface(color = NaranjaBeich,
		shape = RoundedCornerShape(15.dp),
		tonalElevation = 5.dp,
		shadowElevation = 3.dp,
		modifier = Modifier
			.fillMaxWidth()
			.fillMaxHeight()) {
		Column(verticalArrangement = Arrangement.SpaceEvenly,
			modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp)) {
			Row(
				modifier = Modifier.fillMaxWidth()) {
				Icon(imageVector = Icons.Filled.ShoppingCart,
					contentDescription = null, tint = Color.Black )
				Spacer(modifier = Modifier.width(10.dp))
				Text(text = "10%", color = Color.Black, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif, fontSize = 20.sp)
			}
			Box(modifier = Modifier.fillMaxWidth()){
				Text(textAlign = TextAlign.Justify, color = Color.Black, fontSize = 17.sp,text = "Si en una misma compra se lleva en producto el equivalente a 5000 pesos o superior, entonces descontamos la compra por el 10 % del consto total de la compra, a demas de llevarse un regalo extra")
			}
			Surface(onClick = { navController.navigate(route = AppScreens.OfertasScreen.route) },
				color = NaranjaMiel,shape = RoundedCornerShape(10.dp)
				,tonalElevation = 5.dp,
				shadowElevation = value.dp,
				modifier = Modifier
					.fillMaxWidth()
					.padding(10.dp)) {
				Row(verticalAlignment = Alignment.CenterVertically,
					horizontalArrangement = Arrangement.SpaceBetween,
					modifier = Modifier.fillMaxWidth()) {
					Text(text = "VER OFERTAS",
						color = Color.Black,
						fontFamily = FontFamily.Serif,
						fontSize = 23.sp,
						modifier = Modifier
							.weight(7f)
							.padding(start = 10.dp, top = 10.dp, bottom = 10.dp))
					Icon(imageVector = Icons.Filled.ArrowForward,
						contentDescription = null,
						modifier = Modifier
							.weight(3f)
							.size(30.dp))
				}
			}
		}
	}
}

