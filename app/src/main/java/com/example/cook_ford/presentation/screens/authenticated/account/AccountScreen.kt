package com.example.cook_ford.presentation.screens.authenticated.account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.example.cook_ford.R
import com.example.cook_ford.presentation.theme.LightGreen
import com.example.cook_ford.presentation.theme.Orange
import com.example.cook_ford.presentation.theme.OrangeYellow1
import com.example.cook_ford.utils.FontName


@Composable
fun AccountScreen(
    onNavigateToEditProfile: (String) -> Unit,
    onNavigateToAddCookScreen: (String) -> Unit,
    onNavigateToPostJobScreen: () -> Unit,
    onNavigateToContactUsScreen: () -> Unit,
    onNavigateToReviewUsScreen: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {

        AccountProfileImage(onNavigateToEditProfile = { it->onNavigateToEditProfile.invoke(it) })
        HorizontalDivider(modifier = Modifier.padding(top = 10.dp), color = Color.LightGray)
        CallCreditButtons()

        AccountProfileContent(
            textColor = Orange,
            leadingIcon = Icons.Filled.Circle,
            trailingIcon = Icons.Filled.ArrowForwardIos,
            title = "Spin the Wheel",
            subtitle = "Play and Win Call Credits and Documents",
            tintColor = Orange,
            trailingIconSize = 17.dp,
            leadingIconSize = 30.dp,
            navigationRoute = "Spin",
            onNavigateTo = { route->
                when (route) {
                    "Spin" -> {
                        onNavigateToPostJobScreen.invoke()
                    }
                    "AddCook" -> {
                        onNavigateToAddCookScreen.invoke("dtrfjikol")
                    }
                    "ContactUs" -> {
                        onNavigateToContactUsScreen.invoke()
                    }
                    "ReviewUs" -> {
                        onNavigateToReviewUsScreen.invoke()
                    }
                }
            },
        )
        Spacer(modifier = Modifier.height(10.dp))
        AccountProfileContent(
            textColor = Color.DarkGray,
            leadingIcon = Icons.Filled.NotificationsActive,
            trailingIcon = Icons.Filled.ArrowForwardIos,
            title = "Post Job",
            subtitle = "Get Notification when any cook shows interest",
            tintColor = Color.DarkGray,
            trailingIconSize = 17.dp,
            leadingIconSize = 30.dp,
            navigationRoute = "Job",
            onNavigateTo = { route->
                when (route) {
                    "Spin" -> {
                        onNavigateToPostJobScreen.invoke()
                    }
                    "AddCook" -> {
                        onNavigateToAddCookScreen.invoke("dtrfjikol")
                    }
                    "ContactUs" -> {
                        onNavigateToContactUsScreen.invoke()
                    }
                    "ReviewUs" -> {
                        onNavigateToReviewUsScreen.invoke()
                    }
                }
            },
        )
        Spacer(modifier = Modifier.height(10.dp))
        AccountProfileContent(
            textColor = Color.DarkGray,
            leadingIcon = Icons.Filled.PersonAdd,
            trailingIcon = Icons.Filled.ArrowForwardIos,
            title = "Add Your Cook",
            subtitle = "",
            tintColor = Color.DarkGray,
            trailingIconSize = 17.dp,
            leadingIconSize = 30.dp,
            navigationRoute = "AddCook",
            onNavigateTo = { route->
                when (route) {
                    "Spin" -> {
                        onNavigateToPostJobScreen.invoke()
                    }
                    "AddCook" -> {
                        onNavigateToAddCookScreen.invoke("dtrfjikol")
                    }
                    "ContactUs" -> {
                        onNavigateToContactUsScreen.invoke()
                    }
                    "ReviewUs" -> {
                        onNavigateToReviewUsScreen.invoke()
                    }
                }
            },
        )
        Spacer(modifier = Modifier.height(10.dp))
        AccountProfileContent(
            textColor = Color.DarkGray,
            leadingIcon = Icons.Filled.People,
            trailingIcon = Icons.Filled.ArrowForwardIos,
            title = "Tell Your friends and family",
            subtitle = "",
            tintColor = Color.DarkGray,
            trailingIconSize = 17.dp,
            leadingIconSize = 30.dp,
            navigationRoute = "",
            onNavigateTo = {},
        )
        Spacer(modifier = Modifier.height(10.dp))
        AccountProfileContent(
            textColor = Color.DarkGray,
            leadingIcon = Icons.Filled.ContactPhone,
            trailingIcon = Icons.Filled.Email,
            title = "Contact Us",
            subtitle = "",
            tintColor = Color.DarkGray,
            trailingIconSize = 25.dp,
            leadingIconSize = 30.dp,
            navigationRoute = "ContactUs",
            onNavigateTo = { route->
                when (route) {
                    "Spin" -> {
                        onNavigateToPostJobScreen.invoke()
                    }
                    "AddCook" -> {
                        onNavigateToAddCookScreen.invoke("dtrfjikol")
                    }
                    "ContactUs" -> {
                        onNavigateToContactUsScreen.invoke()
                    }
                    "ReviewUs" -> {
                        onNavigateToReviewUsScreen.invoke()
                    }
                }
            },
        )
        Spacer(modifier = Modifier.height(10.dp))
        AccountProfileContent(
            textColor = Color.DarkGray,
            leadingIcon = Icons.Filled.Reviews,
            trailingIcon = Icons.Filled.ArrowForwardIos,
            title = "Review us",
            subtitle = "Good or bad. we are listening",
            tintColor = Color.DarkGray,
            trailingIconSize = 17.dp,
            leadingIconSize = 30.dp,
            navigationRoute = "ReviewUs",
            onNavigateTo = { route->
                when (route) {
                    "Spin" -> {
                        onNavigateToPostJobScreen.invoke()
                    }
                    "AddCook" -> {
                        onNavigateToAddCookScreen.invoke("dtrfjikol")
                    }
                    "ContactUs" -> {
                        onNavigateToContactUsScreen.invoke()
                    }
                    "ReviewUs" -> {
                        onNavigateToReviewUsScreen.invoke()
                    }
                }
            },
        )
        HorizontalDivider(modifier = Modifier.padding(top = 10.dp), color = Color.Gray)

        AccountProfileContent(
            textColor = Color.DarkGray,
            leadingIcon = Icons.Filled.Person,
            trailingIcon = Icons.Filled.ArrowForwardIos,
            title = "SignIn as Cook",
            subtitle = "Good or bad. we are listening",
            tintColor = Color.DarkGray,
            trailingIconSize = 17.dp,
            leadingIconSize = 30.dp,
            navigationRoute = "SignInAsCook",
            onNavigateTo = { route->
                when (route) {
                    "Spin" -> {
                        onNavigateToPostJobScreen.invoke()
                    }
                    "AddCook" -> {
                        onNavigateToAddCookScreen.invoke("dtrfjikol")
                    }
                    "ContactUs" -> {
                        onNavigateToContactUsScreen.invoke()
                    }
                    "ReviewUs" -> {
                        onNavigateToReviewUsScreen.invoke()
                    }
                }
            },
        )

        FooterStatus()
    }
}

