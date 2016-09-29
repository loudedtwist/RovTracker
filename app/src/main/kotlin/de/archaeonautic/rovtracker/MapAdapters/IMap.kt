package de.archaeonautic.rovtracker.MapAdapters

import de.archaeonautic.rovtracker.Model.Grid

abstract class IMap {
    var grid : Grid? = null
    abstract fun onCreate()
    abstract fun addLocationTrackPos(lat: Float, lng: Float)
    abstract fun insertGrid(grid : Grid)
    abstract protected fun moveMarker()
}