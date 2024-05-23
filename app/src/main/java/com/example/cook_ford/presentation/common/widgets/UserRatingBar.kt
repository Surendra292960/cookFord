package com.example.cook_ford.presentation.common.widgets
import android.view.MotionEvent
import android.widget.RatingBar
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R

@Composable
fun UserRatingBar(
    // 1. Parameters for UserRatingBar
    modifier: Modifier = Modifier,
    onRatingChanged: (Int) -> Unit) {
    val ratingState: MutableState<Int> = remember { mutableIntStateOf(0) }
    val selectedColor: Color = Color(0xFFFFD700)
    val unselectedColor: Color = Color(0xFFA2ADB1)

    Row(modifier = modifier.wrapContentSize()) {
        // 2. Star Icon Generation Loop
        for (value in 1..5) {
            StarIcon(
                imageVector = Icons.Default.StarRate,
                ratingValue = value,
                ratingState = ratingState,
                selectedColor = selectedColor,
                unselectedColor = unselectedColor,
                onRatingChanged = onRatingChanged
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun StarIcon(
    // 3. Parameters for StarIcon
    ratingState: MutableState<Int>,
    imageVector: ImageVector,
    ratingValue: Int,
    selectedColor: Color,
    unselectedColor: Color,
    onRatingChanged: (Int) -> Unit) {
    val density = LocalDensity.current.density
    val starSize = (12f * density).dp
    val starSpacing = (0.5f * density).dp
    // 4. Color Animation
    val tint by animateColorAsState(
        targetValue = if (ratingValue <= ratingState.value) selectedColor else unselectedColor,
        label = ""
    )

    Icon(
        imageVector = imageVector,
        contentDescription = null,
        modifier = Modifier
            .width(starSize).height(starSize)
            // 5. Touch Interaction Handling
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        ratingState.value = ratingValue
                        onRatingChanged.invoke(ratingValue)
                    }
                }
                true
            },
        tint = tint
    )
    Spacer(modifier = Modifier.width(starSpacing))
}

@Preview
@Composable
fun PreviewUserRatingBar() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,) {
        UserRatingBar(onRatingChanged = {})
    }
}