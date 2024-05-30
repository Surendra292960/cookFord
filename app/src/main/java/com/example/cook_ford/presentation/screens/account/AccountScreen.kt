package com.example.cook_ford.presentation.screens.account
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R

@Composable
fun ProfileScreen() {

    Column (
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
       Box(modifier = Modifier.fillMaxSize()){
           Row(modifier = Modifier,
               verticalAlignment = Alignment.CenterVertically,
               horizontalArrangement = Arrangement.Center){
               Image(
                   painter = painterResource(id = R.drawable.ic_chef_round),
                   contentDescription =""
               )
               Image(
                   painter = painterResource(id = R.drawable.ic_chef_round),
                   contentDescription =""
               )
           }
       }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}