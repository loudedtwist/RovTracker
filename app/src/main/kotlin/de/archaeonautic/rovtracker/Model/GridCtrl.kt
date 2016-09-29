package de.archaeonautic.rovtracker.Model

import java.util.*

class GridCtrl(pos : Array<GeoPos>){
    var markerPos: MutableList<GeoPos> = ArrayList<GeoPos>()

    var gridCoords: MutableList<GeoPos> = ArrayList<GeoPos>()

    init{
        markerPos.addAll(pos)
    }

    fun changePosOf(pos : GeoPos,index: Int) {
        if(index >= markerPos.size) return
        markerPos.set(index, pos)
        calcGrid()
    }

    private fun calcGrid() {
        var minLat = markerPos.minBy { it.getLat() }
        var maxLat = markerPos.maxBy { it.getLat() }
        var minLng = markerPos.minBy { it.getLng() }
        var maxLng = markerPos.maxBy { it.getLng() }
        if(minLat!=null) gridCoords.add(minLat)
        if(maxLat!=null) gridCoords.add(maxLat)
        if(minLng!=null) gridCoords.add(minLng)
        if(maxLng!=null) gridCoords.add(maxLng)
    }

}