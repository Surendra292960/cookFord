package com.example.cook_ford.presentation.route.bottom_nav.utils

enum class NavPath {
    //UnAuthenticated
     UNAUTHENTICATED,
     SPLASH,
     LANDING,
     PHONE_VERIFICATION,
     ONBOARD,
     SIGN_IN,
     SIGN_UP,
     PROFILE,

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

    //Account
    ACCOUNT_NAVIGATION,
    CALL_CREDIT,
    UPDATE_PROFILE,
    ADD_COOK_PROFILE,
    POST_JOB,
    COOK_PREFERENCES,

    //Bottom Nav
    BOTTOM_NAVIGATION,
    HOME, 
    SEARCH, 
    LIST, 
    ACCOUNT,
}