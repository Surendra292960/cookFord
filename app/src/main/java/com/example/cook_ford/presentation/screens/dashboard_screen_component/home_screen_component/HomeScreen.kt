package com.example.cook_ford.presentation.screens.dashboard_screen_component.home_screen_component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cook_ford.presentation.route.bottom_nav.BottomNavigationBar
import com.example.cook_ford.presentation.route.bottom_nav.getTitleByRoute
import com.example.cook_ford.presentation.route.topbar_nav.NavTopBar
import com.example.cook_ford.presentation.route.HomeNavGraph
import com.example.cook_ford.presentation.theme.Cook_fordTheme
import com.example.cook_ford.utils.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDashBoard(
    navController: NavHostController = rememberNavController(),
    onNavigateToAuthenticatedRoute: () -> Unit) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val topBarVisibilityState = remember { mutableStateOf(true) }
    val appBarTitle = remember { mutableStateOf(AppConstants.EMPTY_STRING) }

    LaunchedEffect(key1 = Unit) {
        onNavigateToAuthenticatedRoute.invoke()
    }
    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            // You can map the title based on the route using:
            appBarTitle.value = backStackEntry.destination.route?.let { getTitleByRoute(it) }!!
            Log.d("TAG", "UserDashBoard: $appBarTitle")
        }
    }

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                NavTopBar(
                    modifier = Modifier,
                    onNavigateBack = {navController.navigateUp()},
                    title = appBarTitle.value,
                    isVisible = topBarVisibilityState.value,
                )
            }
        },
        //topBar = { CollapsingTopBar(scrollBehavior, isCollapsed, scroll) },
        bottomBar = {
            Surface(shadowElevation = 3.dp) {
                BottomNavigationBar(
                    navController = navController,
                   /* title = {title->appBarTitle.value = title}*/){ isVisible ->
                    topBarVisibilityState.value = isVisible
                }
            } }) { padding ->

        // Creating a Column Layout
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            HomeNavGraph(navController = navController)
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    Cook_fordTheme {
        UserDashBoard(
            onNavigateToAuthenticatedRoute = {}
        )
    }
}