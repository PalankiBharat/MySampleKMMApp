package com.example.mysamplekmmapp.data.remote.repository

import com.example.mysamplekmmapp.data.local.SuperHero
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.example.mysamplekmmapp.data.remote.api.SuperheroApi
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepository(val api: SuperheroApi, val realm:Realm) {
    suspend fun getSuperheroList(): List<SuperheroListResponseItem?> {
        val response = api.getSuperHeroList()

       return api.getSuperHeroList()
    }

    suspend fun getSuperheroesFromLocal(): Flow<RealmResults<SuperHero>> {
        return realm.query<SuperHero>().asFlow().map { it.list }
    }



}