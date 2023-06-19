package com.example.mysamplekmmapp

import android.app.Application
import android.content.Context
import com.example.mysamplekmmapp.data.remote.viewModels.SuperheroListingViewModel
import com.example.mysamplekmmapp.di.initKoin
import com.example.mysamplekmmapp.di.sharedModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class MyApplication :Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(sharedModule)
            modules(module {
                single<Context> { this@MyApplication }
                NetworkUtils(get<Context>())
                viewModel { SuperheroListingViewModel(get()) }
            })


        }
    }

}