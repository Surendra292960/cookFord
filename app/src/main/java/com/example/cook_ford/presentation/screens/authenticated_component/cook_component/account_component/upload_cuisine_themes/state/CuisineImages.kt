package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.upload_cuisine_themes.state

import com.example.cook_ford.utils.AppConstants

data class CuisineImages(
    val index: Int? = AppConstants.ZERO,
    var uri: String? = AppConstants.EMPTY_STRING
)

val item = listOf(
    CuisineImages(
        index = 1,
        uri = "http://image.tmdb.org/t/p/w500/Af4bXE63pVsb2FtbW8uYIyPBadD.jpg"
    ),
    CuisineImages(
        index = 2,
        uri = "http://image.tmdb.org/t/p/w500/Af4bXE63pVsb2FtbW8uYIyPBadD.jpg"
    ),
    CuisineImages(
        index = 3,
        uri = "http://image.tmdb.org/t/p/w500/Af4bXE63pVsb2FtbW8uYIyPBadD.jpg"
    ),
    CuisineImages(
        index = 4,
        uri = "http://image.tmdb.org/t/p/w500/Af4bXE63pVsb2FtbW8uYIyPBadD.jpg"
    ),
    CuisineImages(
        index = 5,
        uri = "http://image.tmdb.org/t/p/w500/Af4bXE63pVsb2FtbW8uYIyPBadD.jpg"
    ),
    CuisineImages(index = 6, uri = "http://image.tmdb.org/t/p/w500/Af4bXE63pVsb2FtbW8uYIyPBadD.jpg")
)