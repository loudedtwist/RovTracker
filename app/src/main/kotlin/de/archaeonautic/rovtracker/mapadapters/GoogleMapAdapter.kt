package de.archaeonautic.rovtracker.mapadapters

import android.graphics.Color
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import de.archaeonautic.rovtracker.model.projectmodel.GeoPos
import de.archaeonautic.rovtracker.model.GridCtrl
import de.archaeonautic.rovtracker.model.projectmodel.LatLong
import java.util.*


class GoogleMapAdapter(var mapFragment: SupportMapFragment) : IMap(), OnMapReadyCallback {

    override fun insertGrid(gridCtrl: GridCtrl) {
        this.gridCtrl = gridCtrl
        if(map == null ) return;
        initMarkers(gridCtrl)
    }

    private fun initMarkers(gridCtrl: GridCtrl) {
        for (pos in gridCtrl.markerPos) {
            val marker = (map as GoogleMap).addMarker(MarkerOptions()
                    .position(pos.LatLong())
                    .draggable(true))
            markers.add(marker)
        }
    }

    private var markers : MutableList<Marker> = ArrayList<Marker>()
    private var map: GoogleMap? = null
    private var trackPoints: MutableList<LatLng> = ArrayList<LatLng>()
    private var trackPolyline: Polyline? = null
    private var gridPolygon: Polygon? = null

    override fun onCreate() {
        initTrackPoints()
        mapFragment.getMapAsync(this)
    }

    private fun initTrackPoints() {
        trackPoints.add(LHR)
        trackPoints.add(AKL)
        trackPoints.add(LAX)
        trackPoints.add(JFK)
        trackPoints.add(LHR)
    }

    override fun addLocationTrackPos(lat: Float, lng: Float) {
        val newPoint = LatLng(lat.toDouble(), lng.toDouble())
        trackPoints.add(newPoint)
        trackPolyline?.points = trackPoints

        moveMarker()
    }

    override fun moveMarker() {
        //throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMapReady(gMap: GoogleMap) {
        map = gMap

        gMap.setContentDescription("Google Map with polylines.")
        gMap.addPolyline(PolylineOptions().add(MELBOURNE, ADELAIDE, PERTH))

        trackPolyline = gMap
                .addPolyline(PolylineOptions()
                .addAll(trackPoints)
                .width(5f)
                .color(Color.BLUE)
                .geodesic(true)
                .clickable(true))


        gMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY))

        gMap.setOnPolylineClickListener { polyline ->
            // Flip the values of the r, g and b components of the polyline's color.
            val strokeColor = polyline.color xor 0x00ffffff
            polyline.color = strokeColor
        }

        gMap.setOnMarkerDragListener(object: GoogleMap.OnMarkerDragListener {
            override fun onMarkerDragStart(arg0: Marker) {}
            override fun onMarkerDragEnd(marker: Marker) {
                gridCtrl?.changePosOf(GeoPos(marker.position.latitude,marker.position.longitude),0)
                drawGrid()
                gMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()))
            }

            private fun drawGrid() {
                if(gridCtrl == null) return
                val list = ArrayList<LatLng>()
                for(pos in gridCtrl!!.gridCoords){
                    list.add(pos.LatLong())
                }
                var gridPolyline = gMap
                        .addPolyline(PolylineOptions()
                        .addAll(list)
                        .width(5f)
                        .color(Color.BLACK)
                        .geodesic(true)
                        .clickable(true))
            }

            override fun onMarkerDrag(arg0: Marker) {}
        })
    }

    companion object {

        private val MELBOURNE = LatLng(-37.81319, 144.96298)

        private val SYDNEY = LatLng(-33.87365, 151.20689)

        private val ADELAIDE = LatLng(-34.92873, 138.59995)

        private val PERTH = LatLng(-31.95285, 115.85734)

        private val LHR = LatLng(51.471547, -0.460052)

        private val LAX = LatLng(33.936524, -118.377686)

        private val JFK = LatLng(40.641051, -73.777485)

        private val AKL = LatLng(-37.006254, 174.783018)
    }
}
