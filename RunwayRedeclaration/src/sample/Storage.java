package sample;
import java.util.ArrayList;

//Storage class for Runway and Obstacle array lists
public class Storage {

    private ArrayList runwaysList;

    public Storage() {
        this.runwaysList = new ArrayList<Runway>();

    }

    public ArrayList<Runway> getRunwayList() {
        return runwaysList;
    }

    public void setRunwayList(ArrayList<Runway> newRunwayList) {
        this.runwaysList = newRunwayList;
    }
}
