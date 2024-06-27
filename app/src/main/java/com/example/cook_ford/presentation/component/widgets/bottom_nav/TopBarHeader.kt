package com.example.cook_ford.presentation.component.widgets.bottom_nav

import com.example.cook_ford.presentation.route.bottom_nav.NavTitle
import com.example.cook_ford.utils.AppConstants

fun getTitleByRoute(route:String): String {
    return when (route) {
        AppConstants.HOME -> NavTitle.HOME
        AppConstants.SEARCH -> NavTitle.SEARCH
        AppConstants.PROFILE -> NavTitle.PROFILE
        AppConstants.EDIT_PROFILE -> NavTitle.EDIT_PROFILE
        AppConstants.ADD_COOK_PROFILE -> NavTitle.ADD_COOK_PROFILE
        AppConstants.ACCOUNT_NAVIGATION -> NavTitle.ACCOUNT_NAVIGATION
        AppConstants.PROFILE_ACCOUNT -> NavTitle.PROFILE_ACCOUNT
        AppConstants.CALL_CREDIT -> NavTitle.CALL_CREDIT
        AppConstants.POST_JOB -> NavTitle.POST_JOB
        AppConstants.COOK_PREFERENCES -> NavTitle.COOK_PREFERENCES

        // other cases
        else -> NavTitle.HOME
    }
}