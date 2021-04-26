package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Transform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Math.round;

public class ResultsController implements Initializable{

    @FXML private TextField filename;
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
    @FXML private AnchorPane pane;
    @FXML private Rectangle sideRunway;
    @FXML private Rectangle sideObstacle;
    @FXML private Line directionLine;
    @FXML private Polygon towards;
    @FXML private Polygon away;
    @FXML private Polygon sideArrowRight;
    @FXML private Polygon sideArrowLeft;
    @FXML private Rectangle dashLine;
    @FXML private Label directionLabel;
    @FXML private Rectangle Rstopway;
    @FXML private Rectangle Rclearway;
    @FXML private Rectangle LStopWay;
    @FXML private Rectangle LClearway;
    @FXML private Rectangle sideRStopway;
    @FXML private Rectangle sideRClearway;
    @FXML private Rectangle sideLStopway;
    @FXML private Rectangle sideLClearway;
    @FXML private ImageView sideRightObstacle;
    @FXML private ImageView sideLeftObstacle;
    @FXML private ImageView sideRightPlane;
    @FXML private ImageView sideLeftPlane;
    @FXML private ImageView toooPlane;
    @FXML private ImageView looPlane;
    @FXML private Label leftRunwayNumber;
    @FXML private Label leftRunwayDirection;
    @FXML private Label rightRunwayNumber;
    @FXML private Label rightRunwayDirection;
    @FXML private ImageView topDownPlane;
    @FXML private ImageView topDownObstacle;
    @FXML private RadioButton xmlSave;
    @FXML private RadioButton txtSave;
    @FXML private Line leftTOA;
    @FXML private Line leftToaStart;
    @FXML private Line leftToaEnd;
    @FXML private Label leftBPA;
    @FXML private Line leftToaToda;
    @FXML private Label leftToaTodaLabel;
    @FXML private Line leftToaTodaEnd;
    @FXML private Line leftToaAsda;
    @FXML private Line leftToaAsdaEnd;
    @FXML private Label leftToaAsfaLabel;
    @FXML private Line leftToaTora;
    @FXML private Line leftToaToraEnd;
    @FXML private Label leftToaToraLabel;
    @FXML private Rectangle displacedThreshold;
    @FXML private Line rightToaBpa;
    @FXML private Line rightToaStart;
    @FXML private Line rightToaEnd;
    @FXML private Label rightBPA;
    @FXML private Line rightToaToda;
    @FXML private Label rightToaTodaLabel;
    @FXML private Line rightToaTodaEnd;
    @FXML private Line rightToaAsda;
    @FXML private Line rightToaAsdaEnd;
    @FXML private Label rightToaAsfaLabel;
    @FXML private Line rightToaTora;
    @FXML private Line rightToaToraEnd;
    @FXML private Label rightToaToraLabel;
    @FXML private Rectangle rightdisplacedThreshold;
    @FXML private Rectangle threshold;
    @FXML private Line leftLtoLda;
    @FXML private Line leftLtoResa;
    @FXML private Line leftLtoStripEnd;
    @FXML private Line leftLtoResaStart;
    @FXML private Line leftLtoResaEnd;
    @FXML private Label leftLtoResaLabel;
    @FXML private Line leftLtoStripEndEnd;
    @FXML private Label leftLtoStripEndLabel;
    @FXML private Line leftLtoLdaEnd;
    @FXML private Label leftLtoLdaLabel;
    @FXML private Line rightLtoLda;
    @FXML private Line rightLtoResa;
    @FXML private Line rightLtoStripEnd;
    @FXML private Line rightLtoResaEnd;
    @FXML private Line rightLtoResaStart;
    @FXML private Label rightLtoResaLabel;
    @FXML private Line rightLtoStripEndEnd;
    @FXML private Label rightLtoStripEndLabel;
    @FXML private Line rightLtoLdaEnd;
    @FXML private Label rightLtoLdaLabel;
    @FXML private Line leftTotoAlsEnd;
    @FXML private Line leftTotoAls;
    @FXML private Label leftTotoAlsLabel;
    @FXML private Line rightTotoAlsEnd;
    @FXML private Line rightTotoAls;
    @FXML private Label rightTotoAlsLabel;
    @FXML private Line leftLooBpaStart;
    @FXML private Line leftLooBpa;
    @FXML private Line leftLooBpaEnd;
    @FXML private Label leftLooBpaLabel;
    @FXML private Line rightLooBpastart;
    @FXML private Line rightLooPpa;
    @FXML private Line rightLooBpaEnd;
    @FXML private Line dashedLine;
    @FXML private Label rightLooBpaLabel;


    private Runway oldRunway;
    private Runway newRunway;
    private LandingOverObstacle loo;
    private LandingTowardsObstacle lto;
    private TakeOffAwayFromObstacle toa;
    private TakeOffTowardsObstacle toto;
    private Obstacle obstacle;
    private String calculationOcurred;
    //private TypeOfCalcController landingTowards;
    private ListView list;
    private ListView obstacleList;
    private int slope;
    private int bpa;
    private String runwayLeftOrRight;
    private FileChooser fileChooser = new FileChooser();
    private ToggleGroup group = new ToggleGroup();
    private boolean isInitialized = false;
    ArrayList<RunwayPair> runwayPairs = new ArrayList<>();
    ArrayList<Runway> runwaysList = new ArrayList<Runway>();
    ArrayList<Obstacle> obsList = new ArrayList<Obstacle>();
    ArrayList<String> runwayNames = new ArrayList<String>();
    ArrayList<String> obstacleNames = new ArrayList<String>();

    public void initializeToggleGroup() {
        if(!isInitialized) {
            xmlSave.setToggleGroup(group);
            txtSave.setToggleGroup(group);
            xmlSave.setSelected(true);
            isInitialized = true;
        }
    }

    //Method initialises runway and obstacle arguments given that a calculation has taken place
    public void initRunway(Runway oldRunway, Runway newRunway, Obstacle obstacle, ListView list,ListView list1, ArrayList<Runway> runwaysList1,ListView obstacleList1,ArrayList<Obstacle> obsList1, ArrayList<String> runwayNames, ArrayList<String> obstacleNames){
        this.oldRunway = oldRunway;
        this.newRunway = newRunway;
        this.obstacle = obstacle;
        notifications.getItems().addAll(list.getItems());
        this.list = list1;
        this.runwaysList = runwaysList1;
        this.obstacleList = obstacleList1;
        this.obsList = obsList1;
        this.runwayNames = runwayNames;
        this.obstacleNames = obstacleNames;
    }

    //Method initialises runway, obstacle and calculation arguments given that a landing away From Obstacle calculation
    //has taken place
    public void initRunway(Runway oldRunway, Runway newRunway, LandingOverObstacle loo, Obstacle obstacle, ListView list,ListView list1, ArrayList<Runway> runwaysList1, ListView obstacleList1,ArrayList<Obstacle> obsList1, ArrayList<RunwayPair> runwayPairs, ArrayList<String> runwayNames, ArrayList<String> obstacleNames){
        this.oldRunway = oldRunway;
        this.newRunway = newRunway;
        this.obstacle = obstacle;
        this.loo = loo;
        slope = loo.getAls();
        bpa = loo.getBlastProtectionAllowance();
        calculationOcurred = "loo";
        notifications.getItems().addAll(list.getItems());
        this.list = list1;
        this.runwaysList = runwaysList1;
        this.obstacleList = obstacleList1;
        this.obsList = obsList1;
        this.runwayPairs = runwayPairs;
        this.runwayNames = runwayNames;
        this.obstacleNames = obstacleNames;
        getOppositeRunway(oldRunway);
        showResults();
    }

    //Method initialises runway, obstacle and calculation arguments given that a Landing towards obstacle calculation
    //has taken place
    public void initRunway(Runway oldRunway, Runway newRunway, LandingTowardsObstacle lto, Obstacle obstacle, ListView list,ListView list1, ArrayList<Runway> runwaysList1, ListView obstacleList1,ArrayList<Obstacle> obsList1, ArrayList<RunwayPair> runwayPairs, ArrayList<String> runwayNames, ArrayList<String> obstacleNames){
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
        this.runwayPairs = runwayPairs;
        this.runwayNames = runwayNames;
        this.obstacleNames = obstacleNames;
        getOppositeRunway(oldRunway);
        showResults();
    }

