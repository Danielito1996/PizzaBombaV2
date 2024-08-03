package com.elitec.italiana.presentation

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.sharp.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.elitec.italiana.R
import com.elitec.italiana.domain.Entities.ElementTypes
import com.elitec.italiana.domain.Entities.Producto
import com.elitec.italiana.presentation.PersonalComponents.AnimatedHearthIconButton
import com.elitec.italiana.presentation.PersonalComponents.drawFadingEdgesBasic
import com.elitec.italiana.presentation.ViewModels.MenuViewModel
import com.elitec.italiana.presentation.ViewModels.PedidosViewModel
import com.elitec.italiana.presentation.ViewModels.SavePreferencesViewModel
import com.elitec.italiana.presentation.theme.AmarilloNaranja
import com.elitec.italiana.presentation.theme.NaranjaBeich
import com.elitec.italiana.presentation.theme.NaranjaClaro
import com.elitec.italiana.presentation.theme.NaranjaMiel
import com.elitec.italiana.presentation.theme.NaranjaOrigin
import com.elitec.italiana.presentation.theme.NaranjaPuro
import com.elitec.italiana.presentation.theme.VerdeAgregar
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OfertaScreen(navController: NavController) {
	var showModalBottomSheet by remember { mutableStateOf(false) }
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
	val colorSurface = if (isSystemInDarkTheme())
		Color(0XFF2a2a2a)
	else
		Color.White
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
	val menuViewModel: MenuViewModel= hiltViewModel()
	val pedidosViewModel:PedidosViewModel= hiltViewModel()
	val isHorizontal = LocalConfiguration.current.orientation== Configuration.ORIENTATION_LANDSCAPE
	if (isHorizontal) {
		Scaffold(
			content = {
				val padding = it
				Row(modifier = Modifier
					.fillMaxSize()
					.padding()) {
					Box(contentAlignment = Alignment.TopCenter,modifier = Modifier
						.weight(2.5f)
						.padding(top = 10.dp, start = 5.dp, end = 5.dp, bottom = 5.dp)
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
						Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
								Image(
									painter = painterResource(id = R.drawable.icon),
									contentDescription = null,
									modifier = Modifier
										.size(130.dp)
										.padding(bottom = 18.dp)
								)
							}
							LazyColumn {
								item {
									Surface( shape = RoundedCornerShape(10.dp),
										color = colorSurface,
										shadowElevation = 5.dp,
										tonalElevation = 3.dp,
										modifier = Modifier
											.padding(10.dp)
									) {
										Column(verticalArrangement = Arrangement.Center,
											horizontalAlignment = Alignment.CenterHorizontally,
											modifier = Modifier
												.fillMaxWidth()
												.padding(top = 10.dp, bottom = 10.dp)) {
											Text(text = "Total a pagar:")
											Text(
												text = "$ ${pedidosViewModel.saldoAPagar}",
												fontFamily = FontFamily.Serif,
												fontSize = 25.sp)

										}
									}
								}
								item {
									Surface(
										shape = RoundedCornerShape(10.dp),
										color = NaranjaBeich,
										onClick = {},
										modifier = Modifier
											.fillMaxWidth()
											.padding(10.dp)) {
										Column(
											horizontalAlignment = Alignment.CenterHorizontally,
											modifier = Modifier.padding(
												top = 13.dp,
												bottom = 13.dp)
										) {
											Icon(imageVector = Icons.Filled.Star,
												tint = Color.Black,
												contentDescription =  "Ofertas")
											Text(text = "PROMOCIONES",
												fontWeight = FontWeight.Bold,
												color = Color.Black,
												textAlign = TextAlign.Center,
												fontFamily = FontFamily.Serif)
										}
									}
								}
							}
						}
					}
					Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier
						.weight(4.5f)
						.fillMaxSize()) {
						Box(modifier = Modifier
							.weight(2.4f)
							.fillMaxSize()
							.padding(5.dp)){
							CuadroDeBusqueda()
						}
						Box(modifier = Modifier
							.weight(7.6f)
							.fillMaxSize()) {
							LazyWrapLayout(viewModel = menuViewModel, pedidosViewModel = pedidosViewModel)
						}
					}
					Box(contentAlignment = Alignment.TopCenter,modifier = Modifier
						.weight(3f)
						.fillMaxSize()) {
						Column(verticalArrangement = Arrangement.Top,
							horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.fillMaxWidth() ) {
							Row(horizontalArrangement = Arrangement.SpaceBetween,
								verticalAlignment = Alignment.CenterVertically,
								modifier = Modifier
									.fillMaxWidth()
									.padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
								BadgedBox(badge = {
									Badge(containerColor = Color.Red,
										modifier = Modifier.padding(end = 13.dp, top = 27.dp)) {
										Text(text = pedidosViewModel.cantidadDeProductosAnadidos.toString(), color = Color.White, fontSize = 15.sp)
									}
								}) {
									Surface(shape = RoundedCornerShape(10.dp),
										tonalElevation = 5.dp,
										shadowElevation = 3.dp,
										color = Color(0xFF5DDC54),
										onClick = { pedidosViewModel.showModalBottomSheet = true }) {
										Icon(imageVector = Icons.Filled.ShoppingCart,
											tint = Color.Black,
											contentDescription = "Boton de Pedidos", modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
										)
									}
								}

								Box(contentAlignment = Alignment.TopEnd,
									modifier = Modifier.padding(end = 5.dp)){
									val openNotifyDialog = rememberSaveable { mutableStateOf(false) }
									IconButton(onClick = { openNotifyDialog.value=true}) {
										Icon(imageVector = Icons.Sharp.Notifications,
											contentDescription = "Notificaciones",
											modifier = Modifier.size(30.dp))
									}
									Badge(containerColor = Color.Red,
										modifier = Modifier
									) {
										Text(text = "3", color = Color.White, fontSize = 15.sp)
									}
									CustomAlertNotifications(
										openDialog = openNotifyDialog,
										pedidosViewModel = pedidosViewModel ,
										isHorizontal = isHorizontal
									)

								}
							}
							EnlacesRapidos(menuViewModel,isHorizontal)
						}
					}
				}
			},
			bottomBar = {
				if (pedidosViewModel.showModalBottomSheet) {
					ModalBottomSheet(
						onDismissRequest = { pedidosViewModel.showModalBottomSheet = false },
						content = {
							Column(modifier = Modifier
								.fillMaxWidth()
								.height(550.dp)) {
								ModalSection(pedidosViewModel = pedidosViewModel,
									menuViewModel= menuViewModel,
									colorSurface=colorSurface, isHorizontal = isHorizontal)
							}
						}
					)
				}
			}
		)
	}
	else {
		Scaffold(containerColor = Color.Transparent,
			topBar = {
				Row(horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier
						.fillMaxWidth()
						.padding(top = 10.dp, start = 10.dp, end = 10.dp)) {
					BadgedBox(badge = {
						Badge(containerColor = Color.Red,
							modifier = Modifier.padding(end = 15.dp, top = 20.dp)) {
							Text(
								text = pedidosViewModel.cantidadDeProductosAnadidos.toString(),
								color = Color.White,
								fontSize = 15.sp)
						}
					}) {
						Surface(shape = RoundedCornerShape(10.dp),
							tonalElevation = 5.dp,
							shadowElevation = 3.dp,
							color = Color(0xFF5DDC54),
							onClick = { pedidosViewModel.showModalBottomSheet = true }) {
							Icon(imageVector = Icons.Filled.ShoppingCart,
								tint = Color.Black,
								contentDescription = "Boton de Pedidos",
								modifier = Modifier.padding(
									start = 10.dp,
									end = 10.dp,
									top = 5.dp,
									bottom = 5.dp)
							)
						}
					}

					Box(contentAlignment = Alignment.TopEnd,
						modifier = Modifier.padding(end = 5.dp)){
						val openNotifyDialog = rememberSaveable { mutableStateOf(false) }
						IconButton(onClick = {
							openNotifyDialog.value=true
							pedidosViewModel.notificacionesPendientes=0
							pedidosViewModel.notificationDialog=!pedidosViewModel.notificationDialog
						}) {
							Icon(imageVector = Icons.Sharp.Notifications,
								contentDescription = "Notificaciones",
								modifier = Modifier.size(30.dp))
						}
						Badge(containerColor = Color.Red,
							modifier = Modifier
						) {
							Text(text = pedidosViewModel.notificacionesPendientes.toString(),
								color = Color.White,
								fontSize = 15.sp)
						}
						CustomAlertNotifications(
							openDialog = openNotifyDialog,
							pedidosViewModel = pedidosViewModel ,
							isHorizontal = isHorizontal
						)
					}
				}
			},
			content = {
				Box(modifier = Modifier
					.fillMaxSize()
					.padding(it)) {
					Column {
						Box(modifier = Modifier
							.fillMaxWidth()
							.padding(start = 5.dp, end = 5.dp)
							.weight(2f)) {
							//Ofertas de la Casa
							OfertasDelaCasa(isHorizontal)
						}
						Box(contentAlignment = Alignment.Center,
							modifier = Modifier
								.fillMaxWidth()
								.padding(start = 5.dp, end = 5.dp)
								.weight(1f)) {
							//Cuadro de Busqueda
							CuadroDeBusqueda()
						}
						Box(contentAlignment = Alignment.Center,
							modifier = Modifier
								.fillMaxWidth()
								.padding(start = 5.dp, end = 5.dp)
								.weight(1f)) {
							//Enlaces Rapidos
							EnlacesRapidos(menuViewModel,isHorizontal)
						}

						Box(modifier = Modifier
							.fillMaxWidth()
							.weight(5f)) {
							//Tarjeta de Platos Principales
							LazyWrapLayout(viewModel = menuViewModel, pedidosViewModel = pedidosViewModel)
						}
					}
				}
			},
			bottomBar = {
				if (pedidosViewModel.showModalBottomSheet) {
					ModalBottomSheet(
						onDismissRequest = { pedidosViewModel.showModalBottomSheet = false },
						content = {
							Column(modifier = Modifier
								.fillMaxWidth()
								.height(550.dp)) {
								ModalSection(pedidosViewModel = pedidosViewModel,
									menuViewModel= menuViewModel,
									colorSurface=colorSurface, isHorizontal = isHorizontal)
							}
						}
					)
				}
			}
		)
	}
}

