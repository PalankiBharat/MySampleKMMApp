package com.example.mysamplekmmapp

import com.example.mysamplekmmapp.data.remote.repository.AppRepository
import com.example.mysamplekmmapp.data.remote.viewModels.SuperheroListingViewModel
import com.example.mysamplekmmapp.di.sharedModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class KoinHelperIOS : KoinComponent {
    private val superheroRepository : AppRepository  by inject()
    fun getAppRepo(): AppRepository = superheroRepository

    private val superheroViewModel : SuperheroListingViewModel  by inject()
    fun getSuperheroListingViewModel(): SuperheroListingViewModel = superheroViewModel
}