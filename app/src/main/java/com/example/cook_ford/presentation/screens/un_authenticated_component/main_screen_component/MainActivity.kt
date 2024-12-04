package com.example.cook_ford.presentation.screens.un_authenticated_component.main_screen_component
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.cook_ford.R
import com.example.cook_ford.data.local.SessionConstant.ACCESS_TOKEN
import com.example.cook_ford.data.local.SessionConstant.USER_TYPE
import com.example.cook_ford.data.local.UserSession
import com.example.cook_ford.presentation.route.NavigationRoutes
import com.example.cook_ford.presentation.route.authenticatedGraph
import com.example.cook_ford.presentation.route.unauthenticatedGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  this.window.statusBarColor = ContextCompat.getColor(this,R.color.white)
        Log.d("TAG", "SplashScreen onCreate : ")
        setContent {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                MainAppNavHost()
            }
        }
    }
}
@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current as MainActivity
    val userSession = remember { UserSession(context) }

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
        delay(2000L)
        Log.d("TAG", "SplashScreen Data : ")
      /*  if (userSession.check(ACCESS_TOKEN)){
            navController.navigate(route = NavigationRoutes.Unauthenticated.OnBoard.route)
        }else*/ if (userSession.check(ACCESS_TOKEN)) {
        Log.d("TAG", "SplashScreen if: ${userSession.check(ACCESS_TOKEN)}")
        navController.navigate(route = NavigationRoutes.Authenticated.Dashboard.route) {
            popUpTo(NavigationRoutes.UnauthenticatedUser.Splash.route) {
                inclusive = true
            }
        }
    } else {
        Log.d("TAG", "SplashScreen else: ${userSession.check(ACCESS_TOKEN)}")
        navController.navigate(route = NavigationRoutes.UnauthenticatedUser.Onboard.route) {
            popUpTo(NavigationRoutes.UnauthenticatedUser.Splash.route) {
                inclusive = true
            }
        }
    }
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


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationRoutes.UnauthenticatedUser.NavigationRoute.route) {

        // Unauthenticated user flow screens
        unauthenticatedGraph(navController = navController)

        // Authenticated user flow screens
        authenticatedGraph(navController = navController)
    }
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun MainApp() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        MainAppNavHost()
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview(){
    MainApp()
}

