package com.example.cook_ford.presentation.screens.profile
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cook_ford.presentation.common.user_list_card.getData
import com.example.cook_ford.presentation.theme.AppTheme

@Preview
@Composable
fun ProfileListScreen(profileViewModel: ProfileViewModel = hiltViewModel()) {
    val signInState by remember { profileViewModel.profileState }
    val profileResponse by profileViewModel.profileResponse.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.getProfileRequest()
    }

    if (signInState.isSuccessful) {
        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween,
            contentPadding = PaddingValues(AppTheme.dimens.paddingSmall),
            content = {
                items(profileResponse) {
                    UsersProfile(it)
                }
            })

    }
}
