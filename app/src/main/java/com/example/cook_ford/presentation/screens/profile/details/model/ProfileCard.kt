package com.example.cook_ford.presentation.screens.profile.details.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cook_ford.R

@Composable
fun ProfileCard() {
    Card(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(40.dp),
        shape = RoundedCornerShape(22.dp),
        elevation = 4.dp
    ) {
        Column(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(40.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(id = R.drawable.slide_1),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .shadow(
                        elevation = 20.dp,
                        shape = CircleShape,
                        clip = true
                    )
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.profile), style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    lineHeight = 20.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "ANDROID ENGINEER", style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 20.sp
                ), color = Color.Gray
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Works in", style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    lineHeight = 20.sp
                ), color = if(!isSystemInDarkTheme()) Blue else Color.Gray
            )

            Text(
                text = "Testbook.com", style = TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp,
                    lineHeight = 20.sp,
                    textDecoration = TextDecoration.Underline
                ), color  = if(!isSystemInDarkTheme()) Blue else Color.Gray
            )

            Spacer(modifier = Modifier.height(30.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_note),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.fstep_one),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Techie | Fitness freak | UI/UX lover | Blogger", style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    lineHeight = 20.sp
                )
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    ProfileCard()
}