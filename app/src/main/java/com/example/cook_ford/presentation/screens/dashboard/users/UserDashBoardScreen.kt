package com.example.cook_ford.presentation.screens.dashboard.users
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.theme.Cook_fordTheme

@Composable
fun UserDashBoard(){

    Surface(modifier = Modifier.fillMaxSize()){
        ToolbarWidget()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarWidget() {
    // theme for our app.
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Top Bar", color = Color.Black)
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Icon")
                    }
                },
                modifier= Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                actions = {
                     IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                     IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray,
                ),
            )
        }, content = { it.calculateTopPadding()
            Column(
                modifier = Modifier
                    .fillMaxSize().padding(top=70.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Content of the page",
                    fontSize = 30.sp,
                    color = Color.Black
                )
            }
        })
}

@Preview(showBackground = true)
@Composable
fun Preview(){
    Cook_fordTheme {
        UserDashBoard()
    }
}