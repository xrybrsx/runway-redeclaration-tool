package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class TypeOfCalcController {

    @FXML private Button back;
    @FXML private Button landingOver;
    @FXML private Button landingTowards;
    @FXML private Button takeoffAway;
    @FXML private Button takeoffTowards;
    @FXML private ListView notifications;
    @FXML private Button helpButton;
    private Obstacle obstacle;
    private Runway runway;
    private String name;
    private ListView list;
    private ListView obstacleList;
    ArrayList<Obstacle> obsList = new ArrayList<Obstacle>();
    ArrayList<Runway> runwaysList = new ArrayList<Runway>();

    //Method initialise the runway,obstacle,list of obstacle and list of runways
    public void initRunway(Runway runway, Obstacle obstacle,ListView list,ListView list1,ArrayList<Runway> runwaysList1,ListView obstacleList1,ArrayList<Obstacle> obsList1){
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

    //function to send to "landing over obstacle" scene
    public void switchToLandingOverScreen(ActionEvent event) throws IOException {
        notifications.getItems().add(new String("Landing over obstacle calculations chosen."));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("landing-over-obstacle.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        LandingOverController controller = loader.getController();
        controller.initRunway(runway, obstacle,notifications,list,runwaysList,obstacleList,obsList);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

    //lambda function to send to results scene
    //Landing Towards Obstacle calculation done in this method
    public void switchToResults(ActionEvent event) throws IOException {
        LandingTowardsObstacle lto = new LandingTowardsObstacle();
        Runway newRunway = new Runway(runway.getRunwayNumber(), runway.getTora(), runway.getToda(), runway.getAsda(),lto.recalculate(obstacle.getDistanceFromThreshold(),runway.getResa(),runway.getStripEnd()), runway.getDisplacedThreshold(), runway.getStripEnd(), runway.getStopway(), runway.getClearway(), runway.getResa());
        notifications.getItems().add(new String("Landing towards obstacle calculations chosen."));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("results.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        ResultsController controller = loader.getController();
        controller.initRunway(runway,newRunway,lto,obstacle,notifications,list,runwaysList,obstacleList,obsList);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

   //functionToSendTo "take off away from obstacle screen"
    public void switchToTakeOffAwayScreen(ActionEvent event) throws IOException {
        notifications.getItems().add(new String("Taking off away from obstacle calculations chosen."));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("take-off-away-from-obstacle.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        TakeOffAwayController controller = loader.getController();
        controller.initRunway(runway, obstacle,notifications,list,runwaysList,obstacleList,obsList);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

    //functionToSendTo "take off towards from obstacle screen"
    public void switchToTakeOffTowardsScreen(ActionEvent event) throws IOException {
        notifications.getItems().add(new String("Taking off towards calculations chosen."));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("take-off-towards-obstacle.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        TakeOffTowardsController controller = loader.getController();
        controller.initRunway(runway, obstacle,notifications,list,runwaysList,obstacleList,obsList);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

    //Method sends us back to the obstacle screen
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("obstacle.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        ObstacleController controller = loader.getController();
        controller.back(runway, obstacle,notifications,list,runwaysList,obstacleList,obsList);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

    //Method opens another screen showing instructions on how to choose the type of recalculation to perform
    public void information(ActionEvent event) throws IOException{
        Label info = new Label();
        info.setText("Instructions:\n" +
                "1. Choose and click on the button that has the type of recalculation that you would like to perform.");
        Scene scene = new Scene(info,600,50);
        Stage stage = new Stage();
        stage.setTitle("Instructions on how to choose the type of recalculation to perform.");
        stage.setScene(scene);
        stage.show();
    }
}
