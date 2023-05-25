package com.example.mysamplekmmapp.data.remote.repository

import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.example.mysamplekmmapp.data.remote.api.SuperheroApi
import kotlinx.coroutines.flow.Flow

class AppRepository(val api: SuperheroApi) {
    suspend fun getSuperheroList(): List<SuperheroListResponseItem?> {
       return api.getSuperHeroList()
    }

    suspend fun getSuperheroById(id:Int): SuperheroListResponseItem? {
        return api.getSuperHeroById(id)
    }


}