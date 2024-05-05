package com.example.cook_ford.presentation.route

sealed class NavigationRoutes {

    // Unauthenticated Routes
    sealed class Unauthenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Unauthenticated(route = "unauthenticated")
        data object Splash : Unauthenticated(route = "splash")
        data object SignIn : Unauthenticated(route = "login")
        data object SignUp : Unauthenticated(route = "registration")
    }

    // Authenticated Routes
    sealed class Authenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Authenticated(route = "authenticated")
        data object Dashboard : Authenticated(route = "Dashboard")
    }
}