package de.archaeonautic.rovtracker.mapadapters

import de.archaeonautic.rovtracker.model.GridCtrl

interface IMap {
    var gridCtrl: GridCtrl?

    fun onCreate()
    fun enableCurrentLocation()
    fun addLocationTrackPos(lat: Float, lng: Float)
    fun insertGrid(gridCtrl: GridCtrl)
    fun moveMarker()
}
