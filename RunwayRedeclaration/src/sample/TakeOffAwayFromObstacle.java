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

/* This class represents the "take off away from the obstacle calculation" in which some runway and obstacle
measurements are used (as well as external measurements added to the calculation).
 This recalculation involves recalculating and updating the TORA, TODA and ASDA of the runway.
 */
public class TakeOffAwayFromObstacle extends Calculation {

    //Parameters to be updated via the recalculation
    private int newTora;
    private int newToda;
    private int newAsda;
    private int blastProtectionAllowance;
    @FXML
    private ListView notifications;

    //Method inherited from abstract calculation class
    @Override
    public void recalculate() {}

    public TakeOffAwayFromObstacle(int blastProtectionAllowance) {
        this.blastProtectionAllowance = blastProtectionAllowance;
    }

    /*
    Method recalculates a new runway's new TORA, TODA and ASDA given the obstacles distance from the runway threshold and
    the blast protection allowance.
    If there exist any Clearway	and/or Stopway then those values should be added to	the	reduced	TORA for the
    TODA and ASDA values
     */
    public void recalculate(int tora, int toda, int asda, int distanceFromThreshold, int blastProtectionAllowance, int clearWay, int stopWay){
        this.newTora = (tora - distanceFromThreshold - blastProtectionAllowance) + clearWay + stopWay;
        this.newToda = (toda - distanceFromThreshold - blastProtectionAllowance) + clearWay + stopWay;
        this.newAsda = (asda - distanceFromThreshold - blastProtectionAllowance) + clearWay + stopWay;
    }

    //Getter method returns new recalculated take-off run available (TORA)
    public int getNewTora() {
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

    //Getter for BPA
    public int getBlastProtectionAllowance() {
        return blastProtectionAllowance;
    }

    //Setter for BPA
    public void setBlastProtectionAllowance(int blastProtectionAllowance) {
        this.blastProtectionAllowance = blastProtectionAllowance;
    }

    public void goBack(ActionEvent event) throws IOException {
        Parent obstacleParent = FXMLLoader.load(getClass().getResource("type-of-calc.fxml"));
        Scene obstacleScene = new Scene(obstacleParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }
}

