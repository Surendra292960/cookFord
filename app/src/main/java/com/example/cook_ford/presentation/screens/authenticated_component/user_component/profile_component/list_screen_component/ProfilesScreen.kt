package com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.list_screen_component
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.CuisineSlotComponent
import com.example.cook_ford.presentation.component.widgets.Child
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.component.widgets.OutlinedSubmitButton
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.component.widgets.SmallTitleText
import com.example.cook_ford.presentation.component.widgets.TitleText
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.list_screen_component.ProfileViewModel.Companion.isWelcomeBottomSheetDisplayed
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.list_screen_component.state.ProfileState
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.Green
import com.example.cook_ford.presentation.theme.LightGray_2
import com.example.cook_ford.presentation.theme.LightGreen
import com.example.cook_ford.presentation.theme.OrangeYellow1
import com.example.cook_ford.utils.AppConstants
import com.example.cook_ford.utils.AppConstants.EMPTY_STRING
import com.google.gson.Gson

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    UsersProfileList(
        index = 0, onItemClick = {}, profileState = ProfileState()
    )
}


data class WelcomeModelData(@DrawableRes val leadingIcon: Int?=null, @DrawableRes val trailingIcon: Int?=null, val isBorder:Boolean = false, val title: String = EMPTY_STRING, val subTitle: String = EMPTY_STRING)

val welcomeBottomSheetData = mutableListOf(
    WelcomeModelData(
        title = "A little about the process before you continue."
    ),
    WelcomeModelData(
        title = "Set your cook preferences."
    ),
    WelcomeModelData(
        title = "You will be shown a list of cooks and chef who matches your selection."
    ),
    WelcomeModelData(
        title = "Then contact the cook to discuss the requirements and charges."
    ),
    WelcomeModelData(
        title = "Conduct a background verification for the cook prior to hiring(optional but recommended).",
    )
)


@Composable
fun ProfilesScreen(
    navController: NavHostController,
    profileLazyListState: LazyListState = rememberLazyListState(),
    onNavigateToProfileDetails: () -> Unit) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val profileState by remember { profileViewModel.profileState }
    var isShowWelcomeBottomSheet by remember { mutableStateOf(isWelcomeBottomSheetDisplayed) }
    Log.d("TAG", "ProfileListScreen isLoading: ${profileState.isLoading}")

    Box(modifier = Modifier
        .background(Color.White)
        .fillMaxSize()) {
        if (profileState.isSuccessful) {
            Log.d("TAG", "ProfileListScreen getResponseFromPref : ${profileViewModel.getUserType()}")

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = profileLazyListState,
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(AppTheme.dimens.paddingSmall),
                content = {

                    Log.d("TAG", "ProfileListScreens : ${profileState.profile?.size}")
                    profileState.profile?.size?.let { size ->
                        items(size) { index ->

                            if (!profileState.profile!![index].userType.equals(AppConstants.USER, ignoreCase = true)){
                                Log.d("TAG", "ProfileListScreens userType : ${profileState.profile!![index].userType}")
                                UsersProfileList(
                                    profileState = profileState,
                                    index = index,
                                    onItemClick = {
                                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                                            set(
                                                "profileResponse",
                                                Gson().toJson(profileState.profile!![index])
                                            )
                                        }
                                        onNavigateToProfileDetails.invoke()
                                    }
                                )
                            }
                        }
                    }
                }
            )

            if (isShowWelcomeBottomSheet) {
                BottomSheet(
                    onDismiss = {
                        isWelcomeBottomSheetDisplayed = false
                        isShowWelcomeBottomSheet = false
                    }
                )
            }
        }
        else{
            Progressbar(profileState.isLoading)
        }
    }
}


