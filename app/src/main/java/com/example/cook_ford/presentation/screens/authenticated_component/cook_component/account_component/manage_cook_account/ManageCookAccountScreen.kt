package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_account

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.widgets.LargeText
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.component.widgets.ProfileImage
import com.example.cook_ford.presentation.component.widgets.SemiCircularArcLoaderDemo
import com.example.cook_ford.presentation.component.widgets.SmallButton
import com.example.cook_ford.presentation.component.widgets.SmallTitleText
import com.example.cook_ford.presentation.component.widgets.SubTitleText
import com.example.cook_ford.presentation.component.widgets.TitleText
import com.example.cook_ford.presentation.theme.Green
import com.example.cook_ford.presentation.theme.LightGreen
import com.example.cook_ford.presentation.theme.Orange
import com.google.gson.Gson

// List of Recommendations
val recommendations = listOf(
    "Verify profile using Aadhaar.",
    "No reviews on your profile. Ask people to add reviews on your profile.",
    "Add profile photo.",
    "Add 6 photos of your best food dishes.",
    "Add your home town/state name in your profile.",
    "Add your religion in your profile.",
    "Add time slots for part-time work."
)

// Profile Management Options
data class CookAccountModelData(
    @DrawableRes val trailingIcon: Int,
    val isBorder: Boolean = false,
    val title: String,
    var subTitle: String,
    val titleColor: Color = Color.DarkGray,
    var subTitleColor: Color = Color.Gray
)

val options = mutableListOf(
     CookAccountModelData(
         trailingIcon = R.drawable.arrow_forward_ios,
         isBorder = true,
         title = "Personal Information",
         subTitle = "Add your personal information here",
         titleColor = Color.DarkGray,
         subTitleColor = Color.Gray
     ),
    CookAccountModelData(
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = true,
        title = "View Profile",
        subTitle = "As visible to customer",
        titleColor = Color.DarkGray,
        subTitleColor = Color.Gray
    ),
    CookAccountModelData(
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = true,
        title = "Edit Profile",
        subTitle = "Last updated a month ago",
        titleColor = Color.DarkGray,
        subTitleColor = Color.Gray
    ),
    CookAccountModelData(
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = true,
        title = "Manage time slots",
        subTitle = "Add/Remove your work time slots",
        titleColor = Color.DarkGray,
        subTitleColor = Color.Gray
    ),
    CookAccountModelData(
        trailingIcon = R.drawable.arrow_forward_ios,
        isBorder = true,
        title = "Upload Images",
        subTitle = "",
        titleColor = Color.DarkGray,
        subTitleColor = Color.Gray
    )
)