@Composable
fun CustomAlertDialog(
	openDialog: MutableState<Boolean>,
	producto: Producto,
	pedidosViewModel: PedidosViewModel,
	viewModel: MenuViewModel,
	isHorizontal: Boolean
) {
	val context = LocalContext.current
	var cantidad by remember {
		mutableStateOf(0)
	}

	val pedidoProducto by remember {
		mutableStateOf(viewModel.factory.buildEntity(producto.id))
	}
	pedidoProducto.costoUnitario=producto.precio

	if (openDialog.value) {
		Dialog(onDismissRequest = {
			cantidad=0
			openDialog.value = false
		},) {
			// Tu diseño personalizado aquí
			Surface(shape = RoundedCornerShape(10.dp),
				color = AmarilloNaranja,
				modifier = Modifier
					.fillMaxWidth()
					.padding(15.dp)) {
				Column {
					Row(
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.Center,
						modifier = Modifier
							.weight(3f)
							.fillMaxWidth()
					) {
						ShowImageFromAPI(
							viewModel = viewModel,
							contentScale = ContentScale.Crop,
							type = ElementTypes.ImagenProducto,
							size = 100.dp,
							fileName = producto.nombre,
							modifier = Modifier
								.weight(5f)
								.fillMaxHeight()
								.padding(top = 10.dp, bottom = 10.dp)
						)
						Column {

							Row(
								verticalAlignment = Alignment.CenterVertically,
								modifier = Modifier.padding(5.dp)
							) {
								Column {
									Row(
										verticalAlignment = Alignment.CenterVertically,
										horizontalArrangement = Arrangement.Center,
										modifier = Modifier
											.weight(5f)
											.fillMaxWidth()
									) {
										Surface(
											onClick = {
												if (cantidad != 0) {
													cantidad--
												} else
													Toast.makeText(
														context,
														"No se puede reducir, no tiene anadido nada actualmente",
														Toast.LENGTH_SHORT
													).show()
											},
											color = Color.Red,
											shape = RoundedCornerShape(5.dp),
											modifier = Modifier
												.weight(3f)
												.fillMaxSize()
												.padding(3.dp)
										) {
											Icon(
												imageVector = Icons.Filled.KeyboardArrowDown,
												contentDescription = "Remove Product to Order",
												tint = Color.Black,
												modifier = Modifier.size(30.dp)
											)
										}
										Text(
											text = cantidad.toString(),
											fontSize = 25.sp,
											color = Color.Black,
											fontWeight = FontWeight.Bold,
											fontFamily = FontFamily.Serif,
											modifier = Modifier.padding(8.dp)
										)
										Surface(
											onClick = { cantidad++ },
											color = Color(0xFF5DDC54),
											shape = RoundedCornerShape(5.dp),
											modifier = Modifier.weight(3f)
										) {
											Icon(
												imageVector = Icons.Filled.KeyboardArrowUp,
												contentDescription = "Add Product to Order",
												tint = Color.Black,
												modifier = Modifier
													.fillMaxSize()
													.padding(3.dp)
											)
										}
									}
									Surface(
										shape = RoundedCornerShape(10.dp),
										onClick = {
											pedidoProducto.cantidad = cantidad
											pedidosViewModel.AddProductToListOfOrder(productoVendido = pedidoProducto)
											cantidad = 0
											openDialog.value = false
										},
										modifier = Modifier
											.weight(5f)
											.fillMaxSize()
											.padding(5.dp)
									) {
										Row(
											verticalAlignment = Alignment.CenterVertically,
											horizontalArrangement = Arrangement.Center
										) {
											Icon(
												imageVector = Icons.Filled.ShoppingCart,
												contentDescription = "buy button",
												modifier = Modifier.padding(
													start = 5.dp,
													end = 3.dp,
													top = 5.dp,
													bottom = 5.dp
												)
											)
											Text(text = "Añadir", fontFamily = FontFamily.Serif)
										}
									}
								}
							}
						}
					}
					LazyColumn(modifier = Modifier
						.weight(6f)
						.fillMaxSize()) {
						item {
							Row(
								horizontalArrangement = Arrangement.SpaceBetween,
								verticalAlignment = Alignment.CenterVertically,
								modifier = Modifier
									.fillMaxWidth()
									.padding(7.dp)
							) {
								Text(
									text = producto.nombre, color = Color.Black,
									fontWeight = FontWeight.Bold,
									fontSize = 17.sp,
									fontFamily = FontFamily.Serif
								)
								Text(
									text = producto.precio.toString() + " $",
									fontSize = 17.sp, color = Color.Black,
									fontWeight = FontWeight.Bold,
									fontFamily = FontFamily.Serif
								)
							}
							Divider(modifier = Modifier.fillMaxWidth())
							Text(
								text = producto.descripcion,
								color = Color.Black,
								textAlign = TextAlign.Justify,
								modifier = Modifier.padding(8.dp)
							)
							Spacer(modifier = Modifier.height(8.dp))
						    }
						}
					}
				}
			}
		}
	}