    //Method initialises runway, obstacle and calculation arguments given that a Take off Away from obstacle calculation
    //has taken place
    public void initRunway(Runway oldRunway, Runway newRunway, TakeOffAwayFromObstacle toa, Obstacle obstacle, ListView list,ListView list1, ArrayList<Runway> runwaysList1, ListView obstacleList1,ArrayList<Obstacle> obsList1, ArrayList<RunwayPair> runwayPairs, ArrayList<String> runwayNames, ArrayList<String> obstacleNames){
        this.oldRunway = oldRunway;
        this.newRunway = newRunway;
        this.obstacle = obstacle;
        this.toa = toa;
        bpa = toa.getBlastProtectionAllowance();
        calculationOcurred = "toa";
        notifications.getItems().addAll(list.getItems());
        this.list = list1;
        this.runwaysList = runwaysList1;
        this.obstacleList = obstacleList1;
        this.obsList = obsList1;
        this.runwayPairs = runwayPairs;
        this.runwayNames = runwayNames;
        this.obstacleNames = obstacleNames;
        getOppositeRunway(oldRunway);
        showResults();
    }

    //Method initialises runway, obstacle and calculation arguments given that a Take off towards obstacle calculation
    //has taken place
    public void initRunway(Runway oldRunway, Runway newRunway, TakeOffTowardsObstacle toto, Obstacle obstacle, ListView list,ListView list1, ArrayList<Runway> runwaysList1,ListView obstacleList1,ArrayList<Obstacle> obsList1, ArrayList<RunwayPair> runwayPairs, ArrayList<String> runwayNames, ArrayList<String> obstacleNames){
        this.oldRunway = oldRunway;
        this.newRunway = newRunway;
        this.obstacle = obstacle;
        this.toto = toto;
        slope = toto.getTocs();
        calculationOcurred = "toto";
        notifications.getItems().addAll(list.getItems());
        this.list = list1;
        this.runwaysList = runwaysList1;
        this.obstacleList = obstacleList1;
        this.obsList = obsList1;
        this.runwayPairs = runwayPairs;
        this.runwayNames = runwayNames;
        this.obstacleNames = obstacleNames;
        getOppositeRunway(oldRunway);
        showResults();
    }

    //Method finds the corresponding pair given a runway and adds it to the list of pairs of runways
    public void getOppositeRunway(Runway runway){
        String target = "";
        RunwayPair rp = null;
        if(checkForRunwayCouple(runway)){
           target = checkForPair(runway);
            for(Runway r: runwaysList){
                if(target.equals(r.getRunwayNumber())){
                    rp = new RunwayPair(runway,r);
                    runwayPairs.add(rp);
                }
            }
        }
    }

    //Method checks if the last character of a runway number is a letter
    //Method, therefore, determines if runway is part of a pair
    public boolean checkForRunwayCouple(Runway runway){
        char runwayChar = runway.getRunwayNumber().charAt(runway.getRunwayNumber().length()-1);
        return Character.isLetter(runwayChar);
    }

    //Method determines whether the potential pair of a runway is R, L or C
    public String checkForPair(Runway runway){
        //line used for testing purposes only
        if(runway.getRunwayNumber().charAt(0) == '-')
            return "-1";
      char runwayChar = runway.getRunwayNumber().charAt(runway.getRunwayNumber().length()-1);
      if(Character.toUpperCase(runwayChar) == 'R'){
          return potentialOppositeRunwayR(Integer.parseInt(runway.getRunwayNumber().substring(0,runway.getRunwayNumber().length()-1)));
      }
      else if(Character.toUpperCase(runwayChar) == 'L'){
          return potentialOppositeRunwayL(Integer.parseInt(runway.getRunwayNumber().substring(0,runway.getRunwayNumber().length()-1)));
      }
      else return potentialOppositeRunwayC(Integer.parseInt(runway.getRunwayNumber().substring(0,runway.getRunwayNumber().length()-1)));
    }

    //Method calculates the opposite of a runway with R
    public String potentialOppositeRunwayR(int i){
        if(i>36 || i<1)
            return "-1";
        int pair = (i+18)%36;
        if(pair == 0)
            return "36L";
        if(pair < 10) {
            return "0" + pair + "L";
        }
        else{
            return pair + "L";
        }
    }

    //Method calculates the opposite of a runway with L
    public String potentialOppositeRunwayL(int i){
        if(i>36 || i<1)
            return "-1";
        int pair = (i+18)%36;
        if(pair == 0)
            return "36R";
        if(pair < 10) {
            return "0" + pair + "R";
        }
        else{
            return pair + "R";
        }
    }

    //Method calculates the opposite of a runway with C
    public String potentialOppositeRunwayC(int i){
        if(i>36 || i<1)
            return "-1";
        int pair = (i+18)%36;
        if(pair == 0)
            return "36C";
        if(pair < 10) {
            return "0" + pair + "C";
        }
        else{
            return pair + "C";
        }
    }

    //Method calculates opposite runway number to what is inputted
    public String getOppositeRunwayNumber(int i){
        if(i>36 || i<1)
            return "-1";
        int pair = (i+18)%36;
        if(pair == 0)
            return "36";
        if (pair < 10)
            return "0" + pair;
        else return String.valueOf(pair);

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        notifications.getItems().add(0,new String(dtf.format(now) + ": Calculations done."));
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
        displayTopDownRunwayMarkings();
        displayTopDownInfo();
        displaySideView();
        showResults.setDisable(true);
    }

