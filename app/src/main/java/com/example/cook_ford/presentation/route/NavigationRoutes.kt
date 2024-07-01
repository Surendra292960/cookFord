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
        data object SignUp : Unauthenticated(route = NavPath.SIGN_UP.toString())
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

    }

    sealed class AccountNavigation(val route: String) : NavigationRoutes() {
        data object NavigationRoute : AccountNavigation(route = NavPath.ACCOUNT_NAVIGATION.toString())
        data object CallCredit : DetailsNavigation(route = NavPath.CALL_CREDIT.toString())
        data object EditProfile : AccountNavigation(route = NavPath.UPDATE_PROFILE.toString())
        data object AddCookProfile : AccountNavigation(route = NavPath.ADD_COOK_PROFILE.toString())
        data object PostJob : AccountNavigation(route = NavPath.POST_JOB.toString())
        data object CookPreferences : AccountNavigation(route = NavPath.COOK_PREFERENCES.toString())
    }
}