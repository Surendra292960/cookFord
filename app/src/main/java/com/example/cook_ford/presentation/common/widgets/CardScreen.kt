package com.example.cook_ford.presentation.common.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.presentation.screens.dashboard.users.Flowers
import com.example.cook_ford.presentation.theme.Cook_fordTheme

@Preview
@Composable
fun FlowerCard() {
    Cook_fordTheme {
       // FlowerCardScreen()
    }
}

@Composable
fun CardScreen(flower: Flowers) {

    Card(shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .padding(10.dp)
            .width(180.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Image(
                painter = painterResource(id = flower.image),
                contentDescription = null,
                modifier = Modifier.size(140.dp),
            )
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = flower.name,
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    )
                    Text(
                        text = flower.price,
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 16.sp
                        )
                    )
                }
                IconButton(
                    onClick = { },
                    modifier = Modifier.background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(10.dp)
                    )
                ) {
                    Icon(Icons.Default.Add, tint = Color.White,  contentDescription = null)
                }
            }
        }
    }
}
