package com.example.cook_ford.presentation.route
import AccountsScreen
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.cook_ford.data.remote.profile_response.ProfileResponse
import com.example.cook_ford.presentation.screens.authenticated.accounts.add_cook_screen_component.AddCookProfileScreen
import com.example.cook_ford.presentation.screens.authenticated.accounts.call_credit_screen_component.CallCreditScreen
import com.example.cook_ford.presentation.screens.authenticated.accounts.cook_preferences.CookPreferencesScreen
import com.example.cook_ford.presentation.screens.authenticated.accounts.post_job_screen_component.PostJobScreen
import com.example.cook_ford.presentation.screens.authenticated.accounts.update_profile_screen_component.EditProfileScreen
import com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.chat_screen_component.MessageScreen
import com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.details_screen_component.ProfileDetailScreen
import com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.list_screen_component.ProfilesScreen
import com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.report_screen_component.ReportScreen
import com.example.cook_ford.presentation.screens.authenticated.profile_screen_component.reviews_screen_component.ReviewScreen
import com.example.cook_ford.presentation.screens.dashboard_screen_component.home_screen_component.UserDashBoard
import com.example.cook_ford.presentation.screens.un_authenticated.landind_screen_component.LandingScreen
import com.example.cook_ford.presentation.screens.un_authenticated.main_screen_component.SplashScreen
import com.example.cook_ford.presentation.screens.un_authenticated.onboard_screen_component.OnBoardingScreen
import com.example.cook_ford.presentation.screens.un_authenticated.phone_verification_screen_component.PhoneVerificationScreen
import com.example.cook_ford.presentation.screens.un_authenticated.sign_in_screen_component.SignInScreen
import com.example.cook_ford.presentation.screens.un_authenticated.sign_up_screen_component.SignUpScreen
import com.google.gson.Gson


/**
 * Login, registration, forgot password screens nav graph builder
 * (Unauthenticated user)
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.unauthenticatedGraph(navController: NavController) {

    navigation(
        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Unauthenticated.Splash.route) {

        // Splash
        composable(route = NavigationRoutes.Unauthenticated.Splash.route) {
            SplashScreen(navController = navController)
        }
        // Testing
        /*composable(route = NavigationRoutes.Unauthenticated.Splash.route) {
           *//* AccountsScreen(
                //navController = NavHostController,
                onNavigateToCallCreditScreen = {

                },
                onNavigateToEditProfile = {

                },
                onNavigateToAddCookScreen = {

                },
                onNavigateToPostJobScreen = {

                },
                onNavigateToContactUsScreen = {

                },
                onNavigateToReviewUsScreen = {

                },
                onNavigateToTellCommunity = {

                },
                onNavigateToSignInAsCookScreen = {

                },
                onNavigateToTermsOfUseScreen = {

                },
                onNavigateToPrivacyPolicyScreen = {

                },
                onNavigateToLicenseScreen = {

                }
            )*//*
        }*/

        // Onboard
        composable(route = NavigationRoutes.Unauthenticated.Onboard.route) {
            OnBoardingScreen(navController = navController,
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.Landing.route){
                        popUpTo(route = NavigationRoutes.Unauthenticated.Onboard.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        //Landing
        composable(route = NavigationRoutes.Unauthenticated.Landing.route) {
            LandingScreen(navController = navController,
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.PhoneVerification.route)
                }
            )
        }

        //PhoneVerificationScreen
        composable(route = NavigationRoutes.Unauthenticated.PhoneVerification.route) {
            PhoneVerificationScreen(navController = navController,
                onNavigateBack = { navController.navigateUp() },
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.SignIn.route)
                }
            )
        }

        // SignIn
        composable(route = NavigationRoutes.Unauthenticated.SignIn.route) {
            SignInScreen(
                navController = navController,
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
                navController = navController,
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
                navController = navController,
                onNavigateToProfileDetails = {
                    navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileDetail.route ) {
                        popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(NavigationRoutes.HomeNavigation.Search.route) {  }

        composable(NavigationRoutes.HomeNavigation.Account.route) {
            AccountsScreen(
                navController = navController,
                onNavigateToCallCreditScreen = {
                    navController.navigate(route = NavigationRoutes.AccountNavigation.CallCredit.route){
                         popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                             inclusive = true
                         }
                    }
                },
                onNavigateToEditProfile = {
                    navController.navigate(route = NavigationRoutes.AccountNavigation.EditProfile.route){
                         popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                             inclusive = true
                         }
                    }
                },
                onNavigateToAddCookScreen = {
                    navController.navigate(route = NavigationRoutes.AccountNavigation.AddCookProfile.route){
                         popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                             inclusive = true
                         }
                    }
                },
                onNavigateToPostJobScreen = {
                    navController.navigate(route = NavigationRoutes.AccountNavigation.PostJob.route){
                         popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                             inclusive = true
                         }
                    }
                },
                onNavigateToContactUsScreen = {

                },
                onNavigateToReviewUsScreen = {

                },
                onNavigateToTellCommunity = {

                },
                onNavigateToSignInAsCookScreen = {

                },
                onNavigateToTermsOfUseScreen = {

                },
                onNavigateToPrivacyPolicyScreen = {

                },
                onNavigateToLicenseScreen = {

                }
            )
        }
        detailNavGraph(navController = navController)
    }
}

