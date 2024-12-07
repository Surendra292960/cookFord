package com.example.cook_ford.presentation.route.bottom_nav

import android.util.Log
import com.example.cook_ford.presentation.route.bottom_nav.utils.NavConst
import com.example.cook_ford.presentation.route.bottom_nav.utils.NavPath
import com.example.cook_ford.presentation.route.bottom_nav.utils.NavTitle

fun getTitleByRoute(route:String): String {
    Log.d("TAG", "getTitleByRoute: $route")

    return when (route) {
        //User Home Navigation
        NavConst.HOME -> NavTitle.HOME
        NavConst.SEARCH -> NavTitle.SEARCH
        NavConst.ACCOUNT -> NavTitle.ACCOUNT
        NavConst.PROFILE_DETAILS -> NavTitle.PROFILE_DETAILS
        NavConst.REVIEW -> NavTitle.REVIEW
        NavConst.REPORT -> NavTitle.REPORT
        NavConst.MESSAGE -> NavTitle.MESSAGE
        NavConst.UPDATE_PROFILE -> NavTitle.UPDATE_PROFILE
        NavConst.ADD_COOK_PROFILE -> NavTitle.ADD_COOK_PROFILE
        NavConst.CALL_CREDIT -> NavTitle.CALL_CREDIT
        NavConst.POST_JOB -> NavTitle.POST_JOB
        NavConst.COOK_PREFERENCES -> NavTitle.COOK_PREFERENCES

        //Cook Home Navigation
        NavConst.UPDATE_COOK_PROFILE -> NavTitle.UPDATE_COOK_PROFILE
        NavConst.MANAGE_COOK_ACCOUNT -> NavTitle.MANAGE_COOK_ACCOUNT
        NavConst.COOK_PROFILE_DETAILS -> NavTitle.COOK_PROFILE_DETAILS
        NavConst.COOK_REVIEW -> NavTitle.COOK_REVIEW
        NavConst.COOK_REPORT -> NavTitle.COOK_REPORT
        NavConst.COOK_MESSAGE -> NavTitle.COOK_MESSAGE
        NavConst.ADD_COOK_PREFERENCES -> NavTitle.ADD_COOK_PREFERENCES
        NavConst.UPLOAD_CUISINES -> NavTitle.UPLOAD_CUISINES

        // other cases
        else -> NavTitle.HOME
    }
}