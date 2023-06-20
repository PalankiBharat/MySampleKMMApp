package com.example.mysamplekmmapp

import com.example.mysamplekmmapp.data.remote.viewModels.SuperheroListingViewModel
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.engine.ios.Ios
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun httpClient(config: HttpClientConfig<*>.()->Unit) = HttpClient(Darwin){
    config(this)
    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }.also { initLogger() }
}

actual fun initLogger(){
    Napier.base(DebugAntilog())
}

actual class NetworkUtils {
    actual fun isNetworkAvailable(): Boolean {
        return true
    }
}

actual fun platformModule() = module{
    single {
        SuperheroListingViewModel(get())
    }
}

object KoinHelper{
    val koinHelper = KoinHelperIOS()
}