    public void displaySideView(){
        double leftTORA = 93;
        double leftASDA = 68;
        double leftTODA = 38;
        double leftObstacle = 170;
        double rightObstacle = 560;
        double rightTORA = 650;
        double rightASDA = 680;
        double rightTODA = 690;
        var gc = canvas.getGraphicsContext2D();
        gc.beginPath();
        gc.setLineWidth(3);
        if(newRunway.getDisplacedThreshold()==0)threshold.setVisible(false);

        ///////////////////////////////////////////// LTO

        if (calculationOcurred.equals("lto")){
            if (runwayLeftOrRight.equals("left")){
                sideLeftPlane.setVisible(true);
                sideRightObstacle.setVisible(true);
                if(newRunway.getClearway()>0)sideLClearway.setVisible(true);
                if(newRunway.getStopway()>0)sideLStopway.setVisible(true);
                double max = newRunway.getLda()+ newRunway.getStripEnd() + newRunway.getResa() ;
                double i = 450/max;
                threshold.setLayoutX(leftTORA+newRunway.getDisplacedThreshold()*i);
                //lineHorizontal uses canvas (x,y) parameters to draw a line with text under it
                lineHorizontal(leftTORA + newRunway.getDisplacedThreshold()*i,270,newRunway.getLda()*i-newRunway.getDisplacedThreshold()*i, "LDA: " + newRunway.getLda() + "m", gc);
                lineHorizontal(newRunway.getLda()*i + leftTORA ,270, newRunway.getStripEnd()*i, gc);
                gc.fillText("StripEnd: " + newRunway.getStripEnd()+"m",newRunway.getLda()*i + leftTORA ,290);
                lineHorizontal(newRunway.getLda()*i+ leftTORA + newRunway.getStripEnd()*i,270, newRunway.getResa()*i, gc);
                gc.fillText("RESA: "+ newRunway.getResa() + "m",newRunway.getLda()*i+ leftTORA + newRunway.getStripEnd()*i,305 );
                gc.fillText("Landing Direction", 30, 30);
                drawArrow(gc,20,50,150,50);
                sideArrowRight.setVisible(true);

            }else if(runwayLeftOrRight.equals("right")){
                double max = newRunway.getLda()+ newRunway.getStripEnd() + newRunway.getResa();
                double i = 470/max;
                sideRightPlane.setVisible(true);
                sideLeftObstacle.setVisible(true);
                sideRightPlane.setLayoutX(sideRightPlane.getLayoutX()-newRunway.getDisplacedThreshold()*i);
                if(newRunway.getClearway()>0)sideRClearway.setVisible(true);
                if(newRunway.getStopway()>0)sideRStopway.setVisible(true);
                lineHorizontal(leftObstacle,270,newRunway.getResa()*i, gc);
                gc.fillText("RESA: "+ newRunway.getResa() + "m",90,290);
                lineHorizontal(leftObstacle + newRunway.getResa()*i,270, newRunway.getStripEnd()*i,  gc);
                gc.fillText("StripEnd: " + newRunway.getStripEnd()+"m",150,305);
                lineHorizontal(leftObstacle + newRunway.getResa()*i + newRunway.getStripEnd()*i ,270,newRunway.getLda()*i-newRunway.getDisplacedThreshold()*i,  gc);
                gc.fillText("LDA: " + newRunway.getLda() + "m",leftObstacle + newRunway.getResa()*i + newRunway.getStripEnd()*i*2, 290);
                gc.fillText("Landing Direction",60,30);
                drawArrow(gc,150,50,20,50);
                sideArrowLeft.setVisible(true);
                threshold.setLayoutX(rightTORA-newRunway.getDisplacedThreshold()*i-5);
            }

            ///////////////////////////////////////////// TOA

        } else if(calculationOcurred.equals("toa")){
            if(runwayLeftOrRight.equals("right")){
                sideRightObstacle.setVisible(true);
                sideRightPlane.setLayoutX(220);
                sideRightPlane.setVisible(true);
                if(newRunway.getClearway()>0)sideRClearway.setVisible(true);
                if(newRunway.getStopway()>0)sideRStopway.setVisible(true);
                double max = newRunway.getToda() + toa.getBlastProtectionAllowance()-newRunway.getDisplacedThreshold();
                double i = 520/max;
                lineHorizontal(leftASDA, 180, newRunway.getAsda()*i, "ASDA: " + newRunway.getAsda() + "m", gc);
                lineHorizontal(leftTORA, 150,newRunway.getTora()*i-18,"TORA: " + newRunway.getTora() + "m", gc);
                lineHorizontal(leftTODA + newRunway.getToda()*i,270,toa.getBlastProtectionAllowance()*i,"Blast Protection Allowance: " + toa.getBlastProtectionAllowance() +"m", gc);
                lineHorizontal(leftTODA-1 , 270,  newRunway.getToda()*i, "TODA: "+ newRunway.getToda()+"m",gc);
                gc.fillText("Take-off Direction",60,30);
                drawArrow(gc,150,50,20,50);
                sideArrowLeft.setVisible(true);
                threshold.setLayoutX(rightTORA-newRunway.getDisplacedThreshold()*i-5);

            } else if(runwayLeftOrRight.equals("left")){
                sideLeftObstacle.setVisible(true);
                sideLeftPlane.setLayoutX(330);
                if(newRunway.getClearway()>0)sideLClearway.setVisible(true);
                if(newRunway.getStopway()>0)sideLStopway.setVisible(true);
                sideLStopway.setVisible(true);
                double max = newRunway.getToda() + toa.getBlastProtectionAllowance();
                double i = 530/max;
                lineHorizontal(leftObstacle + toa.getBlastProtectionAllowance()*i, 150, newRunway.getTora()*i-10, "TORA: " + newRunway.getTora() + "m",gc);
                lineHorizontal(leftObstacle + toa.getBlastProtectionAllowance()*i, 180, newRunway.getAsda()*i, "ASDA: " + newRunway.getAsda() + "m",gc);
                lineHorizontal(leftObstacle,270,toa.getBlastProtectionAllowance()*i, gc);
                gc.fillText("Blast Protection Allowance: " + toa.getBlastProtectionAllowance() +"m",90,305);
                lineHorizontal(leftObstacle + toa.getBlastProtectionAllowance()*i, 270, newRunway.getToda()*i, "TODA: "+ newRunway.getToda()+"m",gc);
                gc.fillText("Take-off Direction", 30, 30);
                drawArrow(gc,20,50,150,50);
                sideArrowRight.setVisible(true);
                threshold.setLayoutX(leftTORA+newRunway.getDisplacedThreshold()*i);
            }}

            ///////////////////////////////////////// TOTO [3]

            else if (calculationOcurred.equals("toto")){
                if (runwayLeftOrRight.equals("left")){
                    sideLeftPlane.setVisible(true);

                    sideRightObstacle.setVisible(true);
                    if(newRunway.getClearway()>0)sideLClearway.setVisible(true);
                    if(newRunway.getStopway()>0)sideLStopway.setVisible(true);
                    double slope = toto.getTocs()*obstacle.getHeight();
                    double max = slope + newRunway.getStripEnd() + newRunway.getToda();
                    double i = sideRunway.getWidth()/max;
                    //height of obstacle
                    lineVertical(660,150,90,gc);
                    gc.fillText(obstacle.getHeight()+"m", 675,215);
                    //drawing the slope
                    gc.moveTo(rightTORA-slope*i,240);
                    gc.lineTo(rightTORA,70+(slope*i)*0.3);
                    gc.lineTo(rightTORA-slope*i-newRunway.getStripEnd()*i,240);
                    gc.stroke();
                    //end of drawing slope
                    //strip end
                    lineHorizontal(leftTORA + newRunway.getTora()*i,270,leftTORA + newRunway.getTora()*i,gc);
                    gc.fillText( newRunway.getStripEnd()+" m", leftTORA+ newRunway.getTora()*i-3,295);
                    //als
                    lineHorizontal(leftTORA + newRunway.getTora()*i + newRunway.getStripEnd()*i,280,slope*i,"ALS: "+ slope +"m",gc);
                    //resa
                    lineHorizontal(leftTORA + newRunway.getTora()*i + newRunway.getStripEnd()*i,270,newRunway.getResa(), gc);
                    gc.fillText("RESA "+ newRunway.getResa()+ "m",rightTORA,270);
                    //tora,toda,asda
                    lineHorizontal(leftTORA+newRunway.getDisplacedThreshold()*i,270,(newRunway.getTora()*i-newRunway.getDisplacedThreshold()*i),"TODA "+ newRunway.getToda()+ "m", gc);
                    lineHorizontal(leftTORA+newRunway.getDisplacedThreshold()*i,180,(newRunway.getTora()*i-newRunway.getDisplacedThreshold()*i),"ASDA "+ newRunway.getAsda()+ "m", gc);
                    lineHorizontal(leftTORA+newRunway.getDisplacedThreshold()*i,150,(newRunway.getTora()*i-newRunway.getDisplacedThreshold()*i),"TORA "+ newRunway.getTora()+ "m", gc);
                    gc.fillText("Take-off Direction",40,30);
                    drawArrow(gc,20,50,150,50);
                    sideArrowRight.setVisible(true);
                    threshold.setLayoutX(leftTORA+newRunway.getDisplacedThreshold()*i);


                } else if (runwayLeftOrRight.equals("right")) {
                    sideRightPlane.setVisible(true);
                    sideLeftObstacle.setVisible(true);
                    if(newRunway.getClearway()>0)sideRClearway.setVisible(true);
                    if(newRunway.getStopway()>0)sideRStopway.setVisible(true);
                    double slope = toto.getTocs()*obstacle.getHeight();
                    double max = slope + newRunway.getStripEnd() + newRunway.getToda();
                    double i = sideRunway.getWidth()/max;
                    //height of obstacle
                    lineVertical(80,150,90,gc);
                    gc.fillText(obstacle.getHeight()+"m",50,215);
                    //drawing the slope
                    gc.moveTo(leftTORA + slope*i, 240 );
                    gc.lineTo(leftTORA,(70+(slope*i)*0.3));
                    gc.lineTo(leftTORA + slope*i + newRunway.getStripEnd()*i,240);
                    gc.stroke();
                    //end of drawing slope
                    lineHorizontal(leftTORA+slope*i,270,-(newRunway.getResa()*i), gc);
                    gc.fillText("RESA: "+ newRunway.getResa()+ "m",slope*i - (slope*i*0.1),270);
                    lineHorizontal(leftTORA,280,slope*i,"ALS: "+ slope +"m",gc);
                    lineHorizontal(leftTORA+ slope*i,270, newRunway.getStripEnd()*i, gc);
                    gc.fillText(newRunway.getStripEnd()+" m", leftTORA + slope*i+3,290);
                    lineHorizontal(leftTORA +slope*i + newRunway.getStripEnd()*i,270,newRunway.getToda()*i-newRunway.getDisplacedThreshold()*i,"TODA: "+ newRunway.getToda()+ "m", gc);
                    lineHorizontal(leftTORA +slope*i + newRunway.getStripEnd()*i,180,newRunway.getToda()*i-newRunway.getDisplacedThreshold()*i,"ASDA: "+ newRunway.getToda()+ "m", gc);
                    lineHorizontal(leftTORA +slope*i + newRunway.getStripEnd()*i,150,newRunway.getToda()*i-newRunway.getDisplacedThreshold()*i,"TORA: "+ newRunway.getToda()+ "m", gc);
                    gc.fillText("Take-off Direction",60,30);
                    drawArrow(gc,150,50,20,50);
                    sideArrowLeft.setVisible(true);
                    threshold.setLayoutX(rightTORA-newRunway.getDisplacedThreshold()*i-5);
                }

                ////////////////////////////////////// LOO [4]

                }  else if (calculationOcurred.equals("loo")){
                if (runwayLeftOrRight.equals("right")){
                    double slope = round(loo.getAls()*obstacle.getHeight());
                    double max = slope + newRunway.getStripEnd() + newRunway.getLda();
                    double i = sideRunway.getWidth()/max;
//                    dashedLine.setLayoutX(leftTORA+newRunway.getStripEnd()*i+newRunway.getLda()*i);
//                    dashedLine.setVisible(true);
                    sideRightObstacle.setVisible(true);
                    sideRightPlane.setLayoutX(leftTORA+newRunway.getLda()*i-200);
                    sideRightPlane.setVisible(true);
                    if(newRunway.getClearway()>0)sideRClearway.setVisible(true);
                    if(newRunway.getStopway()>0)sideRStopway.setVisible(true);
//                    dashLine.setLayoutX(leftTORA+newRunway.getLda()*i+newRunway.getStripEnd()*i+loo.getBlastProtectionAllowance()*i-2);
//                    dashLine.setVisible(true);
                    //height of obstacle
                    lineVertical(660,150,90,gc);
                    gc.fillText(obstacle.getHeight()+"m", 675,215);
                    //drawing the slope
                    gc.moveTo(rightTORA-slope*i,240);
                    gc.lineTo(rightTORA,70+(slope*i)*0.3);
                    gc.lineTo(rightTORA-slope*i-newRunway.getStripEnd()*i,240);
                    gc.stroke();
                    //end of drawing slope
                    lineHorizontal(leftTORA+newRunway.getLda()*i+newRunway.getStripEnd()*i,130,-loo.getBlastProtectionAllowance()*i,"BPA: " + loo.getBlastProtectionAllowance() + "m",gc);
                    lineHorizontal(leftTORA+newRunway.getLda()*i,270,newRunway.getStripEnd()*i,gc);
                    gc.fillText("StripEnd: " +newRunway.getStripEnd()+"m",  leftTORA+newRunway.getLda()*i-100,300);
                    lineHorizontal(leftTORA+newRunway.getLda()*i+newRunway.getStripEnd()*i,270,newRunway.getResa()*i, gc);
                    gc.fillText("RESA: "+ newRunway.getResa()+ "m", rightTORA-slope*i+50,270);
                    lineHorizontal(leftTORA+newRunway.getLda()*i+newRunway.getStripEnd()*i,280,slope*i-newRunway.getDisplacedThreshold()*i,"ALS: "+slope+"m",gc);
                    lineHorizontal(leftTORA,270,newRunway.getLda()*i,"LDA: "+ newRunway.getLda()+ "m", gc);
                    gc.fillText("Landing direction",60,30);
                    drawArrow(gc,150,50,20,50);
                    sideArrowLeft.setVisible(true);
                    threshold.setLayoutX(rightTORA-newRunway.getDisplacedThreshold()*i-5);
                }

                else if (runwayLeftOrRight.equals("left")) {
                    double slope = round(loo.getAls()*obstacle.getHeight());
                    double max = slope + newRunway.getStripEnd() + newRunway.getLda()-newRunway.getDisplacedThreshold();
                    double i = sideRunway.getWidth()/max;
                    sideLeftObstacle.setVisible(true);
                    sideLeftPlane.setLayoutX(leftTORA+newRunway.getStripEnd()*i+slope*i+50);
                    sideLeftPlane.setVisible(true);
                    if(newRunway.getClearway()>0)sideLClearway.setVisible(true);
                    if(newRunway.getStopway()>0)sideLStopway.setVisible(true);
//                    dashLine.setLayoutX(leftTORA+slope*i+loo.getBlastProtectionAllowance()*i-3);
//                    dashLine.setVisible(true);

//                    dashedLine.setStartX(leftTORA+slope*i);
//                    dashedLine.setEndX(leftTORA+slope*i);
//                    dashedLine.setVisible(true);
                    //height of obstacle
                    lineVertical(80,150,90,gc);
                    gc.fillText(obstacle.getHeight()+"m",50,215);
                    //drawing the slope
                    gc.moveTo(leftTORA + slope*i, 240 );
                    gc.lineTo(leftTORA,(70+(slope*i)*0.3));
                    gc.lineTo(leftTORA + slope*i + newRunway.getStripEnd()*i,240);
                    gc.stroke();
                    //end of drawing slope
                    lineHorizontal(leftTORA+slope*i,140,loo.getBlastProtectionAllowance()*i,"BPA: " + loo.getBlastProtectionAllowance() + "m",gc);
                    lineHorizontal(leftTORA+slope*i,270,-(newRunway.getResa()*i), gc);
                    gc.fillText("RESA: "+ newRunway.getResa()+ "m",slope*i - (slope*i*0.1),270);
                    lineHorizontal(leftTORA+newRunway.getDisplacedThreshold()*i,280,slope*i-newRunway.getDisplacedThreshold()*i,"ALS: "+ slope +"m",gc);
                    lineHorizontal(leftTORA+ slope*i,270, newRunway.getStripEnd()*i, gc);
                    gc.fillText("StripEnd: "+newRunway.getStripEnd()+"m",leftTORA+slope*i+2,305);
                    lineHorizontal(leftTORA +slope*i + newRunway.getStripEnd()*i,270,newRunway.getLda()*i-newRunway.getDisplacedThreshold()*i,"LDA "+ newRunway.getLda()+ "m", gc);
                    gc.fillText("Landing Direction", 30, 30);
                    drawArrow(gc,20,50,150,50);
                    sideArrowRight.setVisible(true);
                    threshold.setLayoutX(leftTORA+newRunway.getDisplacedThreshold()*i);

                }
            }
        }

