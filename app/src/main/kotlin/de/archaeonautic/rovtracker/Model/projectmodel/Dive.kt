package de.archaeonautic.rovtracker.Model.projectmodel

import io.realm.RealmList
import io.realm.RealmObject

open class Dive(var pilot : String, duration : String, var notes : String, var track : RealmList<TrackItem>, var grid : Grid) : RealmObject() {
    //TODO duration as Simple Date Format
    constructor() : this("","0","",RealmList<TrackItem>(), Grid())
}
