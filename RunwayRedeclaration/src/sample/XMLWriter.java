package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.lang.Character;
import java.util.Optional;

//This is where the XML files are written to save the runways and/or obstacles
public class XMLWriter {

    private String runwaysFilePath;
    private String obstaclesFilePath;

    public String getObstaclesFilePath() {
        return obstaclesFilePath;
    }

    public String getRunwaysFilePath() {
        return runwaysFilePath;
    }

    private void setRunwaysFilePath(String runwaysFilePath) {
        this.runwaysFilePath = runwaysFilePath;
    }

    private void setObstaclesFilePath(String obstaclesFilePath) {
        this.obstaclesFilePath = obstaclesFilePath;
    }

    //this one is used to write the orientation of the runway in case its number has an L, R or C at the end
    public String whatOrientation (String s) {
        char runwayChar = s.charAt(s.length()-1);
        switch(Character.toUpperCase(runwayChar)) {
            case 'L':
                return "left";
            case 'R':
                return "right";
            case 'C':
                return "center";
            default:
                return "none";
        }

    }

    //call the method by giving a name for the XML file where the runways will be saved, plus the array of existing runways
    public void saveRunwaysToXML(String xml, ArrayList<Runway> runwaysList) {
        Document dom;
        Element e = null;
        //this shows the directory chooser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a directory where to save the file");
        Stage primaryStage = new Stage();
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        //these 3 lines get the absolute chosen path and exists is true if a file with the given argument name
        //exists there or false if it doesn't
        String filePath = selectedDirectory.getAbsolutePath();
        setRunwaysFilePath(filePath + "/" + xml);

        boolean exists = Files.exists(Paths.get(getRunwaysFilePath()));

        //the structure of the XML file is made here, taken from
        //https://stackoverflow.com/questions/7373567/how-to-read-and-write-xml-files and adapted
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();

            //the root is created and below the tags are appended to the root
            Element rootEle = dom.createElement("runways");
            dom.appendChild(rootEle);

            for(Runway r: runwaysList) {

                //new runway tag
                Element runway = dom.createElement("runway");
                rootEle.appendChild(runway);

                Attr attribute = dom.createAttribute("id");
                attribute.setValue(r.getRunwayNumber());
                runway.setAttributeNode(attribute);

                //all of these elements are appended to runway and the information needed
                //is extracted for the array list of runways
                e = dom.createElement("orientation");
                e.appendChild(dom.createTextNode(whatOrientation(r.getRunwayNumber())));
                runway.appendChild(e);

                e = dom.createElement("designator");
                e.appendChild(dom.createTextNode(r.getRunwayNumber()));
                runway.appendChild(e);

                e = dom.createElement("tora");
                e.appendChild(dom.createTextNode(Integer.toString(r.getTora())));
                runway.appendChild(e);

                e = dom.createElement("toda");
                e.appendChild(dom.createTextNode(Integer.toString(r.getToda())));
                runway.appendChild(e);

                e = dom.createElement("asda");
                e.appendChild(dom.createTextNode(Integer.toString(r.getAsda())));
                runway.appendChild(e);

                e = dom.createElement("lda");
                e.appendChild(dom.createTextNode(Integer.toString(r.getLda())));
                runway.appendChild(e);

                e = dom.createElement("threshold");
                e.appendChild(dom.createTextNode(Integer.toString(r.getDisplacedThreshold())));
                runway.appendChild(e);

                e = dom.createElement("stripEnd");
                e.appendChild(dom.createTextNode(Integer.toString(r.getStripEnd())));
                runway.appendChild(e);

                e = dom.createElement("clearway");
                e.appendChild(dom.createTextNode(Integer.toString(r.getClearway())));
                runway.appendChild(e);

                e = dom.createElement("stopway");
                e.appendChild(dom.createTextNode(Integer.toString(r.getStopway())));
                runway.appendChild(e);

                e = dom.createElement("resa");
                e.appendChild(dom.createTextNode(Integer.toString(r.getResa())));
                runway.appendChild(e);
            }

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                //here it checks if the file already exists and pops an alert in case it does
                if (exists) {
                    Alert error = new Alert(Alert.AlertType.CONFIRMATION);
                    error.setHeaderText("This file already exists. Do you want to overwrite it?");
                    Optional<ButtonType> result = error.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        //you accept so the file gets overwritten
                        tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(filePath + "/" + xml)));
                    }
                    //you cancel so it does nothing
                    if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                    }
                }
                //if no alert pops up, just write the file
                else {
                    tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(filePath + "/" + xml)));
                }

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            }
    }

    //exactly the same as saveRunwaysToXML, it's just adapted to obstacles
    public void saveObstaclesToXML(String xml, ArrayList<Obstacle> obstaclesList) {
        Document dom;
        Element e = null;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a directory where to save the file");
        Stage primaryStage = new Stage();
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        String filePath = selectedDirectory.getAbsolutePath();
        setObstaclesFilePath(filePath + "/" + xml);

        boolean exists = Files.exists(Paths.get(getObstaclesFilePath()));

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("obstacles");
            dom.appendChild(rootEle);

            for(Obstacle o: obstaclesList) {

                // create data elements and place them under root
                Element obstacle = dom.createElement("obstacle");
                rootEle.appendChild(obstacle);

                //all of these elements are appended to obstacle and the information needed
                //is extracted for the array list of obstacles
                e = dom.createElement("name");
                e.appendChild(dom.createTextNode(o.getName()));
                obstacle.appendChild(e);

                e = dom.createElement("height");
                e.appendChild(dom.createTextNode(Integer.toString(o.getHeight())));
                obstacle.appendChild(e);

                e = dom.createElement("distancefromcenter");
                e.appendChild(dom.createTextNode(Integer.toString(o.getDistanceFromCentreLine())));
                obstacle.appendChild(e);

                e = dom.createElement("distancefromthreshold");
                e.appendChild(dom.createTextNode(Integer.toString(o.getDistanceFromThreshold())));
                obstacle.appendChild(e);
            }

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                if (exists) {
                    Alert error = new Alert(Alert.AlertType.CONFIRMATION);
                    error.setHeaderText("This file already exists. Do you want to overwrite it?");
                    Optional<ButtonType> result = error.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        // send DOM to file
                        tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(filePath + "/" + xml)));
                    }
                    if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                    }
                }
                else{
                    tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(filePath + "/" + xml)));
                }

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
            } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            }
    }

    public void saveResultsToXML(String xml, Runway oldRunway, Runway newRunway, Obstacle obstacle, String calculationOcurred, int slope, int bpa) {
        Document dom;
        Element e = null;

        //this shows the directory chooser
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a directory where to save the file");
        Stage primaryStage = new Stage();
        File selectedDirectory = directoryChooser.showDialog(primaryStage);

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
        setRunwaysFilePath(filePath + "/" + xml);

        boolean exists = Files.exists(Paths.get(getRunwaysFilePath()));

        //the structure of the XML file is made here, taken from
        //https://stackoverflow.com/questions/7373567/how-to-read-and-write-xml-files and adapted
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();

            Element als = dom.createElement("slope");
            Element blastAllowance = dom.createElement("BPA");

            //the root is created and below the tags are appended to the root
            Element rootEle = dom.createElement("results");
            dom.appendChild(rootEle);

                Element maneuver = dom.createElement("maneuver");
                switch (calculationOcurred) {
                    case "loo":
                        maneuver.appendChild(dom.createTextNode("Landing over obstacle"));
                        rootEle.appendChild(maneuver);
                        als.appendChild(dom.createTextNode(Integer.toString(slope)));
                        blastAllowance.appendChild(dom.createTextNode(Integer.toString(bpa)));
                        rootEle.appendChild(als);
                        rootEle.appendChild(blastAllowance);
                        break;
                    case "lto":
                        maneuver.appendChild(dom.createTextNode("Landing towards obstacle"));
                        rootEle.appendChild(maneuver);
                        break;
                    case "toa":
                        maneuver.appendChild(dom.createTextNode("Take-off away from obstacle"));
                        rootEle.appendChild(maneuver);
                        blastAllowance.appendChild(dom.createTextNode(Integer.toString(bpa)));
                        rootEle.appendChild(blastAllowance);
                        break;
                    case "toto":
                        maneuver.appendChild(dom.createTextNode("Take-off towards obstacle"));
                        rootEle.appendChild(maneuver);
                        als.appendChild(dom.createTextNode(Integer.toString(slope)));
                        rootEle.appendChild(als);
                        break;
                }

                //new runway tag
                Element oldRunway1 = dom.createElement("oldRunway");
                rootEle.appendChild(oldRunway1);

                Attr attribute = dom.createAttribute("id");
                attribute.setValue(runwayNumber);
                oldRunway1.setAttributeNode(attribute);

                //all of these elements are appended to runway and the information needed
                //is extracted for the array list of runways
                e = dom.createElement("orientation");
                e.appendChild(dom.createTextNode(whatOrientation(runwayNumber)));
                oldRunway1.appendChild(e);

                e = dom.createElement("designator");
                e.appendChild(dom.createTextNode(runwayNumber));
                oldRunway1.appendChild(e);

                e = dom.createElement("tora");
                e.appendChild(dom.createTextNode(Integer.toString(oldRunway.getTora())));
                oldRunway1.appendChild(e);

                e = dom.createElement("toda");
                e.appendChild(dom.createTextNode(Integer.toString(oldRunway.getToda())));
                oldRunway1.appendChild(e);

                e = dom.createElement("asda");
                e.appendChild(dom.createTextNode(Integer.toString(oldRunway.getAsda())));
                oldRunway1.appendChild(e);

                e = dom.createElement("lda");
                e.appendChild(dom.createTextNode(Integer.toString(oldRunway.getLda())));
                oldRunway1.appendChild(e);

                e = dom.createElement("threshold");
                e.appendChild(dom.createTextNode(Integer.toString(oldRunway.getDisplacedThreshold())));
                oldRunway1.appendChild(e);

                e = dom.createElement("stripEnd");
                e.appendChild(dom.createTextNode(Integer.toString(oldRunway.getStripEnd())));
                oldRunway1.appendChild(e);

                e = dom.createElement("clearway");
                e.appendChild(dom.createTextNode(Integer.toString(oldRunway.getClearway())));
                oldRunway1.appendChild(e);

                e = dom.createElement("stopway");
                e.appendChild(dom.createTextNode(Integer.toString(oldRunway.getStopway())));
                oldRunway1.appendChild(e);

                e = dom.createElement("resa");
                e.appendChild(dom.createTextNode(Integer.toString(oldRunway.getResa())));
                oldRunway1.appendChild(e);

                //new runway tag
                Element newRunway1 = dom.createElement("newRunway");
                rootEle.appendChild(newRunway1);

                Attr attribute1 = dom.createAttribute("id");
                attribute1.setValue(runwayNumber);
                newRunway1.setAttributeNode(attribute1);

                //all of these elements are appended to runway and the information needed
                //is extracted for the array list of runways
                e = dom.createElement("orientation");
                e.appendChild(dom.createTextNode(whatOrientation(runwayNumber)));
                newRunway1.appendChild(e);

                e = dom.createElement("designator");
                e.appendChild(dom.createTextNode(runwayNumber));
                newRunway1.appendChild(e);

                e = dom.createElement("tora");
                e.appendChild(dom.createTextNode(Integer.toString(newRunway.getTora())));
                newRunway1.appendChild(e);

                e = dom.createElement("toda");
                e.appendChild(dom.createTextNode(Integer.toString(newRunway.getToda())));
                newRunway1.appendChild(e);

                e = dom.createElement("asda");
                e.appendChild(dom.createTextNode(Integer.toString(newRunway.getAsda())));
                newRunway1.appendChild(e);

                e = dom.createElement("lda");
                e.appendChild(dom.createTextNode(Integer.toString(newRunway.getLda())));
                newRunway1.appendChild(e);

                e = dom.createElement("threshold");
                e.appendChild(dom.createTextNode(Integer.toString(newRunway.getDisplacedThreshold())));
                newRunway1.appendChild(e);

                e = dom.createElement("stripEnd");
                e.appendChild(dom.createTextNode(Integer.toString(newRunway.getStripEnd())));
                newRunway1.appendChild(e);

                e = dom.createElement("clearway");
                e.appendChild(dom.createTextNode(Integer.toString(newRunway.getClearway())));
                newRunway1.appendChild(e);

                e = dom.createElement("stopway");
                e.appendChild(dom.createTextNode(Integer.toString(newRunway.getStopway())));
                newRunway1.appendChild(e);

                e = dom.createElement("resa");
                e.appendChild(dom.createTextNode(Integer.toString(newRunway.getResa())));
                newRunway1.appendChild(e);

                Element obstacle1 = dom.createElement("obstacle");
                rootEle.appendChild(obstacle1);


                //all of these elements are appended to obstacle and the information needed
                //is extracted for the array list of obstacles
                e = dom.createElement("name");
                e.appendChild(dom.createTextNode(obstacle.getName()));
                obstacle1.appendChild(e);

                e = dom.createElement("height");
                e.appendChild(dom.createTextNode(Integer.toString(obstacle.getHeight())));
                obstacle1.appendChild(e);

                e = dom.createElement("distancefromcenter");
                e.appendChild(dom.createTextNode(Integer.toString(obstacle.getDistanceFromCentreLine())));
                obstacle1.appendChild(e);

                e = dom.createElement("distancefromthreshold");
                e.appendChild(dom.createTextNode(Integer.toString(obstacle.getDistanceFromThreshold())));
                obstacle1.appendChild(e);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                //here it checks if the file already exists and pops an alert in case it does
                if (exists) {
                    Alert error = new Alert(Alert.AlertType.CONFIRMATION);
                    error.setHeaderText("This file already exists. Do you want to overwrite it?");
                    Optional<ButtonType> result = error.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        //you accept so the file gets overwritten
                        tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(filePath + "/" + xml)));
                    }
                    //you cancel so it does nothing
                    if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                    }
                }
                //if no alert pops up, just write the file
                else {
                    tr.transform(new DOMSource(dom), new StreamResult(new FileOutputStream(filePath + "/" + xml)));
                }

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
    }
}
