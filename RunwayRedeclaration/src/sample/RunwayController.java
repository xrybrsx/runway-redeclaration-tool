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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RunwayController implements Initializable {

    private Runway runway;
    @FXML private TextField runwayNumber;
    @FXML private TextField tora;
    @FXML private TextField toda;
    @FXML private TextField asda;
    @FXML private TextField lda;
    @FXML private TextField threshold;
    @FXML private TextField stripEnd;
    @FXML private TextField stopWay;
    @FXML private TextField clearWay;
    @FXML private TextField resa;
    @FXML private Button createRunwayObjectButton;
    @FXML private AnchorPane inputAnchor;
    @FXML private ListView list;
    @FXML private Button chooseButton;
    @FXML private Button editButton;
    @FXML private Button deleteRunwayObjectButton;
    @FXML private Button continueButton;
    @FXML private ListView notifications;
    @FXML private Button helpButton;
    @FXML private Button info;
    ArrayList<Runway> runwaysList = new ArrayList<Runway>();
    ArrayList<Obstacle> obsList = new ArrayList<Obstacle>();
    ArrayList notifList = new ArrayList();
    private ListView obstacleList;
    private boolean valid = true;

    //method initialise the runway, list of obstacles and list of runways
    public void initRunway(Runway runway, ArrayList<Runway> runwaysList1,ListView oldList,ListView list,ListView obstacleList1, ArrayList<Obstacle> obsList1){
        this.runway = runway;
        this.runwaysList = runwaysList1;
        for (Object o: oldList.getItems()){
            this.list.getItems().add(o);
        }
        for (Object o: list.getItems()){
            notifications.getItems().add(o);
        }
        this.obstacleList = obstacleList1;
        this.obsList = obsList1;
    }

    //This method filters out any characters in the text fields (except the one where
    //you enter the name of the runway) that are not numbers, as all fields except the first one
    //require only numbers. It is triggered whenever you start typing on the affected text fields
    public void addFilter() {
        for(Node n: inputAnchor.getChildren())
            if(n instanceof TextField) {
                ((TextField) n).textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!newValue.matches("\\d*")) {
                        ((TextField) n).setText(newValue.replaceAll("[^\\d]", ""));
                    }
                });
            }
    }

    //When "Add to list" Button pressed after entering runway measurements, a Runway object is created.
    public void createRunwayObject(){
        runway = new Runway(runwayNumber.getText(),Integer.parseInt(tora.getText()),Integer.parseInt(toda.getText()),Integer.parseInt(asda.getText()),Integer.parseInt(lda.getText()),Integer.parseInt(threshold.getText()), Integer.parseInt(stripEnd.getText()),Integer.parseInt(stopWay.getText()),Integer.parseInt(clearWay.getText()),Integer.parseInt(resa.getText()));
        setRunway(runway);
        list.getItems().add(runway.getRunwayNumber());
        runwaysList.add(runway);
        notifications.getItems().add(new String("Runway " +  runway.getRunwayNumber() + " was created."));
        disableChecks();
    }

    //Method constanlty checks if the buttons on the scene should be active or not
    public void disableChecks(){
        canAddToList();
        canDeleteFromList();
        canChoose();
    }

    //Method checks if all fields filled in and whether a runway can be added to the runway list
    public void canAddToList(){
        for(Node n: inputAnchor.getChildren())
            if(n instanceof TextField) {
                valid = ((TextField) n).getText().length() != 0;
            }
        if(!valid){
            createRunwayObjectButton.setDisable(true);
            editButton.setDisable(true);
        }
        else{
            createRunwayObjectButton.setDisable(false);
        }
    }

    //Method checks if item from runway list is selected and, therefore, decides
    //if the delete from list button can be used
    public void canDeleteFromList(){
      if(list.getSelectionModel().isEmpty()){
          deleteRunwayObjectButton.setDisable(true);
      }
      else{
          deleteRunwayObjectButton.setDisable(false);
      }
    }

    //Method checks if item from runway list is selected and, therefore, decides if the
    //choose button can be used
    public void canChoose(){
        if(list.getSelectionModel().isEmpty()){
            chooseButton.setDisable(true);
        }
        else{
            chooseButton.setDisable(false);
        }
    }

    //When "delete from list" Button pressed, runway object deleted from list
    public void deleteRunwayObject() {
        Object obj = list.getSelectionModel().getSelectedItem();
        list.getItems().remove(obj);
        runwaysList.remove(obj);
        runwayNumber.clear();
        tora.clear();
        toda.clear();
        asda.clear();
        lda.clear();
        threshold.clear();
        stripEnd.clear();
        stopWay.clear();
        clearWay.clear();
        resa.clear();
        notifications.getItems().add(new String("Runway " +  obj.toString() + " was deleted."));
        disableChecks();
    }

    //Setter method for runway object
    public void setRunway(Runway runway){
        this.runway = runway;
    }

    //Getter method for runway object
    public Runway getRunway(){
        return runway;
    }

    //Method gets the runway that we clicked on and its respective informations
    public void choose(ActionEvent event) throws IOException {
        Object obj = list.getSelectionModel().getSelectedItem();
        for (Runway o: runwaysList) {
            if(o.getRunwayNumber() == obj) {
                runwayNumber.setText(o.getRunwayNumber());
                tora.setText(String.valueOf(o.getTora()));
                toda.setText(String.valueOf(o.getToda()));
                asda.setText(String.valueOf(o.getAsda()));
                threshold.setText(String.valueOf(o.getDisplacedThreshold()));
                lda.setText(String.valueOf(o.getLda()));
                stopWay.setText(String.valueOf(o.getStopway()));
                clearWay.setText(String.valueOf(o.getClearway()));
                stripEnd.setText(String.valueOf(o.getStripEnd()));
                resa.setText(String.valueOf(o.getResa()));
            }
        }

        notifications.getItems().add(new String("Runway " +  obj.toString() + " was chosen from the list."));
        continueButton.setDisable(false);
        editButton.setDisable(false);

        for(Node n: inputAnchor.getChildren())
        if(n instanceof TextField) {
            ((TextField) n).setEditable(false);
        }
    }

    public void enableEditing(ActionEvent event) throws IOException {
        for(Node n: inputAnchor.getChildren())
            if(n instanceof TextField) {
                ((TextField) n).setEditable(true);
            }
    }

    //Method allows switching of scenes (views) from "enter runway" view to obstacle view
    public void switchScenes(ActionEvent event) throws IOException {
        Parent obstacleParent = FXMLLoader.load(getClass().getResource("obstacle.fxml"));
        Scene obstacleScene = new Scene(obstacleParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

    public void switchScenesCarry(ActionEvent event) throws IOException {
        //First checks whether there is any empty TextField. If that's the case then an error is thrown.
        for(Node n: inputAnchor.getChildren())
            if(n instanceof TextField)
                valid = ((TextField) n).getText().length() != 0;

        //This is the error
        if(!valid) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Please fill in all text fields.");
            error.showAndWait();
        }

        //If all fields are non-empty then go ahead
        else {
            //Two extra lines to use as runway the inputs in the text fields even if the "Add to list" button has not been pushed
            runway = new Runway(runwayNumber.getText(),Integer.parseInt(tora.getText()),Integer.parseInt(toda.getText()),Integer.parseInt(asda.getText()),Integer.parseInt(lda.getText()),Integer.parseInt(threshold.getText()), Integer.parseInt(stripEnd.getText()),Integer.parseInt(stopWay.getText()),Integer.parseInt(clearWay.getText()),Integer.parseInt(resa.getText()));
            setRunway(runway);
            notifications.getItems().add(new String("Runway " +  runway.getRunwayNumber() + " was loaded."));
//            for (Object o: notifications.getItems()){
//                notifList.add(o);
//            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("obstacle.fxml"));
            Parent obstacleParent = loader.load();
            Scene obstacleScene = new Scene(obstacleParent);
            ObstacleController controller = loader.getController();
            controller.initRunway(runway,notifications,list,runwaysList,obstacleList,obsList);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(obstacleScene);
            window.show();
        }
    }

    //Methods carried out when scene first loaded
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        continueButton.setDisable(true);
        disableChecks();
    }

    //Method opens another screen showing instructions on how to choose a runway
    public void information(ActionEvent event) throws IOException{
        Label info = new Label();
        info.setText("Instructions:\n" +
                "Either\n" + "1. Enter a runway number and all the other runway parameters to create a new runway.\n" + "2. Click the 'Add to list' button to add it to the list.\n" +
                "3. Find that runway on the list and click it.\n" + "4. Click on the 'Choose' button.\n" + "5. Click on the 'Continue' button.\n\n" +
                "OR\n" + "1. Choose one of the existing runways from the list and click on it.\n" + "2. Click on the 'Choose' button.\n" + "3. Click on the 'Continue' button.\n\n" +
                "**Delete a runway by scrolling down the list to find it then click on it and finally click on the 'Delete from list' button.\n" +
                "NOTE: After choosing a runway you can edit the data by pressing the Edit button then typing on the text fields.");
        Scene scene = new Scene(info,650,300);
        Stage stage = new Stage();
        stage.setTitle("Instructions on how to choose a runway");
        stage.setScene(scene);
        stage.show();
    }

    //Method opens another screen showing all the definitions for the runway parameters
    public void moreInfo (ActionEvent event) throws IOException{
        Label info1 = new Label();
        info1.setText("Definitions:\n"+
                "1. Take-Off Run Available (TORA) : Length of runway available for take-off.\n\n" +
                "2. Take-Off Distance Available (TODA) : TORA plus ClearWay.\n\n" +
                "3. Accelerate-Stop Distance Available (ASDA) : TORA plus any StopWay.\n\n" +
                "4. Landing Distance Available (LDA) : Length of the runway available for landing.\n\n" +
                "5. Displaced Threshold : A runway threshold located at a point other than the physical beginning or the end of the runway. The displaced portion can be used for\n" +
                "take‐off but not for landing. A landing aircraft can use the displaced area on the opposite end of the runway.\n\n"+
                "6. Strip End : An area between the end of the runway and the end of the runway strip.\n\n" +
                "7. StopWay : An area beyond the end of the TORA, which may be used in the case of an abandoned take‐off so that an aircraft can be safely brought to a stop.\n\n" +
                "8. ClearWay : An area beyond the end of the TORA, which may be used during an aircraft’s initial climb to a specified height.\n\n" +
                "9. Runway End Safety Area (RESA) : An area symmetrical about the extended runway centreline and adjacent to the end of the strip primarily intended to reduce the\n" +
                "risk of damage to an aircraft undershooting or overrunning the runway.\n\n");
        Scene scene = new Scene (info1,900,350);
        Stage stage = new Stage();
        stage.setTitle("Definitions for runway parameters");
        stage.setScene(scene);
        stage.show();
    }
}


 */