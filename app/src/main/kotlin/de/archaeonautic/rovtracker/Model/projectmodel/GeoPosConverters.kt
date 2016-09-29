package de.archaeonautic.rovtracker.Model.projectmodel

import com.google.android.gms.maps.model.LatLng

fun GeoPos.LatLong(): LatLng {
    return LatLng(this.lat, this.lng)
}