@Composable
fun CustomAlertNotifications(
	openDialog: MutableState<Boolean>,
	pedidosViewModel: PedidosViewModel,
	isHorizontal: Boolean
) {
	// Recolecta los valores emitidos por el Flow y conviértelos en un State
	val notificaciones = pedidosViewModel.listaNotificaciones.collectAsState()
	val context = LocalContext.current
	if (openDialog.value) {
		Dialog(onDismissRequest = {
			openDialog.value = false
		},) {
			Column(
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier
					.fillMaxWidth()
					.padding(top = 30.dp, bottom = 30.dp)) {
				Surface(
					onClick = {openDialog.value=false},
					shape = RoundedCornerShape(10.dp),
					color = Color.Red,
					modifier = Modifier
						.weight(1f)
						.padding(15.dp)
				) {
					Row(verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceEvenly,
						modifier = Modifier
							.padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)) {
						Icon(
							imageVector = Icons.Filled.Clear,
							contentDescription = "", tint = Color.White)
						Text(text = "CERRAR", color = Color.White
							, fontFamily = FontFamily.Serif)
					}
				}

					LazyColumn(
						horizontalAlignment = Alignment.CenterHorizontally,
						modifier = Modifier
							.weight(9f)
							.fillMaxWidth()
					) {
						items(notificaciones.value.sortedByDescending { it.fecha }) {
							Surface (
								shadowElevation = 5.dp, tonalElevation = 3.dp,
								color = NaranjaBeich,
								shape = RoundedCornerShape(10.dp),
								modifier = Modifier
									.fillMaxSize()
									.padding(bottom = 10.dp)) {
								Column(
									modifier = Modifier
										.fillMaxWidth()
										.padding(10.dp)
								) {

									Row(
										horizontalArrangement = Arrangement.SpaceBetween,
										verticalAlignment = Alignment.CenterVertically,
										modifier = Modifier
											.fillMaxWidth()
											.padding(
												top = 5.dp,
												end = 5.dp, start = 5.dp, bottom = 10.dp
											)
									) {
										Image(
											painter = painterResource(it.icon),
											contentDescription = "",
											modifier = Modifier.size(30.dp)
										)
										Text(
											fontWeight = FontWeight.SemiBold,
											text = it.fecha,
											color = Color.Black,
											fontFamily = FontFamily.Serif,
											modifier = Modifier.padding(bottom = 5.dp)
										)

									}
									Divider(
										modifier = Modifier
											.fillMaxWidth()
											.padding(
												start = 5.dp,
												end = 5.dp,
												top = 10.dp,
												bottom = 10.dp
											)
									)
									Text(
										textAlign = TextAlign.Justify,
										text = it.descripcion,
										color = Color.Black,
										fontFamily = FontFamily.Serif
									)

								}
							}
						}

				}
			}
		}
	}
}

