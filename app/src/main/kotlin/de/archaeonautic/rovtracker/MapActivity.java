
package de.archaeonautic.rovtracker;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.concurrent.ThreadLocalRandom;

import de.archaeonautic.rovtracker.MapAdapters.GoogleMapAdapter;
import de.archaeonautic.rovtracker.MapAdapters.IMap;
import de.archaeonautic.rovtracker.Model.projectmodel.GeoPos;
import de.archaeonautic.rovtracker.Model.GridCtrl;
import io.realm.Realm;

public class MapActivity extends AppCompatActivity {

    private CheckBox mClickabilityCheckbox;
    IMap mapFramework;
    Realm db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.polyline_demo);

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
        mClickabilityCheckbox = (CheckBox) findViewById(R.id.toggleClickability);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void toggleClickability(View view) {
        mapFramework.addLocationTrackPos(64.99525f + ThreadLocalRandom.current().nextFloat()*10, 40.15247f + ThreadLocalRandom.current().nextFloat() * 10);
        mapFramework.insertGrid(new GridCtrl(getExamplePointsForMaker()));
    }

    private GeoPos[] getExamplePointsForMaker() {
        GeoPos[] geoPoses = new GeoPos[4];
        geoPoses[0] = new GeoPos(64.99535f, 40.15217f );
        geoPoses[1] = new GeoPos(63.99525f, 41.15237f );
        geoPoses[2] = new GeoPos(62.99545f, 40.15247f );
        geoPoses[3] = new GeoPos(61.99645f, 40.15447f );
        return geoPoses;
    }
}