    //Method displays values relating to top down view of new runway
    public void displayTopDownInfo(){
        char runwayChar = newRunway.getRunwayNumber().charAt(newRunway.getRunwayNumber().length()-1);
        if(calculationOcurred.equals("toto")){
            if(runwayLeftOrRight.equals("left")){
                topDownPlane.setVisible(true);
                topDownObstacle.setVisible(true);
                directionLabel.setText("Take-off Direction");
                directionLine.setVisible(true);
                towards.setVisible(true);
                leftThresholdTto();
                leftClearStopDisplays();
            }
            else if(runwayLeftOrRight.equals("right")){
                topDownPlane.setRotate(0);
                topDownPlane.setLayoutX(520);
                topDownObstacle.setLayoutX(124);
                topDownObstacle.setVisible(true);
                topDownPlane.setVisible(true);
                directionLabel.setText("Take-off Direction");
                directionLine.setVisible(true);
                away.setVisible(true);
                rightThresholdTto();
                rightClearStopDisplays();
            }

        }
        else if(calculationOcurred.equals("toa")){
            if(runwayLeftOrRight.equals("left")){
                topDownObstacle.setLayoutX(100);
                topDownPlane.setLayoutX(400);
                topDownObstacle.setVisible(true);
                topDownPlane.setVisible(true);
                leftClearStopDisplays();
                leftThresholdToa();
                directionLabel.setText("Take-off Direction");
                directionLine.setVisible(true);
                towards.setVisible(true);
            }
            else if(runwayLeftOrRight.equals("right")){
                topDownPlane.setRotate(0);
                topDownPlane.setLayoutX(400);
                topDownObstacle.setVisible(true);
                topDownPlane.setVisible(true);
                rightClearStopDisplays();
                rightThresholdToa();
                directionLabel.setText("Take-off Direction");
                directionLine.setVisible(true);
                away.setVisible(true);
            }

        }
        else if(calculationOcurred.equals("loo")){
            if(runwayLeftOrRight.equals("left")){
                topDownObstacle.setLayoutX(124);
                topDownPlane.setLayoutX(200);
                topDownObstacle.setVisible(true);
                topDownPlane.setVisible(true);
                leftClearStopDisplays();
                leftThresholdLoo();
                directionLabel.setText("Landing Direction");
                directionLine.setVisible(true);
                towards.setVisible(true);

            }else if(runwayLeftOrRight.equals("right")){
                topDownPlane.setRotate(0);
                topDownPlane.setLayoutX(400);
                topDownPlane.setVisible(true);
                topDownObstacle.setVisible(true);
                rightClearStopDisplays();
                rightThresholdLoo();
                directionLabel.setText("Landing Direction");
                directionLine.setVisible(true);
                away.setVisible(true);

            }
        }
        else{
            if(runwayLeftOrRight.equals("left")){
                topDownPlane.setVisible(true);
                topDownObstacle.setVisible(true);
                leftClearStopDisplays();
                leftThresholdLto();
                directionLabel.setText("Landing Direction");
                directionLine.setVisible(true);
                towards.setVisible(true);

            }
            else if(runwayLeftOrRight.equals("right")){
                topDownPlane.setRotate(0);
                topDownPlane.setLayoutX(520);
                topDownObstacle.setLayoutX(124);
                rightClearStopDisplays();
                rightThresholdLto();
                topDownObstacle.setVisible(true);
                topDownPlane.setVisible(true);
                directionLabel.setText("Landing Direction");
                directionLine.setVisible(true);
                away.setVisible(true);
            }

        }
    }