@Composable
fun CustomAlertDialog(
	openDialog: MutableState<Boolean>,
	producto: Producto,
	pedidosViewModel: PedidosViewModel,
	viewModel: MenuViewModel
) {
	val context = LocalContext.current
	var cantidad by remember {
		mutableStateOf(0)
	}

	val pedidoProducto by remember {
		mutableStateOf(viewModel.factory.buildEntity(producto.id))
	}
	pedidoProducto.costoUnitario=producto.precio

	if (openDialog.value) {
		Dialog(onDismissRequest = {
			cantidad=0
			openDialog.value = false
								  },) {
			// Tu diseño personalizado aquí
			Surface(shape = RoundedCornerShape(10.dp),
				color = AmarilloNaranja,
				modifier = Modifier
					.fillMaxWidth()
					.padding(15.dp)) {
				LazyColumn(
					modifier = Modifier.fillMaxWidth()
				) {
					item {

							ShowImageFromAPI(
								viewModel = viewModel,
								contentScale = ContentScale.Crop,
								type = ElementTypes.ImagenProducto,
								fileName = producto.nombre,
								modifier = Modifier.fillMaxWidth()
							)
							Row(
								horizontalArrangement = Arrangement.SpaceBetween,
								verticalAlignment = Alignment.CenterVertically,
								modifier = Modifier
									.fillMaxWidth()
									.padding(7.dp)
							) {
								Text(
									text = producto.nombre, color = Color.Black,
									fontWeight = FontWeight.Bold,
									fontSize = 17.sp,
									fontFamily = FontFamily.Serif
								)
								Text(
									text = producto.precio.toString() + " $",
									fontSize = 17.sp, color = Color.Black,
									fontWeight = FontWeight.Bold,
									fontFamily = FontFamily.Serif
								)
							}
							Divider(modifier = Modifier.fillMaxWidth())
							Text(
								text = producto.descripcion,
								color = Color.Black,
								textAlign = TextAlign.Justify,
								modifier = Modifier.padding(8.dp)
							)
							Spacer(modifier = Modifier.height(8.dp))
							Row(
								verticalAlignment = Alignment.CenterVertically,
								modifier = Modifier.padding(5.dp)) {
								Row(verticalAlignment = Alignment.CenterVertically,
									horizontalArrangement = Arrangement.Center,
									modifier = Modifier
										.weight(5f)
										.fillMaxWidth()) {
									Surface(onClick = {
										if (cantidad!=0) {
											cantidad--
										}
										else
											Toast.makeText(context,"No se puede reducir, no tiene anadido nada actualmente",Toast.LENGTH_SHORT).show()
									}, color = Color.Red, shape = RoundedCornerShape(5.dp), modifier = Modifier
										.weight(3f)
										.fillMaxSize()
										.padding(3.dp)) {
										Icon(imageVector = Icons.Filled.KeyboardArrowDown,
											contentDescription = "Remove Product to Order",
											tint = Color.Black,
											modifier = Modifier.size(30.dp))
									}
									AnimatedContent(
										targetState = cantidad,
										label = "animated counter",
										transitionSpec = {
											slideInVertically(
												initialOffsetY = {-it},
												animationSpec = tween(500)
											) togetherWith slideOutVertically(
												targetOffsetY = {it},
												animationSpec = tween(500)
											)
										}
									) { targetCount->
										Text(text = targetCount.toString(),
											fontFamily = FontFamily.Serif,
											color = Color.Black,
											fontWeight = FontWeight.Bold,
											fontSize = 25.sp,
											modifier = Modifier.padding(8.dp))
									}

									Surface(
										onClick = { cantidad++ },
										color = Color(0xFF5DDC54),
										shape = RoundedCornerShape(5.dp),
										modifier = Modifier.weight(3f)) {
										Icon(imageVector = Icons.Filled.KeyboardArrowUp,
											contentDescription = "Add Product to Order" ,
											tint = Color.Black,
											modifier = Modifier
												.fillMaxSize()
												.padding(3.dp))
									}
								}
								Surface( shape = RoundedCornerShape(10.dp),
									onClick = {
										pedidoProducto.cantidad=cantidad
										pedidosViewModel.AddProductToListOfOrder(productoVendido = pedidoProducto)
										cantidad=0
										openDialog.value = false
									},
									modifier = Modifier
										.weight(5f)
										.fillMaxSize()
										.padding(5.dp)) {
									Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
										Icon(imageVector = Icons.Filled.ShoppingCart,
											contentDescription = "buy button",
											modifier = Modifier.padding(start = 5.dp, end = 3.dp, top = 5.dp, bottom = 5.dp))
										Text(text = "Añadir", fontFamily = FontFamily.Serif)
									}
								}
							}
						}
				}
			}
		}
	}

}

