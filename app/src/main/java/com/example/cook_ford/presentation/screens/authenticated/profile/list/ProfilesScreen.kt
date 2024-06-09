package com.example.cook_ford.presentation.screens.authenticated.profile.list

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.component.widgets.Child
import com.example.cook_ford.presentation.component.widgets.Progressbar
import com.example.cook_ford.presentation.screens.authenticated.profile.list.state.ProfileState
import com.example.cook_ford.presentation.theme.AppTheme
import com.example.cook_ford.presentation.theme.OrangeYellow1
import com.google.gson.Gson

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    UsersProfileList(
        index = 0, onItemClick = {}, profileState = ProfileState()
    )
}

data class ProfileData(var firstName:String="Surendra", var lastName:String="Kumar")
@Composable
fun ProfilesScreen(
    navController: NavHostController,
    profileLazyListState: LazyListState = rememberLazyListState(),
    onNavigateToProfileDetails: (String) -> Unit) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val profileState by remember { profileViewModel.profileState }

    Progressbar(profileState.isLoading)
    //Log.d("TAG", "ProfileListScreen isLoading: ${profileState.isLoading}")
    Log.d("TAG", "ProfileListScreen isLoading: ${profileViewModel.getResponseFromPref()}")
    LaunchedEffect(Unit) {
        profileViewModel.getProfileRequest()

    }

    if (profileState.isSuccessful) {
        val data = ProfileData(firstName = "Surendra", lastName = "Kumar")
        Log.d("TAG", "ProfileListScreen getResponseFromPref: ${profileViewModel.getResponseFromPref()}")
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = profileLazyListState,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(AppTheme.dimens.paddingSmall,),
            content = {
                Log.d("TAG", "ProfileListScreens : ${profileState.profile?.size}")
                items(profileState.profile!!.size) { index ->
                    UsersProfileList(
                        profileState = profileState,
                        index = index,
                        onItemClick = { profileId ->
                            navController.currentBackStackEntry?.savedStateHandle?.apply { set("profileResponse", Gson().toJson(profileState.profile!![index])) }
                            onNavigateToProfileDetails.invoke(profileId)
                        }
                    )
                }
            }
        )
    }
}


@Composable
fun UsersProfileList(index: Int, onItemClick: (String) -> Unit, profileState: ProfileState) {

        ElevatedCard(modifier = Modifier
            .clickable { profileState.profile?.get(index)?._id?.let { onItemClick(it) } }
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
                            .background(Color.White)) {
                            Card(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .align(Alignment.Center),
                                shape = CircleShape,
                                elevation = 2.dp,
                                border = BorderStroke(1.dp, Color.LightGray)
                            ) {
                                if (profileState.profile?.get(index)?.gender == "Female") {
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

                        profileState?.profile?.get(index)?.profile?.total_rating?.let {
                            Row {
                                Icon(Icons.Filled.Star,
                                    contentDescription = "",
                                    tint = OrangeYellow1,
                                    modifier = Modifier
                                        .size(20.dp)
                                        .align(Alignment.CenterVertically)
                                        .padding(horizontal = 3.dp),
                                )
                                Text(text = it.toString(),
                                    style = MaterialTheme.typography.subtitle2,
                                    textAlign = TextAlign.Center,
                                    color = Color.DarkGray
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

                        profileState?.profile?.get(index)?.username?.let {

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = it,
                                    //fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.subtitle2,
                                    modifier = Modifier.align(Alignment.CenterVertically),
                                    color = Color.DarkGray
                                )
                                Spacer(modifier = Modifier.width(7.dp))
                                Icon(imageVector = Icons.Filled.Verified, tint = Color.Green, contentDescription = "",modifier = Modifier.size(20.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        profileState.profile?.get(index)?.profile?.cuisine?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.subtitle2,
                                modifier = Modifier
                                    .background(OrangeYellow1)
                                    .padding(start = 5.dp, end = 5.dp),
                                color = Color.DarkGray
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        profileState.profile?.get(index)?.profile?.cuisine?.let {
                            Text(
                                text = it,
                                color = Color.Gray
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
                profileState?.profile?.get(index)?.let { BottomMenuText(it) }
        }
    }
}


@Composable
fun BottomMenuText(profileRes: ProfileResponse) {
    Log.d("TAG", "BottomMenuText: ${profileRes.profile}")
    profileRes?.profile?.let {
        //if (profileRes.usertype == "provider"){
            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.paddingTooSmall)) {

                // Experience
                profileRes?.profile?.experience?.let {
                    Child(
                        modifier = Modifier.weight(1f),
                        title = "Experience",
                        text = it.toString()
                    )
                }

                // Language
                profileRes?.profile?.language?.let {
                    Child(
                        modifier = Modifier.weight(1f),
                        title = "Language",
                        text = it
                    )
                }

                // From
               profileRes?.location?.type?.let {
                   Child(
                       modifier = Modifier.weight(1f),
                       title = "From",
                       text = it.toString()
                   )
               }
           // }
        }
    }
}
