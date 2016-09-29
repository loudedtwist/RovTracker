package de.archaeonautic.rovtracker.Model.projectmodel

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Location(@PrimaryKey var id: Int, var name: String, var dates: RealmList<Date>) : RealmObject() {
    constructor() : this(0,"",RealmList<Date>())
}
