package de.archaeonautic.rovtracker.mapadapters

import de.archaeonautic.rovtracker.model.GridCtrl

abstract class IMap {
    var gridCtrl: GridCtrl? = null
    abstract fun onCreate()
    abstract fun addLocationTrackPos(lat: Float, lng: Float)
    abstract fun insertGrid(gridCtrl: GridCtrl)
    abstract protected fun moveMarker()
}