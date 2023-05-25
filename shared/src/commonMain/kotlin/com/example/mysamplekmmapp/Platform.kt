package com.example.mysamplekmmapp

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.http.ContentType

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun httpClient(config: HttpClientConfig<*>.()->Unit = {}):HttpClient

expect fun initLogger()

expect class NetworkUtils() {
    fun isNetworkAvailable(): Boolean
}