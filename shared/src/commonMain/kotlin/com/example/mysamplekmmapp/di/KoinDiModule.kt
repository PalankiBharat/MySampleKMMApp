package com.example.mysamplekmmapp.di

import com.example.mysamplekmmapp.NetworkUtils
import com.example.mysamplekmmapp.data.local.Appearance
import com.example.mysamplekmmapp.data.local.Biography
import com.example.mysamplekmmapp.data.local.Connections
import com.example.mysamplekmmapp.data.local.Powerstats
import com.example.mysamplekmmapp.data.local.SuperHero
import com.example.mysamplekmmapp.data.remote.api.SuperheroApi
import com.example.mysamplekmmapp.data.remote.repository.AppRepository
import com.example.mysamplekmmapp.data.remote.viewModels.SuperheroListingViewModel
import com.example.mysamplekmmapp.platformModule
import io.ktor.client.HttpClient
import io.ktor.serialization.kotlinx.json.json
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val sharedModule = module {
    single { AppRepository(get(), get()) }
    single { SuperheroApi() }
    single { provideRealm() }
}

fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) =
    startKoin {
        appDeclaration()
        modules(sharedModule)
        platformModule()
    }

fun initKoin() = initKoin() {}


fun provideRealm(): Realm {
    val config = RealmConfiguration.Builder(
        schema = setOf(
            SuperHero::class,
            Powerstats::class,
            Appearance::class,
            Connections::class,
            Biography::class
        )
    )
        .compactOnLaunch()
        .build()
    return Realm.open(config)
}

