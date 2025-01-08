package com.example.cook_ford.presentation.route

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
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.account_screen_component.CookAccountsScreen
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.add_cook_personal_info.CookPersonalInfoScreen
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.cook_available_jobs_list.CookJobListScreen
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.edit_cook_profile_component.EditCookProfileScreen
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_account.ManageCookAccountScreen
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.manage_cook_time_slots.ManageTimeSlotsScreen
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.update_profile_screen_component.UpdateCookProfileScreen
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.upload_aadhaar.UploadAadhaarScreen
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.upload_cuisine_themes.UploadCuisineImagesScreen
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.details_screen_component.CookProfileDetailScreen
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.list_screen_component.CookProfilesScreen
import com.example.cook_ford.presentation.screens.authenticated_component.cook_component.profile_component.reviews_screen_component.ReviewScreen
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.account_screen_component.AccountsScreen
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.add_cook_screen_component.AddCookProfileScreen
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.call_credit_screen_component.CallCreditScreen
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.cook_preferences_component.UserPreferencesScreen
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.post_job_screen_component.PostJobScreen
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.accounts_component.update_profile_screen_component.UpdateProfileScreen
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.chat_screen_component.MessageScreen
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.details_screen_component.ProfileDetailScreen
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.list_screen_component.ProfilesScreen
import com.example.cook_ford.presentation.screens.authenticated_component.user_component.profile_component.report_screen_component.ReportScreen
import com.example.cook_ford.presentation.screens.dashboard_component.home_screen_component.HomeScreen
import com.example.cook_ford.presentation.screens.un_authenticated_component.landind_screen_component.LandingScreen
import com.example.cook_ford.presentation.screens.un_authenticated_component.main_screen_component.SplashScreen
import com.example.cook_ford.presentation.screens.un_authenticated_component.onboard_screen_component.OnBoardingScreen
import com.example.cook_ford.presentation.screens.un_authenticated_component.phone_verification_screen_component.PhoneVerificationScreen
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_in_screen_component.SignInScreen
import com.example.cook_ford.presentation.screens.un_authenticated_component.sign_up_screen_user_component.SignUpScreen
import com.example.cook_ford.utils.AppConstants
import com.google.gson.Gson


