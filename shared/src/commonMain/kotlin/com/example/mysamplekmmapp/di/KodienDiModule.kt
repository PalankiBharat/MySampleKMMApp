package com.example.mysamplekmmapp.di

import com.example.mysamplekmmapp.data.remote.api.SuperheroApi
import de.jensklingenberg.ktorfit.converter.builtin.CallResponseConverter
import de.jensklingenberg.ktorfit.converter.builtin.FlowResponseConverter
import de.jensklingenberg.ktorfit.ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val di = DI{
    val ktorfit = lazy {
        ktorfit {
            baseUrl("https://akabab.github.io/superhero-api/api/")
            httpClient(HttpClient {
                install(ContentNegotiation) {
                    json(Json { isLenient = true; ignoreUnknownKeys = true })
                }
            })
            responseConverter(FlowResponseConverter(), CallResponseConverter())
        }
    }

    bindSingleton{
        ktorfit.value.create<SuperheroApi>()
    }


}