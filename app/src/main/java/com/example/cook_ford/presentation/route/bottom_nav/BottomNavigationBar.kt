package com.example.cook_ford.presentation.route.bottom_nav

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cook_ford.presentation.component.widgets.MediumTitleText
import com.example.cook_ford.presentation.route.NavigationRoutes
import com.example.cook_ford.presentation.theme.OrangeYellow1

/**
 * Composable function that represents the bottom navigation bar of the application.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */
@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    /*title: (String) -> Unit,*/
    topBarVisibility: (Boolean) -> Unit, ) {
    val navItems = listOf(
        NavigationRoutes.HomeNavigation.Home,
        NavigationRoutes.HomeNavigation.Search,
        // NavigationRoutes.BottomNavigation.List,
        NavigationRoutes.HomeNavigation.Account
    )
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var bottomNavVisibility by remember { mutableStateOf(true) }

    if (bottomNavVisibility) {
        BottomNavigation(
            backgroundColor = Color.White,
            modifier = Modifier
                .shadow(
                    shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                    elevation = 12.dp,
                )
                .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))) {
            navItems.forEach { screen ->
                NavigationBarItem(
                    alwaysShowLabel = true,
                    icon = {
                        Icon(
                            screen.icon,
                            contentDescription = screen.title,
                            tint = if (navBackStackEntry?.destination?.route == screen.route) OrangeYellow1 else Color.Gray,
                        )
                    },
                    label = {
                        MediumTitleText(
                            modifier = Modifier,
                            text = screen.title,
                            fontWeight = FontWeight.W700,
                            textAlign = TextAlign.Center,
                            textColor = if (navBackStackEntry?.destination?.route == screen.route) OrangeYellow1 else Color.Gray,
                        )
                    },
                    selected = navBackStackEntry?.destination?.route == screen.route,
                    onClick = {

                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                          /*  popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }*/
                            // Avoid multiple copies of the same destination when
                            // re selecting the same item
                            launchSingleTop = true
                            // Restore state when re selecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    //hide topBar
    /**
     * Home Navigation
     */
    when (navBackStackEntry?.destination?.route) {
        //User Home Navigation
        NavigationRoutes.HomeNavigation.Home.route -> {
            bottomNavVisibility = true
            topBarVisibility.invoke(true)
        }
        NavigationRoutes.HomeNavigation.Search.route -> {
            bottomNavVisibility = true
            topBarVisibility.invoke(true)
        }
        NavigationRoutes.HomeNavigation.Account.route -> {
            bottomNavVisibility = true
            topBarVisibility.invoke(true)
        }

        /**
         * Profile Details Navigation
         */
        NavigationRoutes.DetailsNavigation.ProfileDetail.route -> {
            bottomNavVisibility = false
            topBarVisibility.invoke(true)
        }
        NavigationRoutes.DetailsNavigation.ProfileReview.route -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.DetailsNavigation.ProfileReport.route -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.DetailsNavigation.Message.route -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }

        /**
         * Account Navigation
         */
        NavigationRoutes.AccountNavigation.EditProfile.route  -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.AccountNavigation.AddCookProfile.route -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.AccountNavigation.PostJob.route -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.AccountNavigation.CookPreferences.route -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.AccountNavigation.CallCredit.route -> {
            bottomNavVisibility = false
            topBarVisibility(false)
        }

        /**
         * Cook Home Navigation
         * */
        NavigationRoutes.CookHomeNavigation.Home.route -> {
            bottomNavVisibility = true
            topBarVisibility.invoke(true)
        }
        NavigationRoutes.CookHomeNavigation.Search.route -> {
            bottomNavVisibility = true
            topBarVisibility.invoke(true)
        }
        NavigationRoutes.CookHomeNavigation.Account.route -> {
            bottomNavVisibility = true
            topBarVisibility.invoke(true)
        }

        /**
         * Cook Account Navigation
         */
        NavigationRoutes.CookAccountNavigation.UpdateCookProfile.route  -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.CookAccountNavigation.ManageCookAccount.route -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.CookAccountNavigation.UploadCuisines.route  -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.CookAccountNavigation.UploadAadhaar.route  -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.CookAccountNavigation.ManageTimeSlots.route  -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.CookAccountNavigation.CookJobList.route  -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }

        /**
         * Profile Details Navigation
         */
        NavigationRoutes.CookDetailsNavigation.CookProfileDetail.route -> {
            bottomNavVisibility = false
            topBarVisibility.invoke(true)
        }
        NavigationRoutes.CookDetailsNavigation.CookProfileReview.route -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.CookDetailsNavigation.CookProfileReport.route -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }
        NavigationRoutes.CookDetailsNavigation.CookMessage.route -> {
            bottomNavVisibility = false
            topBarVisibility(true)
        }

        /*
        DetailScreen.NotificationScreen.route -> {
            bottomNavVisibility = false
            isVisible(false)
        }*/
        else -> {
            bottomNavVisibility = true
            topBarVisibility(false)
        }
    }
}