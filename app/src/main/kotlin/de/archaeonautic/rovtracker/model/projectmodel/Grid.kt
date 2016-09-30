package de.archaeonautic.rovtracker.model.projectmodel

import io.realm.RealmList
import io.realm.RealmObject

open class Grid(var cellsize: Float, var hull: RealmList<GeoPos>) : RealmObject() {
    constructor() : this(0.0f, RealmList<GeoPos>())
}
