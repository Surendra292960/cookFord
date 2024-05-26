package com.example.cook_ford.presentation.common.widgets.bottom_nav

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
    isVisible: (Boolean) -> Unit,
) {
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
            navItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    alwaysShowLabel = true,
                    icon = {
                        Icon(
                            item.icon,
                            contentDescription = item.title,
                            tint = if (navBackStackEntry?.destination?.route == item.route) MaterialTheme.colors.primary else LocalContentColor.current,
                        )
                    },
                    label = { Text(item.title) },
                    //selected = selectedItem == index,
                    selected = navBackStackEntry?.destination?.route == item.route,
                    onClick = {
                     //  title.invoke(item.title)
                        selectedItem = index
                        navController.navigate(item.route)
                        /*{
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }*/
                    }
                )
            }
        }
    }

    //hide topBar
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
        NavigationRoutes.DetailsNavigation.ProfileDetail.route + "/{${AppConstants.PROFILE_ID}}" -> {
            bottomNavVisibility = false
            isVisible.invoke(false)
        }
        NavigationRoutes.DetailsNavigation.ProfileReview.route -> {
            bottomNavVisibility = false
            isVisible(false)
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