    //Method displays all measurements for the TOA calculation for a left threshold runway
    public void leftThresholdToa(){
        leftTOA.setStartX(0);
        leftTOA.setVisible(true);
        leftTOA.setEndX(leftTOA.getStartX() + (toa.getBlastProtectionAllowance()*0.2));
        leftToaStart.setVisible(true);
        leftToaEnd.setVisible(true);
        leftToaEnd.setTranslateX((toa.getBlastProtectionAllowance()*0.2));
        leftBPA.setText("Blast Protection Allowance: " + toa.getBlastProtectionAllowance() + "m");
        leftToaToda.setVisible(true);
        leftToaToda.setLayoutX(LClearway.getLayoutX());
        leftToaToda.setTranslateX(LClearway.getWidth());
        leftToaToda.setEndX((0- (leftToaToda.getLayoutX() + LClearway.getWidth() )) + leftToaEnd.getLayoutX() + (toa.getBlastProtectionAllowance()*0.2) + leftToaEnd.getEndX() );
        leftToaTodaLabel.setText("TODA: " + newRunway.getToda() + "m");
        leftToaTodaEnd.setVisible(true);
        leftToaTodaEnd.setTranslateX(LClearway.getWidth());
        leftToaAsda.setVisible(true);
        leftToaAsdaEnd.setVisible(true);
        leftToaAsda.setLayoutX(LStopWay.getLayoutX());
        leftToaAsda.setTranslateX(LStopWay.getWidth());
        leftToaAsda.setEndX((0- (leftToaAsda.getLayoutX() + LStopWay.getWidth() )) + leftToaEnd.getLayoutX() + (toa.getBlastProtectionAllowance()*0.2) + leftToaEnd.getEndX() );
        leftToaAsdaEnd.setTranslateX(LStopWay.getWidth());
        leftToaAsfaLabel.setText("ASDA: " + newRunway.getAsda() + "m");
        leftToaTora.setVisible(true);
        leftToaToraEnd.setVisible(true);
        leftToaTora.setLayoutX(LStopWay.getLayoutX());
        leftToaTora.setEndX((0- (leftToaTora.getLayoutX())) + leftToaEnd.getLayoutX() + (toa.getBlastProtectionAllowance()*0.2) + leftToaEnd.getEndX() );
        leftToaToraLabel.setText("Tora: " + newRunway.getTora() + "m");
        if(newRunway.getDisplacedThreshold() > 0){
            displacedThreshold.setVisible(true);
            displacedThreshold.setTranslateX(newRunway.getDisplacedThreshold() * 0.2);
        }
        topDownObstacle.setTranslateX(newRunway.getDisplacedThreshold() * 0.2);


    }

    //Method displays the relevent TOA visualizations for a right threshold runway
    public void rightThresholdToa(){
        rightToaBpa.setVisible(true);
        rightToaStart.setVisible(true);
        rightToaEnd.setVisible(true);
        rightToaBpa.setEndX(0 - toa.getBlastProtectionAllowance()*0.2);
        topDownPlane.setTranslateX(0 -(toa.getBlastProtectionAllowance()*0.2));
        rightToaEnd.setTranslateX(0 - toa.getBlastProtectionAllowance()*0.2);
        rightToaToda.setLayoutX(rightToaToda.getLayoutX() - Rclearway.getWidth() + 2);
        rightToaToda.setEndX((0 - rightToaToda.getLayoutX())+ rightToaEnd.getLayoutX() - toa.getBlastProtectionAllowance()*0.2 );
        rightToaTodaEnd.setTranslateX(0 - Rclearway.getWidth() + 2);
        rightToaToda.setVisible(true);
        rightToaTodaEnd.setVisible(true);
        rightBPA.setText("Blast Protection Allowance: " + toa.getBlastProtectionAllowance() + "m");
        rightToaTodaLabel.setText("TODA: " + newRunway.getToda() + "m");
        rightToaAsda.setVisible(true);
        rightToaAsdaEnd.setVisible(true);
        rightToaAsda.setLayoutX(rightToaAsda.getLayoutX() - Rstopway.getWidth() + 2);
        rightToaAsda.setEndX((0 - rightToaAsda.getLayoutX())+ rightToaEnd.getLayoutX() - toa.getBlastProtectionAllowance()*0.2 );
        rightToaAsdaEnd.setTranslateX(0 - Rstopway.getWidth() + 2);
        rightToaAsfaLabel.setText("ASDA: " + newRunway.getAsda() + "m");
        rightToaToraEnd.setVisible(true);
        rightToaTora.setVisible(true);
        rightToaTora.setEndX((0 - rightToaTora.getLayoutX())+ rightToaEnd.getLayoutX() - toa.getBlastProtectionAllowance()*0.2 );
        rightToaToraLabel.setText("TORA: " + newRunway.getTora() + "m");
        if(newRunway.getDisplacedThreshold() > 0){
            rightdisplacedThreshold.setVisible(true);
            rightdisplacedThreshold.setTranslateX(-newRunway.getDisplacedThreshold()*0.2);
        }
        topDownObstacle.setTranslateX(-newRunway.getDisplacedThreshold()*0.2);

    }

    //Method displays visualizations relevant to the LTO calculation on the left threshold
    public void leftThresholdLto(){
        leftLtoResa.setVisible(true);
        leftLtoResaStart.setVisible(true);
        leftLtoResaEnd.setVisible(true);
        leftLtoResa.setEndX(0 - (newRunway.getResa()*0.2));
        leftLtoResaEnd.setLayoutX(leftLtoResaEnd.getLayoutX() - (newRunway.getResa()*0.2));
        leftLtoResaLabel.setText("RESA: " + newRunway.getResa() + "m");
        leftLtoStripEnd.setLayoutX(leftLtoResaEnd.getLayoutX());
        leftLtoStripEnd.setEndX(0 - (newRunway.getStripEnd()*0.2));
        leftLtoStripEnd.setVisible(true);
        leftLtoStripEndEnd.setVisible(true);
        leftLtoStripEndEnd.setLayoutX(leftLtoStripEnd.getLayoutX() - (newRunway.getStripEnd()*0.2));
        leftLtoStripEndLabel.setText("Strip End: " + newRunway.getStripEnd() + "m");
        if(newRunway.getDisplacedThreshold() > 0){
            displacedThreshold.setVisible(true);
            displacedThreshold.setLayoutX(displacedThreshold.getLayoutX() + newRunway.getDisplacedThreshold()*0.2);
        }
        leftLtoLda.setLayoutX(leftLtoStripEndEnd.getLayoutX());
        leftLtoLda.setEndX(0 - leftLtoLda.getLayoutX() + displacedThreshold.getLayoutX());
        leftLtoLdaEnd.setLayoutX(displacedThreshold.getLayoutX());
        leftLtoLda.setVisible(true);
        leftLtoLdaEnd.setVisible(true);
        topDownPlane.setTranslateX(newRunway.getDisplacedThreshold()*0.2);
        leftLtoLdaLabel.setText("LDA: " + newRunway.getLda() + "m");

    }

    //Method displays visualizations relevant to the LTO calculation on the right threshold
    public void rightThresholdLto(){
        rightLtoResa.setVisible(true);
        rightLtoResaStart.setVisible(true);
        rightLtoResa.setEndX(newRunway.getResa() * 0.2);
        rightLtoResaEnd.setVisible(true);
        rightLtoResaEnd.setLayoutX(rightLtoResaEnd.getLayoutX() + newRunway.getResa()*0.2);
        rightLtoResaLabel.setText("RESA: " + newRunway.getResa() + "m");
        rightLtoStripEnd.setVisible(true);
        rightLtoStripEndEnd.setVisible(true);
        rightLtoStripEnd.setLayoutX(rightLtoResaEnd.getLayoutX());
        rightLtoStripEnd.setEndX(newRunway.getStripEnd()*0.2);
        rightLtoStripEndEnd.setLayoutX(rightLtoResaEnd.getLayoutX() + newRunway.getStripEnd()*0.2);
        rightLtoStripEndLabel.setText("Strip End: " + newRunway.getStripEnd() + "m");
        rightLtoLda.setVisible(true);
        rightLtoLdaEnd.setVisible(true);
        if(newRunway.getDisplacedThreshold() > 0) {
            rightdisplacedThreshold.setVisible(true);
            rightdisplacedThreshold.setLayoutX(rightdisplacedThreshold.getLayoutX() - newRunway.getDisplacedThreshold() * 0.2);
        }
        rightLtoLda.setLayoutX(rightLtoStripEndEnd.getLayoutX());
        rightLtoLda.setEndX(rightdisplacedThreshold.getLayoutX()-rightLtoLda.getLayoutX());
        rightLtoLdaEnd.setLayoutX(rightdisplacedThreshold.getLayoutX());
        topDownPlane.setTranslateX(0 - newRunway.getDisplacedThreshold()*0.2);
        rightLtoLdaLabel.setText("LDA: " + newRunway.getLda() + "m");

    }