/**
 * Login, registration, forgot password screens nav graph builder
 * (Unauthenticated user)
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.unauthenticatedGraph(navController: NavController) {

    navigation(
        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Unauthenticated.Splash.route
    ) {

        // Splash
        composable(route = NavigationRoutes.Unauthenticated.Splash.route) {
            SplashScreen(navController = navController)
        }

        // Onboard
        composable(route = NavigationRoutes.Unauthenticated.Onboard.route) {
            OnBoardingScreen(navController = navController,
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.Landing.route) {
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

        // SignUp User
        composable(route = NavigationRoutes.Unauthenticated.SignUp.route) {
            SignUpScreen(
                userType = AppConstants.USER,
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

        // SignUp Cook
        composable(route = NavigationRoutes.Unauthenticated.CookSignUp.route) {
            SignUpScreen(
                userType = AppConstants.PROVIDER,
                navController = navController,
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.CookSignIn.route) {
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
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.authenticatedGraph(navController: NavController) {
    navigation(
        route = NavigationRoutes.Authenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Authenticated.Dashboard.route
    ) {
        // Dashboard
        composable(route = NavigationRoutes.Authenticated.Dashboard.route) {
            HomeScreen(
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
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController, route = NavigationRoutes.HomeNavigation.NavigationRoute.route,
        startDestination = NavigationRoutes.HomeNavigation.Home.route
    ) {

        composable(NavigationRoutes.HomeNavigation.Home.route) {

            ProfilesScreen(
                navController = navController,
                onNavigateToProfileDetails = {
                    navController.navigate(route = NavigationRoutes.DetailsNavigation.ProfileDetail.route) {
                        popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(NavigationRoutes.HomeNavigation.Search.route) { }

        composable(NavigationRoutes.HomeNavigation.Account.route) {
            AccountsScreen(
                navController = navController,
                onNavigateToCallCreditScreen = {
                    navController.navigate(route = NavigationRoutes.AccountNavigation.CallCredit.route) {
                        popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToEditProfile = {
                    navController.navigate(route = NavigationRoutes.AccountNavigation.EditProfile.route) {
                        popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToAddCookScreen = {
                    navController.navigate(route = NavigationRoutes.AccountNavigation.AddCookProfile.route) {
                        popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToPostJobScreen = {
                    navController.navigate(route = NavigationRoutes.AccountNavigation.PostJob.route) {
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
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.detailNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationRoutes.DetailsNavigation.NavigationRoute.route,
        startDestination = NavigationRoutes.DetailsNavigation.ProfileDetail.route
    ) {

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

        composable(route = NavigationRoutes.DetailsNavigation.ProfileReport.route) {
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

        composable(route = NavigationRoutes.DetailsNavigation.Message.route) {
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
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.accountNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationRoutes.AccountNavigation.NavigationRoute.route,
        startDestination = NavigationRoutes.AccountNavigation.EditProfile.route
    ) {
        composable(NavigationRoutes.AccountNavigation.EditProfile.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "detailNavGraph review data: ${Gson().toJson(profileResponse)}")
                    UpdateProfileScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToSignOut = { userType ->
                            if (userType.equals(AppConstants.PROVIDER, ignoreCase = true)) {
                                navController.navigate(route = NavigationRoutes.Unauthenticated.CookSignIn.route) {
                                     popUpTo(route = NavigationRoutes.AccountNavigation.NavigationRoute.route) {
                                         inclusive = true
                                     }
                                }
                            } else {
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
                    UserPreferencesScreen(
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

       // unauthenticatedGraph(navController = navController)
    }
}


/**
 * Cook Home screens bottom nav graph builder
 * (Unauthenticated user)
 */
