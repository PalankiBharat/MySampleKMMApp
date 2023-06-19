package com.example.mysamplekmmapp.data.local

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Powerstats : RealmObject {
    var strength: Int? = null
    var durability: Int? = null
    var combat: Int? = null
    var power: Int? = null
    var speed: Int? = null
    var intelligence: Int? = null
}

class Appearance : RealmObject {
    var gender: String? = null
    var weight: RealmList<String?> = realmListOf()
    var height: RealmList<String?> = realmListOf()
}

class SuperHero : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var image: String? = null
    var appearance: Appearance? = null
    var occupation: String? = null
    var name: String? = null
    var powerstats: Powerstats? = null
    var id: Int? = null
    var biography: Biography? = null
    var connections: Connections? = null
}
class Connections : RealmObject {
    var groupAffiliation: String? = null
    var relatives: String? = null
}

class Biography : RealmObject {
    var firstAppearance: String? = null
    var placeOfBirth: String? = null
    var aliases: RealmList<String?> = realmListOf()
    var fullName: String? = null
    var publisher: String? = null
    var alterEgos: String? = null
    var alignment: String? = null
}
