package com.example.cook_ford.presentation.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cook_ford.presentation.screens.SplashScreen
import com.example.cook_ford.presentation.screens.dashboard.users.UserDashBoard
import com.example.cook_ford.presentation.screens.onboard.OnBoardingScreen
import com.example.cook_ford.presentation.screens.profile.profile_details.ProfileDetailScreen
import com.example.cook_ford.presentation.screens.profile.profile_list.ProfileListScreen
import com.example.cook_ford.presentation.screens.sign_in.SignInScreen
import com.example.cook_ford.presentation.screens.sign_up.SignUpScreen
import com.example.cook_ford.utils.AppConstants

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
            UserDashBoard()
        }
    }
}


/**
 * Home screens bottom nav graph builder
 * (Unauthenticated user)
 */
@Composable
fun BottomNavigation(navController: NavHostController) {
    NavHost(navController,
        route = NavigationRoutes.BottomNavigation.NavigationRoute.route,
        startDestination = NavigationRoutes.BottomNavigation.Home.route) {
        composable(NavigationRoutes.BottomNavigation.Home.route) {
            ProfileListScreen(
                onNavigateToProfileDetails = { profileId ->
                    navController.navigate(route = NavigationRoutes.Details.ProfileDetail.route + "/${profileId}") {
                        popUpTo(route = NavigationRoutes.BottomNavigation.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(NavigationRoutes.BottomNavigation.Search.route) {  }
        //composable(NavigationRoutes.BottomNavigation.List.route) {  }
        composable(NavigationRoutes.BottomNavigation.Profile.route) { }
        detailNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.detailNavGraph(navController: NavHostController) {
    navigation(route = NavigationRoutes.Details.NavigationRoute.route,
        startDestination =  NavigationRoutes.Details.ProfileDetail.route + "/{${AppConstants.PROFILE}}") {
        composable(NavigationRoutes.Details.ProfileDetail.route + "/{profileId}") { backStackEntry ->
            ProfileDetailScreen (
                onGoBack = {
                navController.popBackStack()
            })
        }
    }
}