@Composable
fun CookHomeNavGraph(navController: NavHostController) {
    NavHost(
        navController, route = NavigationRoutes.CookHomeNavigation.NavigationRoute.route,
        startDestination = NavigationRoutes.CookHomeNavigation.Home.route
    ) {

        //CooProfile List Screen
        composable(route = NavigationRoutes.CookHomeNavigation.Home.route) {
            CookProfilesScreen(
                navController = navController,
                onNavigateToProfileDetails = {
                    navController.navigate(route = NavigationRoutes.CookDetailsNavigation.CookProfileDetail.route) {
                        popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(NavigationRoutes.HomeNavigation.Search.route) { }

        // AddAddress
        composable(route = NavigationRoutes.CookHomeNavigation.Account.route) {
            CookAccountsScreen(
                navController = navController,
                onNavigateToUpdateCookProfile = {
                    navController.navigate(route = NavigationRoutes.CookAccountNavigation.UpdateCookProfile.route) {
                        popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToAddCookScreen = {
                    /*   navController.navigate(route = NavigationRoutes.AccountNavigation.AddCookProfile.route){
                           popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                               inclusive = true
                           }
                       }*/
                },
                onNavigateToManageAccountScreen = {
                    navController.navigate(route = NavigationRoutes.CookAccountNavigation.ManageCookAccount.route) {
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

        cookDetailNavGraph(navController = navController)
    }
}


/**
 * Cook Details screens nav graph builder
 * (Unauthenticated user)
 */
@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.cookDetailNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationRoutes.CookDetailsNavigation.NavigationRoute.route,
        startDestination = NavigationRoutes.CookDetailsNavigation.CookProfileDetail.route
    ) {

        composable(NavigationRoutes.CookDetailsNavigation.CookProfileDetail.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    profileResponse?.let {
                        Log.d("TAG", "cookDetailNavGraph Data : $profileResponse")
                        CookProfileDetailScreen(
                            navController = navController,
                            onNavigateBack = { navController.navigateUp() },
                            profileResponse = profileResponse,
                            onNavigateToReViewScreen = { navController.navigate(route = NavigationRoutes.CookDetailsNavigation.CookProfileReview.route) },
                            onNavigateToReportScreen = { navController.navigate(route = NavigationRoutes.CookDetailsNavigation.CookProfileReport.route)  },
                            onNavigateToCallCreditScreen = { navController.navigate(route = NavigationRoutes.AccountNavigation.CallCredit.route) },
                            onNavigateToMessageScreen = { navController.navigate(route = NavigationRoutes.CookDetailsNavigation.CookMessage.route) },
                            onNavigateToAuthenticatedHomeRoute = { navController.navigate(route = NavigationRoutes.CookDetailsNavigation.CookProfileDetail.route) }
                        )
                    }
                }
        }


        composable(route = NavigationRoutes.CookDetailsNavigation.CookProfileReview.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "cookDetailNavGraph data: ${Gson().toJson(profileResponse)}")
                    ReviewScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedHomeRoute = {
                            navController.navigate(route = NavigationRoutes.CookDetailsNavigation.CookProfileReview.route) {
                                /* popUpTo(route = NavigationRoutes.HomeNavigation.NavigationRoute.route) {
                                    inclusive = true
                                }*/
                            }
                        }
                    )
                }
        }

        composable(route = NavigationRoutes.CookDetailsNavigation.CookProfileReport.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "cookDetailNavGraph review data: ${Gson().toJson(profileResponse)}")
                    ReportScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedHomeRoute = {
                            navController.navigate(route = NavigationRoutes.CookDetailsNavigation.CookProfileReport.route) {
                                /*    popUpTo(route = NavigationRoutes.HomeNavigation.NavigationRoute.route) {
                                inclusive = true
                                }*/
                            }
                        }
                    )
                }
        }

        composable(route = NavigationRoutes.CookDetailsNavigation.CookMessage.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "cookDetailNavGraph review data: ${Gson().toJson(profileResponse)}")
                    MessageScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onSendMessage = {},
                        onNavigateToAuthenticatedHomeRoute = {
                            navController.navigate(route = NavigationRoutes.CookDetailsNavigation.CookMessage.route) {
                                /*    popUpTo(route = NavigationRoutes.HomeNavigation.NavigationRoute.route) {
                                inclusive = true
                                }*/
                            }
                        }
                    )
                }
        }

        cookAccountNavGraph(navController = navController)
    }
}

/**
 * Account screens nav graph builder
 * (Unauthenticated user)
 */