@Composable
fun ModalSection(pedidosViewModel: PedidosViewModel,menuViewModel: MenuViewModel, isHorizontal: Boolean, colorSurface: Color) {
	val context = LocalContext.current
	val scrollableState = rememberLazyListState()
	Column(modifier = Modifier
		.fillMaxWidth()
		.padding(10.dp)) {
		Row(modifier = Modifier.fillMaxWidth()) {
			if (isHorizontal) {
				Surface( shape = RoundedCornerShape(10.dp),
					color = colorSurface,
					shadowElevation = 5.dp,
					tonalElevation = 3.dp,
					modifier = Modifier
						.weight(5f)
						.padding(5.dp)
				) {
					Row(verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(5.dp)) {
						Text(text = "Total a pagar:")
						Text(
							text = "$ ${pedidosViewModel.saldoAPagar}",
							fontFamily = FontFamily.Serif,
							fontSize = 25.sp)

					}
				}
				Surface(
					shape = RoundedCornerShape(10.dp),
					color = Color.Green,
					shadowElevation = 5.dp,
					tonalElevation = 3.dp,
					onClick = {
						pedidosViewModel.SendOrderToServer(context)
					},
					modifier = Modifier
						.weight(5f)
						.fillMaxWidth()
						.padding(5.dp)) {
					Row(verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(5.dp)) {
						Icon(imageVector = Icons.Filled.Done,
							contentDescription = "Send order to server",
							tint = Color.Black)
						Text(text = "CONFIRMAR", fontFamily = FontFamily.Serif, color = Color.Black)
					}
				}
			}
			else {
				Surface( shape = RoundedCornerShape(10.dp),
					color = colorSurface,
					shadowElevation = 5.dp,
					tonalElevation = 3.dp,
					modifier = Modifier
						.weight(5f)
						.padding(5.dp)
				) {
					Column(verticalArrangement = Arrangement.Center,
						horizontalAlignment = Alignment.CenterHorizontally,
						modifier = Modifier.fillMaxWidth()) {
						Text(text = "Total a pagar:")
						Text(
							text = "$ ${pedidosViewModel.saldoAPagar}",
							fontFamily = FontFamily.Serif,
							fontSize = 25.sp)

					}
				}
				Surface(
					shape = RoundedCornerShape(10.dp),
					color = Color.Green,
					shadowElevation = 5.dp,
					tonalElevation = 3.dp,
					onClick = {
						pedidosViewModel.SendOrderToServer(context)
							  },
					modifier = Modifier
						.weight(5f)
						.fillMaxWidth()
						.padding(5.dp)) {
					Column(verticalArrangement = Arrangement.Center,
						horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(5.dp)) {
						Icon(imageVector = Icons.Filled.Done,
							contentDescription = "Send order to server",
							tint = Color.Black)
						Text(text = "CONFIRMAR", fontFamily = FontFamily.Serif, color = Color.Black)
					}
				}
			}
		}
		Divider(modifier = Modifier
			.fillMaxWidth()
			.padding(start = 5.dp, end = 5.dp))
		LazyColumn(state = scrollableState,
			modifier = Modifier
				.drawFadingEdgesBasic(scrollableState, 18.dp, IsDarkTheme = isSystemInDarkTheme())
				.fillMaxWidth()
				.padding(top = 5.dp)) {
			items(pedidosViewModel.pedido) {
				val nombre = menuViewModel.getProductById(id = it.id)?:""
				var cantidad by rememberSaveable {
					mutableStateOf(0)
				}
				cantidad=it.cantidad
				Surface( shape = RoundedCornerShape(10.dp),
					color = colorSurface,
					shadowElevation = 2.dp,
					tonalElevation = 1.dp,
					modifier = Modifier
						.fillMaxWidth()
						.padding(bottom = 3.dp)
				) {
					Row(modifier = Modifier.fillMaxWidth()) {
						ShowImageFromAPI(
							viewModel = menuViewModel,
							contentScale = ContentScale.Crop,
							type = ElementTypes.ImagenProducto,
							fileName = nombre,
							size = 80.dp,
							modifier = Modifier
								.weight(2f)
								.clip(shape = RoundedCornerShape(10.dp))
						)
						Column(
							verticalArrangement = Arrangement.Center,
							modifier = Modifier
								.fillMaxWidth()
								.weight(7f)
						) {
							Row(
								verticalAlignment = Alignment.CenterVertically,
								horizontalArrangement = Arrangement.SpaceBetween,
								modifier = Modifier.fillMaxWidth()
							) {
								LazyRow(
									verticalAlignment = Alignment.CenterVertically,
									horizontalArrangement = Arrangement.SpaceBetween,
									modifier = Modifier
										.weight(7f)
										.fillMaxWidth()
										.padding(end = 10.dp)
								) {
									item {
										Text(
											text = nombre, fontFamily = FontFamily.Serif,
											modifier = Modifier.padding(start = 10.dp)
										)
									}
								}
								Text(
									text = "C/U: $ ${it.costoUnitario}",
									fontWeight = FontWeight.Bold,
									modifier = Modifier.weight(3f)
								)
							}
							Row(
								verticalAlignment = Alignment.CenterVertically,
								horizontalArrangement = Arrangement.SpaceBetween,
								modifier = Modifier.fillMaxWidth()
							) {
								Row {
									Surface(
										shape = RoundedCornerShape(5.dp),
										shadowElevation = 3.dp,
										tonalElevation = 3.dp,
										color = Color.Red,
										onClick = {
											val productoRemovido =
												menuViewModel.factory.buildEntity(it.id)
											productoRemovido.costoUnitario = it.costoUnitario
											productoRemovido.cantidad = 1
											if (cantidad > 0)
												cantidad--
											pedidosViewModel.RemoveProductToOrder(
												context,
												productoRemovido
											)
										}
									) {
										Icon(
											painter = painterResource(id = R.drawable.remove),
											contentDescription = "Remove product of order",

											modifier = Modifier
												.size(30.dp)
												.padding(5.dp),
											tint = Color.Black
										)
									}
									Surface(
										shape = RoundedCornerShape(5.dp),
										shadowElevation = 3.dp,
										tonalElevation = 3.dp,
										color = VerdeAgregar,
										onClick = {
											val productoNuevo =
												menuViewModel.factory.buildEntity(it.id)
											productoNuevo.costoUnitario = it.costoUnitario
											productoNuevo.cantidad = 1
											cantidad++
											pedidosViewModel.AddProductToListOfOrder(productoNuevo)
										}) {
										Icon(
											painter = painterResource(id = R.drawable.add),
											contentDescription = "Add product of order",
											tint = Color.Black,
											modifier = Modifier
												.size(30.dp)
												.padding(5.dp)
										)
									}
								}
								Text(text = "Cantidad: ${cantidad}", fontFamily = FontFamily.Serif)
							}
						}
					}
				}
			}
		}
	}
}

