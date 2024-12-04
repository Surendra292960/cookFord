package com.example.cook_ford.presentation.screens.authenticated_screen_component.user_component.profile_component.list_screen_component.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.wear.compose.material.Icon
import com.example.cook_ford.R
import com.example.cook_ford.presentation.component.widgets.SubTitleText
import com.example.cook_ford.presentation.theme.DeepGreen

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SetCardConstraint() {
    val constraintSet = ConstraintSet {
        val guidelineFromEnd = createGuidelineFromEnd(0.01f)
        val image = createRefFor("image")
        val rating = createRefFor("rating")

        constrain(image) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            bottom.linkTo(parent.bottom)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(rating) {
            top.linkTo(image.bottom, margin = 10.dp)
            start.linkTo(parent.start)
        }
        createVerticalChain(image, rating, chainStyle = ChainStyle.Spread)
        //createHorizontalChain(image, rating, chainStyle = ChainStyle.Spread)
    }

    ConstraintLayout(constraintSet, modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.male_chef),
            contentDescription = "Logo",
            modifier = Modifier.layoutId("image")
        )
        Icon(Icons.Default.Star, contentDescription = "", tint = DeepGreen)
        SubTitleText(text = "Rating", modifier = Modifier, textAlign = TextAlign.Start)
    }
}

/*

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Display(){

   *//* ConstraintLayout(SetCardConstraint() ,modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Image(painter = painterResource(id = R.drawable.slide_1), contentDescription = "Logo", modifier = Modifier.layoutId("image"))
        SubTitleText(text = "Rating", modifier = Modifier.layoutId("rating"))
    }*//*
}*/