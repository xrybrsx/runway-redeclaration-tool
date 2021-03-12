package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

/* This class represents the "take off towards the obstacle calculation" in which some runway and obstacle
measurements are used (as well as external measurements added to the calculation).
 This recalculation involves recalculating and updating the TORA of the runway in which there is an obstacle present
 (which in turn updates the TODA and ASDA of the runway as well).
 */
public class TakeOffTowardsObstacle extends Calculation {

    //parameters to be updated via the recalculation
    private int newTora;
    private int newToda;
    private int newAsda;
    private int tocs;
    private int valueToBeTaken;
    @FXML
    private ListView notifications;

    //Method inherited from abstract calculation class
    @Override
    public void recalculate() {}

    public TakeOffTowardsObstacle(int tocs) {
        this.tocs = tocs;
    }

    /*
    Method recalculates new TORA, ASDA and TODA given the distance of the obstacle from the threshold,
    the displaced threshold of the runway, the obstacle height, the runway approach landing surface (TOCS)
    and the runway strip end.
    TOCS * height must be greater than the RESA (otherwise the RESA is used).
     */
    public int recalculate(int distanceFromThreshold, int displacedThreshold, int height, int tocs, int resa, int stripEnd){
        if(tocs * height > resa){
            valueToBeTaken = tocs * height;
        }
        else{
            valueToBeTaken = resa;
        }
        int newMeasurements = distanceFromThreshold + displacedThreshold - (valueToBeTaken) - stripEnd;
        this.newTora = newMeasurements;
        this.newToda = newMeasurements;
        this.newAsda = newMeasurements;
        return newMeasurements;
    }

    public void goBack(ActionEvent event) throws IOException {
        Parent obstacleParent = FXMLLoader.load(getClass().getResource("type-of-calc.fxml"));
        Scene obstacleScene = new Scene(obstacleParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

    //Getter method returns new recalculated take-off run available (TORA)
    public int getNewTora(){
        return newTora;
    }

    //Getter method returns new recalculated take-off distance available (TODA)
    public int getNewToda(){
        return newToda;
    }

    //Getter method returns new accelerate-stop distance available (ASDA)
    public int getNewAsda(){
        return newAsda;
    }

    //Getter for ALS
    public int getTocs() {
        return tocs;
    }

    //Setter for ALS
    public void setTocs(int tocs) {
        this.tocs = tocs;
    }

    public int getValueToBeTaken(){
        return valueToBeTaken;
    }
}