/**
 * Details screens nav graph builder
 * (Unauthenticated user)
 */
@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.detailNavGraph(navController: NavHostController) {
    navigation(route = NavigationRoutes.DetailsNavigation.NavigationRoute.route,
        startDestination =  NavigationRoutes.DetailsNavigation.ProfileDetail.route ) {
        composable(NavigationRoutes.DetailsNavigation.ProfileDetail.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                profileResponse?.let {
                    ProfileDetailScreen(
                        navController = navController,
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToReViewScreen = { navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileReview.route) },
                        onNavigateToReportScreen = { navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileReport.route) },
                        onNavigateToCallCreditScreen = { navController.navigate(route = NavigationRoutes.AccountNavigation.CallCredit.route) },
                        onNavigateToMessageScreen = { navController.navigate(route = NavigationRoutes.DetailsNavigation.Message.route) },
                        onNavigateToAuthenticatedHomeRoute = {
                            navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileDetail.route) {
                             /* popUpTo(route = NavigationRoutes.HomeNavigation.NavigationRoute.route) {
                                  inclusive = true
                               }*/
                            }
                        }
                    )
                }

            }
        }

        composable(route = NavigationRoutes.DetailsNavigation.ProfileReview.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "detailNavGraph data: ${Gson().toJson(profileResponse)}")
                    ReviewScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedHomeRoute = {
                            navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileReview.route) {
                                /* popUpTo(route = NavigationRoutes.HomeNavigation.NavigationRoute.route) {
                                    inclusive = true
                                }*/
                            }
                        }
                    )
                }
        }

        composable(route = NavigationRoutes.DetailsNavigation.ProfileReport.route){
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "detailNavGraph review data: ${Gson().toJson(profileResponse)}")
                    ReportScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedHomeRoute = {
                            navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileReport.route) {
                                /*    popUpTo(route = NavigationRoutes.HomeNavigation.NavigationRoute.route) {
                                inclusive = true
                                }*/
                            }
                        }
                    )
                }
        }


        composable(route = NavigationRoutes.DetailsNavigation.Message.route){
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "detailNavGraph review data: ${Gson().toJson(profileResponse)}")
                    MessageScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onSendMessage = {},
                        onNavigateToAuthenticatedHomeRoute = {
                            navController.navigate(route = NavigationRoutes.DetailsNavigation.Message.route) {
                                /*    popUpTo(route = NavigationRoutes.HomeNavigation.NavigationRoute.route) {
                                inclusive = true
                                }*/
                            }
                        }
                    )
                }
        }

        accountNavGraph(navController = navController)
    }
}

/**
 * Account screens nav graph builder
 * (Unauthenticated user)
 */
fun NavGraphBuilder.accountNavGraph(navController: NavHostController) {
    navigation(route = NavigationRoutes.AccountNavigation.NavigationRoute.route,
        startDestination =  NavigationRoutes.AccountNavigation.EditProfile.route) {
        composable(NavigationRoutes.AccountNavigation.EditProfile.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "detailNavGraph review data: ${Gson().toJson(profileResponse)}")
                    EditProfileScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToSignOut = {},
                        onNavigateToAuthenticatedRoute = {
                            navController.navigate(route = NavigationRoutes.AccountNavigation.EditProfile.route) {
                                /* popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }*/
                            }
                        },
                    )
                }
        }
        composable(route = NavigationRoutes.AccountNavigation.AddCookProfile.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "detailNavGraph review data: ${Gson().toJson(profileResponse)}")
                    AddCookProfileScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedRoute = {
                            navController.navigate(route = NavigationRoutes.AccountNavigation.AddCookProfile.route) {
                                /* popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                             inclusive = true
                         }*/
                            }
                        },
                    )
                }
        }
        composable(route = NavigationRoutes.AccountNavigation.PostJob.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "detailNavGraph review data: ${Gson().toJson(profileResponse)}")
                    PostJobScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToCookPreferences = { navController.navigate(route = NavigationRoutes.AccountNavigation.CookPreferences.route) },
                        onNavigateToAuthenticatedRoute = {
                            navController.navigate(route = NavigationRoutes.AccountNavigation.PostJob.route) {
                                /* popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                             inclusive = true
                         }*/
                            }
                        },
                    )
                }
        }
        composable(route = NavigationRoutes.AccountNavigation.CookPreferences.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "detailNavGraph review data: ${Gson().toJson(profileResponse)}")
                    CookPreferencesScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedRoute = {
                            navController.navigate(route = NavigationRoutes.AccountNavigation.CookPreferences.route) {
                                /* popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                             inclusive = true
                         }*/
                            }
                        },
                    )
                }
        }
        composable(route = NavigationRoutes.AccountNavigation.CallCredit.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "detailNavGraph review data: ${Gson().toJson(profileResponse)}")
                    CallCreditScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedRoute = {
                            navController.navigate(route = NavigationRoutes.AccountNavigation.CallCredit.route) {
                                /* popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                             inclusive = true
                         }*/
                            }
                        },
                    )
                }
        }
    }
}
