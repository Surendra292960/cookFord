package com.example.cook_ford.presentation.common.widgets

import android.content.Context
import android.widget.Toast

fun mToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
} 