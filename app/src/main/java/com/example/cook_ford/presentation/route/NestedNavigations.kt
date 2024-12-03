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
import com.example.cook_ford.presentation.screens.authenticated_screen_component.accounts_screen_component.add_cook_screen_component.AddCookProfileScreen
import com.example.cook_ford.presentation.screens.authenticated_screen_component.accounts_screen_component.call_credit_screen_component.CallCreditScreen
import com.example.cook_ford.presentation.screens.authenticated_screen_component.accounts_screen_component.cook_preferences_component.UserPreferencesScreen
import com.example.cook_ford.presentation.screens.authenticated_screen_component.accounts_screen_component.post_job_screen_component.PostJobScreen
import com.example.cook_ford.presentation.screens.authenticated_screen_component.accounts_screen_component.update_profile_screen_component.EditProfileScreen
import com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.chat_screen_component.MessageScreen
import com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.details_screen_component.ProfileDetailScreen
import com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.list_screen_component.ProfilesScreen
import com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.report_screen_component.ReportScreen
import com.example.cook_ford.presentation.screens.authenticated_screen_component.profile_screen_component.reviews_screen_component.ReviewScreen
import com.example.cook_ford.presentation.screens.dashboard_screen_component.home_screen_component.UserDashBoard
import com.example.cook_ford.presentation.screens.un_authenticated_component.landind_screen_component.LandingScreen
import com.example.cook_ford.presentation.screens.un_authenticated_component.main_screen_component.SplashScreen
import com.example.cook_ford.presentation.screens.un_authenticated_component.onboard_screen_component.OnBoardingScreen
import com.example.cook_ford.presentation.screens.un_authenticated_component.phone_verification_screen_component.PhoneVerificationScreen
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.SignInScreen
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_cook_component.add_cook_address.AddressScreen
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.SignUpScreen
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
                onNavigateToUserSignInRoute = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.SignIn.route)
                },
                onNavigateToCookSignInRoute = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.CookSignIn.route)
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
            Log.d("TAG", "unauthenticatedGraph: SignIn ")
            SignInScreen(
                navController = navController,
                onNavigateToSignUp = { navController.navigate(route = NavigationRoutes.Unauthenticated.SignUp.route) },
                onNavigateToForgotPassword = {},
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // SignIn Cook
        composable(route = NavigationRoutes.Unauthenticated.CookSignIn.route) {
            Log.d("TAG", "unauthenticatedGraph: CookSignIn ")
            SignInScreen(
                navController = navController,
                onNavigateToSignUp = { navController.navigate(route = NavigationRoutes.Unauthenticated.CookSignUp.route) },
                onNavigateToForgotPassword = {},
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // SignUp User
        composable(route = NavigationRoutes.Unauthenticated.SignUp.route) {
            SignUpScreen(
                userType = "User",
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

        // SignUp Cook
        composable(route = NavigationRoutes.Unauthenticated.CookSignUp.route) {
            SignUpScreen(
                userType = "Provider",
                navController = navController,
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.AddAddress.route) {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // SignUp Cook
        composable(route = NavigationRoutes.Unauthenticated.AddAddress.route) {
            AddressScreen(
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
                        onNavigateToSignOut = { userType->
                            if(userType=="Provider"){
                                navController.navigate(route = NavigationRoutes.Unauthenticated.CookSignIn.route) {
                                   /* popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                                        inclusive = true
                                    }*/
                                }
                            }else{
                                navController.navigate(route = NavigationRoutes.Unauthenticated.SignIn.route) {
                                 /*   popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                                        inclusive = true
                                    }*/
                                }
                            }
                        },
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
                    UserPreferencesScreen (
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
