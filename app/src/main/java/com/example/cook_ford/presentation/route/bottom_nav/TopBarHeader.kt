package com.example.cook_ford.presentation.route.bottom_nav

import android.util.Log
import com.example.cook_ford.presentation.route.bottom_nav.utils.NavConst
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

        // other cases
        else -> NavTitle.HOME
    }
}