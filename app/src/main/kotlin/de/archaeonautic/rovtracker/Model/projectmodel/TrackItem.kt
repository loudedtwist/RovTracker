package de.archaeonautic.rovtracker.Model.projectmodel

import io.realm.RealmObject

open class TrackItem(var pos: GeoPos, var sat: Int, var hdop: Int, var time: String) : RealmObject() {
    //TODO time as Simple Date Format
    constructor() : this(GeoPos(), 0, 0, "00:00:00")
}
