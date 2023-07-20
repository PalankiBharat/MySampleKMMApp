package com.example.mysamplekmmapp.data.mappers

import com.example.mysamplekmmapp.data.local.SuperHero
import com.example.mysamplekmmapp.data.model.SuperheroDetailsDataHolder
import com.example.mysamplekmmapp.data.model.SuperheroListResponseItem

fun SuperheroListResponseItem?.toSuperheroDataHolder(): SuperheroDetailsDataHolder {
    val alisases =
        if (this?.biography?.aliases?.isEmpty() != true) "also known as " + this?.biography?.aliases?.joinToString() else ""
    return SuperheroDetailsDataHolder(
        id = this?.id ?:-1,
        name = this?.name ?: "No Name Found",
        bio = "${this?.name}${this?.biography?.fullName?.let { " aka $it" } ?: ""} $alisases was born at ${this?.biography?.placeOfBirth ?: "New Jersy"}",
        imageUrl = this?.images?.lg
            ?: "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/2-abe-sapien.jpg",
        strength = this?.powerstats?.strength ?: 0,
        durability = this?.powerstats?.durability ?: 0,
        combat = this?.powerstats?.combat ?: 0,
        power = this?.powerstats?.power ?: 0,
        speed = this?.powerstats?.speed ?: 0,
        intelligence = this?.powerstats?.intelligence ?: 0,
        weight = this?.appearance?.weight?.getOrElse(1) { "" } ?: "No Weight Records Found",
        height = this?.appearance?.height?.getOrElse(1) { "" } ?: "No Height Records Found"
    )
}

fun SuperHero?.toSuperheroDataHolder(): SuperheroDetailsDataHolder {
    val alisases =
        if (this?.biography?.aliases?.isEmpty() != true) "also known as " + this?.biography?.aliases?.joinToString() else ""
    return SuperheroDetailsDataHolder(
        id = this?.id ?:-1,
        name = this?.name ?: "No Name Found",
        bio = "${this?.name}${this?.biography?.fullName?.let { " aka $it" } ?: ""} $alisases was born at ${this?.biography?.placeOfBirth ?: "New Jersy"}",
        imageUrl = this?.image
            ?: "https://cdn.jsdelivr.net/gh/akabab/superhero-api@0.3.0/api/images/lg/2-abe-sapien.jpg",
        strength = this?.powerstats?.strength ?: 0,
        durability = this?.powerstats?.durability ?: 0,
        combat = this?.powerstats?.combat ?: 0,
        power = this?.powerstats?.power ?: 0,
        speed = this?.powerstats?.speed ?: 0,
        intelligence = this?.powerstats?.intelligence ?: 0,
        weight = this?.appearance?.weight?.getOrElse(1) { "" } ?: "No Weight Records Found",
        height = this?.appearance?.height?.getOrElse(1) { "" } ?: "No Height Records Found"
    )
}