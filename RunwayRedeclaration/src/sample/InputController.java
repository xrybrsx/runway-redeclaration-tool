package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.xml.sax.SAXException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputController {

    //runway
    private Runway runway;
    FileChooser fileChooser = new FileChooser();
    @FXML
    private TextField runwayFileName;
    @FXML
    private TextField obstacleFileName;
    @FXML
    private TextField runwayNumber;
    @FXML
    private TextField tora;
    @FXML
    private TextField toda;
    @FXML
    private TextField asda;
    @FXML
    private TextField lda;
    @FXML
    private TextField threshold;
    @FXML
    private TextField stripEnd;
    @FXML
    private TextField stopWay;
    @FXML
    private TextField clearWay;
    @FXML
    private TextField resa;
    @FXML
    private Button createRunwayObjectButton;
    @FXML
    private SplitPane pane;
    @FXML
    private Button loadRunway;
    @FXML
    private Button loadObstacle;
    @FXML
    private Button saveRunways;
    @FXML
    private Button saveObstacles;
    @FXML
    private AnchorPane rightAnchor;
    @FXML
    private AnchorPane leftAnchor;
    @FXML
    private ListView list;
    @FXML
    private Button chooseButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteRunwayObjectButton;
    @FXML
    private Button continueButton;
    @FXML
    private ListView notifications;
    @FXML
    private Button helpButton;
    @FXML
    private Button info;
    private ArrayList<Runway> extractedRunways = new ArrayList<Runway>();
    private ArrayList<Obstacle> extractedObstacles = new ArrayList<Obstacle>();
    ArrayList<Runway> runwaysList = new ArrayList<Runway>();
    ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
    ArrayList<RunwayPair> runwayPairs = new ArrayList<RunwayPair>();
    ArrayList<String> runwayNames = new ArrayList<String>();
    ArrayList<String> obstacleNames = new ArrayList<String>();
    ArrayList notifList = new ArrayList();
    private ListView obList;
    private boolean valid = true;
    //obstacle
    @FXML
    private TextField name;
    @FXML
    private TextField height;
    @FXML
    private TextField centreline;
    @FXML
    private TextField distanceFromThreshold;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button newObstacle;
    @FXML
    private Button chooseObstacle;
    @FXML
    private Button deleteObstacle;
    @FXML
    private Button obstacleInfo;
    @FXML
    private Button editObstacle;
    @FXML
    private ListView listOfOb;
    private Obstacle obstacle;
    private Obstacle backObstacle;
    private ListView list2;
    //type of calculation
    @FXML
    private CheckBox landingOver;
    @FXML
    private CheckBox landingTowards;
    @FXML
    private CheckBox takeoffAway;
    @FXML
    private CheckBox takeoffTowards;
    private ObservableSet<CheckBox> selectedCheckBoxes = FXCollections.observableSet();

    private final int maxChecksSelected = 1;

    @FXML
    private TextField als;
    @FXML
    private TextField bpa;
    private Runway newRunway;
    private String whichCalculation;
    private LandingTowardsObstacle lto;
    private LandingOverObstacle loo;
    private TakeOffAwayFromObstacle toaf;
    private TakeOffTowardsObstacle toto;
    private boolean defObstaclesAdded = false;
    private boolean rightCalc = true;

    //method initialise the runway, list of obstacles and list of runways
    public void initRunway(Runway runway, Obstacle backObstacle, ArrayList<Runway> runwaysList1, ListView oldList, ListView list, ListView obstacleList1, ArrayList<Obstacle> obsList1, ArrayList<RunwayPair> runwayPairs, ArrayList<String> runwayNames, ArrayList<String> obstacleNames) {
        this.runway = runway;
        this.runwaysList = runwaysList1;
        this.obstacle = backObstacle;
        this.obstacleList = obsList1;
        this.runwayPairs = runwayPairs;
        this.runwayNames = runwayNames;
        this.obstacleNames = obstacleNames;
        for (Object o : oldList.getItems()) {
            this.list.getItems().add(o);
        }
        for (Object o : list.getItems()) {
            notifications.getItems().add(o);
        }
        for (Object o : obstacleList1.getItems()) {
            this.listOfOb.getItems().add(o);
        }

    }

    //method to add the filter on the text fields for the runway
    public void filter(TextField t) {
        t.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                t.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    //This method filters out any characters in the text fields (except the one where
    //you enter the name of the runway) that are not numbers, as all fields except the first one
    //require only numbers. It is triggered whenever you start typing on the affected text fields
    //this method is called whenever the user starts inputting stuff for the obstacle
    //it adds a filter to the height, centreline and threshold TextFields so that they only allow for numbers to be inputted
    public void addFilter() {
        filter(tora);
        filter(toda);
        filter(asda);
        filter(lda);
        filter(threshold);
        filter(stripEnd);
        filter(clearWay);
        filter(stopWay);
        filter(resa);
        filter(height);
        filter(centreline);
        filter(distanceFromThreshold);
        filter(als);
        filter(bpa);
    }

    //When "Add to list" Button pressed after entering runway measurements, a Runway object is created.
    public void createRunwayObject() {
        boolean exists = false;
        boolean invalidNumber = false;
        int number;
        char runwayChar = runwayNumber.getText().charAt(runwayNumber.getText().length()-1);

        if(Character.toUpperCase(runwayChar) == 'R' || Character.toUpperCase(runwayChar) == 'L' || Character.toUpperCase(runwayChar) == 'C')
            number = Integer.parseInt(runwayNumber.getText().substring(0,runwayNumber.getText().length()-1));
        else number = Integer.parseInt(runwayNumber.getText());

        if(number > 36 || number < 1) {
            invalidNumber = true;
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("The numbers allowed are 1-36.");
            error.showAndWait();
        }

        for(Runway r: runwaysList) {
            if(runwayNumber.getText().equals(r.getRunwayNumber()))
                exists = true;
        }

        //It also checks by name whether this runway exists already. If that's the case then it's not added and an info box is shown
        if(!exists) {
            if(runwayNumber.getText().length() == 1) {
                char ch = runwayNumber.getText().charAt(0);
                if(!Character.isDigit(ch)) {
                    invalidNumber = true;
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setHeaderText("Please add a number on the \"Runway number\" field.");
                    error.showAndWait();
                }
            }

            if(!invalidNumber) {
                runway = new Runway(runwayNumber.getText(), Integer.parseInt(tora.getText()), Integer.parseInt(toda.getText()), Integer.parseInt(asda.getText()), Integer.parseInt(lda.getText()), Integer.parseInt(threshold.getText()), Integer.parseInt(stripEnd.getText()), Integer.parseInt(clearWay.getText()),Integer.parseInt(stopWay.getText()), Integer.parseInt(resa.getText()));
                setRunway(runway);
                list.getItems().add(runway.getRunwayNumber());
                runwaysList.add(runway);
                runwayNames.add(runway.getRunwayNumber());
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                notifications.getItems().add(0,new String(dtf.format(now) + ": Runway " + runway.getRunwayNumber() + " was created."));
            }
            disableChecks();
        } else {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setHeaderText("A runway with this name already exists. Please enter a different name or runway.");
            error.showAndWait();
        }
    }

    //this makes an obstacle and adds it to the list on the right side of the scene (you will see its name there)
    public void createObstacleObject() {
        boolean exists = false;
        for(Obstacle o: obstacleList) {
            if(name.getText().equals(o.getName()))
                exists = true;
        }

        //It also checks by name whether this obstacle exists already. If that's the case then it's not added and an info box is shown
        if(!exists) {
            obstacle = new Obstacle(name.getText(), Integer.parseInt(height.getText()), Integer.parseInt(centreline.getText()), Integer.parseInt(distanceFromThreshold.getText()));
            setObstacle(obstacle);
            if (obstacle.getDistanceFromCentreLine() > 75) {
                centreLineNotification();
            }
            listOfOb.getItems().add(obstacle.getName());
            obstacleList.add(obstacle);
            obstacleNames.add(obstacle.getName());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            notifications.getItems().add(0,new String(dtf.format(now) + ": Obstacle " + obstacle.getName() + " was created."));
        } else {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setHeaderText("An obstacle with this name already exists. Please enter a different name or obstacle.");
            error.showAndWait();
        }
    }

    public void disableChecks() {
        canAddToList();
        canDeleteFromList();
        canChoose();
        canContinue();
        canSaveRunways();
        canSaveObstacles();
    }

    public void addDefaultObstacles() {

            Obstacle ob1 = new Obstacle("plane engine", 12, 50, 500);
            Obstacle ob2 = new Obstacle("Boeing 777", 22, 25, 1800);
        for(Obstacle o: obstacleList) {
            if(ob1.getName().equals(o.getName()) || ob2.getName().equals(o.getName()))
                    defObstaclesAdded = true;
                }

                if (!defObstaclesAdded) {
                    listOfOb.getItems().add(ob1.getName());
                    obstacleList.add(ob1);
                    obstacleNames.add(ob1.getName());
                    listOfOb.getItems().add(ob2.getName());
                    obstacleList.add(ob2);
                    obstacleNames.add(ob2.getName());
                }

            }



    private void canSaveObstacles() {
        if(obstacleList.isEmpty() || obstacleNames.isEmpty() || listOfOb.getItems().isEmpty())
            saveObstacles.setDisable(true);
        else saveObstacles.setDisable(false);
    }

    private void canSaveRunways() {
        if(runwaysList.isEmpty() || runwayNames.isEmpty() || list.getItems().isEmpty())
            saveRunways.setDisable(true);
        else saveRunways.setDisable(false);
    }

    //Method only enables the 'add to list' button after checking that all the textfields are filled.
    public void canAddToList() {
        if(runwayNumber.getText().isEmpty() || tora.getText().isEmpty() || toda.getText().isEmpty() || asda.getText().isEmpty() || threshold.getText().isEmpty() || stopWay.getText().isEmpty() || stripEnd.getText().isEmpty() || clearWay.getText().isEmpty() || resa.getText().isEmpty() || lda.getText().isEmpty())
            createRunwayObjectButton.setDisable(true);
        else createRunwayObjectButton.setDisable(false);

        if (height.getText().isEmpty() || centreline.getText().isEmpty() || distanceFromThreshold.getText().isEmpty() || name.getText().isEmpty()) {
            newObstacle.setDisable(true);
        } else {
            newObstacle.setDisable(false);
        }
    }

    //Method checks if item from runway list is selected and, therefore, decides
    //if the delete from list button can be used
    public void canDeleteFromList() {
        if (list.getSelectionModel().isEmpty()) {
            deleteRunwayObjectButton.setDisable(true);

            editButton.setDisable(true);

        } else {
            deleteRunwayObjectButton.setDisable(false);

            editButton.setDisable(false);

        }
        if (listOfOb.getSelectionModel().isEmpty()) {

            deleteObstacle.setDisable(true);

            editObstacle.setDisable(true);
        } else {

            deleteObstacle.setDisable(false);

            editObstacle.setDisable(false);
        }
    }

    //Method checks if item from runway list is selected and, therefore, decides if the
    //choose button can be used
    public void canChoose() {
        if (list.getSelectionModel().isEmpty()) {
            chooseButton.setDisable(true);

        } else {
            chooseButton.setDisable(false);

        }
        if (listOfOb.getSelectionModel().isEmpty()) {
            chooseObstacle.setDisable(true);
        } else {
            chooseObstacle.setDisable(false);
        }
    }

    //method for disabling the continue to results button
    public void canContinue() {

        landingTowards.setId("1");
        takeoffAway.setId("2");
        takeoffTowards.setId("3");
        landingOver.setId("4");
        boolean check = false;
        boolean alsAndBpa = false;

        for (Node n : rightAnchor.getChildren()) {
            if (n instanceof CheckBox) {
                if (((CheckBox) n).isSelected()) {
                    check = true;
                    switch(n.getId()) {
                        case "1":
                            alsAndBpa = true;
                            break;
                        case "2":
                            if (bpa.getText() != null && bpa.getText() != "")
                                alsAndBpa = true;
                            break;
                        case "3":
                            if (als.getText() != null && als.getText() != "")
                                alsAndBpa = true;
                            break;
                        case "4":
                            if (bpa.getText() != null && als.getText() != null && bpa.getText() != "" && als.getText() != "")
                                alsAndBpa = true;
                    }
                }
            }
        }

        if (runway == null || obstacle == null || check == false || alsAndBpa == false) {
            continueButton.setDisable(true);
        } else {
            continueButton.setDisable(false);
        }
    }

    //When "delete from list" Button pressed, runway object deleted from list
    public void deleteRunwayObject() {
        Object obj = list.getSelectionModel().getSelectedItem();
        int index = list.getSelectionModel().getSelectedIndex();
        String runwayToDelete = runwayNames.get(list.getSelectionModel().getSelectedIndex());
        list.getItems().remove(obj);
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
        runwaysList.remove(index);
        runwayNames.remove(runwayToDelete);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        notifications.getItems().add(0,new String(dtf.format(now) + ": Runway " + obj.toString() + " was deleted."));
    }

    //Setter method for runway object
    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    //Getter method for runway object
    public Runway getRunway() {
        return runway;
    }

    //Method gets the runway that we clicked on and its respective informations
    public void choose(ActionEvent event) throws IOException {
        Object obj = list.getSelectionModel().getSelectedItem();
        for (Runway o : runwaysList) {
            if (o.getRunwayNumber() == obj) {
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
                //this next line might need checking
                setRunway(new Runway(runwayNumber.getText(), Integer.parseInt(tora.getText()), Integer.parseInt(toda.getText()), Integer.parseInt(asda.getText()), Integer.parseInt(lda.getText()), Integer.parseInt(threshold.getText()), Integer.parseInt(stripEnd.getText()),  Integer.parseInt(clearWay.getText()),Integer.parseInt(stopWay.getText()), Integer.parseInt(resa.getText())));
            }
        }

        for(Node n: leftAnchor.getChildren())
            if(n instanceof TextField)
                ((TextField) n).setEditable(false);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        notifications.getItems().add(0,new String(dtf.format(now) + ": Runway " + obj.toString() + " was chosen from the list."));
    }

    //Method allows users to make changes on the text fields that contain information about the selected runway.
    public void editRunway (ActionEvent event) throws IOException {
        for(Node n: leftAnchor.getChildren())
            if(n instanceof TextField)
                ((TextField) n).setEditable(true);
    }

    //Method allows users to make changes on the text fields that contain information about the selected obstacle.
    public void editObstacle (ActionEvent event) throws IOException {
        for(Node n: rightAnchor.getChildren())
            if(n instanceof TextField)
                ((TextField) n).setEditable(true);
    }

    //Method opens another screen showing all the definitions for the runway parameters
    public void moreInfo(ActionEvent event) throws IOException {
        Label info1 = new Label();
        info1.setText("Definitions:\n" +
                "1. Take-Off Run Available (TORA) : Length of runway available for take-off.\n\n" +
                "2. Take-Off Distance Available (TODA) : TORA plus ClearWay.\n\n" +
                "3. Accelerate-Stop Distance Available (ASDA) : TORA plus any StopWay.\n\n" +
                "4. Landing Distance Available (LDA) : Length of the runway available for landing.\n\n" +
                "5. Displaced Threshold : A runway threshold located at a point other than the physical beginning or the end of the runway. The displaced portion can be used for\n" +
                "take‐off but not for landing. A landing aircraft can use the displaced area on the opposite end of the runway.\n\n" +
                "6. Strip End : An area between the end of the runway and the end of the runway strip.\n\n" +
                "7. StopWay : An area beyond the end of the TORA, which may be used in the case of an abandoned take‐off so that an aircraft can be safely brought to a stop.\n\n" +
                "8. ClearWay : An area beyond the end of the TORA, which may be used during an aircraft’s initial climb to a specified height.\n\n" +
                "9. Runway End Safety Area (RESA) : An area symmetrical about the extended runway centreline and adjacent to the end of the strip primarily intended to reduce the\n" +
                "risk of damage to an aircraft undershooting or overrunning the runway.\n\n");
        VBox box = new VBox(info1);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box, 900, 350);
        Stage stage = new Stage();
        stage.setTitle("Definitions for runway parameters");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    //Method displays pop-up stating that, as the distance of the obstacle from the threshold is greater than
    //75m.....no runway redeclaration is required
    public void centreLineNotification() {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setHeaderText("Distance from centreline is greater than 75m. No runway redeclaration is required.");
        error.showAndWait();
    }

    //When "delete obstacle" Button pressed, obstacle is deleted from the list
    public void deleteObstacleObject() {
        Object obj = listOfOb.getSelectionModel().getSelectedItem();
        int index = listOfOb.getSelectionModel().getSelectedIndex();
        String obstacleToDelete = obstacleNames.get(listOfOb.getSelectionModel().getSelectedIndex());
        listOfOb.getItems().remove(obj);
        obstacleList.remove(obj);
        name.clear();
        height.clear();
        centreline.clear();
        distanceFromThreshold.clear();
        obstacleList.remove(index);
        obstacleNames.remove(obstacleToDelete);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        notifications.getItems().add(0,new String(dtf.format(now) + ": Obstacle " + obj.toString() + " was deleted."));
    }

    //Method takes the obstacle that we clicked on and gets all of its respective informations.
    public void chooseOb(ActionEvent event) throws IOException {
        Object obj = listOfOb.getSelectionModel().getSelectedItem();
        for (Obstacle o : obstacleList) {
            if (o.getName() == obj) {
                name.setText(o.getName());
                height.setText(String.valueOf(o.getHeight()));
                centreline.setText(String.valueOf(o.getDistanceFromCentreLine()));
                distanceFromThreshold.setText(String.valueOf(o.getDistanceFromThreshold()));
                //this next line might need checking
                setObstacle(new Obstacle(name.getText(), Integer.parseInt(height.getText()), Integer.parseInt(centreline.getText()), Integer.parseInt(distanceFromThreshold.getText())));
            }
        }
        name.setEditable(false);
        height.setEditable(false);
        centreline.setEditable(false);
        distanceFromThreshold.setEditable(false);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        notifications.getItems().add(0,new String(dtf.format(now) + ": Obstacle " + obj.toString() + " was chosen from list of obstacles."));
    }

    //Method sets the obstacle
    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    //Method gets the current obstacle
    public Obstacle getObstacle() {
        return obstacle;
    }

    //Method creates a new landing towards obstacle object and a new runway that contains the recalculated runway values.
    public void landingTowardsObstacle() {
        lto = new LandingTowardsObstacle();
        newRunway = new Runway(runway.getRunwayNumber(), runway.getTora(), runway.getToda(), runway.getAsda(), lto.recalculate(obstacle.getDistanceFromThreshold(), runway.getResa(), runway.getStripEnd()), runway.getDisplacedThreshold(), runway.getStripEnd(),  runway.getClearway(),runway.getStopway(), runway.getResa());
        if(newRunway.getLda() < 0) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setHeaderText("Some results are negative. You should change the parameters for the calculation.");
            error.showAndWait();
            rightCalc = false;
        }else {
            rightCalc = true;
        }
        whichCalculation = "lto";
    }

    //Method creates a new landing over obstacle object and a new runway that contains the recalculated runway values.
    public void landingOverObstacle() {
        loo = new LandingOverObstacle(Integer.parseInt(als.getText()), Integer.parseInt(bpa.getText()));
        newRunway = new Runway(runway.getRunwayNumber(), runway.getTora(), runway.getToda(), runway.getAsda(), loo.recalculate(runway.getTora(), obstacle.getDistanceFromThreshold(), obstacle.getHeight(), loo.getAls(), runway.getResa(), runway.getStripEnd(), loo.getBlastProtectionAllowance()), runway.getDisplacedThreshold(), runway.getStripEnd(),  runway.getClearway(),runway.getStopway(), runway.getResa());
        if(newRunway.getLda() < 0) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setHeaderText("Some results are negative. You should change the parameters for the calculation.");
            error.showAndWait();
            rightCalc = false;
        }else {
            rightCalc = true;
        }
        whichCalculation = "loo";
    }

    //Method creates a new take-off towards obstacle object and a new runway that contains the recalculated runway values.
    public void takeOffTowardsObstacle() {
        toto = new TakeOffTowardsObstacle(Integer.parseInt(als.getText()));
        newRunway = new Runway(runway.getRunwayNumber(), toto.recalculate(obstacle.getDistanceFromThreshold(), runway.getDisplacedThreshold(), obstacle.getHeight(), toto.getTocs(), runway.getResa(), runway.getStripEnd()), toto.recalculate(obstacle.getDistanceFromThreshold(), runway.getDisplacedThreshold(), obstacle.getHeight(), toto.getTocs(), runway.getResa(), runway.getStripEnd()), toto.recalculate(obstacle.getDistanceFromThreshold(), runway.getDisplacedThreshold(), obstacle.getHeight(), toto.getTocs(), runway.getResa(), runway.getStripEnd()), runway.getLda(), runway.getDisplacedThreshold(), runway.getStripEnd(), runway.getClearway(),runway.getStopway(), runway.getResa());
        if(newRunway.getTora() < 0 || newRunway.getToda() < 0 || newRunway.getAsda() < 0) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setHeaderText("Some results are negative. You should change the parameters for the calculation.");
            error.showAndWait();
            rightCalc = false;
        }else {
            rightCalc = true;
        }
        whichCalculation = "toto";
    }

    //Method creates a new take-off away from obstacle object and a new runway that contains the recalculated runway values.
    public void takeOffAwayFromObstacle() {
        toaf = new TakeOffAwayFromObstacle(Integer.parseInt(bpa.getText()));
        toaf.recalculate(runway.getTora(), runway.getToda(), runway.getAsda(), obstacle.getDistanceFromThreshold(), toaf.getBlastProtectionAllowance(), runway.getClearway(), runway.getStopway());
        int tora = toaf.getNewTora();
        int toda = toaf.getNewToda();
        int asda = toaf.getNewAsda();
        if(tora < 0 || toda < 0 || asda < 0) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setHeaderText("Some results are negative or the blast protection allowance is too much. You should change the parameters for the calculation.");
            error.showAndWait();
            rightCalc = false;
        }else {
            rightCalc = true;
        }
        newRunway = new Runway(runway.getRunwayNumber(), tora, toda, asda, runway.getLda(), runway.getDisplacedThreshold(), runway.getStripEnd(), runway.getClearway(), runway.getStopway(), runway.getResa());
        whichCalculation = "toaf";
    }

    //Method opens another window that allows the user to search and load the xml file that contains all the different runways onto the runway list.
    public void loadXMLRunway(){
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml") );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFileRunway(file, runwaysList);
        }
    }

    //Method opens another window that allows the user to search and load the xml file that contains all the different obstacles onto the obstacle list.
    public void loadXMLObstacle(){
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFileObstacle(file, obstacleList);
        }
    }

    //methods to save the runways into XML files
    public void saveXMLRunways() {
        XMLWriter writer = new XMLWriter();
        if(runwayFileName.getText() == "" || runwayFileName.getText() == null) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Please e       nter the name of the file to save the runways (without extension).");
            error.showAndWait();
        }
        else {
            String file = runwayFileName.getText() + ".xml";
            writer.saveRunwaysToXML(file, runwaysList);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            notifications.getItems().add(0,new String(dtf.format(now) + ": Runways saved at " +  writer.getRunwaysFilePath()) + ".");
        }
    }

    //methods to save the obstacles into XML files
    public void saveXMLObstacles() {
        XMLWriter writer = new XMLWriter();
        if(obstacleFileName.getText() == "" || obstacleFileName.getText() == null) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Please enter the name of the file to save the obstacles (without extension).");
            error.showAndWait();
        }
        else {
            String file = obstacleFileName.getText() + ".xml";
            writer.saveObstaclesToXML(file, obstacleList);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            notifications.getItems().add(0,new String(dtf.format(now) + ": Obstacles saved at " +  writer.getObstaclesFilePath() + "."));
        }
    }

    private void openFileObstacle(File file, ArrayList<Obstacle> obstacleList) {
        try {
            XMLReader reader = new XMLReader();
            boolean wrongFile;
            extractedObstacles = reader.parseObstacle(file);
            if(extractedObstacles.isEmpty())
                wrongFile = true;
            else wrongFile = false;
            int counter = 0;
            for(Obstacle o: extractedObstacles) {
                boolean exists = false;
                for(Obstacle o2: obstacleList)
                    if(o.getName().equals(o2.getName()))
                        exists = true;
                if(!exists) {
                    setObstacle(o);
                    if (o.getDistanceFromCentreLine() > 75) {
                        centreLineNotification();
                    }
                    listOfOb.getItems().add(o.getName());
                    obstacleList.add(o);
                    obstacleNames.add(o.getName());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    notifications.getItems().add(0,new String(dtf.format(now) + ": Obstacle " + o.getName() + " was loaded."));
                    disableChecks();
                    counter++;
                }
            }
            if(counter == 0) {
                Alert error = new Alert(Alert.AlertType.INFORMATION);
                if(listOfOb.getItems().isEmpty() || wrongFile == true) {
                    error.setHeaderText("No obstacles have been found in this file. Please use another file.");
                } else error.setHeaderText("No new obstacles have been added as they already exist.");
                error.showAndWait();
            }
        } catch (IOException ex) {
            Logger.getLogger(
                    InputController.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
    private void openFileRunway(File file, ArrayList<Runway> runwaysList) {
        try {
            XMLReader reader = new XMLReader();
            boolean wrongFile;
            extractedRunways = reader.parseRunway(file);

            if(extractedRunways.isEmpty())
                wrongFile = true;
            else wrongFile = false;

            int counter = 0;

            for(Runway r: extractedRunways) {
                boolean exists = false;
                boolean invalidNumber = false;

                for(Runway r2: runwaysList)
                    if(r.getRunwayNumber().equals(r2.getRunwayNumber()))
                        exists = true;

                if(!exists) {
                    if(r.getRunwayNumber().length() == 1) {
                        char ch = r.getRunwayNumber().charAt(0);
                        if(!Character.isDigit(ch))
                            invalidNumber = true;
                    }

                    else {
                        int number;
                        char runwayChar = r.getRunwayNumber().charAt(r.getRunwayNumber().length()-1);

                        if(Character.toUpperCase(runwayChar) == 'R' || Character.toUpperCase(runwayChar) == 'L' || Character.toUpperCase(runwayChar) == 'C')
                            number = Integer.parseInt(r.getRunwayNumber().substring(0,r.getRunwayNumber().length()-1));
                        else number = Integer.parseInt(r.getRunwayNumber());

                        if(number > 36 || number < 1)
                            invalidNumber = true;
                    }
                }

                if(!exists && !invalidNumber) {
                    setRunway(r);
                    list.getItems().add(r.getRunwayNumber());
                    runwaysList.add(r);
                    runwayNames.add(r.getRunwayNumber());
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    notifications.getItems().add(0,new String(dtf.format(now) + ": Runway " + r.getRunwayNumber() + " was loaded."));
                    disableChecks();
                    counter++;
                }
            }

            if(counter == 0) {
                Alert error = new Alert(Alert.AlertType.INFORMATION);
                if(list.getItems().isEmpty() || wrongFile == true) {
                    error.setHeaderText("No runways have been found in this file. Please use another file.");
                } else error.setHeaderText("No new runways have been added as they already exist.");
                error.showAndWait();
            }

        } catch (IOException ex) {
            Logger.getLogger(
                    InputController.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    //lambda function to send to results scene
    //Landing Towards Obstacle calculation done in this method
    public void switchToResults(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("results.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        ResultsController controller = loader.getController();
        if (landingTowards.isSelected()) {
            landingTowardsObstacle();
            controller.initRunway(runway, newRunway, lto, obstacle, notifications, list, runwaysList, listOfOb, obstacleList,runwayPairs, runwayNames, obstacleNames);
        } else if (landingOver.isSelected()) {
            landingOverObstacle();
            controller.initRunway(runway, newRunway, loo, obstacle, notifications, list, runwaysList, listOfOb, obstacleList,runwayPairs, runwayNames, obstacleNames);
        } else if (takeoffAway.isSelected()) {
            takeOffAwayFromObstacle();
            controller.initRunway(runway, newRunway, toaf, obstacle, notifications, list, runwaysList, listOfOb, obstacleList,runwayPairs, runwayNames, obstacleNames);
        } else if (takeoffTowards.isSelected()) {
            takeOffTowardsObstacle();
            controller.initRunway(runway, newRunway, toto, obstacle, notifications, list, runwaysList, listOfOb, obstacleList,runwayPairs, runwayNames, obstacleNames);
        }
        if (rightCalc == true) {
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(obstacleScene);
            window.show();
        }
    }

    //Method opens another screen showing all the definitions for the obstacle parameters
    public void moreInfoObs() throws IOException {
        Label info1 = new Label();
        info1.setText("Definitions:\n"+
                "1. Name : Name the new obstacle.\n\n" +
                "2. Height : Height of the new obstacle.\n\n" +
                "3. Distance from centreline : Distance of the obstacle from the centreline of the runway.\n\n" +
                "4. Distance from threshold : Distance of the obstacle from the runway threshold.\n\n" +
                "5. Slope : Rate on which the plane descends. If the slope is 50, then for every 50 m the plane \n\n" +
                "travels, it descends by 1 m. \n\n" +
                "6: Blast protection allowance : The minimum distance the plane has to keep from an obstacle \n\n" +
                "behind its engines.");
        VBox box = new VBox(info1);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box, 500, 300);
        Stage stage = new Stage();
        stage.setTitle("Definitions for obstacles parameters");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    //method to restrict only one choose box to be clicked
    public void setSelectedCheckBoxes() {

        landingTowards.setId("1");
        takeoffAway.setId("2");
        takeoffTowards.setId("3");
        landingOver.setId("4");

        int i = 0;
        for (Node n : rightAnchor.getChildren()) {
            if (n instanceof CheckBox) {
                if (((CheckBox) n).isSelected()) {
                    i++;
                }
            }
        }
        if (i == 1) {
            for (Node n : rightAnchor.getChildren()) {
                if (n instanceof CheckBox) {
                    if (!((CheckBox) n).isSelected()) {
                        n.setDisable(true);
                    }
                   else {
                        switch(n.getId()) {
                            case "1":
                                als.setDisable(true);
                                bpa.setDisable(true);
                                break;
                            case "2":
                                als.setDisable(true);
                                bpa.setDisable(false);
                                break;
                            case "3":
                                bpa.setDisable(true);
                                als.setDisable(false);
                                break;
                            case "4":
                                als.setDisable(false);
                                bpa.setDisable(false);
                        }
                    }
                }
            }
        } else {
            for (Node n : rightAnchor.getChildren()) {
                if (n instanceof CheckBox) {
                    n.setDisable(false);
                    als.clear();
                    bpa.clear();
                }
            }
            als.setDisable(true);
            bpa.setDisable(true);
        }
    }

    //Method opens another window and shows steps on how to use the tool
    public void instructions (ActionEvent event) throws IOException{
        Label info = new Label();
        info.setText("Instructions:\n\n"+
                "(1) CHOOSE A RUNWAY:\n"+
                "Either\n" + "1. Enter a runway number and all the other runway parameters to create a new runway.\n" + "2. Click the 'Add to list' button to add it to the list.\n" +
                "3. Find that runway on the list and click it.\n" + "4. Click on the 'Choose' button.\n" +
                "OR\n" + "1. Choose one of the existing runways from the list and click on it.\n" + "2. Click on the 'Choose' button.\n" +
                "OR\n" + "1. Click on 'Load file'. \n" + "2. Go and look for the runways XML document you want to load. \n" + "3. Double click on it. \n" +
                "4. Double click on the runway you wish to choose from the list and click 'Choose'. \n\n" +
                "(2) CHOOSE AN OBSTACLE:\n"+
                "Either\n" + "1. Enter the name, height, distance from centreline and distance from threshold to create a new obstacle.\n" + "2. Click the 'Add to list' button to add it to the list.\n" +
                "3. Find that obstacle on the list and click it.\n" + "4. Click on the 'Choose' button.\n" +
                "OR\n" + "1. Choose one of the existing obstacles from the list and click on it.\n" + "2. Click on the 'Choose' button.\n"+
                "OR\n" + "1. Click on 'Load file'. \n" + "2. Go and look for the obstacles XML document you want to load. \n" + "3. Double click on it. \n" +
                "4. Double click on the obstacle you wish to choose from the list and click 'Choose'. \n\n" +
                "NOTE:\n"+"(i) After choosing an obstacle/runway you can edit their respective data by pressing the Edit button under their respective section then typing on their respective text fields.\n"+
                "(ii) Delete an obstacle/runway by scrolling down their respective lists to find that obstacle/runway then click on it and finally click on the 'Delete from list' button that is located below their respective section.\n\n"+
                "(3) CHOOSE A TYPE OF RECALCULATION TO PERFORM:\n"+
                "1. Choose and click on the checkbox that has the type of recalculation that you would like to perform.\n\n"+
                "(4) ENTER VALUES FOR THE BPS AND/OR SLOPE DEPENDING ON THE TYPE OF RECALCULATION THAT IS SELECTED:\n"+
                "1. Enter values for the bps and/or als according to the type of recalculation that is selected.\n"+
                "2. Click on the 'Continue' button.");
        VBox box = new VBox(info);
        box.setAlignment(Pos.CENTER);
        Scene scene = new Scene(box, 1100, 800);
        Stage stage = new Stage();
        stage.setTitle("Instructions on how to use the tool");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}



