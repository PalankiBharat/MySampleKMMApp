package com.example.mysamplekmmapp.data.remote.api

import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.example.mysamplekmmapp.httpClient
import com.example.mysamplekmmapp.initLogger
import com.example.mysamplekmmapp.utils.ApiConstants.BASE_URL
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json


class SuperheroApi
{
    val httpClient = httpClient{
        install(ContentNegotiation){
            json()
        }
        install(Logging){
            level = LogLevel.ALL
            logger = object:Logger{
                override fun log(message: String) {
                    Napier.v(tag = "HTTP Client", message = message)
                }
            }.also { initLogger() }
        }
    }
    suspend fun getSuperHeroList(): List<SuperheroListResponseItem?> {
        val result = try {
            httpClient.get {
                url(BASE_URL)
                contentType(ContentType.Application.Json)
            }
        }catch (e:Exception)
        {
            throw CustomException(CustomError.SERVICE_UNAVAILABLE)
        }
        when(result.status.value) {
            in 200..299 -> Unit
            500 -> throw CustomException(CustomError.SERVER_ERROR)
            in 400..499 -> throw CustomException(CustomError.CLIENT_ERROR)
            else -> throw CustomException(CustomError.UNKNOWN_ERROR)
        }
        return try {
            result.body()
        } catch(e: Exception) {
            throw CustomException(CustomError.SERVER_ERROR)
        }
    }
}