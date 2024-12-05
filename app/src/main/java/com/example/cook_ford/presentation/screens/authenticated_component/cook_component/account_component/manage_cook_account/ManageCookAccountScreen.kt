package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_account

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cook_ford.data.remote.profile_response.ProfileResponse

@Composable
fun ManageCookAccountScreen(
    onNavigateBack: () -> Unit,
    profileResponse: ProfileResponse? = null,
    onNavigateToAuthenticatedRoute: () -> Unit
) {
    Text(text = "Manage Cook Account Screen")
}