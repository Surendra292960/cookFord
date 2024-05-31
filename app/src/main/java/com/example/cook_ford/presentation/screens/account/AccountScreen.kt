package com.example.cook_ford.presentation.screens.account
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.ContactPhone
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExposureZero
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Reviews
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.example.cook_ford.R
import com.example.cook_ford.presentation.theme.LightGreen

@Composable
fun AccountScreen() {

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally){
        AccountProfileImage()
        HorizontalDivider(modifier = Modifier.padding(top = 10.dp), color = Color.LightGray)
        CallCreditButtons()
        AccountProfileContent(
            textColor = Color.Red,
            leadingIcon = Icons.Filled.Circle,
            trailingIcon = Icons.Filled.ArrowForwardIos,
            title = "Spin the Wheel",
            subtitle = "Play and Win Call Credits and Documents"
        )
        AccountProfileContent(
            textColor = Color.Gray,
            leadingIcon = Icons.Filled.NotificationsActive,
            trailingIcon = Icons.Filled.ArrowForwardIos,
            title = "Post Job",
            subtitle = "Get Notification when any cook shows interest"
        )
        AccountProfileContent(
            textColor = Color.Gray,
           leadingIcon = Icons.Filled.PersonAdd,
            trailingIcon = Icons.Filled.ArrowForwardIos,
            title = "Add Network",
            subtitle = ""
        )
        AccountProfileContent(
            textColor = Color.Gray,
            leadingIcon = Icons.Filled.People,
            trailingIcon = Icons.Filled.ArrowForwardIos,
            title = "Tell Your friends and family",
            subtitle = ""
        )
        AccountProfileContent(
            textColor = Color.Gray,
            leadingIcon = Icons.Filled.ContactPhone,
            trailingIcon = Icons.Filled.Email,
            title = "Contact Us",
            subtitle = ""
        )
        AccountProfileContent(
            textColor = Color.Gray,
            leadingIcon = Icons.Filled.Reviews,
            trailingIcon = Icons.Filled.ArrowForwardIos,
            title = "Review us",
            subtitle = "Good or bad. we are listening"
        )
        HorizontalDivider(modifier = Modifier.padding(top = 10.dp), color = Color.Gray)

        AccountProfileContent(
            textColor = Color.Gray,
            leadingIcon = Icons.Filled.Person,
            trailingIcon = Icons.Filled.ArrowForwardIos,
            title = "Login as Cook",
            subtitle = "Good or bad. we are listening"
        )

        FooterStatus()
    }
}

@Composable
fun CallCreditButtons() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

       Column(modifier = Modifier) {
           Row(horizontalArrangement = Arrangement.spacedBy(10.dp,
               Alignment.Start),
               verticalAlignment = Alignment.CenterVertically){
               Icon(
                   Icons.Filled.ExposureZero,
                   contentDescription = null,
                   tint = Color.Red,
                   modifier = Modifier.size(40.dp)
               )
               Text(
                   text = "call credit  \navailable",
                   style = MaterialTheme.typography.subtitle2,
                   fontSize = 14.sp,
                   color = Color.Red
               )
           }
       }

     Column(modifier = Modifier) {
         ExtendedFloatingActionButton(
             backgroundColor = LightGreen,
             onClick = { },
             icon = { Icon(Icons.Filled.Cookie, contentDescription = "") },
             text = { Text(text = "By call credits  \n(View plans)") },
         )
     }
   }
}

@Composable
fun FooterStatus() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,) {

        Text(
            text = "Terms of Use",
            color = Color.Gray,
            fontSize = 12.sp,
            style = MaterialTheme.typography.subtitle2
        )

        Text(
            text = "Privacy Policy",
            color = Color.Gray,
            fontSize = 12.sp,
            style = MaterialTheme.typography.subtitle2
        )

        Text(
            text = "License",
            color = Color.Gray,
            fontSize = 12.sp,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
fun AccountProfileImage() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)){
        Row(modifier = Modifier
            .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Box (modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)){
                Image(
                    painterResource(id = R.drawable.ic_chef_round),
                    contentDescription = "Artist image"
                )
                Icon(painter = painterResource(id = R.drawable.ic_verified) ,
                    tint = Color.Green,
                    contentDescription = "Check mark",
                    modifier = Modifier.align(Alignment.BottomCenter))
            }

            Column(
                modifier = Modifier.padding(start = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center) {
                Text(
                    text = "Welcome Back",
                    color = Color.LightGray,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    text = "William",
                    color = Color.Gray,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }

        Icon(Icons.Filled.Edit,
            contentDescription = "",
            tint = Color.Gray,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp))
    }
}

@Composable
fun AccountProfileContent(
    textColor: Color,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector,
    title: String,
    subtitle: String
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)){
    Row(modifier = Modifier
        .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {

        Icon(leadingIcon ,
            tint = Color.DarkGray,
            contentDescription = "")

        Column(
            modifier = Modifier.padding(start = 15.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = textColor,
                fontSize = 15.sp,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.align(Alignment.Start)
            )
              if (subtitle.isNotEmpty()){
                  Text(
                      text = subtitle,
                      color = textColor,
                      fontSize = 12.sp,
                      style = MaterialTheme.typography.subtitle2,
                      modifier = Modifier.align(Alignment.Start)
                  )
              }
        }
    }

    Icon(trailingIcon,
        contentDescription = "",
        tint = Color.Gray,
        modifier = Modifier
            .size(20.dp)
            .align(Alignment.CenterEnd))
}
}
@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    AccountScreen()
}