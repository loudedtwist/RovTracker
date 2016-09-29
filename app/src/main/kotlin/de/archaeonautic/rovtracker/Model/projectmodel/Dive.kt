package de.archaeonautic.rovtracker.Model.projectmodel

import io.realm.RealmList
import io.realm.RealmObject

class Dive(val pilot : String, duration : String, val notes : String, val track : RealmList<TrackItem>, val grid : Grid) : RealmObject() {
    //TODO duration as Simple Date Format
}
