package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class BothRunwaysController {

    @FXML
    private Button returnButton;

    //Method allows switching from viewing both runways (new and old) to viewing the new runway with the results
    public void returnToResults(ActionEvent event) throws IOException {
        Parent obstacleParent = FXMLLoader.load(getClass().getResource("results.fxml"));
        Scene obstacleScene = new Scene(obstacleParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(obstacleScene);
        window.show();
    }
}
