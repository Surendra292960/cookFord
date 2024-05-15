package com.example.cook_ford.presentation.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import com.example.cook_ford.presentation.route.bottom_nav.Item
import com.example.cook_ford.presentation.route.bottom_nav.NavPath
import com.example.cook_ford.presentation.route.bottom_nav.NavTitle

sealed class NavigationRoutes {

    // Unauthenticated Routes
    sealed class Unauthenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Unauthenticated(route = NavTitle.UNAUTHENTICATED)
        data object Splash : Unauthenticated(route = NavTitle.SPLASH)
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
    sealed class NavItem: NavigationRoutes(){
        object Home : Item(route = NavPath.HOME.toString(), title = NavTitle.HOME, icon = Icons.Default.Home)

        object Search : Item(route = NavPath.SEARCH.toString(), title = NavTitle.SEARCH, icon = Icons.Default.Search)

        object List : Item(route = NavPath.LIST.toString(), title = NavTitle.LIST, icon = Icons.Default.List)

        object Profile : Item(route = NavPath.PROFILE.toString(), title = NavTitle.PROFILE, icon = Icons.Default.Person)
    }
}