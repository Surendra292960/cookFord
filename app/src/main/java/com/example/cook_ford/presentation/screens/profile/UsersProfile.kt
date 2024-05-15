package com.example.cook_ford.presentation.screens.profile
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cook_ford.R
import com.example.cook_ford.data.remote.response.ProfileResponse
import com.example.cook_ford.presentation.common.customeComposableViews.Child
import com.example.cook_ford.presentation.common.customeComposableViews.MediumTitleText
import com.example.cook_ford.presentation.common.customeComposableViews.SubTitleText
import com.example.cook_ford.presentation.theme.AppTheme
@Composable
fun UsersProfile(profile: ProfileResponse) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(top = AppTheme.dimens.paddingSmall),
        contentAlignment = Alignment.BottomCenter) {

        ElevatedCard(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            colors = CardDefaults.cardColors(Color.White),
            elevation = CardDefaults.elevatedCardElevation(AppTheme.dimens.paddingSmall),
            shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp, topEnd = 0.dp, bottomStart = 0.dp)) {

           Column (modifier = Modifier.fillMaxSize()){

               Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                   Row(modifier = Modifier
                       .fillMaxSize(),
                       horizontalArrangement = Arrangement.Start,
                       verticalAlignment = Alignment.CenterVertically) {

                       // ProfilePicture() composable
                       Box(modifier = Modifier
                           .size(80.dp)
                           .clip(CircleShape)
                           .background(Color.White)){
                            Column (modifier = Modifier.wrapContentSize(),
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally){

                                Image(
                                    painter = painterResource(id = R.drawable.profile_circle),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.size(80.dp))
                            }
                       }

                       Spacer(modifier = Modifier.size(size = AppTheme.dimens.paddingNormal))

                       // ProfileContent() composable
                       Box(modifier = Modifier
                           .fillMaxSize()
                           .padding(end = AppTheme.dimens.paddingNormal),
                        Alignment.Center) {
                           Column(modifier = Modifier
                               .fillMaxHeight()
                               .padding(start = AppTheme.dimens.paddingNormal),
                               horizontalAlignment = Alignment.Start) {

                               //Cook Name
                               Row(modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(top = AppTheme.dimens.paddingExtraLarge)) {
                                   MediumTitleText(
                                       modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                                       text = profile?.cook?.username.toString()
                                   )
                                   Icon(Icons.Default.Verified, "", modifier = Modifier
                                       .align(Alignment.CenterVertically)
                                       .padding(AppTheme.dimens.paddingSmall))
                               }
                               //Cook Rating
                               Row(modifier = Modifier.fillMaxWidth()) {
                                   Icon(Icons.Default.Star, "", modifier = Modifier
                                       .align(Alignment.CenterVertically)
                                       .padding(end = AppTheme.dimens.paddingSmall))
                                   SubTitleText(
                                       modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall),
                                       text = profile?.rating.toString()
                                   )
                               }
                               //Cook Type
                               AssistChip(
                                   onClick = { Log.d("Assist chip", "hello world") },
                                   label = { SubTitleText(modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall), "Domestic") },
                                   leadingIcon = {
                                       Icon(
                                           Icons.Default.Fastfood,
                                           contentDescription = "Localized description",
                                           Modifier.size(AssistChipDefaults.IconSize)
                                       )
                                   }
                               )
                               //Cook Cousin
                               SubTitleText(
                                   modifier = Modifier.padding(top = AppTheme.dimens.paddingSmall, bottom = AppTheme.dimens.paddingSmall),
                                   text = profile?.cuisine.toString()
                               )
                           }
                       }
                   }
               }
           }

            HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(AppTheme.dimens.paddingSmall)) {
                // Experience
                    Child(
                        modifier = Modifier.weight(1f),
                        title = "Experience",
                        text = profile?.experience.toString()
                    )
                // Language
                    Child(
                        modifier = Modifier.weight(1f),
                        title = "Language",
                        text = profile?.language.toString()
                    )
                // From
                    Child(
                        modifier = Modifier.weight(1f),
                        title = "From",
                        text = "profile.location.type"
                    )
                }
            }
        }
    }