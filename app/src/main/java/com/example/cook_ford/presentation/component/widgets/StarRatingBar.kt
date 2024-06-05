package com.example.cook_ford.presentation.component.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
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
    val starSize = (12f * density).dp
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
                modifier = Modifier
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            onRatingChanged(i.toFloat())
                            userRating.floatValue = i.toFloat()
                        }
                    )
                    .width(starSize)
                    .height(starSize)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpacing))
            }
        }
    }
}


/*@Composable
fun RatingStar(rating: Float = 5f, maxRating: Int = 5, onStarClick: (Int) -> Unit, isIndicator: Boolean = false) {
    Row {
        for (i in 1..maxRating) {
            if (i <= rating.toInt()) {
                // Full stars
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .size(24.dp)
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
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(24.dp)
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
            modifier = Modifier.size(24.dp)
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
                tint = MaterialTheme.colors.secondary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

private class FractionalClipShape(private val fraction: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(
            rect = Rect(
                left = 0f,
                top = 0f,
                right = size.width * fraction,
                bottom = size.height
            )
        )
    }
}*/