@Composable
fun UsersProfileList(index: Int, onItemClick: (String) -> Unit, profileState: ProfileState) {
    Log.d("TAG", "UsersProfileList userType : ${profileState.profile!![index].userType}")
    ElevatedCard(modifier = Modifier
        .clickable { profileState.profile[index]._id?.let { onItemClick(it) } }
        .padding(bottom = 10.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingTooSmall),
        shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                contentAlignment = Alignment.Center) {
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {

                    Column(modifier = Modifier,
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {

                        // ProfilePicture
                        Box(modifier = Modifier
                            .size(80.dp)
                            .wrapContentHeight()
                            .clip(CircleShape)
                            .border(1.dp, Color.LightGray, CircleShape)
                            .background(Color.White)) {
                            ElevatedCard(
                                modifier = Modifier
                                    .background(color = Color.LightGray, shape = RectangleShape)
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .align(Alignment.Center),
                                shape = CircleShape,
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 6.dp
                                ),
                            ) {
                                if (profileState.profile[index].gender == "Female") {
                                    Image(
                                        painter = painterResource(id = R.drawable.female_chef),
                                        contentDescription = "Profile Photo",
                                        modifier = Modifier,
                                        contentScale = ContentScale.Crop,
                                    )
                                }else{
                                    Image(
                                        painter = painterResource(id = R.drawable.male_chef),
                                        contentDescription = "Profile Photo",
                                        modifier = Modifier,
                                        contentScale = ContentScale.Crop,
                                    )
                                }
                            }
                        }

                        profileState.profile[index].profile?.total_rating?.let {
                            Row(modifier = Modifier.padding(vertical = 5.dp)) {
                                Icon(Icons.Filled.Star,
                                    contentDescription = "",
                                    tint = OrangeYellow1,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .align(Alignment.CenterVertically)
                                        .padding(horizontal = 3.dp),
                                )
                                MediumTitleText(
                                    modifier = Modifier
                                        .padding(start = 5.dp, end = 5.dp),
                                    text = it.toString(),
                                    textAlign = TextAlign.Start,
                                    textColor = Color.Gray,
                                    fontWeight = FontWeight.W500
                                )
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.Start) {

                        profileState.profile[index].username?.let {

                            Row(modifier = Modifier.padding(horizontal = 2.dp, vertical = 2.dp)) {

                                MediumTitleText(
                                    modifier = Modifier,
                                    text = it,
                                    textAlign = TextAlign.Start,
                                    textColor = Color.DarkGray,
                                    fontWeight = FontWeight.W500
                                )
                                Spacer(modifier = Modifier.width(7.dp))

                                Icon(imageVector = Icons.Filled.Verified, tint = LightGreen, contentDescription = "",modifier = Modifier.size(20.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        profileState.profile[index].profile?.cuisine?.let { CuisineSlotComponent(slots = it, borderColor = LightGray_2) }

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
            BottomMenuText(
                profileState.profile[index]
            )
        }
    }
}


@Composable
fun BottomMenuText(profileRes: ProfileResponse) {
    profileRes.profile?.let {
        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

        Row(
            modifier = Modifier
                .padding(vertical = 2.dp)
                .fillMaxWidth()
                .padding(AppTheme.dimens.paddingTooSmall)
            ,  horizontalArrangement = Arrangement.SpaceBetween) {

            // Experience
            profileRes.profile.experience.let {
                Child(
                    modifier = Modifier.weight(1f),
                    title = AppConstants.EXPERIENCE,
                    text = it.toString()
                )
            }

            // Language
            profileRes.profile.language.let {
                Column(modifier = Modifier
                    .padding(bottom = AppTheme.dimens.paddingSmall)
                    .weight(1f)
                    .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    MediumTitleText(
                        modifier = Modifier,
                        text = AppConstants.LANGUAGE,
                        fontWeight = FontWeight.W500,
                        textAlign = TextAlign.Center,
                        textColor = Color.DarkGray
                    )
                    it.forEach {
                        MediumTitleText(
                            modifier = Modifier,
                            text = it,
                            fontWeight = FontWeight.W500,
                            textAlign = TextAlign.Center,
                            textColor = Color.Gray
                        )
                    }
                }
            }

            // From
            profileRes.location?.type?.let {
                Child(
                    modifier = Modifier.weight(1f),
                    title = AppConstants.FROM,
                    text = it
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    Log.d("TAG", "WelcomeBottomSheet : ")
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = null) {
        WelcomeBottomSheet(
            welcomeModelData = welcomeBottomSheetData,
            onDismiss = { onDismiss() }
        )
    }
}

@Composable
fun WelcomeBottomSheet(welcomeModelData: MutableList<WelcomeModelData>, onDismiss: () -> Unit) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(top = 20.dp)
        .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp, Alignment.CenterVertically)) {

        TitleText(
            modifier = Modifier,
            text = "Welcome to CookFord!",
            textAlign = TextAlign.Start,
            textColor = Color.DarkGray,
            fontWeight = FontWeight.ExtraBold
        )

        LazyColumn (modifier = Modifier
            .padding(AppTheme.dimens.paddingSmall),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween) {
            items(welcomeModelData.size) { index ->

                if (index == AppConstants.ZERO){
                    SmallTitleText(
                        modifier = Modifier.align(Alignment.Start),
                        text = welcomeModelData[index].title,
                        textAlign = TextAlign.Start,
                        textColor = Color.DarkGray,
                        fontWeight = FontWeight.W500
                    )
                }else{
                    Box(modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.wrapContentSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween) {

                            Box(modifier = Modifier
                                .size(25.dp)
                                .background(Green, shape = RectangleShape),
                                contentAlignment = Alignment.Center) {

                                MediumTitleText(
                                    modifier = Modifier,
                                    text = index.toString(),
                                    textAlign = TextAlign.Start,
                                    textColor = Color.White,
                                    fontWeight = FontWeight.W900
                                )

                            }

                            Column(modifier = Modifier.padding(start = 15.dp),
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center) {

                                MediumTitleText(
                                    modifier = Modifier.align(Alignment.Start),
                                    text = welcomeModelData[index].title,
                                    textAlign = TextAlign.Start,
                                    textColor = Color.DarkGray,
                                    fontWeight = FontWeight.W700
                                )
                            }
                        }
                    }
                }
            }
        }

        OutlinedSubmitButton(
            modifier = Modifier.padding(all = 20.dp),
            textColor = Color.Gray,
            text = stringResource(id = R.string.submit_button_continue),
            isLoading = false,
            onClick = { onDismiss.invoke() }
        )
    }
}
