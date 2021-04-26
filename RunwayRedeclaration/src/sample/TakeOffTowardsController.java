/*
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TakeOffTowardsController implements Initializable {

    @FXML private TextField tocs;
    @FXML private Button confirm;
    @FXML private Button back;
    @FXML private ListView notifications;
    @FXML private Button helpButton;
    @FXML private Button info;
    private Runway runway;
    private Runway newRunway;
    private TakeOffTowardsObstacle toto;
    private Obstacle obstacle;
    private ListView list;
    private ListView obstacleList;
    ArrayList<Runway> runwaysList = new ArrayList<Runway>();
    ArrayList<Obstacle> obsList = new ArrayList<Obstacle>();

    //Method initialises the runway , obstacle, list of obstacles and list of runways
    public void initRunway(Runway runway, Obstacle obstacle, ListView list, ListView list1 , ArrayList<Runway> runwaysList1,ListView obstacleList1,ArrayList<Obstacle> obsList1){
        this.runway = runway;
        this.obstacle = obstacle;
        notifications.getItems().addAll(list.getItems());
        this.list = list1;
        this.runwaysList = runwaysList1;
        this.obstacleList = obstacleList1;
        this.obsList = obsList1;
    }

    //Method allows switching of scenes (views) from "take-off towards obstacle" view to results view
    public void switchScenes(ActionEvent event) throws IOException {
        calculateResults();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("results.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        ResultsController controller = loader.getController();
        controller.initRunway(runway,newRunway,toto,obstacle,notifications,list,runwaysList,obstacleList,obsList);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

    //Method carries out all checks to determine which buttons
    //are enabled in the view
    public void disableChecks(){
        canUseConfirmButton();
    }

    //Method determines if confirm button is disabled or not
    //(The button is only enabled when a TOCS has been entered in the textfield
    public void canUseConfirmButton(){
        if(tocs.getText().equals("")){
            confirm.setDisable(true);
        }
        else{
            confirm.setDisable(false);
        }
    }

    //Method creates new Taking-Off towards obstacle calculation object that updates the value of the Runway's TORA, TODA and ASDA.
    public void calculateResults(){
        toto = new TakeOffTowardsObstacle(Integer.parseInt(tocs.getText()));
        newRunway = new Runway (runway.getRunwayNumber(), toto.recalculate(obstacle.getDistanceFromThreshold(), runway.getDisplacedThreshold(), obstacle.getHeight(), toto.getTocs(), runway.getResa(), runway.getStripEnd()),toto.recalculate(obstacle.getDistanceFromThreshold(), runway.getDisplacedThreshold(), obstacle.getHeight(), toto.getTocs(), runway.getResa(), runway.getStripEnd()), toto.recalculate(obstacle.getDistanceFromThreshold(), runway.getDisplacedThreshold(), obstacle.getHeight(), toto.getTocs(), runway.getResa(), runway.getStripEnd()), runway.getLda(),runway.getDisplacedThreshold(),runway.getStripEnd(),runway.getStopway(),runway.getClearway(),runway.getResa());
    }

    //Method sends us back to the type-of-calc screen
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("type-of-calc.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        TypeOfCalcController controller = loader.getController();
        controller.initRunway(runway, obstacle,notifications,list,runwaysList,obstacleList,obsList);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

    //Method executes as soon as view/controller loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        confirm.setDisable(true);
        disableChecks();
    }

    //Method opens another screen showing instructions on how to input additional information to perform a recalculation
    public void information(ActionEvent event) throws IOException{
        Label info = new Label();
        info.setText("Instructions:\n" +
                "1. Enter the TOCS according to the chosen runway and obstacle.\n" + "2. Click the 'Confirm' button.\n");
        Scene scene = new Scene(info,500,100);
        Stage stage = new Stage();
        stage.setTitle("Instructions on how to input additional information to perform a recalculation");
        stage.setScene(scene);
        stage.show();
    }

    //Method opens another screen showing all the definitions for other parameters
    public void moreInfo (ActionEvent event) throws IOException{
        Label info1 = new Label();
        info1.setText("Definitions:\n"+
                "1. Take-Off Climb Surface (TOCS) : The surface formed between the runway and the top of the obstacle when taking into account the airport’s minimum angle of ascent.");
        Scene scene = new Scene (info1,900,100);
        Stage stage = new Stage();
        stage.setTitle("Definitions for other parameters");
        stage.setScene(scene);
        stage.show();
    }
}


 */