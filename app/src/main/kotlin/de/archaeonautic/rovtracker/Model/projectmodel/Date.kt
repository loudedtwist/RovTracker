package de.archaeonautic.rovtracker.Model.projectmodel

import io.realm.RealmList
import io.realm.RealmObject

open class Date(var date : String, var dives : RealmList<Dive>) : RealmObject() {
    constructor() : this("0", RealmList<Dive>())
    //TODO date as Simple Date Format
}
