package de.archaeonautic.rovtracker.Model.projectmodel

import io.realm.RealmList
import io.realm.RealmObject

class Grid : RealmObject() {
    var cellsize : Float = 0.0f
    var hull : RealmList<GeoPos> = RealmList<GeoPos>()
}
