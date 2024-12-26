package com.example.cook_ford.presentation.route.bottom_nav.utils

enum class NavPath {
    //UnAuthenticated
     UNAUTHENTICATED,
     SPLASH,
     LANDING,
     PHONE_VERIFICATION,
     ONBOARD,
     SIGN_IN,
     SIGN_IN_AS_COOK,
     SIGN_UP,
     SIGN_UP_AS_COOK,

    //Authenticated
     AUTHENTICATED,
     DASHBOARD,
     USERS,
     NO_INTERNET,

    //Profile Details
    DETAILS_NAV,
    PROFILE_DETAILS,
    REVIEW,
    REPORT,
    MESSAGE,

    //Account
    ACCOUNT_NAVIGATION,
    CALL_CREDIT,
    UPDATE_PROFILE,
    ADD_COOK_PROFILE,
    POST_JOB,
    COOK_PREFERENCES,

    //Bottom Nav
    BOTTOM_NAVIGATION,


    //Cook Bottom Nav
    COOK_BOTTOM_NAVIGATION,
    HOME, 
    SEARCH, 
    LIST, 
    ACCOUNT,

    //Cook Profile Details
    COOK_DETAILS_NAV,
    COOK_PROFILE_DETAILS,
    COOK_REVIEW,
    COOK_REPORT,
    COOK_MESSAGE,

    //Cook Account
    COOK_ACCOUNT_NAVIGATION,
    UPDATE_COOK_PROFILE,
    MANAGE_COOK_ACCOUNT,
    UPLOAD_CUISINES,
    UPLOAD_AADHAAR,
    MANAGE_TIME_SLOTS,
    COOK_JOB_LIST,
    PERSONAL_INFO,
    EDIT_COOK_PROFILE


}