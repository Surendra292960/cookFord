package com.example.cook_ford.presentation.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface Dimensions {
    val paddingTooSmall: Dp
    val paddingExtraSmall: Dp
    val paddingSmall: Dp
    val paddingNormal: Dp
    val paddingLarge: Dp
    val paddingExtraLarge: Dp
    val normalButtonHeight: Dp
    val mediumButtonHeight: Dp
    val smallButtonHeight: Dp
    val minButtonWidth: Dp
    val maxButtonWidth: Dp

}

val normalDimensions: Dimensions = object : Dimensions {
    override val paddingTooSmall: Dp
        get() = 2.dp
    override val paddingExtraSmall: Dp
        get() = 4.dp
    override val paddingSmall: Dp
        get() = 8.dp
    override val paddingNormal: Dp
        get() = 16.dp
    override val paddingLarge: Dp
        get() = 24.dp
    override val paddingExtraLarge: Dp
        get() = 32.dp
    override val normalButtonHeight: Dp
        get() = 56.dp
    override val mediumButtonHeight: Dp
        get() = 48.dp
    override val smallButtonHeight: Dp
        get() = 40.dp
    override val minButtonWidth: Dp
        get() = 120.dp
    override val maxButtonWidth: Dp
        get() = 240.dp
}