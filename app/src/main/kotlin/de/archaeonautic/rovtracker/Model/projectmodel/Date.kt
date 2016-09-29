package de.archaeonautic.rovtracker.Model.projectmodel

import io.realm.RealmList
import io.realm.RealmObject

class Date(val date : String, val dives : RealmList<Dive>) : RealmObject() {
    //TODO date as Simple Date Format
}
