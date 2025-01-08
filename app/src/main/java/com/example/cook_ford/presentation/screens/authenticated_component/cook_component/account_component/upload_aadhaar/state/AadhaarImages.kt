package com.example.cook_ford.presentation.screens.authenticated_component.cook_component.account_component.upload_aadhaar.state

import com.example.cook_ford.utils.AppConstants

data class AadhaarImages(
    val title: String? = AppConstants.EMPTY_STRING,
    var uri: String? = AppConstants.EMPTY_STRING
)

val data = listOf(
    AadhaarImages(
        title = "Aadhaar front",
        uri = "http://image.tmdb.org/t/p/w500/Af4bXE63pVsb2FtbW8uYIyPBadD.jpg"
    ),
    AadhaarImages(
        title = "Aadhaar back",
        uri = "http://image.tmdb.org/t/p/w500/Af4bXE63pVsb2FtbW8uYIyPBadD.jpg"
    )
)