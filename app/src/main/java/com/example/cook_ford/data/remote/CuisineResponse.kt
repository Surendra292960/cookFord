package com.example.cook_ford.data.remote


data class CuisineResponse(
    val cuisine: List<Cuisine>,
)

data class Cuisine(
    val id: Long,
    val cuisinetype: String,
)