    //Method displays visualizations relevant to the TOTO calculation on the left threshold
    public void leftThresholdTto(){
        leftLtoResa.setVisible(true);
        leftLtoResaStart.setVisible(true);
        leftLtoResaEnd.setVisible(true);
        leftLtoResa.setEndX(0 - (newRunway.getResa()*0.2));
        leftLtoResaEnd.setLayoutX(leftLtoResaEnd.getLayoutX() - (newRunway.getResa()*0.2));
        leftLtoResaLabel.setText("RESA: " + newRunway.getResa() + "m");
        leftLtoStripEnd.setLayoutX(leftLtoResaEnd.getLayoutX());
        leftLtoStripEnd.setEndX(0 - (newRunway.getStripEnd()*0.2));
        leftLtoStripEnd.setVisible(true);
        leftLtoStripEndEnd.setVisible(true);
        leftLtoStripEndEnd.setLayoutX(leftLtoStripEnd.getLayoutX() - (newRunway.getStripEnd()*0.2));
        leftLtoStripEndLabel.setText("Strip End: " + newRunway.getStripEnd() + "m");
        if(newRunway.getDisplacedThreshold() > 0){
            displacedThreshold.setVisible(true);
            displacedThreshold.setLayoutX(displacedThreshold.getLayoutX() + newRunway.getDisplacedThreshold()*0.2);
        }
        leftLtoLda.setLayoutX(leftLtoStripEndEnd.getLayoutX());
        leftLtoLda.setEndX(0 - leftLtoLda.getLayoutX() + displacedThreshold.getLayoutX());
        leftLtoLdaEnd.setLayoutX(displacedThreshold.getLayoutX());
        leftLtoLda.setVisible(true);
        leftLtoLdaEnd.setVisible(true);
        topDownPlane.setTranslateX(newRunway.getDisplacedThreshold()*0.2);
        leftLtoLdaLabel.setText("TORA, TODA, ASDA : " + newRunway.getTora() + "m");
        leftLtoResaEnd.setStartY(-30);
        leftTotoAlsEnd.setVisible(true);
        leftTotoAls.setVisible(true);
        leftTotoAls.setEndX(0- (leftTotoAlsEnd.getLayoutX() - leftLtoResaEnd.getLayoutX() + leftTotoAlsEnd.getEndX()));
        leftLtoResaLabel.setTranslateY(3);
        leftTotoAlsLabel.setText("ALS: (" + obstacle.getHeight() + " * " + toto.getTocs() +")m");
        leftTotoAlsLabel.setTranslateY(-5);
    }

    //Method displays visualizations relevant to the TOTO calculation on the right threshold
    public void rightThresholdTto(){
        rightLtoResa.setVisible(true);
        rightLtoResaStart.setVisible(true);
        rightLtoResa.setEndX(newRunway.getResa() * 0.2);
        rightLtoResaEnd.setVisible(true);
        rightLtoResaEnd.setLayoutX(rightLtoResaEnd.getLayoutX() + newRunway.getResa()*0.2);
        rightLtoResaLabel.setText("RESA: " + newRunway.getResa() + "m");
        rightLtoStripEnd.setVisible(true);
        rightLtoStripEndEnd.setVisible(true);
        rightLtoStripEnd.setLayoutX(rightLtoResaEnd.getLayoutX());
        rightLtoStripEnd.setEndX(newRunway.getStripEnd()*0.2);
        rightLtoStripEndEnd.setLayoutX(rightLtoResaEnd.getLayoutX() + newRunway.getStripEnd()*0.2);
        rightLtoStripEndLabel.setText("Strip End: " + newRunway.getStripEnd() + "m");
        rightLtoLda.setVisible(true);
        rightLtoLdaEnd.setVisible(true);
        if(newRunway.getDisplacedThreshold() > 0) {
            rightdisplacedThreshold.setVisible(true);
            rightdisplacedThreshold.setLayoutX(rightdisplacedThreshold.getLayoutX() - newRunway.getDisplacedThreshold() * 0.2);
        }
        rightLtoLda.setLayoutX(rightLtoStripEndEnd.getLayoutX());
        rightLtoLda.setEndX(rightdisplacedThreshold.getLayoutX()-rightLtoLda.getLayoutX());
        rightLtoLdaEnd.setLayoutX(rightdisplacedThreshold.getLayoutX());
        topDownPlane.setTranslateX(0 - newRunway.getDisplacedThreshold()*0.2);
        rightLtoLdaLabel.setText("LDA: " + newRunway.getLda() + "m");
        rightLtoLdaLabel.setText("TORA, TODA, ASDA: " + newRunway.getTora() + "m");
        rightTotoAlsEnd.setVisible(true);
        rightTotoAls.setVisible(true);
        rightLtoResaEnd.setEndY(40);
        rightTotoAls.setEndX((rightLtoResaEnd.getLayoutX() -rightTotoAlsEnd.getLayoutX()));
        rightLtoResaLabel.setTranslateY(-52);
        rightLtoStripEndLabel.setTranslateY(-52);
        rightTotoAlsLabel.setText("ALS: (" + obstacle.getHeight() + " * " + toto.getTocs() +")m");


    }

    public void leftThresholdLoo() {
        if (newRunway.getDisplacedThreshold() > 0) {
            displacedThreshold.setVisible(true);
            displacedThreshold.setLayoutX(displacedThreshold.getLayoutX() + newRunway.getDisplacedThreshold() * 0.2);
        }
        topDownPlane.setTranslateX(newRunway.getDisplacedThreshold() * 0.2);
        topDownObstacle.setTranslateX(newRunway.getDisplacedThreshold() * 0.2);
        rightLtoResaStart.setVisible(true);
        rightLtoResa.setVisible(true);
        rightLtoStripEnd.setVisible(true);
        rightLtoResaEnd.setVisible(true);
        rightLtoStripEndEnd.setVisible(true);
        rightLtoResaStart.setLayoutX(topDownObstacle.getLayoutX() + 80);
        rightLtoResa.setLayoutX(rightLtoResaStart.getLayoutX());
        rightLtoResa.setEndX(newRunway.getResa() * 0.2);
        rightLtoResaEnd.setLayoutX(rightLtoResaStart.getLayoutX() + newRunway.getResa() * 0.2);
        rightLtoResaLabel.setText("RESA: " + newRunway.getResa() + "m");
        rightLtoResaLabel.setLayoutX(rightLtoResaStart.getLayoutX() - 100);
        rightLtoResaLabel.setLayoutY(rightLtoResaStart.getLayoutY());
        rightLtoStripEnd.setLayoutX(rightLtoResaEnd.getLayoutX());
        rightLtoStripEnd.setEndX(newRunway.getStripEnd() * 0.2);
        rightLtoStripEnd.setVisible(true);
        rightLtoStripEndEnd.setVisible(true);
        rightLtoStripEndEnd.setLayoutX(rightLtoStripEnd.getLayoutX() + newRunway.getStripEnd() * 0.2);
        rightLtoStripEndLabel.setText("Strip End: " + newRunway.getStripEnd() + "m");
        rightLtoStripEndLabel.setLayoutX(rightLtoStripEndEnd.getLayoutX() + 2);
        rightLtoLdaEnd.setVisible(true);
        rightLtoLdaLabel.setText("LDA: " + newRunway.getLda());
        rightLtoResaEnd.setEndY(40);
        rightTotoAlsEnd.setLayoutX(displacedThreshold.getLayoutX());
        rightTotoAls.setLayoutX(rightTotoAlsEnd.getLayoutX());
        rightTotoAls.setVisible(true);
        rightTotoAlsEnd.setVisible(true);
        rightTotoAls.setEndX(rightLtoResaEnd.getLayoutX() - rightTotoAlsEnd.getLayoutX());
        if(loo.getBlastProtectionAllowance() > 0){
            leftLooBpaStart.setLayoutX(rightLtoResaStart.getLayoutX());
            leftLooBpaStart.setVisible(true);
            leftLooBpaStart.setStartY(-30);
            leftLooBpa.setVisible(true);
            leftLooBpa.setLayoutX(leftLooBpaStart.getLayoutX());
            leftLooBpa.setEndX(loo.getBlastProtectionAllowance()*0.2);
            leftLooBpaEnd.setLayoutX(leftLooBpa.getLayoutX() + loo.getBlastProtectionAllowance()*0.2);
            leftLooBpaEnd.setVisible(true);
        }
        rightLtoLda.setVisible(true);
        if(loo.getBlastProtectionAllowance() > newRunway.getStripEnd() + newRunway.getResa()){
            rightLtoLdaEnd.setEndY(100);
            rightLtoLda.setLayoutY(leftLooBpa.getLayoutY());
            rightLtoLda.setLayoutX(leftLooBpaEnd.getLayoutX());
            rightLtoLda.setEndX((rightdisplacedThreshold.getLayoutX() + 5 - leftLooBpaEnd.getLayoutX()));
        }
        else{
            rightLtoLda.setLayoutX(rightLtoStripEndEnd.getLayoutX());
            rightLtoLda.setEndX((rightdisplacedThreshold.getLayoutX() + 5 - rightLtoStripEndEnd.getLayoutX()));
        }

        rightLtoLdaEnd.setLayoutX(rightdisplacedThreshold.getLayoutX() + 5);
        leftLooBpaLabel.setLayoutY(leftLooBpaStart.getLayoutY() + 30);
        leftLooBpaLabel.setLayoutX(leftLooBpaStart.getLayoutX() + 2);
        leftLooBpaLabel.setText("BPA: " + loo.getBlastProtectionAllowance() + "m");
        rightTotoAlsLabel.setLayoutX(rightTotoAlsEnd.getLayoutX() - 100);
        rightTotoAlsLabel.setText("ALS: (" + obstacle.getHeight() + " * " + loo.getAls() +")m");
        rightLtoLdaLabel.setLayoutX(rightLtoLdaEnd.getLayoutX() - 100);

    }

