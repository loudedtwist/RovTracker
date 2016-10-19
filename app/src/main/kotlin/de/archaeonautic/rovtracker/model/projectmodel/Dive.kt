package de.archaeonautic.rovtracker.model.projectmodel

import io.realm.RealmObject

open class Dive(var pilot: String, duration: String, var notes: String, var track: TrackItem, var grid: Grid) : RealmObject() {
    //TODO duration as Simple Date Format
    constructor() : this("", "0", "", TrackItem(), Grid())
}
