package com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.list_screen_component.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R

@Composable
fun Header() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.chef_cooking),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .height(120.dp)
                .padding(end = 32.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Beasts Products",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(text = "Discover a new products from us",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}