package com.example.cook_ford.presentation.common.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldWithTopBar() {

    Column() {
        Scaffold(
            topBar ={
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            Alignment.Center) {
                            Text(text = " ")
                        } },
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "Icon")
                        }
                    },
                    modifier= Modifier
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                        .clip(shape = RoundedCornerShape(16.dp)),
                    actions = {
                        /*  IconButton(onClick = { *//*TODO*//* }) {
                        Icon(imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null)
                    }*/
                        /* IconButton(onClick = { *//*TODO*//* }) {
                        Icon(imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null)
                    }*/
                    }
                )
            }
        )
        {it.calculateTopPadding()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScaffoldWithTopBar()
}