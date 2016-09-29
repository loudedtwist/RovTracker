package de.archaeonautic.rovtracker.model.projectmodel

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Location(@PrimaryKey var id: Int, var name: String, var campaigns: RealmList<Campaign>) : RealmObject() {
    constructor() : this(0, "", RealmList<Campaign>())
}
