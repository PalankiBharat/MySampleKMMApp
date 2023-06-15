package com.example.mysamplekmmapp.android.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.google.gson.Gson
import org.koin.androidx.compose.koinViewModel

inline fun <reified A> String.deserialize():A{
    return Gson().fromJson(this,A::class.java)
}

inline fun <reified T> T.serialize():String{
    return Gson().toJson(this)
}

