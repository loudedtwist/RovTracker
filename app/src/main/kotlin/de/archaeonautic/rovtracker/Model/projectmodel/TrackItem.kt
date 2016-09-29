package de.archaeonautic.rovtracker.Model.projectmodel

import io.realm.RealmObject

class TrackItem(val pos : GeoPos, val sat : Int, val hdop : Int, val time : String) : RealmObject() {
    //TODO time as Simple Date Format
}
