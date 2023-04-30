package com.example.mysamplekmmapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform