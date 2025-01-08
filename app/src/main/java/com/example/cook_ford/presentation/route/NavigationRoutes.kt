package com.example.cook_ford.presentation.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cook_ford.presentation.route.bottom_nav.utils.NavPath
import com.example.cook_ford.presentation.route.bottom_nav.utils.NavTitle

sealed class NavigationRoutes {

    // Unauthenticated Routes
    sealed class Unauthenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Unauthenticated(route = NavPath.UNAUTHENTICATED.toString())
        data object Splash : Unauthenticated(route = NavPath.SPLASH.toString())
        data object Onboard : Unauthenticated(route = NavPath.ONBOARD.toString())
        data object Landing : Unauthenticated(route = NavPath.LANDING.toString())
        data object PhoneVerification : Unauthenticated(route = NavPath.PHONE_VERIFICATION.toString())
        data object SignIn : Unauthenticated(route = NavPath.SIGN_IN.toString())
        data object CookSignIn : Unauthenticated(route = NavPath.SIGN_IN_AS_COOK.toString())
        data object SignUp : Unauthenticated(route = NavPath.SIGN_UP.toString())
        data object CookSignUp : Unauthenticated(route = NavPath.SIGN_UP_AS_COOK.toString())
    }

    // Authenticated Routes
    sealed class Authenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Authenticated(route = NavPath.AUTHENTICATED.toString())
        data object Dashboard : Authenticated(route = NavPath.DASHBOARD.toString())
        data object Users : Authenticated(route = NavPath.USERS.toString())
        data object NoInternet : Authenticated(route = NavPath.NO_INTERNET.toString())
    }

    //BottomNavigation Routes
    sealed class HomeNavigation(val route: String, val title: String, val icon: ImageVector): NavigationRoutes(){
        data object NavigationRoute : HomeNavigation(route = NavPath.BOTTOM_NAVIGATION.toString(), title = NavTitle.HOME, icon = Icons.Default.Home)
        data object Home : HomeNavigation(route = NavPath.HOME.toString(), title = NavTitle.HOME, icon = Icons.Default.Home)
        data object Search : HomeNavigation(route = NavPath.SEARCH.toString(), title = NavTitle.SEARCH, icon = Icons.Default.Search)
        data object Account : HomeNavigation(route = NavPath.ACCOUNT.toString(), title = NavTitle.ACCOUNT, icon = Icons.Default.Person)
    }

    sealed class DetailsNavigation(val route: String) : NavigationRoutes() {
        data object NavigationRoute : DetailsNavigation(route = NavPath.DETAILS_NAV.toString())
        data object ProfileDetail : DetailsNavigation(route = NavPath.PROFILE_DETAILS.toString())
        data object ProfileReview : DetailsNavigation(route = NavPath.REVIEW.toString())
        data object ProfileReport : DetailsNavigation(route = NavPath.REPORT.toString())
        data object Message : DetailsNavigation(route = NavPath.MESSAGE.toString())
    }

    sealed class AccountNavigation(val route: String) : NavigationRoutes() {
        data object NavigationRoute : AccountNavigation(route = NavPath.ACCOUNT_NAVIGATION.toString())
        data object CallCredit : AccountNavigation(route = NavPath.CALL_CREDIT.toString())
        data object EditProfile : AccountNavigation(route = NavPath.UPDATE_PROFILE.toString())
        data object AddCookProfile : AccountNavigation(route = NavPath.ADD_COOK_PROFILE.toString())
        data object PostJob : AccountNavigation(route = NavPath.POST_JOB.toString())
        data object CookPreferences : AccountNavigation(route = NavPath.COOK_PREFERENCES.toString())
    }

    // BottomNavigation Cook Routes
    sealed class CookHomeNavigation(val route: String, val title: String, val icon: ImageVector) : NavigationRoutes() {
        data object NavigationRoute : CookHomeNavigation(route = NavPath.COOK_BOTTOM_NAVIGATION.toString(), title = NavTitle.HOME, icon = Icons.Default.Home)
        data object Home : CookHomeNavigation(route = NavPath.HOME.toString(), title = NavTitle.HOME, icon = Icons.Default.Home)
        data object Search : HomeNavigation(route = NavPath.SEARCH.toString(), title = NavTitle.SEARCH, icon = Icons.Default.Search)
        data object Account : HomeNavigation(route = NavPath.ACCOUNT.toString(), title = NavTitle.ACCOUNT, icon = Icons.Default.Person)
    }

    sealed class CookDetailsNavigation(val route: String) : NavigationRoutes() {
        data object NavigationRoute : CookDetailsNavigation(route = NavPath.COOK_DETAILS_NAV.toString())
        data object CookProfileDetail : CookDetailsNavigation(route = NavPath.COOK_PROFILE_DETAILS.toString())
        data object CookProfileReview : CookDetailsNavigation(route = NavPath.COOK_REVIEW.toString())
        data object CookProfileReport : CookDetailsNavigation(route = NavPath.COOK_REPORT.toString())
        data object CookMessage : CookDetailsNavigation(route = NavPath.COOK_MESSAGE.toString())
    }

    sealed class CookAccountNavigation(val route: String) : NavigationRoutes() {
        data object NavigationRoute : CookAccountNavigation(route = NavPath.COOK_ACCOUNT_NAVIGATION.toString())
        data object UpdateCookProfile : CookAccountNavigation(route = NavPath.UPDATE_COOK_PROFILE.toString())
        data object ManageCookAccount : CookAccountNavigation(route = NavPath.MANAGE_COOK_ACCOUNT.toString())
        data object UploadCuisines : CookAccountNavigation(route = NavPath.UPLOAD_CUISINES.toString())
        data object UploadAadhaar : CookAccountNavigation(route = NavPath.UPLOAD_AADHAAR.toString())
        data object ManageTimeSlots : CookAccountNavigation(route = NavPath.MANAGE_TIME_SLOTS.toString())
        data object CookJobList : CookAccountNavigation(route = NavPath.COOK_JOB_LIST.toString())
        data object PersonalInfo : CookAccountNavigation(route = NavPath.PERSONAL_INFO.toString())
        data object EditCookProfile : CookAccountNavigation(route = NavPath.EDIT_COOK_PROFILE.toString())
    }
}