@Composable
fun CallCreditButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(modifier = Modifier) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.ExposureZero,
                    contentDescription = null,
                    tint = OrangeYellow1,
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = "call credit  \navailable",
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 14.sp,
                    color = Orange,
                    fontFamily = FontName,
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        Column(modifier = Modifier) {
            ExtendedFloatingActionButton(
                backgroundColor = LightGreen,
                onClick = { },
                icon = { Icon(Icons.Filled.Cookie, contentDescription = "") },
                text = {
                    Text(
                        text = "By call credits  \n(View plans)",
                        fontFamily = FontName,
                        fontWeight = FontWeight.Medium
                    )
                },
            )
        }
    }
}


@Composable
fun AccountProfileImage(onNavigateToEditProfile: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            ) {
                Card(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .align(Alignment.Center),
                    shape = CircleShape,
                    elevation = 2.dp,
                    border = BorderStroke(1.dp, Color.LightGray)
                ) {
                    /* if (changeProfileState.value == "Female") {
                         Image(
                             painter = painterResource(id = R.drawable.female_chef),
                             contentDescription = "Profile Photo",
                             modifier = Modifier,
                             contentScale = ContentScale.Crop,
                         )
                     }else*/{

                }

                    Image(
                        painter = painterResource(id = R.drawable.male_chef),
                        contentDescription = "Profile Photo",
                        modifier = Modifier,
                        contentScale = ContentScale.Crop,
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_verified),
                    tint = Color.Green,
                    contentDescription = "Check mark",
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }

            Column(
                modifier = Modifier.padding(start = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome Back",
                    color = Color.Gray,
                    fontSize = 20.sp,
                    fontFamily = FontName,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    text = "William",
                    color = Color.DarkGray,
                    fontSize = 20.sp,
                    fontFamily = FontName,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }

        Icon(
            Icons.Filled.Edit,
            contentDescription = "",
            tint = Color.Gray,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .clickable { onNavigateToEditProfile.invoke("dtrfjikol") }
        )
    }
}

@Composable
fun AccountProfileContent(
    textColor: Color,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector,
    title: String,
    subtitle: String,
    tintColor: Color,
    trailingIconSize: Dp,
    leadingIconSize: Dp,
    navigationRoute: String,
    onNavigateTo: (String) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                .clickable {onNavigateTo.invoke(navigationRoute)},
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                leadingIcon,
                tint = tintColor,
                contentDescription = ""
            )

            Column(
                modifier = Modifier.padding(start = 15.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = textColor,
                    fontSize = 15.sp,
                    fontFamily = FontName,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(Alignment.Start)
                )
                if (subtitle.isNotEmpty()) {
                    Text(
                        text = subtitle,
                        color = textColor,
                        fontSize = 13.sp,
                        fontFamily = FontName,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
            }
        }

        Icon(
            trailingIcon,
            contentDescription = "",
            tint = Color.DarkGray,
            modifier = Modifier
                .size(trailingIconSize)
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun FooterStatus() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Text(
            text = "Terms of Use",
            color = Color.Gray,
            fontSize = 12.sp,
            fontFamily = FontName,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle2
        )

        Text(
            text = "Privacy Policy",
            color = Color.Gray,
            fontSize = 12.sp,
            fontFamily = FontName,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle2
        )

        Text(
            text = "License",
            color = Color.Gray,
            fontSize = 12.sp,
            fontFamily = FontName,
            fontWeight = FontWeight.Normal,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAccountScreen() {
    AccountScreen(
        onNavigateToEditProfile = {},
        onNavigateToAddCookScreen = {},
        onNavigateToPostJobScreen = {},
        onNavigateToContactUsScreen = {},
        onNavigateToReviewUsScreen = {}
    )
}