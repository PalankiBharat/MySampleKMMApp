package com.example.mysamplekmmapp

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import java.util.concurrent.TimeUnit

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun httpClient(config: HttpClientConfig<*>.()->Unit) = HttpClient(OkHttp){
    config(this)
    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(5,TimeUnit.SECONDS)
        }
    }
}

actual fun initLogger(){
    Napier.base(DebugAntilog())
}