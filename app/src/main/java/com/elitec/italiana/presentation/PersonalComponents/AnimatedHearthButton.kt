package com.elitec.italiana.presentation.PersonalComponents

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.elitec.italiana.presentation.ViewModels.SavePreferencesViewModel

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun AnimatedHearthIconButton(
	modifier: Modifier=Modifier,
	id:Int,
	preferedViewModel: SavePreferencesViewModel
) {
	var checkState by rememberSaveable { mutableStateOf(false) }

	LaunchedEffect(id) {
		checkState = preferedViewModel.isPrefered(id)
	}
	IconToggleButton(
		checked = checkState,
		onCheckedChange = {
			checkState=!checkState
			if (checkState) {
				preferedViewModel.SavePreferences(id)
			}else {
				preferedViewModel.DeletePreferences(id)
			}
		} ) {
		val icon = if (checkState) {
			Icons.Filled.Favorite
		} else {
			Icons.Filled.FavoriteBorder
		}
		val transition = updateTransition(targetState = checkState, label = "Animacion de Presion")
		val myTint by transition.animateColor { isChecked->
			if (isChecked) {
					Color.Red
				} else {
					Color.Black
				}
			}

		Icon(
			imageVector = icon,
			contentDescription = "Like Button",
			tint = myTint )
	}
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun AnimatedHearthIconButton(
	modifier: Modifier=Modifier,
	checkState:Boolean,
	onCheckedChange: (Boolean) -> Unit) {

	IconToggleButton(
		checked = checkState,
		onCheckedChange = {!checkState} ) {
		val icon = if (checkState) {
			Icons.Filled.Favorite
		} else {
			Icons.Filled.FavoriteBorder
		}
		val transition = updateTransition(targetState = checkState, label = "Animacion de Presion")
		val myTint by transition.animateColor { isChecked->
			if (isChecked) {
				Color.Red
			} else {
				Color.Black
			}
		}

		Icon(
			imageVector = icon,
			contentDescription = "Like Button",
			tint = myTint )
	}
}