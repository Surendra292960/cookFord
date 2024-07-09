package com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.details_screen_component.model

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.AbstractComposeView
import androidx.navigation.NavHostController
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.details_screen_component.ProfileDetailScreen

class ProfileCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AbstractComposeView(context, attrs, defStyleAttr) {


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun Content() {
        ProfileDetailScreen(
            navController = NavHostController(context),
            onNavigateBack = {},
            profileResponse = ProfileResponse(),
            onNavigateToReViewScreen = {},
            onNavigateToReportScreen = {},
            onNavigateToAuthenticatedHomeRoute = {},
            onNavigateToCallCreditScreen = {},
            onNavigateToMessageScreen = {},)
    }

    fun capture(view: ProfileCardView) {
        val bitmap = ImageUtils.generateShareImage(view)
        ShareUtils.shareImageToOthers(context,"test", bitmap)
    }

}