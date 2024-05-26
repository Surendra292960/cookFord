package com.example.cook_ford.presentation.route

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cook_ford.presentation.screens.SplashScreen
import com.example.cook_ford.presentation.screens.report.ReportScreen
import com.example.cook_ford.presentation.screens.dashboard.home.UserDashBoard
import com.example.cook_ford.presentation.screens.onboard.OnBoardingScreen
import com.example.cook_ford.presentation.screens.profile.profile_details.ProfileDetailScreen
import com.example.cook_ford.presentation.screens.profile.profile_list.ProfilesScreen
import com.example.cook_ford.presentation.screens.profile.profile_review.ReviewScreen
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
            UserDashBoard(
                onNavigateToAuthenticatedRoute = {
                    /*navController.navigate(route = NavigationRoutes.Authenticated.Dashboard.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }*/
                }
            )
        }
    }
}


/**
 * Home screens bottom nav graph builder
 * (Unauthenticated user)
 */
@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(navController, route = NavigationRoutes.HomeNavigation.NavigationRoute.route,
        startDestination = NavigationRoutes.HomeNavigation.Home.route) {

        composable(NavigationRoutes.HomeNavigation.Home.route) {
            ProfilesScreen(
                onNavigateToProfileDetails = { profileId ->
                    navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileDetail.route + "/${profileId}") {
                        popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(NavigationRoutes.HomeNavigation.Search.route) {  }
        //composable(NavigationRoutes.BottomNavigation.List.route) {  }
        composable(NavigationRoutes.HomeNavigation.Profile.route) { }
        detailNavGraph(navController = navController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.detailNavGraph(navController: NavHostController) {
    navigation(route = NavigationRoutes.DetailsNavigation.NavigationRoute.route,
        startDestination =  NavigationRoutes.DetailsNavigation.ProfileDetail.route + "/{${AppConstants.PROFILE_ID}}") {
        composable(NavigationRoutes.DetailsNavigation.ProfileDetail.route + "/{profileId}") {
            ProfileDetailScreen (
                onNavigateBack = { navController.navigateUp() },
                onNavigateToReViewScreen = { profileId->
                    navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileReview.route + "/${profileId}")
                },
                onNavigateToReportScreen = { profileId->
                    Log.d("TAG", "detailNavGraph: onNavigateToReportScreen")
                    navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileReport.route + "/${profileId}")
                },
                onNavigateToAuthenticatedHomeRoute = {
                    navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileDetail.route){
                   /*    popUpTo(route = NavigationRoutes.HomeNavigation.NavigationRoute.route) {
                            inclusive = true
                        }*/
                    }
                }
            )
        }

        composable(route = NavigationRoutes.DetailsNavigation.ProfileReview.route+ "/{profileId}"){
            Log.d("TAG", "detailNavGraph: ProfileReviewScreen")
            ReviewScreen (
                onNavigateBack = { navController.navigateUp() },
                onNavigateToAuthenticatedHomeRoute = {
                    navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileReview.route){
                        /*    popUpTo(route = NavigationRoutes.HomeNavigation.NavigationRoute.route) {
                                inclusive = true
                            }*/
                    }
                }
            )
        }

        composable(route = NavigationRoutes.DetailsNavigation.ProfileReport.route+ "/{profileId}"){
            Log.d("TAG", "detailNavGraph: ProfileReportScreen")
            ReportScreen (
                onNavigateBack = { navController.navigateUp() },
                onNavigateToAuthenticatedHomeRoute = {
                    navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileReport.route){
                        /*    popUpTo(route = NavigationRoutes.HomeNavigation.NavigationRoute.route) {
                                inclusive = true
                            }*/
                    }
                }
            )
        }
    }
}
