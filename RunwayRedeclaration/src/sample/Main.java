package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("Input.fxml"));
        primaryStage.setTitle("Runway Redeclaration Home Screen");
        primaryStage.setScene(new Scene(root, 1080, 802));
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
            launch(args);
    }
}



