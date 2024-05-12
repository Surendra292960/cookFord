package com.example.cook_ford.presentation.route

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cook_ford.presentation.common.user_list_card.MainScreen
import com.example.cook_ford.presentation.screens.SplashScreen
import com.example.cook_ford.presentation.screens.onboard.OnBoardingScreen
import com.example.cook_ford.presentation.screens.sign_in.SignInScreen
import com.example.cook_ford.presentation.screens.sign_up.SignUpScreen

/**
 * Login, registration, forgot password screens nav graph builder
 * (Unauthenticated user)
 */
fun NavGraphBuilder.unauthenticatedGraph(navController: NavController) {

    navigation(
        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Unauthenticated.Splash.route) {

        // Splash
        composable(route = NavigationRoutes.Unauthenticated.Splash.route) {
            SplashScreen(
                navController
            )
        }

        //OnBoard
        composable(route = NavigationRoutes.Unauthenticated.OnBoard.route) {
            OnBoardingScreen(
                //navController
            )
        }

        // SignIn
        composable(route = NavigationRoutes.Unauthenticated.SignIn.route) {
            SignInScreen(
                onNavigateToRegistration = { navController.navigate(route = NavigationRoutes.Unauthenticated.SignUp.route) },
                onNavigateToForgotPassword = {},
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        // SignUp
        composable(route = NavigationRoutes.Unauthenticated.SignUp.route) {
            SignUpScreen(
                onNavigateBack = { navController.navigateUp() },
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.SignIn.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}


/**
 * Authenticated screens nav graph builder
 */
fun NavGraphBuilder.authenticatedGraph(navController: NavController) {
    navigation(route = NavigationRoutes.Authenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Authenticated.Dashboard.route) {
        // Dashboard
        composable(route = NavigationRoutes.Authenticated.Dashboard.route) {
           /* val openFullDialogCustom = remember { mutableStateOf(true) }
            NoInternetScreen(openFullDialogCustom = openFullDialogCustom)*/
            //UserDashBoard()
            MainScreen()
        }
    }
}