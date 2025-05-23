package com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.post_job_screen_component

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.CurrencyRupee
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.cook_ford.presentation.component.widgets.OutlinedSmallSubmitButton
import com.example.cook_ford.presentation.component.widgets.ProfileImage
import com.example.cook_ford.presentation.component.widgets.AutoSizeButton
import com.example.cook_ford.presentation.theme.FontName
import com.example.cook_ford.presentation.theme.Green

@Composable
fun PostJobScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse?=null,
    onNavigateToAuthenticatedRoute: () -> Unit,
    onNavigateToCookPreferences: (String) -> Unit){

    val postJobViewModel:PostJobViewModel = hiltViewModel()
    val changeProfileState = remember { mutableStateOf("Male") }
    val postJobState = remember { mutableStateOf(false) }

    Column(modifier = Modifier.background(Color.White).fillMaxSize()) {

        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(all = 5.dp)
            .weight(1f, false), horizontalAlignment = Alignment.CenterHorizontally) {

            ProfileImage(modifier = Modifier, changeProfileState, onChange = {})

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

                AutoSizeButton(
                    modifier = Modifier.padding(3.dp),
                    text = stringResource(id = R.string.post_job_button_text),
                    buttonColor = Green,
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
                    tintColor = Color.Gray,
                    trailingIconSize = 22.dp,
                    leadingIconSize = 30.dp,
                    onNavigateTo = onNavigateToCookPreferences
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
                    tintColor = Color.Gray,
                    trailingIconSize = 22.dp,
                    leadingIconSize = 30.dp,
                    onNavigateTo = onNavigateToCookPreferences
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
                    tintColor = Color.Gray,
                    trailingIconSize = 22.dp,
                    leadingIconSize = 30.dp,
                    onNavigateTo = {  }
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

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween){

                    InputTextField(
                        value = "",
                        onChange = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
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
                        textColor = Color.Gray
                        /*submit = { TODO() }*/
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    InputTextField(
                        value = "",
                        onChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
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
                        textColor = Color.Gray
                        /*submit = { TODO() }*/
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                AccountProfileContent(
                    textColor = Color.DarkGray,
                    leadingIcon = Icons.Filled.People,
                    trailingIcon = Icons.Filled.CheckBoxOutlineBlank,
                    isLeadingIcon = true,
                    isTrailingIcon = true,
                    title = "Allow cooks to call you",
                    subtitle = "Matched cooks will be able to call you on\n +91".plus(postJobViewModel.phoneNumber),
                    tintColor = Color.Gray,
                    trailingIconSize = 22.dp,
                    leadingIconSize = 30.dp,
                    allowToCall = true,
                    onNavigateTo = {

                    },
                    onCheckedChange = {
                        Log.d("TAG", "PostJobScreen ttt : $it")
                    }
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
                    tintColor = Color.Gray,
                    trailingIconSize = 22.dp,
                    leadingIconSize = 30.dp,
                    onNavigateTo = {  }
                )


                Spacer(modifier = Modifier.height(20.dp))

                AccountProfileContent(
                    textColor = Color.DarkGray,
                    leadingIcon = Icons.Filled.Update,
                    trailingIcon = Icons.Filled.Edit,
                    isLeadingIcon = true,
                    isTrailingIcon = false,
                    title = "Last updated on",
                    subtitle = "7 june 2024, 09:00 AM",
                    tintColor = Color.Gray,
                    trailingIconSize = 22.dp,
                    leadingIconSize = 30.dp,
                    onNavigateTo = {  }
                )
            }

            if (postJobState.value) {
                Spacer(modifier = Modifier.height(20.dp))
                FooterStatus()
            }
        }

        if (postJobState.value){
            Column(modifier = Modifier.background(Color.White)){
                HorizontalDivider(modifier = Modifier.height(1.dp))
                Spacer(modifier = Modifier.height(10.dp))

                Row (modifier = Modifier
                    .background(Color.White)
                    .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){

                    Text(
                        modifier = Modifier
                            .weight(0.4f),
                        text = "Delete Job",
                        style = MaterialTheme.typography.subtitle2,
                        fontFamily = FontName,
                        fontSize = 17.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.W600,
                        color = Color.Red
                    )
                    Spacer(modifier = Modifier.width(50.dp))
                    OutlinedSmallSubmitButton(
                        modifier = Modifier
                            .weight(0.6f),
                        textColor = Color.Gray,
                        text = "Update Job",
                        isLoading = false,
                        onClick = { /*onSubmit*/ }
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun FooterStatus() {
   Row(modifier = Modifier
       .background(Color.Transparent)
       .padding(all = 15.dp),
       verticalAlignment = Alignment.CenterVertically,
       horizontalArrangement = Arrangement.SpaceBetween) {
       Text(
           text = "Job will be auto deleted after 3 months. if not updated.For catering, Job will be auto deleted on the next day of the event.",
           style = MaterialTheme.typography.subtitle2,
           fontSize = 15.sp,
           textAlign = TextAlign.Start,
           color = Color.Gray,
           fontFamily = FontName,
           fontWeight = FontWeight.Medium,
       )
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
    onNavigateTo: (String) -> Unit,
    onCheckedChange: (Boolean)->Unit = {},
    allowToCall: Boolean = false) {

    var checked by remember { mutableStateOf(true) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(all = 10.dp)) {
        Row(modifier = Modifier
            .wrapContentSize()
            .clickable { onNavigateTo.invoke("Test") },
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle2,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    fontFamily = FontName,
                    fontWeight = FontWeight.Medium,
                )
                if (subtitle.isNotEmpty()) {

                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                        color = Color.DarkGray,
                        fontFamily = FontName,
                        fontWeight = FontWeight.Normal,
                    )
                }
            }
        }

        if (isTrailingIcon){
            if (allowToCall){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .size(trailingIconSize)
                        .align(Alignment.CenterEnd)) {
                    Checkbox(
                        colors = CheckboxDefaults.colors(Color.Gray),
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                            onCheckedChange.invoke(it)
                        }
                    )
                }
            }else{
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
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewScreen(){
    PostJobScreen(  onNavigateBack = {}, onNavigateToAuthenticatedRoute = {}, onNavigateToCookPreferences={})
}