fun NavGraphBuilder.cookAccountNavGraph(navController: NavHostController) {
    navigation(
        route = NavigationRoutes.CookAccountNavigation.NavigationRoute.route,
        startDestination = NavigationRoutes.CookAccountNavigation.ManageCookAccount.route
    ) {
        composable(NavigationRoutes.CookAccountNavigation.UpdateCookProfile.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d("TAG", "cookAccountNavGraph review data: ${Gson().toJson(profileResponse)}")
                    UpdateCookProfileScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToSignOut = { userType ->
                            if (userType.equals(AppConstants.PROVIDER, ignoreCase = true)) {
                                navController.navigate(route = NavigationRoutes.Unauthenticated.CookSignIn.route) {
                                    /* popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                                         inclusive = true
                                     }*/
                                }
                            } else {
                                navController.navigate(route = NavigationRoutes.Unauthenticated.SignIn.route) {
                                    /*   popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                                           inclusive = true
                                       }*/
                                }
                            }
                        },
                        onNavigateToAuthenticatedRoute = {
                            navController.navigate(route = NavigationRoutes.CookAccountNavigation.UpdateCookProfile.route) {
                                /* popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }*/
                            }
                        },
                    )
                }
        }

        composable(route = NavigationRoutes.CookAccountNavigation.ManageCookAccount.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d(
                        "TAG",
                        "cookAccountNavGraph review data: ${Gson().toJson(profileResponse)}"
                    )
                    ManageCookAccountScreen(
                        navController = navController,
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedRoute = {
                            navController.navigate(route = NavigationRoutes.CookAccountNavigation.ManageCookAccount.route) {
                                 popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                                    inclusive = true
                                }
                            }
                        },
                        onNavigateToCookProfileDetail = {
                            navController.navigate(route = NavigationRoutes.CookDetailsNavigation.CookProfileDetail.route) {
                                popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                                    inclusive = true
                                }
                            }
                        },
                        onNavigateToUploadCuisines = {
                            navController.navigate(route = NavigationRoutes.CookAccountNavigation.UploadCuisines.route) {
                                popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                                    inclusive = true
                                }
                            }
                        },
                        onNavigateToUploadAadhaar = {
                            navController.navigate(route = NavigationRoutes.CookAccountNavigation.UploadAadhaar.route) {
                                popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                                    inclusive = true
                                }
                            }
                        },
                        onNavigateToManageTimeSlots = {
                            navController.navigate(route = NavigationRoutes.CookAccountNavigation.ManageTimeSlots.route) {
                                popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                                    inclusive = true
                                }
                            }
                        },
                        onNavigateToCookJobList = {
                            navController.navigate(route = NavigationRoutes.CookAccountNavigation.CookJobList.route) {
                                popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                                    inclusive = true
                                }
                            }
                        },
                        onNavigateToPersonalInfo = {
                            navController.navigate(route = NavigationRoutes.CookAccountNavigation.PersonalInfo.route) {
                                popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                                    inclusive = true
                                }
                            }
                        },
                        onNavigateToEditCookProfile = {
                            navController.navigate(route = NavigationRoutes.CookAccountNavigation.EditCookProfile.route) {
                                popUpTo(route = NavigationRoutes.Authenticated.NavigationRoute.route) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
        }

        composable(route = NavigationRoutes.CookAccountNavigation.UploadCuisines.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d(
                        "TAG",
                        "cookAccountNavGraph review data: ${Gson().toJson(profileResponse)}"
                    )
                    UploadCuisineImagesScreen(
                        navController = navController,
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedRoute = {}
                    )
                }
        }

        composable(route = NavigationRoutes.CookAccountNavigation.UploadAadhaar.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d(
                        "TAG",
                        "cookAccountNavGraph review data: ${Gson().toJson(profileResponse)}"
                    )
                    UploadAadhaarScreen(
                        navController = navController,
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedRoute = {}
                    )
                }
        }

        composable(route = NavigationRoutes.CookAccountNavigation.ManageTimeSlots.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d(
                        "TAG",
                        "cookAccountNavGraph review data: ${Gson().toJson(profileResponse)}"
                    )
                    ManageTimeSlotsScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedRoute = {}
                    )
                }
        }

        composable(route = NavigationRoutes.CookAccountNavigation.CookJobList.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d(
                        "TAG",
                        "cookAccountNavGraph review data: ${Gson().toJson(profileResponse)}"
                    )
                    CookJobListScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedRoute = {}
                    )
                }
        }

        composable(route = NavigationRoutes.CookAccountNavigation.PersonalInfo.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d(
                        "TAG",
                        "cookAccountNavGraph review data: ${Gson().toJson(profileResponse)}"
                    )
                    CookPersonalInfoScreen (
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedRoute = {}
                    )
                }
        }

        composable(route = NavigationRoutes.CookAccountNavigation.EditCookProfile.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<String>("profileResponse")
                .let { Gson().fromJson(it, ProfileResponse::class.java) }.let { profileResponse ->
                    Log.d(
                        "TAG",
                        "cookAccountNavGraph review data: ${Gson().toJson(profileResponse)}"
                    )
                    EditCookProfileScreen(
                        onNavigateBack = { navController.navigateUp() },
                        profileResponse = profileResponse,
                        onNavigateToAuthenticatedRoute = {}
                    )

                }
        }

    }
}
