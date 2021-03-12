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

public class LandingOverController implements Initializable {

    @FXML private Button submit;
    @FXML private Button confirm;
    @FXML private TextField als;
    @FXML private TextField bpa;
    @FXML private Button back;
    @FXML private ListView notifications;
    @FXML private Button helpButton;
    @FXML private Button info;
    @FXML private Button info1;
    private Runway runway;
    private Runway newRunway;
    private Obstacle obstacle;
    private LandingOverObstacle loo;
    private ListView list;
    private ListView obstacleList;
    ArrayList<Runway> runwaysList = new ArrayList<Runway>();
    ArrayList<Obstacle> obsList = new ArrayList<Obstacle>();

    //Method initialise the runway, list of obstacles and list of runways
    public void initRunway(Runway runway, Obstacle obstacle,ListView list,ListView list1, ArrayList<Runway> runwaysList1,ListView obstacleList1,ArrayList<Obstacle> obsList1){
        this.runway = runway;
        this.obstacle = obstacle;
        for (Object o : list.getItems()){
            notifications.getItems().add(o);
        }
        this.list = list1;
        this.runwaysList = runwaysList1;
        this.obstacleList = obstacleList1;
        this.obsList = obsList1;
    }

    //Method carries our checks to determine if a button can be used
    public void disableChecks(){
        canUseSubmitButton();
    }

    //Method allows submit button to be used only if both the ALS and BPA textfields contain values
    public void canUseSubmitButton(){
        if(als.getText().equals("")){
            submit.setDisable(true);
        }
        if(bpa.getText().equals("")){
            submit.setDisable(true);
        }
        else{
            submit.setDisable(false);
        }
    }

    //Method allows switching of scenes (views) from "landing over obstacle" view to results view
    public void switchScenes(ActionEvent event) throws IOException {
        calculateResults();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("results.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        ResultsController controller = loader.getController();
        controller.initRunway(runway,newRunway,loo, obstacle,notifications,list,runwaysList,obstacleList,obsList);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

    //Method creates new Landing Over obstacle calculation object that updates the value of the Runway's LDA
    public void calculateResults() {
        loo = new LandingOverObstacle(Integer.parseInt(als.getText()), Integer.parseInt(bpa.getText()));
        newRunway = new Runway(runway.getRunwayNumber(), runway.getTora(), runway.getToda(), runway.getAsda(),loo.recalculate(runway.getTora(), obstacle.getDistanceFromThreshold(), obstacle.getHeight(), loo.getAls(), runway.getResa(), runway.getStripEnd(), loo.getBlastProtectionAllowance()), runway.getDisplacedThreshold(), runway.getStripEnd(), runway.getStopway(), runway.getClearway(), runway.getResa());
        confirm.setDisable(false);
    }

    //Method sends us back to the type-of-calc screen
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("type-of-calc.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        TypeOfCalcController controller = loader.getController();
        controller.initRunway(runway, obstacle, notifications,list,runwaysList,obstacleList,obsList);
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
                "1. Enter values for both the ALS and BPA according to the chosen runway and obstacle.\n" + "2. Click the 'Submit' button.\n" +
                "3. Click on the 'Confirm' button.\n");
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
                "1. Approach Landing Surface (ALS) : The surface formed between the top of the obstacle and the runway when taking into account the airport’s minimum angle of descent.");
        Scene scene = new Scene (info1,920,100);
        Stage stage = new Stage();
        stage.setTitle("Definitions for other parameters");
        stage.setScene(scene);
        stage.show();
    }

    //Method opens another screen showing all the definitions for other parameters
    public void moreInfo1 (ActionEvent event) throws IOException{
        Label info2 = new Label();
        info2.setText("Definitions:\n"+
                "1. Blast Protection Allowance (BPA) : A safety area behind an aircraft to prevent the engine blast from affecting any obstacles located behind it.");
        Scene scene = new Scene (info2,850,100);
        Stage stage = new Stage();
        stage.setTitle("Definitions for other parameters");
        stage.setScene(scene);
        stage.show();
    }
}
