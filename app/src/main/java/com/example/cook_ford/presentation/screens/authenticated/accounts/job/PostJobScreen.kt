package com.example.cook_ford.presentation.screens.authenticated.accounts.job

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.ProfileImage
import com.example.cook_ford.presentation.component.widgets.SubmitButtonAutoSize
import com.example.cook_ford.presentation.theme.Orange
import com.example.cook_ford.utils.FontName

@Composable
fun PostJobScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAuthenticatedRoute: () -> Unit){

    val postJobViewModel:PostJobViewModel = hiltViewModel()
    val changeProfileState = remember { mutableStateOf("Male") }
    val postJobState = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        ProfileImage(changeProfileState, onChange = {})

        Text(
            text = "We will notify you when any new cook is\n available for your requirement",
            style = MaterialTheme.typography.subtitle2,
            fontSize = 14.sp,
            color = Color.Gray,
            fontFamily = FontName,
            fontWeight = FontWeight.Medium,
        )

        if (!postJobState.value) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "You have not posted any job",
                style = MaterialTheme.typography.subtitle2,
                fontSize = 12.sp,
                color = Color.Gray,
                fontFamily = FontName,
                fontWeight = FontWeight.Medium,
            )

            Spacer(modifier = Modifier.height(10.dp))

            SubmitButtonAutoSize(
                modifier = Modifier.padding(3.dp),
                text = stringResource(id = R.string.post_job_button_text),
                isLoading = false,
                onClick = { postJobState.value = true }
            )
        }else{

            Spacer(modifier = Modifier.height(20.dp))

            AccountProfileContent(
                textColor = Color.Gray,
                leadingIcon = Icons.Filled.LocationOn,
                trailingIcon = Icons.Filled.Edit,
                title = "Spin the Wheel",
                subtitle = "Play and Win Call Credits and Documents",
                tintColor = Color.Gray,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            AccountProfileContent(
                textColor = Color.Gray,
                leadingIcon = Icons.Filled.Mail,
                trailingIcon = Icons.Filled.Edit,
                title = "Spin the Wheel",
                subtitle = "Play and Win Call Credits and Documents",
                tintColor = Color.Gray,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp
            )

            Spacer(modifier = Modifier.height(20.dp))

            AccountProfileContent(
                textColor = Color.Gray,
                leadingIcon = Icons.Filled.Home,
                trailingIcon = Icons.Filled.Edit,
                title = "Spin the Wheel",
                subtitle = "Play and Win Call Credits and Documents",
                tintColor = Color.Gray,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp
            )
        }
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
    leadingIconSize: Dp) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .wrapContentSize()
                /*.clickable { onNavigateTo.invoke(navigationRoute)
                           }*/,
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    PostJobScreen(  onNavigateBack = {}, onNavigateToAuthenticatedRoute = {})
}