package de.archaeonautic.rovtracker.Model;

import com.google.android.gms.maps.model.LatLng

class GeoPos(lat: Double, lng: Double) {
    private val point: LatLng

    init {
        point = LatLng(lat, lng)
    }
    fun getLat() : Double {
        return point.latitude
    }
    fun getLng() : Double {
        return point.longitude
    }
}

