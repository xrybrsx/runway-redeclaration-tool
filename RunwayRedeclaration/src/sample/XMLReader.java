package sample;
import javafx.scene.control.Alert;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;


public class XMLReader {
    Runway runway;
    Obstacle obstacle;

    public ArrayList<Runway> parseRunway(File file) throws ParserConfigurationException, SAXException, IOException, NumberFormatException, RuntimeException {
        ArrayList<Runway> runwaysInXML = new ArrayList<Runway>();
        try {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        //this looks for all elements appended to runway
        NodeList nList = doc.getElementsByTagName("runway");
        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);
            //this retrieves the information within the tags appended to runway, making a new
            //runway that gets added to the array that will be returned containing all extracted runways
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element el = (Element) nNode;
                runway = new Runway(String.valueOf(el.getElementsByTagName("designator").item(0).getTextContent()),
                        Integer.parseInt(el.getElementsByTagName("tora").item(0).getTextContent()),
                        Integer.parseInt(el.getElementsByTagName("toda").item(0).getTextContent()),
                        Integer.parseInt(el.getElementsByTagName("asda").item(0).getTextContent()),
                        Integer.parseInt(el.getElementsByTagName("lda").item(0).getTextContent()),
                        Integer.parseInt(el.getElementsByTagName("threshold").item(0).getTextContent()),
                        Integer.parseInt(el.getElementsByTagName("stripEnd").item(0).getTextContent()),
                        Integer.parseInt(el.getElementsByTagName("clearway").item(0).getTextContent()),
                        Integer.parseInt(el.getElementsByTagName("stopway").item(0).getTextContent()),
                        Integer.parseInt(el.getElementsByTagName("resa").item(0).getTextContent()));
                runwaysInXML.add(runway);
            }
        }
        } catch (NumberFormatException e) {
            //this is for when some tags are empty
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Invalid XML file: Incomplete fields. Please choose a valid XML file or complete this one.");
            error.showAndWait();
        } catch (RuntimeException e) {
            //this is for when some tags are missing
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Invalid XML file: Missing tags. Please choose a valid XML file or complete this one.");
            error.showAndWait();
        }
        return runwaysInXML;
    }

    //exactly the same here as parseRunway but adapted to obstacles
    public ArrayList<Obstacle> parseObstacle(File file) throws ParserConfigurationException, SAXException, IOException, NumberFormatException, RuntimeException {
        ArrayList<Obstacle> obstaclesInXML = new ArrayList<Obstacle>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("obstacle");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element el = (Element) nNode;
                    obstacle = new Obstacle(String.valueOf(el.getElementsByTagName("name").item(0).getTextContent()),
                            Integer.parseInt(el.getElementsByTagName("height").item(0).getTextContent()),
                            Integer.parseInt(el.getElementsByTagName("distancefromcenter").item(0).getTextContent()),
                            Integer.parseInt(el.getElementsByTagName("distancefromthreshold").item(0).getTextContent()));
                    obstaclesInXML.add(obstacle);
                }
            }
        } catch (NumberFormatException e) {
            //this is for when some tags are empty
            Alert error = new Alert(Alert.AlertType.ERROR);
                error.setHeaderText("Invalid XML file: Incomplete fields. Please choose a valid XML file or complete this one.");
                error.showAndWait();
        } catch (RuntimeException e) {
            //this is for when some tags are missing
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("Invalid XML file: Missing tags. Please choose a valid XML file or complete this one.");
            error.showAndWait();
        }
        return obstaclesInXML;
    }
}
