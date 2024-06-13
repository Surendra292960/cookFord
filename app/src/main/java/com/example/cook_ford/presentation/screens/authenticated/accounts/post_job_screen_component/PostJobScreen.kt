package com.example.cook_ford.presentation.screens.authenticated.accounts.post_job_screen_component

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.Text
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.widgets.DefaultIcons
import com.example.cook_ford.presentation.component.widgets.InputTextField
import com.example.cook_ford.presentation.component.widgets.KeyboardOption
import com.example.cook_ford.presentation.component.widgets.ProfileImage
import com.example.cook_ford.presentation.component.widgets.SubmitButtonAutoSize
import com.example.cook_ford.presentation.theme.FontName

@Composable
fun PostJobScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse?=null,
    onNavigateToAuthenticatedRoute: () -> Unit,
    onNavigateToCookPreferences: (String) -> Unit,){

    val postJobViewModel:PostJobViewModel = hiltViewModel()
    val changeProfileState = remember { mutableStateOf("Male") }
    val postJobState = remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally) {

        ProfileImage(changeProfileState, onChange = {})

        Text(
            text = "We will notify you when any new cook is\n available for your requirement",
            style = MaterialTheme.typography.subtitle2,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.DarkGray,
            fontFamily = FontName,
            fontWeight = FontWeight.Medium,
        )

        if (!postJobState.value) {
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "You have not posted any job",
                style = MaterialTheme.typography.subtitle2,
                fontSize = 14.sp,
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

            Spacer(modifier = Modifier.height(30.dp))

            AccountProfileContent(
                textColor = Color.DarkGray,
                leadingIcon = Icons.Filled.LocationOn,
                trailingIcon = Icons.Filled.Edit,
                isLeadingIcon = true,
                isTrailingIcon = true,
                title = "Location",
                subtitle = "Sector 62, Noida Uttar Pradesh",
                tintColor = MaterialTheme.colors.primary,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp,
                onNavigateToCookPreferences = onNavigateToCookPreferences
            )

            Spacer(modifier = Modifier.height(20.dp))

            AccountProfileContent(
                textColor = Color.DarkGray,
                leadingIcon = Icons.Filled.Cookie,
                trailingIcon = Icons.Filled.Edit,
                isLeadingIcon = true,
                isTrailingIcon = true,
                title = "Cook Preferences",
                subtitle = "Languages: [Hindi, English]\nFood Type: [Indian, Chinese]\n" +
                        "Availability: [8am-9pm]\nGender: Male\nCook type: Part time\nNumber of visits: One Visit\nExperience: 0-2 Years",
                tintColor = MaterialTheme.colors.primary,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp,
                onNavigateToCookPreferences = onNavigateToCookPreferences
            )

            Spacer(modifier = Modifier.height(20.dp))

            AccountProfileContent(
                textColor = Color.DarkGray,
                leadingIcon = Icons.Filled.People,
                trailingIcon = Icons.Filled.Edit,
                isLeadingIcon = true,
                isTrailingIcon = false,
                title = "Number of Members",
                subtitle = "",
                tintColor = MaterialTheme.colors.primary,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp,
                onNavigateToCookPreferences = onNavigateToCookPreferences
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Budget Range",
                style = MaterialTheme.typography.subtitle2,
                fontSize = 16.sp,
                color = Color.DarkGray,
                fontFamily = FontName,
                fontWeight = FontWeight.Medium,
            )

            Row(modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){

                InputTextField(
                    value = "",
                    onChange = { },
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    keyboardOptions = KeyboardOption(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number,
                        label = "Min",
                        placeholder = ""
                    ),
                    DefaultIcons(leadingIcon = Icons.Default.CurrencyRupee),
                    isError = false,
                    errorText = "",
                    maxChar = 5,
                    texColor = Color.Gray
                    /*submit = { TODO() }*/
                )
                Spacer(modifier = Modifier.width(20.dp))
                InputTextField(
                    value = "",
                    onChange = {},
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    keyboardOptions = KeyboardOption(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number,
                        label = "Max",
                        placeholder = ""
                    ),
                    DefaultIcons(leadingIcon = Icons.Default.CurrencyRupee),
                    isError = false,
                    errorText = "",
                    maxChar = 5,
                    texColor = Color.Gray
                    /*submit = { TODO() }*/
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            AccountProfileContent(
                textColor = Color.DarkGray,
                leadingIcon = Icons.Filled.People,
                trailingIcon = Icons.Filled.Edit,
                isLeadingIcon = true,
                isTrailingIcon = false,
                title = "Allow cooks to call you",
                subtitle = "Matched cooks will be able to call you on\n +919899199199",
                tintColor = MaterialTheme.colors.primary,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp,
                onNavigateToCookPreferences = onNavigateToCookPreferences
            )

            Spacer(modifier = Modifier.height(20.dp))

            AccountProfileContent(
                textColor = Color.DarkGray,
                leadingIcon = Icons.Filled.Email,
                trailingIcon = Icons.Filled.Edit,
                isLeadingIcon = true,
                isTrailingIcon = false,
                title = "Notification will be sent on",
                subtitle = "cookford@gmail.com",
                tintColor = MaterialTheme.colors.primary,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp,
                onNavigateToCookPreferences = onNavigateToCookPreferences
            )


            Spacer(modifier = Modifier.height(20.dp))

            AccountProfileContent(
                textColor = Color.DarkGray,
                leadingIcon = Icons.Filled.Email,
                trailingIcon = Icons.Filled.Edit,
                isLeadingIcon = true,
                isTrailingIcon = false,
                title = "Last updated on",
                subtitle = "7 june 2024, 09:00 AM",
                tintColor = MaterialTheme.colors.primary,
                trailingIconSize = 17.dp,
                leadingIconSize = 30.dp,
                onNavigateToCookPreferences = onNavigateToCookPreferences
            )

            Spacer(modifier = Modifier
                .height(20.dp)
                .padding(start = 20.dp, end = 20.dp))

            Text(
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                text = "Job will be auto deleted after 3 months. if not updated.For catering, Job will be auto deleted on the next day of the event.",
                style = MaterialTheme.typography.subtitle2,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                color = Color.DarkGray,
                fontFamily = FontName,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}


@Composable
fun AccountProfileContent(
    textColor: Color,
    leadingIcon: ImageVector,
    trailingIcon: ImageVector,
    isTrailingIcon: Boolean,
    isLeadingIcon:Boolean,
    title: String,
    subtitle: String,
    tintColor: Color,
    trailingIconSize: Dp,
    leadingIconSize: Dp,
    onNavigateToCookPreferences: (String) -> Unit) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp, end = 20.dp)) {
        Row(modifier = Modifier
                .wrapContentSize()
                .clickable { onNavigateToCookPreferences.invoke("qeqwdcwf") },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

           if (isLeadingIcon){
               Icon(
                   imageVector = leadingIcon,
                   tint = tintColor,
                   contentDescription = "",
               )
           }

            Column(
                modifier = Modifier.padding(start = 15.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center) {
                Text(
                    text = title,
                    color = textColor,
                    fontSize = 16.sp,
                    fontFamily = FontName,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.align(Alignment.Start))

                if (subtitle.isNotEmpty()) {
                    Text(
                        text = subtitle,
                        color = textColor,
                        fontSize = 14.sp,
                        fontFamily = FontName,
                        fontWeight = FontWeight.Normal,
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
            }
        }

        if (isTrailingIcon){
            Icon(
                imageVector = trailingIcon,
                contentDescription = "",
                tint = tintColor,
                modifier = Modifier
                    .size(trailingIconSize)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    PostJobScreen(  onNavigateBack = {}, onNavigateToAuthenticatedRoute = {}, onNavigateToCookPreferences={})
}