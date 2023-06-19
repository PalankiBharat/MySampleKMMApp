package com.example.mysamplekmmapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.mysamplekmmapp.data.remote.viewModels.SuperheroListingViewModel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
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

actual class NetworkUtils actual constructor() {
    lateinit var context: Context
        private set

    constructor(context: Context) : this() {
        this.context = context
    }

    actual fun isNetworkAvailable(): Boolean {
       return context.checkForInternetConnection()
    }
}

actual fun platformModule() = module {
    viewModel { SuperheroListingViewModel(get()) }
}

fun Context.checkForInternetConnection(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
    return when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
        else -> false
    }
}