@Composable
fun ManageCookAccountScreen(
    navController: NavHostController,
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse? = null,
    onNavigateToAuthenticatedRoute: () -> Unit,
    onNavigateToCookProfileDetail: () -> Unit,
    onNavigateToUploadCuisines: () -> Unit,
    onNavigateToUploadAadhaar: () -> Unit,
    onNavigateToManageTimeSlots: () -> Unit,
    onNavigateToCookJobList: () -> Unit,
    onNavigateToPersonalInfo: () -> Unit,
    onNavigateToEditCookProfile: () -> Unit,

    ) {
    val changeProfileState = remember { mutableStateOf("Male") }
    val manageCookAccountViewModel: ManageCookAccountViewModel = hiltViewModel()
    val accountState by remember { manageCookAccountViewModel.manageAccountState }
    var isEnable by remember { mutableStateOf(true) }
    val progress = 100f

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {

            Column(modifier = Modifier.fillMaxSize().weight(1f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(modifier = Modifier.height(16.dp))

                //Profile Image
                ProfileImage(modifier = Modifier.weight(1f), changeProfileState, onChange = {})

                //Profile Name
                accountState.profileResponse?.username?.let {
                    SubTitleText(
                        modifier = Modifier,
                        text = it,
                        textAlign = TextAlign.Start,
                        textColor = Color.DarkGray,
                        fontWeight = FontWeight.W700
                    )
                }
            }

            Column(modifier = Modifier.fillMaxSize().weight(1f),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally) {

                SemiCircularArcLoaderDemo(progress)
            }
        }

        // Profile Stats Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ProfileStatItem(status = "4", label = "Profile Views")
            ProfileStatItem(status = "0", label = "Customers Called")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Profile Score Card
        Card(
            colors = CardColors(
                containerColor = Orange,
                contentColor = Color.Gray,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.Gray
            ), // Red background
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Title
                SubTitleText(
                    modifier = Modifier,
                    text = "Profile score",
                    textAlign = TextAlign.Start,
                    textColor = Color.White,
                    fontWeight = FontWeight.W700
                )

                Spacer(modifier = Modifier.height(8.dp))

                recommendations.forEach { item ->
                    Spacer(modifier = Modifier.height(5.dp))
                    SmallTitleText(
                        modifier = Modifier,
                        text = "· $item",
                        textAlign = TextAlign.Start,
                        textColor = Color.White,
                        fontWeight = FontWeight.W700
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Score
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TitleText(
                        modifier = Modifier,
                        text = "28",
                        textAlign = TextAlign.Start,
                        textColor = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    TitleText(
                        modifier = Modifier,
                        text = "/100",
                        textAlign = TextAlign.Start,
                        textColor = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Aadhaar Verification Section
        Card(
            colors = CardColors(
                containerColor = LightGreen,
                contentColor = Color.Gray,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.Gray
            ),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    SubTitleText(
                        modifier = Modifier.align(Alignment.Start),
                        text = "Aadhaar verification",
                        textAlign = TextAlign.Start,
                        textColor = Color.DarkGray,
                        fontWeight = FontWeight.Bold
                    )

                    MediumTitleText(
                        modifier = Modifier.align(Alignment.Start),
                        text = "Verify your profile using Aadhaar and get more calls from customers",
                        textAlign = TextAlign.Start,
                        textColor = Color.DarkGray,
                        fontWeight = FontWeight.W200
                    )
                }
                SmallButton(
                    modifier = Modifier,
                    text = stringResource(id = R.string.apply_button_text),
                    textColor = Color.White,
                    buttonColor = Green,
                    isLoading = false,
                    onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set(
                                "profileResponse",
                                Gson().toJson(accountState.profileResponse)
                            )
                        }
                        onNavigateToUploadAadhaar.invoke()
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Looking for Work Toggle
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier, verticalArrangement = Arrangement.SpaceBetween) {

                TitleText(
                    modifier = Modifier,
                    text = "Looking for work",
                    textAlign = TextAlign.Start,
                    textColor = Color.DarkGray,
                    fontWeight = FontWeight.Medium
                )

                if (isEnable) {
                    MediumTitleText(
                        modifier = Modifier,
                        text = "Your profile is Active",
                        textAlign = TextAlign.Start,
                        textColor = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                } else {
                    MediumTitleText(
                        modifier = Modifier,
                        text = "Your profile is InActive",
                        textAlign = TextAlign.Start,
                        textColor = Color.Red,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Switch(
                checked = isEnable, // Adjust state accordingly
                onCheckedChange = { isEnable = it }
            )
        }

        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

            Column(modifier = Modifier.clickable {
                if (isEnable){
                    navController.currentBackStackEntry?.savedStateHandle?.apply {
                        set(
                            "profileResponse",
                            Gson().toJson(accountState.profileResponse)
                        )
                    }
                    onNavigateToCookJobList.invoke()
                }
            }, verticalArrangement = Arrangement.SpaceBetween) {

                TitleText(
                    modifier = Modifier,
                    text = "View Job Listings",
                    textAlign = TextAlign.Start,
                    textColor = Color.DarkGray,
                    fontWeight = FontWeight.Medium
                )

                if (isEnable) {
                    MediumTitleText(
                        modifier = Modifier,
                        text = "Available jobs as per your profile",
                        textAlign = TextAlign.Start,
                        textColor = Color.Gray,
                        fontWeight = FontWeight.Medium
                    )
                } else {
                    MediumTitleText(
                        modifier = Modifier,
                        text = "Make your profile active to view jobs",
                        textAlign = TextAlign.Start,
                        textColor = Color.Red,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                tint = Color.DarkGray,
                contentDescription = "Arrow"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        options.forEach { option ->
            ProfileOptionItem(option) { option ->
                when (option) {
                    "View Profile" -> {
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set(
                                "profileResponse",
                                Gson().toJson(accountState.profileResponse)
                            )
                        }
                        onNavigateToCookProfileDetail.invoke()
                    }
                    "Upload Images" -> {
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set(
                                "profileResponse",
                                Gson().toJson(accountState.profileResponse)
                            )
                        }
                        onNavigateToUploadCuisines.invoke()
                    }
                    "Manage time slots" -> {
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set(
                                "profileResponse",
                                Gson().toJson(accountState.profileResponse)
                            )
                        }
                        onNavigateToManageTimeSlots.invoke()
                    }
                    "Personal Information" -> {
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set(
                                "profileResponse",
                                Gson().toJson(accountState.profileResponse)
                            )
                        }
                        onNavigateToPersonalInfo.invoke()
                    }
                    "Edit Profile" -> {
                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                            set(
                                "profileResponse",
                                Gson().toJson(accountState.profileResponse)
                            )
                        }
                        onNavigateToEditCookProfile.invoke()
                    }
                }
            }
        }
    }
}


@Composable
fun ProfileStatItem(status: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LargeText(
            modifier = Modifier,
            text = status,
            textAlign = TextAlign.Start,
            textColor = Green,
            fontWeight = FontWeight.Bold
        )

        MediumTitleText(
            modifier = Modifier,
            text = label,
            textAlign = TextAlign.Start,
            textColor = Color.DarkGray,
            fontWeight = FontWeight.W500
        )
    }
}


@Composable
fun ProfileOptionItem(option: CookAccountModelData, onClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke(option.title) }
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier, verticalArrangement = Arrangement.SpaceBetween) {
            TitleText(
                modifier = Modifier,
                text = option.title,
                textAlign = TextAlign.Start,
                textColor = option.titleColor,
                fontWeight = FontWeight.Medium
            )

            MediumTitleText(
                modifier = Modifier,
                text = option.subTitle,
                textAlign = TextAlign.Start,
                textColor = option.subTitleColor,
                fontWeight = FontWeight.Medium
            )
        }
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            tint = Color.DarkGray,
            contentDescription = "Arrow"
        )
    }
}