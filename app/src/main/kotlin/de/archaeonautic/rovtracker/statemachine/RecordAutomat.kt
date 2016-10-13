package de.archaeonautic.rovtracker.statemachine

import android.util.Log
import de.archaeonautic.rovtracker.mapadapters.IMap
import de.archaeonautic.rovtracker.model.projectmodel.GeoPos
import java.util.*


class BTClient {
    fun init(){

    }
    fun destroy(){

    }
    fun getPoint() : GeoPos {
        return GeoPos(0.0,0.0)
    }
}
interface Question{
    fun ask() : Boolean
}

class RecordCtrl(val btClient: BTClient, val mapFramework: IMap) {
    private val trackPoints: MutableList<GeoPos> = ArrayList()
    fun init(){
        btClient.init()
        //realm.emptyTempTrack()
        //bla bla init btClient;
    }
    fun catchPoint(){
        //start funtion die sich jede 1/2 sekunde aufruft
        val newPoint = btClient.getPoint();
        trackPoints.add(newPoint);
        mapFramework.addLocationTrackPos(newPoint.lat.toFloat(),newPoint.lng.toFloat())
        //realm.addPointToTempTrack(newPoint)
    }
}
class ProjectManager{
    fun save(){
        //var points = realm.getTempProjectPoints()
    }
}
class RecordAutomat(val recordCtrl :RecordCtrl, val question: Question){
    private val idle  = Idle(this)
    private val recording  = Recording(this)
    private val stopped = Stopped(this)
    private var currentState : State = idle
    fun getRecordingState() : State{
        return recording;
    }
    fun getStoppedState() : State{
        return stopped;
    }
    fun getIdleState() : State{
        return idle;
    }
    fun setState(newState: State){
        currentState = newState;
    }
    fun ctrlPressed(){
        currentState.ctrlPressed()
        currentState.onStart();
    }

    fun getIcon(): String {
        return currentState.getIcon()
    }
}
abstract class State( var recordStatemachine: RecordAutomat ){
    abstract fun ctrlPressed()
    abstract fun getIcon() : String
    abstract fun onStart() : Unit
}
class Idle(recordAutomat: RecordAutomat) : State(recordAutomat) {
    override fun onStart() {
        recordStatemachine.recordCtrl.init()
    }

    override fun getIcon(): String {
        return "ic_record"
    }
    override fun ctrlPressed() {
        recordStatemachine.setState(recordStatemachine.getRecordingState())
    }
}
class Recording(recordAutomat: RecordAutomat) : State(recordAutomat) {
    override fun onStart() {
        //aufrufen jede 1/2 s
        recordStatemachine.recordCtrl.catchPoint()
    }

    override fun getIcon(): String {
        return "ic_save"
        //return "Stop"
    }

    override fun ctrlPressed() {
        recordStatemachine.setState(recordStatemachine.getStoppedState())
    }

}
class Paused(recordAutomat: RecordAutomat) : State(recordAutomat) {
    override fun onStart() {

    }

    override fun getIcon(): String {
        return "ic_save"
    }

    override fun ctrlPressed() {
        //recordStatemachine.setState(recordStatemachine.getStoppedState())
    }
}
class Stopped(recordAutomat: RecordAutomat) : State(recordAutomat) {
    override fun onStart(){
        var r = recordStatemachine.question.ask()
        recordStatemachine.setState(recordStatemachine.getIdleState())
        Log.e("ANTWORT", r.toString())
    }
    override fun getIcon(): String {
        return "ic_stopped"
    } // save on stop
    override fun ctrlPressed() {
        Log.e("ANTWORT", "")
    }
}
