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
        NavConst.UPLOAD_CUISINES -> NavTitle.UPLOAD_CUISINES
        NavConst.UPLOAD_AADHAAR -> NavTitle.UPLOAD_AADHAAR
        NavConst.MANAGE_TIME_SLOTS -> NavTitle.MANAGE_TIME_SLOTS
        NavConst.COOK_JOB_LIST -> NavTitle.COOK_JOB_LIST
        NavConst.PERSONAL_INFO -> NavTitle.PERSONAL_INFO
        NavConst.EDIT_COOK_PROFILE -> NavTitle.EDIT_COOK_PROFILE

        // other cases
        else -> NavTitle.HOME
    }
}