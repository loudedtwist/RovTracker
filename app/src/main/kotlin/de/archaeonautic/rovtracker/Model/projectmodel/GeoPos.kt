package de.archaeonautic.rovtracker.Model.projectmodel

import io.realm.RealmList
import io.realm.RealmObject

open class GeoPos(var lat: Double, var lng: Double) : RealmObject() {
    constructor() : this(0.0,0.0)
}