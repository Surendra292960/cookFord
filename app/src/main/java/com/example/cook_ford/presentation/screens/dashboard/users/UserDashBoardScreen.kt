package com.example.cook_ford.presentation.screens.dashboard.users
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.R
import com.example.cook_ford.presentation.common.widgets.CardScreen
import com.example.cook_ford.presentation.theme.Cook_fordTheme

@Composable
fun UserDashBoard(){

    Surface{

        Column(modifier = Modifier.fillMaxSize()) {
            Row (modifier = Modifier
                .fillMaxWidth()
                .width(150.dp)
                .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = "Cook Ford",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                )

                Image(painter = painterResource(id = R.drawable.slide_1),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .width(80.dp)
                        .height(80.dp))
            }

            Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

                Card(shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp)) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp, top = 30.dp)) {

                        LazyRow(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(FlowersData.list.size) {
                                CardScreen(FlowersData.list[it])
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview(){
    Cook_fordTheme {
        UserDashBoard()
    }
}