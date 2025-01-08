package com.example.cook_ford.presentation.component.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.presentation.theme.LightGreen
import com.example.cook_ford.presentation.theme.LightGreen1
import com.example.cook_ford.presentation.theme.OrangeYellow1

@Composable
fun StarRatingBar(
    maxStars: Int = 5,
    rating: Float,
    onRatingChanged: (Float) -> Unit) {
    val userRating = remember { mutableFloatStateOf(rating) }
    val density = LocalDensity.current.density
    val starSize = (10f * density).dp
    val starSpacing = (0.5f * density).dp

    Row(modifier = Modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) Icons.Filled.Star else Icons.Default.Star
            val iconTintColor = if (isSelected && userRating.floatValue == 0.0f) {
                Color.Red
            } else if (isSelected && userRating.floatValue == 1.0f) {
                Color.Red
            } else if (isSelected && userRating.floatValue == 2.0f) {
                OrangeYellow1
            } else if (isSelected && userRating.floatValue == 3.0f) {
                LightGreen
            } else if (isSelected && userRating.floatValue == 4.0f) {
                LightGreen1
            } else if (isSelected && userRating.floatValue == 5.0f) {
                DeepGreen
            } else if (isSelected && userRating.floatValue == 6.0f) {
                DeepGreen
            } else {
                Color.LightGray
            }
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTintColor,
                modifier = Modifier.size(starSize)
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            onRatingChanged(i.toFloat())
                            userRating.floatValue = i.toFloat()
                        }
                    )

            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}

@Composable
fun RatingStar(rating: Float = 5f, maxRating: Int = 5, onStarClick: (Int) -> Unit, isIndicator: Boolean = false) {
    Row {
        for (i in 1..maxRating) {
            if (i <= rating.toInt()) {
                // Full stars
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = OrangeYellow1,
                    modifier = Modifier.size(30.dp)
                        .clickable(!isIndicator) {
                            onStarClick(i)
                        }
                )
            } else if (i == rating.toInt() + 1 && rating % 1 != 0f) {
                // Partial star
                PartialStar(fraction = rating % 1)
            } else {
                // Empty stars
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.LightGray,
                    modifier = Modifier.size(30.dp)
                        .clickable(!isIndicator) {
                            onStarClick(i)
                        }
                )
            }
        }
    }
}
@Composable
private fun PartialStar(fraction: Float) {
    val customShape = FractionalClipShape(fraction)

    Box {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(30.dp)
        )
        Box(
            modifier = Modifier
                .graphicsLayer(
                    clip = true,
                    shape = customShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = OrangeYellow1,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

private class FractionalClipShape(private val fraction: Float) : Shape {

    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(
            rect = Rect(
                left = 0f,
                top = 0f,
                right = fraction ,
                bottom = fraction
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewUserRatingBar() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,) {
        RatingStar(rating = 5f, maxRating = 5, onStarClick = {})
    }
}