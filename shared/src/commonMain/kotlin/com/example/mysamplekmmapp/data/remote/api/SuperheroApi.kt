package com.example.mysamplekmmapp.data.remote.api

import com.example.mysamplekmmapp.data.model.Person
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import de.jensklingenberg.ktorfit.Call
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Query
import kotlinx.coroutines.flow.Flow

interface SuperheroApi {

    @GET("all.json")
    suspend fun getAllSuperheroList(): List<SuperheroListResponseItem?>


}