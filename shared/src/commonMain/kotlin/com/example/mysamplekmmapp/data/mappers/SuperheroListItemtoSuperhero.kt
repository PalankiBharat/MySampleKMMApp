
package com.example.mysamplekmmapp.data.mappers

import com.example.mysamplekmmapp.data.local.SuperHero
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import io.realm.kotlin.ext.toRealmList

fun SuperheroListResponseItem.toSuperheroLocal():SuperHero{
    val remoteSuperhero = this
    return SuperHero().apply {
        this.name = remoteSuperhero.name
        this.image = remoteSuperhero.images?.lg
        this.appearance.apply {
            this?.gender = remoteSuperhero.appearance?.gender
            remoteSuperhero.appearance?.height?.let {
                this?.height = it.toRealmList()
            }
            remoteSuperhero.appearance?.weight?.let {
                this?.weight = it.toRealmList()
            }
        }
        this.powerstats.apply {
            this?.speed = remoteSuperhero.powerstats?.speed
            this?.intelligence = remoteSuperhero.powerstats?.intelligence
            this?.power = remoteSuperhero.powerstats?.power
            this?.combat = remoteSuperhero.powerstats?.combat
            this?.durability = remoteSuperhero.powerstats?.durability
            this?.strength = remoteSuperhero.powerstats?.strength
        }
        this.id = remoteSuperhero.id
        this.connections.apply {
            this?.relatives = remoteSuperhero.connections?.relatives
            this?.groupAffiliation = remoteSuperhero.connections?.groupAffiliation
        }
        this.occupation = remoteSuperhero.work?.occupation

        this.biography.apply {
            this?.fullName = remoteSuperhero.biography?.fullName
            this?.firstAppearance = remoteSuperhero.biography?.firstAppearance
            this?.publisher = remoteSuperhero.biography?.publisher
            this?.alterEgos = remoteSuperhero.biography?.alterEgos
            remoteSuperhero.biography?.aliases?.let {
                this?.aliases =  it.toRealmList()
            }
            this?.alignment = remoteSuperhero.biography?.alignment
            this?.placeOfBirth = remoteSuperhero.biography?.placeOfBirth
        }
    }

}