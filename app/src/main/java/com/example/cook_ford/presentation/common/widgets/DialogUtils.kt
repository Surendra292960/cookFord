package com.example.cook_ford.presentation.common.widgets

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Money
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.cook_ford.R
import com.example.cook_ford.presentation.theme.Cook_fordTheme

@Composable
fun CustomDialog(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White) {

            Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(20.dp, 0.dp, 40.dp, 0.dp)) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = value,
                            style = TextStyle(
                                fontSize = 24.sp,
                                color = Color.Green,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp).height(150.dp).width(150.dp)) {
                        Image(painter = painterResource(id = R.drawable.cook_ford_rounded_logo),
                            contentDescription = "Logo",
                            modifier = Modifier.fillMaxSize().padding(start = 50.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = { setShowDialog(false) },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)) {
                            Text(text = "Done")
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun Preview(){
    Cook_fordTheme {
        val showDialog =  remember { mutableStateOf(true) }
        CustomDialog(
            value = "Data", setShowDialog = { showDialog.value = it}) {
            Log.i("HomePage","HomePage : $it")
        }
    }
}
