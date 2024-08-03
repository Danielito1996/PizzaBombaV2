package com.elitec.italiana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.elitec.italiana.presentation.Navigation.Navigator
import com.elitec.italiana.presentation.theme.ItalianaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			ItalianaTheme {
				Scaffold(topBar = {StatusBar()}) {
					Box(modifier = Modifier
						.fillMaxSize()
						.padding(it))
					{
						if (isSystemInDarkTheme()) {
							Image(contentScale = ContentScale.Crop,
								painter = painterResource(id = R.drawable.fondo_oscuro),
								contentDescription = null,
								modifier = Modifier
									.fillMaxSize()
									.padding(top = 10.dp))
						}
						else {
							Image(contentScale = ContentScale.Crop,
								painter = painterResource(id = R.drawable.fondo_claro),
								contentDescription = null,
								modifier = Modifier
									.fillMaxSize()
									.padding(top = 10.dp))
						}
						Navigator()
					}
				}
			}
		}
	}
}

@Composable
fun StatusBar() = Spacer(modifier = Modifier.height(20.dp))
