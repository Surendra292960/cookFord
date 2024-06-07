package com.example.cook_ford.presentation.component.widgets.bottom_nav

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cook_ford.presentation.route.NavigationRoutes
import com.example.cook_ford.utils.AppConstants

/**
 * Composable function that represents the bottom navigation bar of the application.
 *
 * @param navController The navigation controller used for handling navigation between screens.
 */
@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    /*title: (String) -> Unit,*/
    isVisible: (Boolean) -> Unit, ) {
    val navItems = listOf(
        NavigationRoutes.HomeNavigation.Home,
        NavigationRoutes.HomeNavigation.Search,
        // NavigationRoutes.BottomNavigation.List,
        NavigationRoutes.HomeNavigation.Profile
    )
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var bottomNavVisibility by remember { mutableStateOf(true) }

    if (bottomNavVisibility) {
        BottomNavigation(
            backgroundColor = Color.White,
            modifier = Modifier
                .background(color = Color.White)
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
                            tint = if (navBackStackEntry?.destination?.route == screen.route) MaterialTheme.colors.primary else LocalContentColor.current,
                        )
                    },
                    label = { Text(screen.title) },
                    //selected = selectedItem == index,
                    selected = navBackStackEntry?.destination?.route == screen.route,
                    onClick = {

                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
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
        NavigationRoutes.HomeNavigation.Home.route -> {
            bottomNavVisibility = true
            isVisible.invoke(true)
        }
        NavigationRoutes.HomeNavigation.Search.route -> {
            bottomNavVisibility = true
            isVisible.invoke(true)
        }
        NavigationRoutes.HomeNavigation.Profile.route -> {
            bottomNavVisibility = true
            isVisible.invoke(true)
        }
        /**
         * Detail Navigation
         */
        NavigationRoutes.DetailsNavigation.ProfileDetail.route + "/{${AppConstants.PROFILE_ID}}" -> {
            bottomNavVisibility = false
            isVisible.invoke(false)
        }
        NavigationRoutes.DetailsNavigation.ProfileReview.route + "/{${AppConstants.PROFILE_ID}}" -> {
            bottomNavVisibility = false
            isVisible(false)
        }
        NavigationRoutes.DetailsNavigation.ProfileReport.route + "/{${AppConstants.PROFILE_ID}}" -> {
            bottomNavVisibility = false
            isVisible(false)
        }

        /**
         * Account Navigation
         */
        NavigationRoutes.AccountNavigation.EditProfile.route + "/{${AppConstants.PROFILE_ID}}" -> {
            bottomNavVisibility = false
            isVisible(true)
        }
        NavigationRoutes.AccountNavigation.AddCookProfile.route + "/{${AppConstants.PROFILE_ID}}" -> {
            bottomNavVisibility = false
            isVisible(true)
        }
        NavigationRoutes.AccountNavigation.PostJob.route + "/{${AppConstants.PROFILE_ID}}" -> {
            bottomNavVisibility = false
            isVisible(true)
        }
        /*
        DetailScreen.NotificationScreen.route -> {
            bottomNavVisibility = false
            isVisible(false)
        }*/
        else -> {
            bottomNavVisibility = true
            isVisible(false)
        }
    }
}