@Composable
fun SeccionDeCompras(viewModel: MenuViewModel, producto: Producto) {
	val context = LocalContext.current
	var cantidad by remember {
		mutableStateOf(0)
	}
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier.padding(5.dp)) {
		Row(verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.Center,
			modifier = Modifier
				.weight(5f)
				.fillMaxWidth()) {
			Surface(onClick = {
				if (cantidad!=0) {
					cantidad--
				}
				else
					Toast.makeText(context,"No se puede reducir, no tiene anadido nada actualmente",Toast.LENGTH_SHORT).show()
							  }, color = Color.Red, shape = RoundedCornerShape(5.dp), modifier = Modifier
				.weight(3f)
				.fillMaxSize()
				.padding(3.dp)) {
				Icon(imageVector = Icons.Filled.KeyboardArrowDown,
					contentDescription = "Remove Product to Order",
					modifier = Modifier.size(30.dp))
			}
			Text(
				text = cantidad.toString(),
				fontSize = 25.sp,
				fontWeight = FontWeight.Bold,
				fontFamily = FontFamily.Serif,
				modifier = Modifier.padding(8.dp)
			)
			Surface(onClick = { cantidad++ }, color = Color(0xFF5DDC54), shape = RoundedCornerShape(5.dp), modifier = Modifier.weight(3f)) {
				Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "Add Product to Order" ,
					modifier = Modifier
						.fillMaxSize()
						.padding(3.dp))
			}
		}
		Surface( shape = RoundedCornerShape(10.dp),
			onClick = { /*TODO*/ },
			modifier = Modifier
				.weight(5f)
				.fillMaxSize()
				.padding(5.dp)) {
			Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
				Icon(imageVector = Icons.Filled.ShoppingCart,
					contentDescription = "buy button",
					modifier = Modifier.padding(start = 5.dp, end = 3.dp, top = 5.dp, bottom = 5.dp))
				Text(text = "Añadir", fontFamily = FontFamily.Serif)
			}
		}
	}

}

@Composable
fun SaldoAPagar(pedidosViewModel: PedidosViewModel,modifier: Modifier=Modifier) {
	Surface(
		color = if (isSystemInDarkTheme())
			Color(0XFF2a2a2a)
		else
			Color.White,
		shadowElevation = 5.dp,
		tonalElevation = 3.dp,
		modifier = Modifier
			.padding(5.dp)
	) {
		Column(verticalArrangement = Arrangement.Center,
			modifier = Modifier.fillMaxWidth()) {
			Text(text = "Total a pagar:")
			Box(contentAlignment = Alignment.Center,modifier = Modifier.fillMaxWidth()) {
				Text(
					text = "$ ${pedidosViewModel.saldoAPagar}",
					fontFamily = FontFamily.Serif,
					fontSize = 25.sp)
			}
		}
	}
}

@Composable
fun LazyWrapLayout(viewModel: MenuViewModel,pedidosViewModel: PedidosViewModel) {
	val listaProducto by viewModel.listaProducto.collectAsState()
	val preferedViewModel:SavePreferencesViewModel = hiltViewModel()
	LazyVerticalStaggeredGrid(
		columns = StaggeredGridCells.Adaptive(minSize = 150.dp),
		verticalItemSpacing = 5.dp,
		horizontalArrangement = Arrangement.spacedBy(5.dp)) {
		items(listaProducto) {
			ItemByCategory(
				viewModel = viewModel,
				pedidosViewModel,
				savePreferencesViewModel = preferedViewModel,
				producto = it,
				modifier = Modifier.fillMaxWidth())
		}
	}
}

