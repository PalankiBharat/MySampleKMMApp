package com.example.mysamplekmmapp.di

import com.example.mysamplekmmapp.data.remote.api.SuperheroApi
import com.example.mysamplekmmapp.data.remote.repository.AppRepository
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.builtin.CallResponseConverter
import de.jensklingenberg.ktorfit.converter.builtin.FlowResponseConverter
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun provideKtorFit(): Ktorfit {
    return ktorfit {
        baseUrl("https://akabab.github.io/superhero-api/api/")
        httpClient(HttpClient {
            install(ContentNegotiation) {
                json(Json { isLenient = true; ignoreUnknownKeys = true })
            }
        })
        responseConverter(FlowResponseConverter(), CallResponseConverter())
    }
}

fun provideSuperheroApi(ktorfit: Ktorfit):SuperheroApi {
    return ktorfit.create<SuperheroApi>()
}

val sharedModule = module {
    single { AppRepository(get()) }
    single { provideKtorFit() }
    single { provideSuperheroApi(get()) }
}