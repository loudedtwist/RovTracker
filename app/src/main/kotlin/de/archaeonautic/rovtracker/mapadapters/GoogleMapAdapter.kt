package de.archaeonautic.rovtracker.mapadapters

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import de.archaeonautic.rovtracker.model.GridCtrl
import java.util.*


class GoogleMapAdapter(var mapFragment: SupportMapFragment) : IMap,
        OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener {

    private var map: GoogleMap? = null
    private var trackPoints: MutableList<LatLng> = ArrayList<LatLng>()
    private var trackPolyline: Polyline? = null

    override var gridCtrl: GridCtrl?
        get() = throw UnsupportedOperationException()
        set(value) {
        }

    override fun insertGrid(gridCtrl: GridCtrl) {
        // TODO
    }

    override fun onCreate() {
        mapFragment.getMapAsync(this)
    }

    override fun addLocationTrackPos(lat: Float, lng: Float) {
        val newPoint = LatLng(lat.toDouble(), lng.toDouble())
        trackPoints.add(newPoint)
        trackPolyline?.points = trackPoints

        moveMarker()
    }

    override fun moveMarker() {
        // TODO
    }

    override fun onMapReady(gMap: GoogleMap) {
        map = gMap
        map?.setContentDescription("Google Map with polylines.")
        enableCurrentLocation()

    }

    override fun enableCurrentLocation() {
        map?.isMyLocationEnabled = true
        map?.setOnMyLocationButtonClickListener(this)
    }

    override fun onMyLocationButtonClick(): Boolean {
        return false
    }
}
