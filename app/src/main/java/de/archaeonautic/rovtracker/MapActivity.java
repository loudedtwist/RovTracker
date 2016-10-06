package de.archaeonautic.rovtracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.SupportMapFragment;

import de.archaeonautic.rovtracker.mapadapters.GoogleMapAdapter;
import de.archaeonautic.rovtracker.mapadapters.IMap;
import io.realm.Realm;

public class MapActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean permissionDenied = false;

    IMap mapFramework;
    Realm db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.map);
        enableLocationPermission();
        initGuiElements();

        mapFramework = new GoogleMapAdapter((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mapFramework.onCreate();
        db = Realm.getDefaultInstance();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    private void initGuiElements() {
    }

    private void enableLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            enableLocationPermission();
        } else {
            permissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (permissionDenied) {
            showMissingPermissionError();
            permissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }
}

