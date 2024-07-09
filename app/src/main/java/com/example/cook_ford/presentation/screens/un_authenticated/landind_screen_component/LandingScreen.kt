package com.example.cook_ford.presentation.screens.un_authenticated.landind_screen_component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.DefaultBackArrow
import com.example.cook_ford.presentation.component.widgets.OutlinedSmallSubmitButton
import com.example.cook_ford.presentation.theme.DeepGreen
import com.example.cook_ford.presentation.theme.FontName


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    LandingScreen(navController = rememberNavController(), onNavigateToAuthenticatedRoute = {})
}

@Composable
fun LandingScreen(navController: NavController, onNavigateToAuthenticatedRoute:()-> Unit) {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 20.dp, end = 20.dp)
        .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start) {

        Row(modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.weight(0.5f)) {
                DefaultBackArrow(onClick = {
                    navController.navigateUp()
                })
            }
            Box(modifier = Modifier.weight(1.0f)) {
                Text(
                    text = "",
                    color = Color.DarkGray,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700)
                )
            }
        }


        Spacer(modifier = Modifier.height(60.dp))

        Text(text = "Cook Ford",
            fontFamily = FontName,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            fontSize = 30.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Best place to find cook for home",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.chef_cooking),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Hire Cooks Direct No Commission",
            fontSize = 14.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedSmallSubmitButton(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            textColor = Color.Gray,
            text = stringResource(id = R.string.sign_in_with_phone_button_text),
            isLoading = false,
            onClick = { onNavigateToAuthenticatedRoute.invoke() }
        )

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {

            HorizontalDivider(modifier = Modifier.weight(1f))

            Text(text = "or",
                fontSize = 15.sp,
                fontFamily = FontName,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 10.dp),)

            HorizontalDivider(modifier = Modifier.weight(1f))
        }

        OutlinedSmallSubmitButton(
            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
            textColor = Color.Gray,
            text = stringResource(id = R.string.sign_in_as_a_cook_button_text),
            isLoading = false,
            onClick = { /*onSubmit*/ }
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.DarkGray)) {
                    append("By signing in you agree to our ")
                }
                withStyle(style = SpanStyle(color = DeepGreen)) {
                    append("terms of service and privacy policy")
                }
            },
            modifier = Modifier.wrapContentSize()
                .align(Alignment.CenterHorizontally)
                .padding(start = 10.dp, end = 10.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}