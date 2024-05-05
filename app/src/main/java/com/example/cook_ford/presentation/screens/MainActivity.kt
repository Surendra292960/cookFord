package com.example.cook_ford.presentation.screens
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cook_ford.R
import com.example.cook_ford.presentation.route.NavigationRoutes
import com.example.cook_ford.presentation.route.authenticatedGraph
import com.example.cook_ford.presentation.route.unauthenticatedGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                MainAppNavHost()
            }
        }
    }
}
@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }))
        // Customize the delay time
        delay(3000L)
        navController.navigate(route = NavigationRoutes.Unauthenticated.SignIn.route)
    }

    // Image
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        // Change the logo
        Image(painter = painterResource(id = R.drawable.cook_ford_rounded_logo),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
                .height(200.dp)
                .width(200.dp)
        )
    }
}

@Composable
fun MainApp() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        MainAppNavHost()
    }

}

@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(), ) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
        // Unauthenticated user flow screens
        unauthenticatedGraph(navController = navController)

        // Authenticated user flow screens
        authenticatedGraph(navController = navController)
    }

}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview(){
    MainApp()
}

