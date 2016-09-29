package de.archaeonautic.rovtracker.Model.projectmodel

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Project(@PrimaryKey var id : Int, var name : String, var locations : RealmList<Location>) : RealmObject() {
    constructor() : this(0,"",RealmList<Location>())
}