@Composable
fun ItemByCategory (
	viewModel: MenuViewModel,
	pedidosViewModel: PedidosViewModel,
	savePreferencesViewModel: SavePreferencesViewModel,
	producto: Producto,
	modifier: Modifier) {
	val openDialog = rememberSaveable { mutableStateOf(false) }
	val isHorizontal = LocalConfiguration.current.orientation== Configuration.ORIENTATION_LANDSCAPE
	Surface(shape = RoundedCornerShape(20.dp),tonalElevation = 5.dp,
		shadowElevation = 3.dp, onClick = {
			openDialog.value=true
		},
		modifier = Modifier
			.fillMaxWidth()
			.fillMaxHeight()
			.padding(start = 5.dp, top = 10.dp, end = 5.dp)) {

			Column(verticalArrangement = Arrangement.Center,
				modifier = Modifier.fillMaxWidth()) {
				Box(modifier = Modifier
					.fillMaxWidth()
					.fillMaxHeight()) {
					ShowImageFromAPI(
						viewModel = viewModel ,
						contentScale = ContentScale.Crop,
						type = ElementTypes.ImagenProducto,
						fileName = producto.nombre,
						modifier = Modifier.fillMaxWidth())
				}
				Surface(tonalElevation = 5.dp,
					shadowElevation = 3.dp,
					modifier = Modifier.width(300.dp)) {
					Column {
						Row(
							horizontalArrangement = Arrangement.SpaceBetween,
							verticalAlignment = Alignment.CenterVertically,
							modifier = Modifier
								.fillMaxWidth()
								.padding(5.dp)
						) {
							Box(contentAlignment = Alignment.CenterStart,modifier = Modifier
								.weight(9f)
								.fillMaxWidth()) {
								Text(
									textAlign = TextAlign.Center,
									text = producto.nombre,
									fontWeight = FontWeight.Bold,

								)
							}
							AnimatedHearthIconButton(
								modifier=Modifier.weight(1f),
								producto.id,
								preferedViewModel = savePreferencesViewModel)

						}
						Divider(modifier = Modifier.padding(start =5.dp,end =5.dp, bottom = 5.dp))
						Row(
							modifier = Modifier
								.fillMaxWidth()
								.padding(start = 10.dp, bottom = 5.dp, end = 10.dp),
							verticalAlignment = Alignment.CenterVertically,
							horizontalArrangement = Arrangement.SpaceBetween
						) {
							Text(text = "Precio:",
								fontFamily = FontFamily.Serif)
							Row(
								verticalAlignment = Alignment.CenterVertically,
								horizontalArrangement = Arrangement.Center,
								modifier = Modifier
							) {
								Text(text = producto.precio.toString(),fontWeight = FontWeight.Bold)
								Text(text = " $", fontWeight = FontWeight.Bold)
							}
						}

					}
				}
		}
	}
	if (isHorizontal) {
		CustomAlertDialog(openDialog = openDialog, producto = producto, pedidosViewModel = pedidosViewModel,viewModel,isHorizontal )

	}
	else {
		CustomAlertDialog(
			openDialog = openDialog, producto = producto, pedidosViewModel = pedidosViewModel,viewModel
		)
	}
}

@Composable
fun TargetaPlatosPrincipales(viewModel: MenuViewModel,pedidosViewModel: PedidosViewModel) {
	val listaProducto by viewModel.listaProducto.collectAsState()
	val preferedViewModel:SavePreferencesViewModel = hiltViewModel()
	LazyRow(modifier = Modifier.fillMaxWidth()) {
		items(listaProducto) {
			ItemByCategory(viewModel = viewModel,
				pedidosViewModel = pedidosViewModel,
				savePreferencesViewModel = preferedViewModel,
				producto = it,
				modifier = Modifier.fillMaxWidth())
		}
	}
}

@Composable
fun ShowImageFromAPI(
	viewModel: MenuViewModel,
	contentScale: ContentScale,
	type:ElementTypes,
	fileName: String,
	size:Dp,
	modifier: Modifier=Modifier
) {
	val context = LocalContext.current
	var image by remember {
		mutableStateOf<ImageBitmap?>(null)
	}

	val scope = rememberCoroutineScope()

	LaunchedEffect(key1 =fileName) {
		scope.launch {
			try {
				image= viewModel.cache.get(fileName,type,context )
			}
			catch (e:Exception) {
				Toast.makeText(context,"Existen Errores en la carga de algunas Imagenes",Toast.LENGTH_SHORT)
			}
		}
	}
	image?.let { imageBitmap ->
		val alpha: Float by animateFloatAsState(targetValue = 1f,
			animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
		)
		Image(bitmap = imageBitmap,
			contentDescription = null,
			contentScale = contentScale,
			modifier = Modifier
				.size(size)
				.alpha(alpha)
				.padding(5.dp)
				.clip(shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)))
	}
}

@Composable
fun ShowImageFromAPI(
	viewModel: MenuViewModel,
	contentScale: ContentScale,
	type:ElementTypes,
	fileName: String,
	modifier: Modifier=Modifier
) {
	val context = LocalContext.current
	var image by remember {
		mutableStateOf<ImageBitmap?>(null)
	}

	val scope = rememberCoroutineScope()

	LaunchedEffect(key1 =fileName) {
		scope.launch {
			try {
				image= viewModel.cache.get(fileName,type,context )
			}
			catch (e:Exception) {
				Toast.makeText(context,"Existen Errores en la carga de algunas Imagenes",Toast.LENGTH_SHORT)
			}
		}
	}
	image?.let { imageBitmap ->
		val alpha: Float by animateFloatAsState(targetValue = 1f,
			animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing)
		)
		Image(bitmap = imageBitmap,
			contentDescription = null,
			contentScale = contentScale,
			modifier = Modifier
				.fillMaxWidth()
				.alpha(alpha))
	}
}

@Composable
fun EnlacesRapidos(viewModel: MenuViewModel, isHorizontal: Boolean) {
	var escogido by rememberSaveable {
		mutableStateOf("Pizza")
	}
	val context = LocalContext.current
	val colorfondo=if (isSystemInDarkTheme()) {Color(0xFF2D2D30)} else {Color(0xFFEDEDEF)}
	val listaCategorias by viewModel.listaCategorias.collectAsState()
	if (isHorizontal) {
		LazyColumn(verticalArrangement = Arrangement.SpaceAround,
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier.padding(top = 10.dp)) {
			items(listaCategorias) {
				Surface( tonalElevation = 5.dp,
					onClick = {
						escogido = it.descripcion
						viewModel.getProductOfCategory(it.id,context)
							  },
					shadowElevation = 3.dp,
					color = if (escogido==it.descripcion) {
						NaranjaMiel
					} else {
						colorfondo
					} ,
					shape = RoundedCornerShape(15.dp),
					modifier = Modifier
						.fillMaxHeight()
						.padding(start = 15.dp, end = 15.dp, top = 5.dp)
				) {
					Row(verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.SpaceAround,
						modifier = Modifier.fillMaxWidth()) {
						ShowImageFromAPI(viewModel = viewModel, size = 60.dp ,
							contentScale = ContentScale.Fit,
							type = ElementTypes.ImagenCategoria,
							fileName =it.descripcion, modifier = Modifier.weight(3f))
						Box(contentAlignment = Alignment.Center,
							modifier = Modifier
								.fillMaxWidth()
								.padding(start = 4.dp, end = 4.dp)) {
							Text(
								text = it.descripcion ?: "Error con categoria",
								fontFamily = FontFamily.Serif,
								fontSize = 17.sp,
								textAlign = TextAlign.Center
							)
						}

					}
				}
			}
		}
	}
	else {
		LazyRow(
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier
				.padding(top =20.dp, bottom = 20.dp)
		) {
			items(listaCategorias) {
				Surface( tonalElevation = 5.dp,
					onClick = {
						escogido = it.descripcion
						viewModel.getProductOfCategory(it.id,context)
							  },
					shadowElevation = 3.dp,
					color = if (escogido==it.descripcion) {
						NaranjaPuro
					} else {
						colorfondo
					}  ,
					shape = RoundedCornerShape(15.dp),
					modifier = Modifier
						.fillMaxHeight()
						.padding(start = 10.dp, end = 10.dp, top = 5.dp)
				) {
					Row(verticalAlignment = Alignment.CenterVertically,
						modifier = Modifier.padding(5.dp)) {
						ShowImageFromAPI(viewModel = viewModel,
							contentScale = ContentScale.Fit,
							type = ElementTypes.ImagenCategoria,
							fileName =it.descripcion )
						Text(
							text = it.descripcion?:"Error con categoria",
							fontFamily = FontFamily.Serif,
							fontSize = 20.sp,
							modifier = Modifier.padding(start = 5.dp, end = 15.dp)
						)

					}
				}
			}
		}
	}

}

