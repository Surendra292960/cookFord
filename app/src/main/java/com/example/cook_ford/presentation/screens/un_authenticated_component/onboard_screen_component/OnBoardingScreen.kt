package com.example.cook_ford.presentation.screens.un_authenticated_component.onboard_screen_component
import android.util.Log
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cook_ford.presentation.theme.FontName
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(navController: NavController, onNavigateToAuthenticatedRoute: () -> Unit) {
    val items = OnBoardingItems.getData()
    val scope = rememberCoroutineScope()
    val pageState = rememberPagerState(pageCount = {3} )

    Column(modifier = Modifier.fillMaxSize()) {
        TopSection(
            onBackClick = {
                if (pageState.currentPage + 1 > 1) scope.launch {
                    pageState.scrollToPage(pageState.targetPage - 1)
                }
            },
            onSkipClick = {
                if (pageState.currentPage + 1 < items.size) scope.launch {
                    pageState.scrollToPage(items.size - 1)
                }
            }
        )

        HorizontalPager(
            state = pageState,
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth()) { page ->
            OnBoardingItem(items = items[page])
        }

        BottomSection(size = items.size, index = pageState.currentPage) {
            Log.d("TAG", "OnBoardingScreen: ${pageState.currentPage}")
            if (pageState.currentPage + 1 < items.size) scope.launch {
                pageState.scrollToPage(pageState.targetPage + 1)
            }else{
                if(pageState.currentPage + 1 == items.size) onNavigateToAuthenticatedRoute()
            }
        }
    }
}

@Composable
fun TopSection(onBackClick: () -> Unit = {}, onSkipClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
        // Back button
        IconButton( onClick = onBackClick, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(imageVector = Icons.Outlined.ArrowBackIosNew, contentDescription = null, modifier =
            Modifier
                .shadow(0.dp)
                .clip(CircleShape))
        }

        // Skip Button
        TextButton(
            shape = CircleShape,
            onClick = onSkipClick,
            modifier = Modifier.align(Alignment.CenterEnd),
            contentPadding = PaddingValues(5.dp)) {

            Text(text = "Skip",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 17.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .shadow(0.dp)
                    .clip(CircleShape))
        }
    }
}

@Composable
fun BottomSection(size: Int, index: Int, onButtonClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        // Indicators
        Indicators(size, index)

        // FAB Next
        FloatingActionButton(
            onClick = onButtonClick,
            containerColor = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clip(RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))) {
            Icon(Icons.Outlined.KeyboardArrowRight,
                tint = Color.White,
                contentDescription = "Localized description")
        }
    }
}

@Composable
fun BoxScope.Indicators(size: Int, index: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        repeat(size) {
            Indicator(isSelected = it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy), label = ""
    )

    Box(
        modifier = Modifier
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.primary else Color(0XFFF8E2E7)
            )
    )
}

@Composable
fun OnBoardingItem(items: OnBoardingItems) {
    Column(modifier = Modifier
        .padding(start = 30.dp, end = 30.dp)
        .fillMaxSize()
        .verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Cook Ford",
            fontFamily = FontName,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Best place to find cook for home",
            fontSize = 17.sp,
            fontWeight = FontWeight.Normal,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = items.image),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )

        Text(
            text = stringResource(id = items.title),
            fontSize = 20.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.W900,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(id = items.desc),
            fontSize = 15.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.W400,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(20.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewFunction(){
    Surface(modifier = Modifier.fillMaxSize()) {
        OnBoardingScreen(navController = NavController(LocalContext.current), onNavigateToAuthenticatedRoute = {})
    }
}