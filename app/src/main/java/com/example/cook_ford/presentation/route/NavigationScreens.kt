package com.example.cook_ford.presentation.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cook_ford.presentation.screens.profile.ProfileListScreen

/**
 * Composable function that defines the navigation screens and their corresponding destinations.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */
@Composable
fun NavigationScreens(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationRoutes.NavItem.Home.route) {
        composable(NavigationRoutes.NavItem.Home.route) {  }
        composable(NavigationRoutes.NavItem.Search.route) {  }
        composable(NavigationRoutes.NavItem.List.route) {  }
        composable(NavigationRoutes.NavItem.Profile.route) {
            ProfileListScreen()
        }
    }
}