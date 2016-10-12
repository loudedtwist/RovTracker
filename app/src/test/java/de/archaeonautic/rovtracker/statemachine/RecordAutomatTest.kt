package de.archaeonautic.rovtracker.statemachine

import com.google.android.gms.maps.SupportMapFragment
import de.archaeonautic.rovtracker.mapadapters.GoogleMapAdapter
import de.archaeonautic.rovtracker.mapadapters.IMap
import org.junit.Assert.assertNotNull
import org.junit.Test

class RecordAutomatTest{

    @Test
    fun automatCreation() {
        val mapFramework : IMap = GoogleMapAdapter(SupportMapFragment())
        val recordA : RecordAutomat  = RecordAutomat(RecordCtrl(BTClient(), mapFramework))
        recordA.ctrlPressed()
        assertNotNull(recordA)
    }
}