    public void rightThresholdLoo(){
        if(newRunway.getDisplacedThreshold() > 0){
            rightdisplacedThreshold.setVisible(true);
            rightdisplacedThreshold.setLayoutX(rightdisplacedThreshold.getLayoutX() - newRunway.getDisplacedThreshold()*0.2);
        }
        topDownPlane.setTranslateX(0 - newRunway.getDisplacedThreshold()*0.2);
        topDownObstacle.setTranslateX(0 -newRunway.getDisplacedThreshold()*0.2);
        leftLtoResaStart.setVisible(true);
        leftLtoResa.setVisible(true);
        leftLtoStripEnd.setVisible(true);
        leftLtoResaEnd.setVisible(true);
        leftLtoStripEndEnd.setVisible(true);
        leftLtoResaStart.setLayoutX(topDownObstacle.getLayoutX());
        leftLtoResa.setLayoutX(leftLtoResaStart.getLayoutX());
        leftLtoResa.setEndX(0 - (newRunway.getResa()*0.2));
        leftLtoResaEnd.setLayoutX(leftLtoResaStart.getLayoutX() - newRunway.getResa()*0.2);
        leftLtoResaLabel.setText("RESA: " + newRunway.getResa() + "m");
        leftLtoStripEnd.setLayoutX(leftLtoResaEnd.getLayoutX());
        leftLtoStripEnd.setEndX(0 - (newRunway.getStripEnd()*0.2));
        leftLtoStripEnd.setVisible(true);
        leftLtoStripEndEnd.setVisible(true);
        leftLtoStripEndEnd.setLayoutX(leftLtoStripEnd.getLayoutX() - (newRunway.getStripEnd()*0.2));
        leftLtoStripEndLabel.setText("Strip End: " + newRunway.getStripEnd() + "m");
        leftLtoStripEndLabel.setLayoutX(leftLtoStripEndEnd.getLayoutX()-100);
        leftLtoLda.setLayoutX(leftLtoStripEndEnd.getLayoutX());
        leftLtoLdaEnd.setLayoutX(displacedThreshold.getLayoutX());
        leftLtoLda.setVisible(true);
        leftLtoLdaEnd.setVisible(true);
        leftLtoLdaLabel.setText("LDA : " + newRunway.getLda() + "m");
        leftLtoResaEnd.setStartY(-30);
        leftTotoAlsEnd.setLayoutX(rightdisplacedThreshold.getLayoutX() - leftTotoAlsEnd.getEndX());
        leftTotoAls.setLayoutX(rightdisplacedThreshold.getLayoutX());
        leftTotoAls.setEndX((0- (leftTotoAls.getLayoutX() - leftLtoResaEnd.getLayoutX())));
        leftTotoAls.setVisible(true);
        leftTotoAlsEnd.setVisible(true);
        leftTotoAlsLabel.setText("ALS: (" + obstacle.getHeight() + " * " + loo.getAls() +")m");
        leftTotoAlsLabel.setLayoutX(rightdisplacedThreshold.getLayoutX() + 8);
        leftTotoAlsLabel.setLayoutY(leftTotoAlsEnd.getLayoutY());
        leftLtoResaLabel.setTranslateY(30);
        if(loo.getBlastProtectionAllowance() >0) {
            rightLooBpastart.setLayoutX(leftLtoResaStart.getLayoutX());
            rightLooBpastart.setVisible(true);
            rightLooBpastart.setEndY(100);
            rightLooPpa.setVisible(true);
            rightLooPpa.setLayoutX(rightLooBpastart.getLayoutX());
            rightLooPpa.setEndX(0 - loo.getBlastProtectionAllowance() * 0.2);
            rightLooBpaEnd.setLayoutX(rightLooBpastart.getLayoutX() - loo.getBlastProtectionAllowance() * 0.2);
            rightLooBpaEnd.setVisible(true);
        }
        leftLtoLda.setVisible(true);
        if(loo.getBlastProtectionAllowance() > newRunway.getStripEnd() + newRunway.getResa()) {
            leftLtoLdaEnd.setStartY(-80);
            leftLtoLda.setLayoutY(rightLooPpa.getLayoutY());
            leftLtoLda.setLayoutX(rightLooBpaEnd.getLayoutX());

        }
        else{
            leftLtoLda.setLayoutX(leftLtoStripEndEnd.getLayoutX());
        }
        leftLtoLda.setEndX(0 - leftLtoLda.getLayoutX() + displacedThreshold.getLayoutX());
        rightLooBpaLabel.setLayoutY(rightLooBpastart.getLayoutY());
        rightLooBpaLabel.setLayoutX(rightLooBpastart.getLayoutX()+10);
        rightLooBpaLabel.setText("BPA: " + loo.getBlastProtectionAllowance() + "m");

    }