@Composable
fun OfertasDelaCasa(isHorizontal:Boolean) {
	Surface(tonalElevation = 5.dp,
		shadowElevation = 3.dp,
		color = NaranjaBeich,
		shape= RoundedCornerShape(20.dp),
		modifier = Modifier
			.fillMaxWidth()
			.fillMaxHeight()
			.padding(start = 5.dp, top = 10.dp, end = 5.dp, bottom = 5.dp)) {
		if (isHorizontal) {
			Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
					Image(contentScale = ContentScale.Crop,
						painter = painterResource(id = R.drawable.pizzaofertas2),
						contentDescription = "Ofertas",
						modifier = Modifier
							.weight(3f)
							.size(50.dp)
							.clip(
								shape = RoundedCornerShape(
									20.dp
								)
							))
					LazyColumn(modifier = Modifier.weight(7f)) {
						item {
						Text(text = "50%",
							color = Color.Black,
							fontSize = 35.sp,
							fontFamily = FontFamily.Serif,
							modifier = Modifier
								.padding(top = 5.dp))
						Text(text = "Si consumes mas de 5 productos de mas de 300 CUP, se llevara el 50% de descuento de la primero",
							textAlign = TextAlign.Justify,
							color = Color.Black,
							modifier = Modifier
								.padding(5.dp))
						Row(verticalAlignment = Alignment.CenterVertically,
							horizontalArrangement = Arrangement.Center,
							modifier = Modifier
								.fillMaxWidth()) {
							Icon(imageVector = Icons.Filled.Star,
								tint = Color.Black,
								contentDescription = null)
							Icon(imageVector = Icons.Filled.Star,
								tint = Color.Black,
								contentDescription = null)
							Icon(imageVector = Icons.Filled.Star,
								tint = Color.Black,
								contentDescription = null)
							Icon(imageVector = Icons.Filled.Star,
								tint = Color.Black,
								contentDescription = null)
							Icon(imageVector = Icons.Filled.Star,
								tint = Color.Black,
								contentDescription = null)
						}
					}
				}
			}
		}
		else {
			Row {
				Column(modifier = Modifier.weight(7f)) {
					Text(text = "50%",
						color = Color.Black,
						fontSize = 35.sp,
						fontFamily = FontFamily.Serif,
						modifier = Modifier
							.weight(3f)
							.padding(start = 15.dp))
					Text(text = "Si consumes mas de 5 productos de mas de 300 CUP, se llevara el 50% de descuento de la primero",
						textAlign = TextAlign.Justify,
						color = Color.Black,
						modifier = Modifier
							.weight(5f)
							.padding(start = 15.dp, end = 10.dp))
					Row(verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.Center,
						modifier = Modifier
							.weight(2f)
							.fillMaxWidth()) {
						Icon(imageVector = Icons.Filled.Star,
							tint = Color.Black,
							contentDescription = null)
						Icon(imageVector = Icons.Filled.Star,
							tint = Color.Black,
							contentDescription = null)
						Icon(imageVector = Icons.Filled.Star,
							tint = Color.Black,
							contentDescription = null)
						Icon(imageVector = Icons.Filled.Star,
							tint = Color.Black,
							contentDescription = null)
						Icon(imageVector = Icons.Filled.Star,
							tint = Color.Black,
							contentDescription = null)
					}
				}
				Image(contentScale = ContentScale.Crop,
					painter = painterResource(id = R.drawable.pizzaofertas2),
					contentDescription = "Ofertas",
					modifier = Modifier
						.weight(3f)
						.clip(
							shape = RoundedCornerShape(
								topStart = 50.dp,
								bottomStart = 50.dp
							)
						))

			}
		}
	}
}

@Composable
fun CuadroDeBusqueda() {
	val colorTextField = if (isSystemInDarkTheme()) {
		Color.White
	}
	else {
		Color.Black
	}
	OutlinedTextField(shape = RoundedCornerShape(10.dp),
		value = "",
		onValueChange = {},
		label = {
			Row(verticalAlignment = Alignment.CenterVertically) {
				Icon(imageVector = Icons.Filled.Search,
					tint = colorTextField,
					contentDescription = "Icon Search")
				Text(text = "Busca el producto de su preferencia",
					color = colorTextField, fontSize = 15.sp)
			}
		},
		modifier = Modifier
			.fillMaxWidth()
			.padding(5.dp))
}
