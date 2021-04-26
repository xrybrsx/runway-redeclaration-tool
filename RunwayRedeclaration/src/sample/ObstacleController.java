/*
package sample;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ObstacleController implements Initializable {

    @FXML private TextField name;
    @FXML private TextField height;
    @FXML private TextField centreline;
    @FXML private TextField threshold;
    @FXML private ListView list;
    @FXML private AnchorPane anchor;
    @FXML private Button back;
    @FXML private Button continueButton;
    @FXML private Button newObstacle;
    @FXML private Button chooseObstacle;
    @FXML private Button deleteObstacle;
    @FXML private ListView notifications;
    @FXML private Button helpButton;
    @FXML private Button info;
    @FXML private Button editButton;
    private boolean valid = true;
    private Obstacle obstacle;
    private Runway runway;
    private Obstacle backObstacle;
    private ListView list2;
    ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
    ArrayList<Runway> runwayList = new ArrayList<Runway>();

    //Method initialise the runway, list of obstacles and list of runways
    public void initRunway(Runway runway,ListView list,ListView list1,ArrayList<Runway> runwayList1,ListView obstacleList1, ArrayList<Obstacle> obsList){
        this.runway = runway;
        this.list2 = list1;
        this.runwayList = runwayList1;
        for (Object o: list.getItems()){
            notifications.getItems().add(o);
        }
        if(obstacleList1 != null){
            for (Object o: obstacleList1.getItems()){
                this.list.getItems().add(o);
            }
        }
        if(obsList !=null){
            this.obstacleList = obsList;
        }
    }

    //Method initialise the runway, list of obstacles and list of runways
    public void back(Runway runway, Obstacle backObstacle,ListView list,ListView list1, ArrayList<Runway> runwayList1,ListView obstacleList1,ArrayList<Obstacle> obsList){
        this.runway = runway;
        this.backObstacle = backObstacle;
        for (Object o: list.getItems()){
            notifications.getItems().add(o);
        }
        this.list2 = list1;
        this.runwayList = runwayList1;
        for (Object o: obstacleList1.getItems()){
                this.list.getItems().add(o);
        }
        this.obstacleList = obsList;
    }

    //this method is called whenever the user starts inputting stuff for the obstacle
    //it adds a filter to the height, centreline and threshold TextFields so that they only allow for numbers to inputted
    public void addFilter() {
        height.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                height.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        centreline.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                centreline.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        threshold.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                threshold.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    //this makes an obstacle and adds it to the list on the right side of the scene (you will see its name there)
    public void createObstacleObject() {
        obstacle = new Obstacle(name.getText(), Integer.parseInt(height.getText()), Integer.parseInt(centreline.getText()), Integer.parseInt(threshold.getText()));
        setObstacle(obstacle);
        if(obstacle.getDistanceFromCentreLine() > 75){
            centreLineNotification();
        }
        list.getItems().add(obstacle.getName());
        obstacleList.add(obstacle);
        notifications.getItems().add(new String("Obstacle " + obstacle.getName() + " was created."));
    }

    //Method displays pop-up stating that, as the distance of the obstacle from the threshold is greater than
    //75m.....no runway redeclaration is required
    public void centreLineNotification(){
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Distance from centreline is greater than 75m \n No runway redeclaration is required");
        Optional<ButtonType> result = alert.showAndWait();
    }

    //When "delete obstacle" Button pressed, obstacle is deleted from the list
    public void deleteObstacleObject() {
        Object obj = list.getSelectionModel().getSelectedItem();
        list.getItems().remove(obj);
        obstacleList.remove(obj);
        name.clear();
        height.clear();
        centreline.clear();
        threshold.clear();
        notifications.getItems().add(new String("Obstacle " + obj.toString() + " was deleted."));
    }

    //Method takes the obstacle that we clicked on and gets all of its respective informations.
    public void choose(ActionEvent event) throws IOException {
        Object obj = list.getSelectionModel().getSelectedItem();
        for (Obstacle o: obstacleList) {
            if(o.getName() == obj) {
                name.setText(o.getName());
                height.setText(String.valueOf(o.getHeight()));
                centreline.setText(String.valueOf(o.getDistanceFromCentreLine()));
                threshold.setText(String.valueOf(o.getDistanceFromThreshold()));
            }
        }
        notifications.getItems().add(new String("Obstacle " + obj.toString()+ " was chosen from list of obstacles."));
        continueButton.setDisable(false);
        editButton.setDisable(false);

        for(Node n: anchor.getChildren())
            if(n instanceof TextField) {
                ((TextField) n).setEditable(false);
            }
    }

    //Method carries out all checks to determine if buttons on the view can be used
    public void disableChecks(){
        canCreateNewObstacle();
        canDeleteObjectFromList();
        canChoose();
    }

    //Method determines if an obstacle can be added to the list of obstacles
    // (all text fields must be filled)
    public void canCreateNewObstacle(){
        for(Node n: anchor.getChildren())
            if(n instanceof TextField)
                valid = ((TextField) n).getText().length() != 0;
        if(!valid){
            newObstacle.setDisable(true);
            editButton.setDisable(true);
        }
        else{
            newObstacle.setDisable(false);
        }
    }

    //Method determines if the delete obstacle button can be used
    //(An obstacle from the list must be selected)
    public void canDeleteObjectFromList(){
        if(list.getSelectionModel().isEmpty()){
            deleteObstacle.setDisable(true);
        }
        else{
            deleteObstacle.setDisable(false);
        }
    }

    //Method determines if the choose obstacle can be used
    //(An obstacle from the list must be selected)
    public void canChoose(){
        if(list.getSelectionModel().isEmpty()){
            chooseObstacle.setDisable(true);
        }
        else{
            chooseObstacle.setDisable(false);
        }
    }

    //Method sets the obstacle
    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    //Method gets the current obstacle
    public Obstacle getObstacle() {
        return obstacle;
    }

    //Method allows switching of scenes (views) from "enter obstacle" view to type of calculation view
    public void switchScenes(ActionEvent event) throws IOException {
        //First checks whether there is any empty TextField. If that's the case then an error is thrown.
        for (Node n : anchor.getChildren())
            if (n instanceof TextField)
                valid = ((TextField) n).getText().length() != 0;

            //This is the error
            if (!valid) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Please fill in all text fields.");
                error.showAndWait();
            }

            //If all fields are non-empty then go ahead
            else {
                //These two lines is to ensure that there is an obstacle chosen in case the user just inputs
                //the information needed and doesn't save it in the list
                obstacle = new Obstacle(name.getText(), Integer.parseInt(height.getText()), Integer.parseInt(centreline.getText()), Integer.parseInt(threshold.getText()));
                setObstacle(obstacle);
                notifications.getItems().add(new String("Obstacle " + obstacle.getName() + " was loaded."));

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("type-of-calc.fxml"));
                Parent obstacleParent = loader.load();
                Scene obstacleScene = new Scene(obstacleParent);
                TypeOfCalcController controller = loader.getController();
                controller.initRunway(runway, obstacle, notifications, list2, runwayList, list, obstacleList);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(obstacleScene);
                window.show();
            }
    }

    public void enableEditing(ActionEvent event) throws IOException {
        for(Node n: anchor.getChildren())
            if(n instanceof TextField) {
                ((TextField) n).setEditable(true);
            }
    }

    //Method sends us back to the homepage screen
    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("enter-runway.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        RunwayController controller = loader.getController();
        controller.initRunway(runway,runwayList,list2,notifications,list,obstacleList);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

    //Method executes as soon as view/controller loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        continueButton.setDisable(true);
        disableChecks();
    }

    //Method opens another screen showing instructions on how to choose an obstacle
    public void information(ActionEvent event) throws IOException{
        Label info = new Label();
        info.setText("Instructions:\n" +
                "Either\n" + "1. Enter the name,height,distance from centreline and distance from threshold to create a new obstacle.\n" + "2. Click the 'Create new' button to add it to the list.\n" +
                "3. Find that obstacle on the list and click it.\n" + "4. Click on the 'Choose' button.\n" + "5. Click on the 'Continue' button.\n\n" +
                "OR\n" + "1. Choose one of the existing obstacles from the list and click on it.\n" + "2. Click on the 'Choose' button.\n" + "3. Click on the 'Continue' button.\n\n" +
                "**Delete an obstacle by scrolling down the list to find it then click on it and finally click on the 'Delete obstacle' button.\n" +
                "NOTE: After choosing an obstacle you can edit the data by pressing the Edit button then typing on the text fields.");
        Scene scene = new Scene(info,650,300);
        Stage stage = new Stage();
        stage.setTitle("Instructions on how to choose an obstacle");
        stage.setScene(scene);
        stage.show();
    }

    //Method opens another screen showing all the definitions for the obstacle parameters
    public void moreInfo (ActionEvent event) throws IOException{
        Label info1 = new Label();
        info1.setText("Definitions:\n"+
                "1. Name : Name the new obstacle.\n\n" +
                "2. Height : Height of the new obstacle.\n\n" +
                "3. Distance from centreline : Distance of the obstacle from the centreline of the runway.\n\n" +
                "4. Distance from threshold : Distance of the obstacle from the runway threshold.\n\n");
        Scene scene = new Scene (info1,500,150);
        Stage stage = new Stage();
        stage.setTitle("Definitions for obstacle parameters");
        stage.setScene(scene);
        stage.show();
    }
}


 */
