package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ResultsController {

    @FXML private Button showResults;
    @FXML private Label newToda;
    @FXML private Label newTora;
    @FXML private Label newAsda;
    @FXML private Label newLDA;
    @FXML private Label newDisplacedThreshold;
    @FXML private Label newStripEnd;
    @FXML private Label newClearway;
    @FXML private Label newStopway;
    @FXML private Label newResa;
    @FXML private Label oldTora;
    @FXML private Label oldToda;
    @FXML private Label oldAsda;
    @FXML private Label oldLDA;
    @FXML private Label oldDisplacedThreshold;
    @FXML private Label oldStripEnd;
    @FXML private Label oldStopway;
    @FXML private Label oldClearway;
    @FXML private Label oldResa;
    @FXML private Canvas canvas;
    @FXML private Button breakdownCalc;
    @FXML private Button returnToHomePage;
    @FXML private ListView notifications;
    @FXML private Button helpButton;
    private Runway oldRunway;
    private Runway newRunway;
    private LandingOverObstacle loo;
    private LandingTowardsObstacle lto;
    private TakeOffAwayFromObstacle toa;
    private TakeOffTowardsObstacle toto;
    private Obstacle obstacle;
    private String calculationOcurred;
    private TypeOfCalcController landingTowards;
    private ListView list;
    private ListView obstacleList;
    ArrayList<Runway> runwaysList = new ArrayList<Runway>();
    ArrayList<Obstacle> obsList = new ArrayList<Obstacle>();

    //Method initialises runway and obstacle arguments given that a calculation has taken place
    public void initRunway(Runway oldRunway, Runway newRunway, Obstacle obstacle, ListView list,ListView list1, ArrayList<Runway> runwaysList1,ListView obstacleList1,ArrayList<Obstacle> obsList1){
        this.oldRunway = oldRunway;
        this.newRunway = newRunway;
        this.obstacle = obstacle;
        notifications.getItems().addAll(list.getItems());
        this.list = list1;
        this.runwaysList = runwaysList1;
        this.obstacleList = obstacleList1;
        this.obsList = obsList1;
    }

    //Method initialises runway, obstacle and calculation arguments given that a landing away From Obstacle calculation
    //has taken place
    public void initRunway(Runway oldRunway, Runway newRunway, LandingOverObstacle loo, Obstacle obstacle, ListView list,ListView list1, ArrayList<Runway> runwaysList1, ListView obstacleList1,ArrayList<Obstacle> obsList1){
        this.oldRunway = oldRunway;
        this.newRunway = newRunway;
        this.obstacle = obstacle;
        this.loo = loo;
        calculationOcurred = "loo";
        notifications.getItems().addAll(list.getItems());
        this.list = list1;
        this.runwaysList = runwaysList1;
        this.obstacleList = obstacleList1;
        this.obsList = obsList1;
    }

    //Method initialises runway, obstacle and calculation arguments given that a Landing towards obstacle calculation
    //has taken place
    public void initRunway(Runway oldRunway, Runway newRunway, LandingTowardsObstacle lto, Obstacle obstacle, ListView list,ListView list1, ArrayList<Runway> runwaysList1, ListView obstacleList1,ArrayList<Obstacle> obsList1){
        this.oldRunway = oldRunway;
        this.newRunway = newRunway;
        this.obstacle = obstacle;
        this.lto = lto;
        calculationOcurred = "lto";
        notifications.getItems().addAll(list.getItems());
        this.list = list1;
        this.runwaysList = runwaysList1;
        this.obstacleList = obstacleList1;
        this.obsList = obsList1;
    }

    //Method initialises runway, obstacle and calculation arguments given that a Take off Away from obstacle calculation
    //has taken place
    public void initRunway(Runway oldRunway, Runway newRunway, TakeOffAwayFromObstacle toa, Obstacle obstacle, ListView list,ListView list1, ArrayList<Runway> runwaysList1, ListView obstacleList1,ArrayList<Obstacle> obsList1){
        this.oldRunway = oldRunway;
        this.newRunway = newRunway;
        this.obstacle = obstacle;
        this.toa = toa;
        calculationOcurred = "toa";
        notifications.getItems().addAll(list.getItems());
        this.list = list1;
        this.runwaysList = runwaysList1;
        this.obstacleList = obstacleList1;
        this.obsList = obsList1;
    }

    //Method initialises runway, obstacle and calculation arguments given that a Take off towards obstacle calculation
    //has taken place
    public void initRunway(Runway oldRunway, Runway newRunway, TakeOffTowardsObstacle toto, Obstacle obstacle, ListView list,ListView list1, ArrayList<Runway> runwaysList1,ListView obstacleList1,ArrayList<Obstacle> obsList1){
        this.oldRunway = oldRunway;
        this.newRunway = newRunway;
        this.obstacle = obstacle;
        this.toto = toto;
        calculationOcurred = "toto";
        notifications.getItems().addAll(list.getItems());
        this.list = list1;
        this.runwaysList = runwaysList1;
        this.obstacleList = obstacleList1;
        this.obsList = obsList1;
    }

    //Method returns strings showing which calculation, out of the four, has occurred
    public String getCalculationOcurred(){
        return calculationOcurred;
    }

    //Method allows switching from viewing the new runway with the results to viewing both the old and new runways
    public void goToBothRunwaysView(ActionEvent event) throws IOException {
        Parent obstacleParent = FXMLLoader.load(getClass().getResource("both-runways.fxml"));
        Scene obstacleScene = new Scene(obstacleParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

    //Method displays updated runway parameters after calculations as well as old runway values.
    public void showResults(){
        notifications.getItems().add(new String("Calculations done."));
        newLDA.setText(String.valueOf("LDA: " + newRunway.getLda() + " m"));
        newTora.setText(String.valueOf("TORA: " + newRunway.getTora() + " m"));
        newToda.setText(String.valueOf("TODA: " + newRunway.getToda() + " m"));
        newAsda.setText(String.valueOf("ASDA: " + newRunway.getAsda() + " m"));
        newDisplacedThreshold.setText(String.valueOf("DisplacedThreshold: " + newRunway.getDisplacedThreshold() + " m"));
        newStripEnd.setText(String.valueOf("Strip End: " + newRunway.getStripEnd() + " m"));
        newClearway.setText(String.valueOf("Clearway: " + newRunway.getClearway() + " m"));
        newStopway.setText(String.valueOf("Stopway: " + newRunway.getStopway() + " m"));
        newResa.setText(String.valueOf("RESA: " + newRunway.getResa() + " m"));
        oldLDA.setText("LDA: " + oldRunway.getLda() + " m");
        oldTora.setText("TORA: " + oldRunway.getTora() + " m");
        oldToda.setText("TODA: " + oldRunway.getToda() + " m");
        oldAsda.setText("ASDA: " + oldRunway.getAsda() + " m");
        oldDisplacedThreshold.setText("Displaced Threshold: " + oldRunway.getDisplacedThreshold() + " m");
        oldStripEnd.setText("Strip End: " + oldRunway.getStripEnd() + " m");
        oldClearway.setText("Clearway: " + oldRunway.getClearway() + " m");
        oldStopway.setText("Stopway: " + oldRunway.getStopway() + " m");
        oldResa.setText("RESA: " + oldRunway.getResa() + " m");
    }

    //Method shows graphically the runway and obstacle according to its respective type of recalculation
    public void drawRunway(){
        var gc = canvas.getGraphicsContext2D();
        if(getCalculationOcurred().equals("lto")){
            LTO(gc);
            drawObstacleLTO(gc);
        }
        if(getCalculationOcurred().equals("loo")){
            LOO(gc);
            drawObstacleLOO(gc);
            gc.fillText("Landing Direction",40,30);
            drawArrow(gc,150,50,20,50);
        }
        if(getCalculationOcurred().equals("toto")){
            TOTO(gc);
            gc.fillRect(225,150,50,35);
        }
        if(getCalculationOcurred().equals("toa")){
            TOA(gc);
            gc.fillRect(240,200,60,35);
        }
    }

    //Method draws graphically the obstacle when a landing towards obstacle recalculation is performed.
    public void drawObstacleLTO(GraphicsContext gc){
        gc.fillRect(255,160,20,25);
    }

    //Method draws graphically the obstacle when a landing over obstacle recalculation is performed.
    public void drawObstacleLOO(GraphicsContext gc){
        gc.fillRect(245,200,50,35);
    }

    public void LOO(GraphicsContext gc){
        canvas.setWidth(340);
        lineHorizontal(20,270,120,gc);
        gc.fillText(newRunway.getLda()+"m LDA",30,290);
        lineHorizontal(140,270,30,gc);
        gc.fillText("60m",140,290);
        lineHorizontal(170,270,70,gc);
        gc.fillText(newRunway.getResa()+"m RESA",180,290);
        lineHorizontal(170,300,130,gc);
        gc.fillText((obstacle.getHeight()*50)+"m",180,320);
        lineVertical(305,200,35,gc);
        gc.fillText(obstacle.getHeight()+"m", 315,215);
        gc.moveTo(170,240);
        gc.lineTo(290,170);
        gc.lineTo(140,240);
        gc.stroke();
    }

    private void LTO(GraphicsContext gc){
        lineHorizontal(20,270,150,gc);
        gc.fillText(newRunway.getLda()+"m LDA",30,290);
        lineHorizontal(170,270,30,gc);
        gc.fillText("60m",170,290);
        lineHorizontal(200,270,70,gc);
        gc.fillText(newRunway.getResa()+"m RESA",210,290);
        gc.fillText("Landing Direction", 30, 30);
        drawArrow(gc,20,50,150,50);
    }

    private void TOTO(GraphicsContext gc) {
        canvas.setWidth(340);
        lineHorizontal(20,270,120,gc);
        gc.fillText(newRunway.getTora()+"m TORA",30,290);
        lineHorizontal(140,270,30,gc);
        gc.fillText("60m",140,290);
        lineHorizontal(170,270,70,gc);
        gc.fillText(newRunway.getResa()+"m RESA",180,290);
        lineHorizontal(170,300,130,gc);
        gc.fillText((obstacle.getHeight()*50)+"m",180,320);
        lineVertical(300,200,35,gc);
        gc.fillText(obstacle.getHeight()+"m", 310,210);
        gc.moveTo(170,240);
        gc.lineTo(290,170);
        gc.lineTo(140,240);
        gc.stroke();
        gc.fillText("Take-off Direction", 30, 30);
        drawArrow(gc,20,50,150,50);
    }

    private void TOA(GraphicsContext gc){
        lineHorizontal(20,270,180,gc);
        gc.fillText(newRunway.getTora()+"m TORA",30,290);
        lineHorizontal(200,270,50,gc);
        gc.fillText(toa.getBlastProtectionAllowance()+"m BPA",200,290);
        gc.fillRect(260,210,30,30);
        gc.fillText("Take-off Direction",40,30);
        drawArrow(gc,150,50,20,50);
    }

    private final int ARR_SIZE = 8;

    //Method draws an arrow indicating the landing direction
    public void drawArrow(GraphicsContext gc, int x1, int y1, int x2, int y2) {
        gc.setFill(Color.BLACK);

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);

        Transform transform = Transform.translate(x1, y1);
        transform = transform.createConcatenation(Transform.rotate(Math.toDegrees(angle), 0, 0));
        gc.setTransform(new Affine(transform));

        gc.strokeLine(0, 0, len, 0);
        gc.fillPolygon(new double[]{len, len - ARR_SIZE, len - ARR_SIZE, len}, new double[]{0, -ARR_SIZE, ARR_SIZE, 0},
                4);
    }

    //Method draws on the canvas the length of the runway parameters
    public void lineHorizontal (int x, int y, int length, GraphicsContext gc){
        gc.beginPath();
        gc.moveTo(x,y-5);
        gc.lineTo(x,y+5);
        gc.moveTo(x,y);
        gc.lineTo(x+length,y);
        gc.moveTo(x+length,y-5);
        gc.lineTo(x+length,y+5);
        gc.stroke();
    }
    public void lineVertical (int x, int y, int length, GraphicsContext gc){
        gc.beginPath();
        gc.moveTo(x-5,y);
        gc.lineTo(x+5,y);
        gc.moveTo(x,y);
        gc.lineTo(x,y+length);
        gc.moveTo(x-5,y+length);
        gc.lineTo(x+5,y+length);
        gc.stroke();
    }

    //Method displays the calculation that has taken place
    public void displayCalculation(){
        notifications.getItems().add(new String("Calculations breakdown shown."));
        if(getCalculationOcurred().equals("loo")){
            displayLoo();
        }
        if(getCalculationOcurred().equals("lto")){
            displayLto();
        }
        if(getCalculationOcurred().equals("toa")){
            displayToa();
        }
        if(getCalculationOcurred().equals("toto")){
            displayToto();
        }
    }

    //method displays a break down of the Landing Over Obstacle Calculation
    public void displayLoo(){
        Label calculations = new Label();
        calculations.setMinSize(400,400);
        calculations.setDisable(false);
        calculations.setStyle("-fx-font-weight: bold");
        calculations.setText("The Landing Distance Available (LDA) has been updated in this calculation \n the original LDA value was: \n" + oldRunway.getLda() + " m" + "\n The new LDA value is: \n" + newRunway.getLda() + " m" + "\n The calculation that occured was: \n" + oldRunway.getLda() + " m" + " - " + obstacle.getDistanceFromThreshold() + " m" + " - "  + loo.getFinalValueToBeUsed() + " m" + " - " + oldRunway.getStripEnd() + " m" + "\n= \n" + newRunway.getLda() + " m");
        VBox calcBox = new VBox(calculations);
        calcBox.setAlignment(Pos.CENTER);

        Scene inputScene = new Scene(calcBox, 450, 300);
        Stage newWindow = new Stage();
        newWindow.setTitle("Calculations Breakdown");
        newWindow.setScene(inputScene);
        newWindow.setResizable(false);

        newWindow.show();
    }

    //Method displays a break down of the Landing Towards Obstacle calculation
    public void displayLto(){
        Label calculations = new Label();
        calculations.setMinSize(400,400);
        calculations.setDisable(false);
        calculations.setStyle("-fx-font-weight: bold");
        calculations.setText("The Landing Distance Available (LDA) has been updated in this calculation \n the original LDA value was: \n" + oldRunway.getLda() + " m" + "\n The new LDA value is: \n" + newRunway.getLda() + " m" + "\n The calculation that occured was: \n" + obstacle.getDistanceFromThreshold() + " m" + " - " + oldRunway.getResa() + " m" + " - " + oldRunway.getStripEnd() + " m" + "\n= \n" + newRunway.getLda() + " m");
        VBox calcBox = new VBox(calculations);
        calcBox.setAlignment(Pos.CENTER);

        Scene inputScene = new Scene(calcBox, 450, 300);
        Stage newWindow = new Stage();
        newWindow.setTitle("Calculations Breakdown");
        newWindow.setScene(inputScene);
        newWindow.setResizable(false);

        newWindow.show();
    }

    //Method displays a breakdown of the Take Off Towards obstacle calculation
    public void displayToto(){
        Label calculations = new Label();
        calculations.setMinSize(400,400);
        calculations.setDisable(false);
        calculations.setStyle("-fx-font-weight: bold");
        calculations.setText("The Take-Off Run Available (TORA), Take-Off Distance Available (TODA) and Accelerate-Stop Distance Available (ASDA) \n were updated in this calculation \n the original TORA value was: \n" + oldRunway.getTora() + " m" + "\n The original TODA value was: \n" + oldRunway.getToda() + " m" +"\n The original ASDA value was: \n" + oldRunway.getAsda() + " m" + "\n The new TORA value is: \n"+ newRunway.getTora() + " m" + "\n The new TODA value is: \n" + newRunway.getToda() + " m" + "\n The new ASDA value is: \n" + newRunway.getAsda() + " m" + "\n The calculation that occured was: \n" + obstacle.getDistanceFromThreshold() + " m" + " + " + oldRunway.getDisplacedThreshold() + " m" + " - " + toto.getValueToBeTaken() + " m" + " - " + oldRunway.getStripEnd() + " m" + "\n TORA  = \n" + newRunway.getTora() + " m" + "\n TODA = \n" + newRunway.getToda() + " m" + "\n ASDA = \n" + newRunway.getAsda() + " m");
        VBox calcBox = new VBox(calculations);
        calcBox.setAlignment(Pos.CENTER);

        Scene inputScene = new Scene(calcBox, 700, 450);
        Stage newWindow = new Stage();
        newWindow.setTitle("Calculations Breakdown");
        newWindow.setScene(inputScene);
        newWindow.setResizable(false);

        newWindow.show();
    }

    //Method displays a break fown of the Take Off Away From Obstacle Calculation
    public void displayToa(){
        Label calculations = new Label();
        calculations.setMinSize(400,400);
        calculations.setDisable(false);
        calculations.setStyle("-fx-font-weight: bold");
        calculations.setText("The Take-Off Run Available (TORA), Take-Off Distance Available (TODA) and Accelerate-Stop Distance Available (ASDA) \n were updated in this calculation. \n The original TORA value was: \n" + oldRunway.getTora() + " m" + ".\n The original TODA value was: \n" + oldRunway.getToda() + " m" + ".\n The original ASDA value was: \n" + oldRunway.getAsda() + " m" + ".\n The new TORA value is: \n"+ newRunway.getTora() + " m" + ".\n The new TODA value is: \n" + newRunway.getToda() + " m" + ".\n The new ASDA value is: \n" + newRunway.getAsda() + " m" + "\n The calculation that occured was: \n" + "TORA = " + oldRunway.getTora() + " m" + " - " + obstacle.getDistanceFromThreshold() + " m" + " - " + toa.getBlastProtectionAllowance() + " m" + " + " + oldRunway.getStopway() + "m" + " + " + oldRunway.getClearway() + "m" + " = " + newRunway.getTora() + " m" + "\n TODA= " + oldRunway.getToda() + " m" + " - " + obstacle.getDistanceFromThreshold() + " m" + " - " + toa.getBlastProtectionAllowance() + " + "+ oldRunway.getStopway() + "m" + " + " + oldRunway.getClearway() + " m" + " = " + newRunway.getToda() +  " m" + "\n TODA= " + oldRunway.getAsda() + " m" + " - " + obstacle.getDistanceFromThreshold() + " m" +  " - " + toa.getBlastProtectionAllowance() + " + "+ oldRunway.getStopway() + "m" + " + " + oldRunway.getClearway() +  "m" + " = " + newRunway.getAsda() + " m");
        VBox calcBox = new VBox(calculations);
        calcBox.setAlignment(Pos.CENTER);

        Scene inputScene = new Scene(calcBox, 700, 450);
        Stage newWindow = new Stage();
        newWindow.setTitle("Calculations Breakdown");
        newWindow.setScene(inputScene);
        newWindow.setResizable(false);

        newWindow.show();
    }

    //Method sends us back to the homepage screen
    public void goBack(ActionEvent event) throws IOException {
        newRunway = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("enter-runway.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        RunwayController controller = loader.getController();
        controller.initRunway(oldRunway,runwaysList ,list, notifications,obstacleList,obsList);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }

    //Method opens another screen showing the instructions on how to view the results
    public void information(ActionEvent event) throws IOException{
        Label info = new Label();
        info.setText("Instructions:\n" +
                "1. Click the 'Show Results' button to view graphically the runway and obstacles and also the difference between the values of the old and new runway parameters.\n" +
                "2. Click on the 'View Calculation Breakdown' button to view details of the calculation.\n" +
                "3. Click on the 'Return to homepage' button to go back to the main page.");
        Scene scene = new Scene(info,900,100);
        Stage stage = new Stage();
        stage.setTitle("Instructions on how to view the results");
        stage.setScene(scene);
        stage.show();
    }
}

