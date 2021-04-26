package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class TXTSaver {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    private void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void saveResultsToTXT(String txt, Runway oldRunway, Runway newRunway, Obstacle obstacle, String calculationOcurred, int slope, int bpa, ListView notifications) {

        //this shows the directory chooser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a directory where to save the file");
        Stage primaryStage = new Stage();
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        String maneuver = "";

        //This ifs check if there needs to be a 0 added in front of the runway number
        String runwayNumber = oldRunway.getRunwayNumber();
        if(runwayNumber.length() == 1)
            runwayNumber = "0" + runwayNumber;
        else {
            char runwayChar = oldRunway.getRunwayNumber().charAt(oldRunway.getRunwayNumber().length()-1);
            if (Character.toUpperCase(runwayChar) == 'R' || Character.toUpperCase(runwayChar) == 'L' || Character.toUpperCase(runwayChar) == 'C')
                if(runwayNumber.length() == 2)
                    runwayNumber = "0" + runwayNumber;
        }

        //these 3 lines get the absolute chosen path and exists is true if a file with the given argument name
        //exists there or false if it doesn't
        String filePath = selectedDirectory.getAbsolutePath();
        setFilePath(filePath + "/" + txt);

        switch (calculationOcurred) {
            case "loo":
                maneuver = "Landing over obstacle";
                break;
            case "lto":
                maneuver = "Landing towards obstacle";
                break;
            case "toa":
                maneuver = "Take-off over obstacle";
                break;
            case "toto":
                maneuver = "Take-off towards obstacle";
                break;
        }

        boolean exists = Files.exists(Paths.get(getFilePath()));
        //here it checks if the file already exists and pops an alert in case it does
        if (exists) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("This file already exists.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        else {
            try {
                File myObj = new File(getFilePath());
                if (myObj.createNewFile()) {
                    FileWriter myWriter = new FileWriter(getFilePath());
                    myWriter.write("RESULTS:\n" +
                            "Runway number: " + runwayNumber + "\n" +
                            "TORA: " + oldRunway.getTora() + " -> " + newRunway.getTora() + "\n" +
                            "TODA: " + oldRunway.getToda() + " -> " + newRunway.getToda() + "\n" +
                            "ASDA: " + oldRunway.getAsda() + " -> " + newRunway.getAsda() + "\n" +
                            "LDA: " + oldRunway.getLda() + " -> " + newRunway.getLda() + "\n" +
                            "Threshold: " + oldRunway.getDisplacedThreshold() + " -> " + newRunway.getDisplacedThreshold() + "\n" +
                            "Strip end: " + oldRunway.getStripEnd() + " -> " + newRunway.getStripEnd() + "\n" +
                            "Clearway: " + oldRunway.getClearway() + " -> " + newRunway.getClearway() + "\n" +
                            "Stopway: " + oldRunway.getStopway() + " -> " + newRunway.getStopway() + "\n" +
                            "RESA: " + oldRunway.getResa() + " -> " + newRunway.getResa() + "\n" +
                            "Maneuver: " + maneuver + "\n" +
                            "Blast protection allowance: " + bpa + "\n" +
                            "Slope: " + slope + "\n" +
                            "Obstacle name: " + obstacle.getName() + "\n" +
                            "Obstacle height: " + obstacle.getHeight() + "\n" +
                            "Distance from threshold: " + obstacle.getDistanceFromThreshold() + "\n" +
                            "Distance from centreline: " + obstacle.getDistanceFromCentreLine());
                    myWriter.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("File created successfully.");
                    Optional<ButtonType> result = alert.showAndWait();
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    notifications.getItems().add(0,new String(dtf.format(now) + ": Results saved at " + getFilePath()) + ".");
                }
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("An error occurred.");
                Optional<ButtonType> result = alert.showAndWait();
                e.printStackTrace();
            }
        }
    }
}
