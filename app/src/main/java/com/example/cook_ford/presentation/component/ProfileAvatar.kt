package com.example.cook_ford.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R

@Composable
fun ProfileAvatar(
    painter: Painter,
    size: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.male_chef),
        contentDescription = "Profile Picture",
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .size(size.dp),
    )
}