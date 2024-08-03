package com.elitec.italiana.presentation.PersonalComponents

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// This is the most common approach.
// To keep it simple, we only apply the fading edge to the top.
fun Modifier.drawFadingEdgesBasic(
	scrollableState: ScrollableState,
	topEdgeHeight: Dp = 18.dp,
	IsDarkTheme:Boolean
) = then(
	Modifier
		.graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
		.drawWithContent {
			drawContent()
			val topEdgeHeightPx = topEdgeHeight.toPx()
			if (scrollableState.canScrollBackward && topEdgeHeightPx >= 1f) {
				drawRect(
					brush = Brush.verticalGradient(
						colors = listOf(Color.Transparent,
							if (IsDarkTheme) {
								Color.Black
							} else {
								Color.White
							}
						),
						startY = 0f,
						endY = topEdgeHeightPx,
					),
					blendMode = BlendMode.DstIn,
				)
			}
		}
)
