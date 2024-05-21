package com.example.cook_ford.presentation.common.widgets

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Progressbar(showProgressbar:Boolean){
    Log.d("TAG", "Progressbar isLoading: $showProgressbar")
    if (showProgressbar){
        Column (
            modifier = Modifier.fillMaxSize()
            .navigationBarsPadding()
            .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,){
            CircularProgressIndicator()
        }
    }
}
