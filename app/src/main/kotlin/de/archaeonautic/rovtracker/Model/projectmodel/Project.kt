package de.archaeonautic.rovtracker.Model.projectmodel

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class Project(@PrimaryKey val id : Int, val name : String, val locations : RealmList<Location>) : RealmObject()
