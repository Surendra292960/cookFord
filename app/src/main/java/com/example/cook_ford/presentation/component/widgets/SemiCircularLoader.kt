package com.example.cook_ford.presentation.component.widgets

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

/*** Semi-Circular Arc Loader Demo ***/
@Preview
@Composable
fun SemiCircularArcLoaderDemo(progress: Float) {
    Box(
        modifier = Modifier
            .size(90.dp),
        contentAlignment = Alignment.Center
    ) {
        SemiCircularLoader(
            size = 190.dp,
            progress = progress,
            startAngle = 99.9f,
            indicatorThickness = 7.dp,
            indicatorBrush = Brush.linearGradient(
                listOf(
                    Color(0xFFE55E34),
                    Color(0xFFFDC229),
                    Color(0xFFFDC229),
                    Color(0xFFFDC229)
                )
            ),
            indicatorBrushComplete = Brush.linearGradient(
                listOf(
                    Color(0xFF0C3103),
                    Color(0xFF118105),
                    Color(0xFF318304),
                    Color(0xFF21B607)
                )
            )
        )
    }
}

/*** Base Code  Starts here. ***/
@Composable
fun SemiCircularLoader(
    modifier: Modifier = Modifier,
    progress: Float = 20f,
    size: Dp = 250.dp,
    animationDuration: Int = 1000,
    indicatorColor: Color? = null,
    indicatorBrush: Brush? = null,
    indicatorBrushComplete: Brush? = null,
    trackColor: Color = Color.LightGray.copy(alpha = 0.2f),
    showProgressDot: Boolean = true,
    progressDotColor: Color = Color.White,
    progressDotSize: Dp = 8.dp,
    indicatorThickness: Dp = 20.dp,
    startAngle: Float = 180f,
    maxProgress: Float = 100f,
    textStyle: TextStyle = TextStyle.Default,
    textColor: Color = Color.Black
) {
    require(!(indicatorColor != null && indicatorBrush != null && indicatorBrushComplete != null)) {
        "Only one of indicatorColor or indicatorBrush can be specified, not both."
    }

    val endsSpacing = (startAngle - 100) * 2

    val progressAnimation = remember { Animatable(0f) }
    LaunchedEffect(progressAnimation) {
        progressAnimation.animateTo(
            targetValue = progress.coerceIn(minimumValue = 0f, maximumValue = 100f),
            animationSpec = tween(durationMillis = animationDuration, easing = FastOutSlowInEasing)
        )
    }

    Canvas(
        modifier = Modifier
            .size(size = size)
            .then(modifier),
    ) {
        val width = this.size.width
        val height = this.size.height

        // Track Arc
        drawArc(
            color = trackColor,
            startAngle = startAngle,
            sweepAngle = 360f - endsSpacing,
            useCenter = false,
            style = Stroke(width = indicatorThickness.toPx(), cap = StrokeCap.Round)
        )

        val sweepAngle = (progressAnimation.value / maxProgress) * (360f - endsSpacing)

        // Indicator Arc
        if (progress.toInt() == 100 && indicatorBrushComplete != null) {
            drawArc(
                brush = indicatorBrushComplete,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(indicatorThickness.toPx(), cap = StrokeCap.Round)
            )
        }else{
            if (indicatorBrush != null) {
                drawArc(
                    brush = indicatorBrush,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(indicatorThickness.toPx(), cap = StrokeCap.Round)
                )
            } else {
                drawArc(
                    color = indicatorColor ?: Color.Red,
                    startAngle = startAngle,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    style = Stroke(indicatorThickness.toPx(), cap = StrokeCap.Round)
                )
            }
        }

        if (showProgressDot) {
            val dotRadius = progressDotSize / 2
            val endAngle = startAngle + sweepAngle
            val dotX = width / 2 + width / 2 * cos(Math.toRadians(endAngle.toDouble())).toFloat()
            val dotY = height / 2 + height / 2 * sin(Math.toRadians(endAngle.toDouble())).toFloat()

            drawCircle(
                color = progressDotColor,
                center = Offset(dotX, dotY),
                radius = dotRadius.toPx()
            )
        }

        // Draw Text in Center
        val progressText = "${progressAnimation.value.toInt()}%".plus(" Completed")
        val textPaint = android.graphics.Paint().apply {
            this.color = textColor.toArgb()
            this.textSize = 10.dp.toPx() // Adjust text size relative to the size
            this.textAlign = android.graphics.Paint.Align.CENTER
        }

        drawContext.canvas.nativeCanvas.drawText(
            progressText,
            width / 2,
            height / 2 - (textPaint.ascent() + textPaint.descent()) / 2,
            textPaint
        )
    }
}
