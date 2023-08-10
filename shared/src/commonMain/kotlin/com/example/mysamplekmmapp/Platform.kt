package com.example.mysamplekmmapp

import app.cash.sqldelight.db.SqlDriver
import com.example.SuperheroDb
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import org.koin.core.module.Module

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun httpClient(config: HttpClientConfig<*>.()->Unit = {}):HttpClient

expect fun initLogger()

expect class NetworkUtils() {
    fun isNetworkAvailable(): Boolean
}

expect fun platformModule(): Module

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory):SuperheroDb {
    val driver = driverFactory.createDriver()
    val database = SuperheroDb(driver)
    return database
    // Do more work with the database (see below).
}

