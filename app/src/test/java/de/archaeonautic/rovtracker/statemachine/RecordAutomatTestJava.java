package de.archaeonautic.rovtracker.statemachine;

import android.app.Activity;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import de.archaeonautic.rovtracker.BuildConfig;
import de.archaeonautic.rovtracker.MapActivity;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class RecordAutomatTestJava{

    @Test
    public void automatCreation() {

        Activity activity = Robolectric.setupActivity(MapActivity.class);
        Button ctrlButton = new Button(activity);
    }
}