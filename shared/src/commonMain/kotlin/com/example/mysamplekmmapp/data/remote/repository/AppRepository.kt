package com.example.mysamplekmmapp.data.remote.repository

import com.example.mysamplekmmapp.data.local.SuperHero
import com.example.mysamplekmmapp.data.mappers.toSuperheroDataHolder
import com.example.mysamplekmmapp.data.mappers.toSuperheroLocal
import com.example.mysamplekmmapp.data.model.SuperheroDetailsDataHolder
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import com.example.mysamplekmmapp.data.remote.api.SuperheroApi
import io.github.aakira.napier.LogLevel
import io.github.aakira.napier.Napier
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class AppRepository(val api: SuperheroApi, val realm:Realm) {
    suspend fun getSuperheroList() {

        val superheroList = api.getSuperHeroList()
        if (superheroList.isNotEmpty())
        {
            realm.write {
                deleteAll()
            }
            api.getSuperHeroList().forEach {
                if (it != null) {
                    Napier.d(message = it.toSuperheroLocal().powerstats?.combat.toString(), tag = "add heroes")
                    addDataToLocalDB(it.toSuperheroLocal())
                }else{
                    Napier.d(message ="Not Added", tag = "getAllHeroes notAdded")
                }
            }
        }

    }

    fun getSuperheroesFromLocal(): Flow<List<SuperheroDetailsDataHolder>> {
        return realm.query<SuperHero>().asFlow().map { it.list.toList().also {
        }.map { item -> item.toSuperheroDataHolder() }}
    }

    private suspend fun addDataToLocalDB(superhero:SuperHero){
        try {
            realm.write { copyToRealm(superhero) }
        }
        catch (e:Exception)
        {
            Napier.d(message = e.message.toString(), tag = "getAllHeroes")
        }
    }



}