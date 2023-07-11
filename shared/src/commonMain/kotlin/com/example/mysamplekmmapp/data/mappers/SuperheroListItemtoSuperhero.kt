
package com.example.mysamplekmmapp.data.mappers

import com.example.mysamplekmmapp.data.local.Appearance
import com.example.mysamplekmmapp.data.local.Biography
import com.example.mysamplekmmapp.data.local.Connections
import com.example.mysamplekmmapp.data.local.Powerstats
import com.example.mysamplekmmapp.data.local.SuperHero
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import io.realm.kotlin.ext.toRealmList

fun SuperheroListResponseItem.toSuperheroLocal():SuperHero{
    val remoteSuperhero = this

    return SuperHero().apply {
        val localSuperhero = this
        localSuperhero.name = remoteSuperhero.name
        localSuperhero.image = remoteSuperhero.images?.lg
        localSuperhero.appearance = Appearance().apply {
            val appearance = this
            appearance.gender = remoteSuperhero.appearance?.gender
            remoteSuperhero.appearance?.height?.let {
                this.height = it.toRealmList()
            }
            remoteSuperhero.appearance?.weight?.let {
                this.weight = it.toRealmList()
            }
        }
        localSuperhero.powerstats = Powerstats().apply {
            val powerstats = this
            powerstats.speed = remoteSuperhero.powerstats?.speed
            powerstats.intelligence = remoteSuperhero.powerstats?.intelligence
            powerstats.power = remoteSuperhero.powerstats?.power
            powerstats.combat = remoteSuperhero.powerstats?.combat
            powerstats.durability = remoteSuperhero.powerstats?.durability
            powerstats.strength = remoteSuperhero.powerstats?.strength
        }

        localSuperhero.id = remoteSuperhero.id
        localSuperhero.connections= Connections().apply {
            this.relatives = remoteSuperhero.connections?.relatives
            this.groupAffiliation = remoteSuperhero.connections?.groupAffiliation
        }
        localSuperhero.occupation = remoteSuperhero.work?.occupation

        localSuperhero.biography= Biography().apply {
            val biography = this
            biography.fullName = remoteSuperhero.biography?.fullName
            biography.firstAppearance = remoteSuperhero.biography?.firstAppearance
            biography.publisher = remoteSuperhero.biography?.publisher
            biography.alterEgos = remoteSuperhero.biography?.alterEgos
            remoteSuperhero.biography?.aliases?.let {
                this.aliases =  it.toRealmList()
            }
            biography.alignment = remoteSuperhero.biography?.alignment
            biography.placeOfBirth = remoteSuperhero.biography?.placeOfBirth
        }
    }

}