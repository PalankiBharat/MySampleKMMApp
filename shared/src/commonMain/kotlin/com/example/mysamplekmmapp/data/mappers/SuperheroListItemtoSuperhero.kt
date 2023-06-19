package com.example.mysamplekmmapp.data.mappers

import com.example.mysamplekmmapp.data.local.SuperHero
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem
import io.realm.kotlin.ext.toRealmList

fun SuperheroListResponseItem.toSuperheroLocal():SuperHero{
    return SuperHero().apply {
        this.name = this@toSuperheroLocal.name
        this.image = this@toSuperheroLocal.images?.lg
        this.appearance.apply {
            this?.gender = this@toSuperheroLocal.appearance?.gender
            this@toSuperheroLocal.appearance?.height?.let {
                this?.height = it.toRealmList()
            }
            this@toSuperheroLocal.appearance?.weight?.let {
                this?.weight = it.toRealmList()
            }
        }
        this.powerstats.apply {
            this?.speed = this@toSuperheroLocal.powerstats?.speed
            this?.intelligence = this@toSuperheroLocal.powerstats?.intelligence
            this?.power = this@toSuperheroLocal.powerstats?.power
            this?.combat = this@toSuperheroLocal.powerstats?.combat
            this?.durability = this@toSuperheroLocal.powerstats?.durability
            this?.strength = this@toSuperheroLocal.powerstats?.strength
        }
        this.id = this@toSuperheroLocal.id
        this.connections.apply {
            this?.relatives = this@toSuperheroLocal.connections?.relatives
            this?.groupAffiliation = this@toSuperheroLocal.connections?.groupAffiliation
        }
        this.occupation = this@toSuperheroLocal.work?.occupation

        this.biography.apply {
            this?.fullName = this@toSuperheroLocal.biography?.fullName
            this?.firstAppearance = this@toSuperheroLocal.biography?.firstAppearance
            this?.publisher = this@toSuperheroLocal.biography?.publisher
            this?.alterEgos = this@toSuperheroLocal.biography?.alterEgos
            this@toSuperheroLocal.biography?.aliases?.let {
                this?.aliases =  it.toRealmList()
            }
            this?.alignment = this@toSuperheroLocal.biography?.alignment
            this?.placeOfBirth = this@toSuperheroLocal.biography?.placeOfBirth
        }



    }
}