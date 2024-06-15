package com.example.cook_ford.presentation.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cook_ford.presentation.route.bottom_nav.NavPath
import com.example.cook_ford.presentation.route.bottom_nav.NavTitle

sealed class NavigationRoutes {

    // Unauthenticated Routes
    sealed class Unauthenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Unauthenticated(route = NavTitle.UNAUTHENTICATED)
        data object Splash : Unauthenticated(route = NavTitle.SPLASH)
        data object Landing : Unauthenticated(route = NavTitle.LANDING)
        data object PhoneVerification : Unauthenticated(route = NavTitle.PHONE_VERIFICATION)
        data object OnBoard : Unauthenticated(route = NavTitle.ONBOARD)
        data object SignIn : Unauthenticated(route = NavTitle.SIGN_IN)
        data object SignUp : Unauthenticated(route = NavTitle.SIGN_UP)
    }

    // Authenticated Routes
    sealed class Authenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Authenticated(route = NavTitle.AUTHENTICATED)
        data object Dashboard : Authenticated(route = NavTitle.DASHBOARD)
        data object Users : Authenticated(route = NavTitle.USERS)
        data object NoInternet : Authenticated(route = NavTitle.NO_INTERNET)
    }

    //BottomNavigation Routes
    sealed class HomeNavigation(val route: String, val title: String, val icon: ImageVector): NavigationRoutes(){
        data object NavigationRoute : HomeNavigation(route = NavTitle.BOTTOM_NAVIGATION, title = NavTitle.HOME, icon = Icons.Default.Home)
        data object Home : HomeNavigation(route = NavPath.HOME.toString(), title = NavTitle.HOME, icon = Icons.Default.Home)
        data object Search : HomeNavigation(route = NavPath.SEARCH.toString(), title = NavTitle.SEARCH, icon = Icons.Default.Search)
       // data object List : BottomNavigation(route = NavPath.LIST.toString(), title = NavTitle.LIST, icon = Icons.Default.List)
        data object Profile : HomeNavigation(route = NavPath.PROFILE.toString(), title = NavTitle.PROFILE, icon = Icons.Default.Person)
    }

    sealed class DetailsNavigation(val route: String) : NavigationRoutes() {
        data object NavigationRoute : DetailsNavigation(route = NavTitle.DETAILS_NAV)
        data object ProfileDetail : DetailsNavigation(route = NavTitle.DETAILS)
        data object ProfileReview : DetailsNavigation(route = NavTitle.REVIEW)
        data object ProfileReport : DetailsNavigation(route = NavTitle.REPORT)

    }

    sealed class AccountNavigation(val route: String) : NavigationRoutes() {
        data object NavigationRoute : AccountNavigation(route = NavTitle.ACCOUNT_NAVIGATION)
        data object Account : AccountNavigation(route = NavTitle.PROFILE_ACCOUNT)
        data object EditProfile : AccountNavigation(route = NavTitle.EDIT_PROFILE)
        data object AddCookProfile : AccountNavigation(route = NavTitle.ADD_COOK_PROFILE)
        data object PostJob : AccountNavigation(route = NavTitle.POST_JOB)
        data object CookPreferences : AccountNavigation(route = NavTitle.COOK_PREFERENCES)
    }
}