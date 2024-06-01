package com.example.cook_ford.presentation.screens.authenticated.profile.list.product

import androidx.compose.ui.graphics.Color

data class Product(
    val id: Int=0,
    val image: Int=0,
    val productName: String="",
    val productDescription: String="",
    val price: String="",
    val color: Color=Color.Red,
    val rating: String=""
)

val size = (36..42).toList()