    // Method displays runway number markings
    //ERRORS: 27 -> 2
    public void displayTopDownRunwayMarkings(){
        leftRunwayDirection.setVisible(true);
        rightRunwayDirection.setVisible(true);
        boolean hasLetter;
        int letterSubstring = 1;

        if(newRunway.getRunwayNumber().length() == 1) {
            hasLetter = false;
            letterSubstring = 0;
            newRunway.setRunwayNumber("0" + newRunway.getRunwayNumber());
        }

        if(newRunway.getRunwayNumber().length() == 2) {
            char runwayChar = newRunway.getRunwayNumber().charAt(newRunway.getRunwayNumber().length()-1);
            if(Character.toUpperCase(runwayChar) == 'R' || Character.toUpperCase(runwayChar) == 'L' || Character.toUpperCase(runwayChar) == 'C') {
                newRunway.setRunwayNumber("0" + newRunway.getRunwayNumber());
                hasLetter = true;
            }
            else {
                hasLetter = false;
                letterSubstring = 0;
            }
        }

        char runwayChar = newRunway.getRunwayNumber().charAt(newRunway.getRunwayNumber().length()-1);
        if(Character.toUpperCase(runwayChar) == 'R' || Character.toUpperCase(runwayChar) == 'L' || Character.toUpperCase(runwayChar) == 'C')
            hasLetter = true;
        else {
            hasLetter = false;
            letterSubstring = 0;
        }

        if(Integer.parseInt(newRunway.getRunwayNumber().substring(0,newRunway.getRunwayNumber().length()-letterSubstring))> Integer.parseInt(getOppositeRunwayNumber(Integer.parseInt(newRunway.getRunwayNumber().substring(0,newRunway.getRunwayNumber().length()-letterSubstring))))){
            rightRunwayNumber.setText(newRunway.getRunwayNumber().substring(0,newRunway.getRunwayNumber().length()-letterSubstring));

            if(hasLetter)
            rightRunwayDirection.setText(String.valueOf(runwayChar));
            else rightRunwayDirection.setText(null);

            runwayLeftOrRight = "right";
            leftRunwayNumber.setText(getOppositeRunwayNumber(Integer.parseInt(newRunway.getRunwayNumber().substring(0,newRunway.getRunwayNumber().length()-letterSubstring))));

            if(Character.toUpperCase(runwayChar)=='R'){
                leftRunwayDirection.setText("L");
            }

            else if (Character.toUpperCase(runwayChar)=='L'){
                leftRunwayDirection.setText("R");
            }

            else if (Character.toUpperCase(runwayChar)=='C') {
                leftRunwayDirection.setText("C");
            }

            else leftRunwayDirection.setText(null);
        }

        else{
            leftRunwayNumber.setText(newRunway.getRunwayNumber().substring(0,newRunway.getRunwayNumber().length()-letterSubstring));
            rightRunwayNumber.setText(getOppositeRunwayNumber(Integer.parseInt(newRunway.getRunwayNumber().substring(0,newRunway.getRunwayNumber().length()-letterSubstring))));

            if (hasLetter)
            leftRunwayDirection.setText(String.valueOf(runwayChar));
            else leftRunwayDirection.setText(null);

            runwayLeftOrRight = "left";

            if(Character.toUpperCase(runwayChar)=='R'){
                rightRunwayDirection.setText("L");
            }

            else if (Character.toUpperCase(runwayChar)=='L'){
                rightRunwayDirection.setText("R");
            }

            else if (Character.toUpperCase(runwayChar)=='C') {
                rightRunwayDirection.setText("C");
            }

            else rightRunwayDirection.setText(null);
        }
        leftRunwayNumber.setVisible(true);
        rightRunwayNumber.setVisible(true);
    }

    //Method displays values corresponding for a runway of the left threshold
    public void leftClearStopDisplays(){
        LClearway.setWidth(newRunway.getClearway()*0.5);
        LStopWay.setWidth(newRunway.getStopway()*0.5);
        LStopWay.setVisible(true);
        LClearway.setVisible(true);

    }

    //Method displays values corresponding for a runway of the right threshold
    public void rightClearStopDisplays(){
        double oldClearWidth = Rclearway.getWidth();
        double oldStopWidth = Rstopway.getWidth();
        double oldClearX = Rclearway.getX();
        double oldStopX = Rstopway.getX();
        Rclearway.setWidth((newRunway.getClearway()*0.5));
        Rclearway.setX(oldClearX- (Math.abs(Rclearway.getWidth()-oldClearWidth)) + 10);
        Rstopway.setWidth((newRunway.getStopway()*0.5));
        Rstopway.setX(oldStopX-Math.abs(Rstopway.getWidth()-oldStopWidth) +11);
        Rclearway.setVisible(true);
        Rstopway.setVisible(true);

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

    }

    //Method draws on the canvas the length of the runway parameters
    public void lineHorizontal (double x, double y, double length, GraphicsContext gc){
        gc.moveTo(x,y-5);
        gc.lineTo(x,y+5);
        gc.moveTo(x,y);
        gc.lineTo(x+length,y);
        gc.moveTo(x+length,y-5);
        gc.lineTo(x+length,y+5);
        gc.stroke();
    }
    //Method draws on the canvas the length of  runway parameters with text
    public void lineHorizontal (double x, double y, double length, String text ,GraphicsContext gc){


        gc.moveTo(x,y-5);
        gc.lineTo(x,y+5);
        gc.moveTo(x,y);
        gc.lineTo(x+length,y);
        gc.moveTo(x+length,y-5);
        gc.lineTo(x+length,y+5);
        gc.fillText(text, x+10,y+20);
        gc.stroke();
    }
    //Method draws vertical line
    public void lineVertical (double x, double y, double length, GraphicsContext gc){

        gc.moveTo(x-5,y);
        gc.lineTo(x+5,y);
        gc.moveTo(x,y);
        gc.lineTo(x,y+length);
        gc.moveTo(x-5,y+length);
        gc.lineTo(x+5,y+length);
        gc.stroke();
    }

    //Method displays the calculation that has taken place
    public void displayCalculation() throws FileNotFoundException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        notifications.getItems().add(0,new String(dtf.format(now) + ": Calculations breakdown shown."));
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

    public void saveTheResults(){
        if(filename.getText() == "" || filename.getText() == null) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Please enter the name of the file to save the runways (without extension).");
            error.showAndWait();
        }
        else {
            RadioButton option = (RadioButton) group.getSelectedToggle();

            if(option.getText().equals(".xml")) {
                XMLWriter writer = new XMLWriter();
                String file = filename.getText() + ".xml";
                writer.saveResultsToXML(file, oldRunway, newRunway, obstacle, getCalculationOcurred(), slope, bpa);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                notifications.getItems().add(0,new String(dtf.format(now) + ": Results saved at " +  writer.getRunwaysFilePath()) + ".");
            }

            if(option.getText().equals(".txt")) {
                TXTSaver saver = new TXTSaver();
                String file = filename.getText() + ".txt";
                saver.saveResultsToTXT(file, oldRunway, newRunway, obstacle, getCalculationOcurred(), slope, bpa, notifications);
            }
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
        loader.setLocation(getClass().getResource("Input.fxml"));
        Parent obstacleParent = loader.load();
        Scene obstacleScene = new Scene(obstacleParent);
        InputController controller = loader.getController();
        controller.initRunway(oldRunway,obstacle,runwaysList ,list, notifications,obstacleList,obsList,runwayPairs, runwayNames, obstacleNames);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        directionLine.setVisible(false);
        towards.setVisible(false);
        away.setVisible(false);
        Rclearway.setVisible(false);
        Rstopway.setVisible(false);
        LStopWay.setVisible(false);
        LClearway.setVisible(false);
        leftRunwayDirection.setVisible(false);
        leftRunwayNumber.setVisible(false);
        rightRunwayDirection.setVisible(false);
        rightRunwayNumber.setVisible(false);
        topDownObstacle.setVisible(false);
        topDownPlane.setVisible(false);
        leftTOA.setVisible(false);
        leftToaStart.setVisible(false);
        leftToaEnd.setVisible(false);
        leftToaToda.setVisible(false);
        leftToaTodaEnd.setVisible(false);
        leftToaAsda.setVisible(false);
        leftToaAsdaEnd.setVisible(false);
        leftToaToraEnd.setVisible(false);
        leftToaTora.setVisible(false);
        displacedThreshold.setVisible(false);
        rightToaBpa.setVisible(false);
        rightToaStart.setVisible(false);
        rightToaEnd.setVisible(false);
        rightToaToda.setVisible(false);
        rightToaTodaEnd.setVisible(false);
        rightToaAsda.setVisible(false);
        rightToaAsdaEnd.setVisible(false);
        rightToaTora.setVisible(false);
        rightToaToraEnd.setVisible(false);
        rightdisplacedThreshold.setVisible(false);
        leftLtoResa.setVisible(false);
        leftLtoResaEnd.setVisible(false);
        leftLtoResaStart.setVisible(false);
        leftLtoStripEnd.setVisible(false);
        leftLtoStripEndEnd.setVisible(false);
        leftLtoLda.setVisible(false);
        leftLtoLdaEnd.setVisible(false);
        rightLtoResaStart.setVisible(false);
        rightLtoResa.setVisible(false);
        rightLtoResaEnd.setVisible(false);
        rightLtoStripEnd.setVisible(false);
        rightLtoStripEndEnd.setVisible(false);
        rightLtoLda.setVisible(false);
        rightLtoLdaEnd.setVisible(false);
        leftTotoAls.setVisible(false);
        leftTotoAlsEnd.setVisible(false);
        rightTotoAls.setVisible(false);
        rightTotoAlsEnd.setVisible(false);
        rightLooPpa.setVisible(false);
        rightLooBpastart.setVisible(false);
        rightLooBpaEnd.setVisible(false);
        leftLooBpaEnd.setVisible(false);
        leftLooBpa.setVisible(false);
        leftLooBpaStart.setVisible(false);
        showResults.setVisible(false);

    }
}

