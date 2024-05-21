package com.example.cook_ford.presentation.screens.dashboard.users

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cook_ford.presentation.common.widgets.bottom_nav.BottomNavigationBar
import com.example.cook_ford.presentation.common.widgets.topbar_nav.AppTopBar
import com.example.cook_ford.presentation.route.BottomNavigation
import com.example.cook_ford.presentation.route.NavigationRoutes
import com.example.cook_ford.presentation.route.authenticatedGraph
import com.example.cook_ford.presentation.route.unauthenticatedGraph
import com.example.cook_ford.presentation.theme.Cook_fordTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDashBoard() {
    val navController = rememberNavController()
    val scroll = rememberScrollState(0)
    val isCollapsed = remember { false }
    //val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Surface(shadowElevation = 3.dp) {
                AppTopBar(scrollBehavior)
            }
        },
        //topBar = { CollapsingTopBar(scrollBehavior, isCollapsed, scroll) },
        bottomBar = {
            Surface(shadowElevation = 3.dp) {
                BottomAppBar {
                    BottomNavigationBar(navController = navController)
                }
            } }) { padding ->

        // Creating a Column Layout
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BottomNavigation(navController = navController)
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    Cook_fordTheme {
        UserDashBoard()
    }
}