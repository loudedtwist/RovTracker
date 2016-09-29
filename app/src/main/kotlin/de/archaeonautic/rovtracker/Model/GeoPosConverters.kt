package de.archaeonautic.rovtracker.Model

import com.google.android.gms.maps.model.LatLng

fun GeoPos.LatLong(): LatLng {
    return LatLng(this.getLat(), this.getLng());
}