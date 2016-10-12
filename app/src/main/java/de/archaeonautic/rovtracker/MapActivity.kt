package de.archaeonautic.rovtracker

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.google.android.gms.maps.SupportMapFragment
import de.archaeonautic.rovtracker.mapadapters.GoogleMapAdapter
import de.archaeonautic.rovtracker.mapadapters.IMap
import de.archaeonautic.rovtracker.statemachine.BTClient
import de.archaeonautic.rovtracker.statemachine.Question
import de.archaeonautic.rovtracker.statemachine.RecordAutomat
import de.archaeonautic.rovtracker.statemachine.RecordCtrl
import io.realm.Realm
import org.jetbrains.anko.alert

class MapActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback, Question {
    private var permissionDenied = false

    internal var mapFramework: IMap? = null
    internal var db: Realm? = null
    internal var ctrlButton: Button? = null
    internal var recCtrl : RecordCtrl? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.map)
        enableLocationPermission()
        initGuiElements()

        mapFramework = GoogleMapAdapter(supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
        mapFramework?.onCreate()
        db = Realm.getDefaultInstance()
        recCtrl = RecordCtrl(BTClient(), mapFramework!!)
        val a = RecordAutomat(recCtrl!!,this)
        ctrlButton?.setOnClickListener {
            a.ctrlPressed()
            ctrlButton?.text = a.getIcon()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        db?.close()
    }
    override fun ask() : Boolean {
        //TODO change true to false
        var result = false;
        alert("Project", "Do you want to save the track?") {
            yesButton {
                /*projectSaverActivity() // die felder festlegen
                var project  = projectSaverActivity.getNewProject()
                recCtrl
                DataHandler(project,recCtrl.getPoints()) // e.g. DB / JSON ...
                */
            }
            noButton {
                //recCtrl?.empty()
            }
        }.show()
        return result
    }
    private fun initGuiElements() {
        ctrlButton = findViewById(R.id.ctrlButton) as Button
    }

    private fun enableLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            enableLocationPermission()
        } else {
            permissionDenied = true
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (permissionDenied) {
            showMissingPermissionError()
            permissionDenied = false
        }
    }

    private fun showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog.newInstance(true).show(supportFragmentManager, "dialog")
    }

    companion object {
        private val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}

