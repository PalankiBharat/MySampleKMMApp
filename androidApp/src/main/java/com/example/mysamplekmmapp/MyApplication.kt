package com.example.mysamplekmmapp

import android.app.Application
import android.content.Context
import com.example.mysamplekmmapp.android.MainViewModel
import com.example.mysamplekmmapp.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


class MyApplication :Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(sharedModule)
            modules(module {
                viewModel { MainViewModel(get()) }
            })
        }
    }
}