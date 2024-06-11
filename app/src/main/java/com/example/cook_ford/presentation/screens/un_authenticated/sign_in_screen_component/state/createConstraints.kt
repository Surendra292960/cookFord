/*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@Composable
fun ComposeUIWithAnimation() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
        constraintSet = createConstraints(),
    ) {
        // Text with animation in the center
        val animationProgress by animateFloatAsState(
            targetValue = if (isAnimationRunning) 1f else 0f
        )
        Text(
            text = "Animated Text",
            modifier = Modifier
                .layoutId("animatedText")
                .alpha(animationProgress)
                .padding(16.dp),
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center
        )

        // First outlined fancy button
        OutlinedButton(
            onClick = { */
/* TODO *//*
 },
            modifier = Modifier
                .layoutId("button1")
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Button 1")
        }

        // Second outlined fancy button
        OutlinedButton(
            onClick = { */
/* TODO *//*
 },
            modifier = Modifier
                .layoutId("button2")
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Button 2")
        }

        // First text below image
        Text(
            text = "Text 1 below image",
            modifier = Modifier.layoutId("text1"),
            style = MaterialTheme.typography.body1,
        )

        // Second text below image
        Text(
            text = "Text 2 below image",
            modifier = Modifier.layoutId("text2"),
            style = MaterialTheme.typography.body2,
        )
    }
}

private fun createConstraints(): ConstraintSet {
    return ConstraintSet {
        val animatedText = createGuidelineFromTop(0.4f)
        val button1 = createGuidelineFromTop(0.6f)
        val button2 = createGuidelineFromTop(0.7f)
        val text1 = createGuidelineFromTop(0.8f)
        val text2 = createGuidelineFromTop(0.9f)

        constrain("animatedText") {
            top.linkTo(animatedText)
            centerHorizontallyTo(parent)
        }

        constrain("button1") {
            top.linkTo(button1)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain("button2") {
            top.linkTo(button2)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain("text1") {
            top.linkTo(text1)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain("text2") {
            top.linkTo(text2)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
}*/
