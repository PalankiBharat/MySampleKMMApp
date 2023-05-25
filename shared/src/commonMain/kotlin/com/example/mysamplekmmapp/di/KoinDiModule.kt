package com.example.mysamplekmmapp.di

import com.example.mysamplekmmapp.NetworkUtils
import com.example.mysamplekmmapp.data.remote.api.SuperheroApi
import com.example.mysamplekmmapp.data.remote.repository.AppRepository
import io.ktor.client.HttpClient
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

val sharedModule = module {
    single { AppRepository(get()) }
    single { SuperheroApi() }

}