package de.archaeonautic.rovtracker.model.projectmodel

import io.realm.RealmObject

open class GeoPos(var lat: Double = 0.0, var lng: Double = 0.0, var alt: Int = 0) : RealmObject() {
    constructor(lat: Double, lng: Double) : this(lat, lng, 0)
}