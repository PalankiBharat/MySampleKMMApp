package com.example.mysamplekmmapp.android.utils

import com.google.gson.Gson

inline fun <reified A> String.deserialize():A{
    return Gson().fromJson(this,A::class.java)
}

inline fun <reified T> T.serialize():String{
    return Gson().toJson(this)
}