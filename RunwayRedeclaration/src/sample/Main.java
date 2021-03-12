package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("enter-runway.fxml"));
        primaryStage.setTitle("Runway Redeclaration Home Screen");
        primaryStage.setScene(new Scene(root, 900, 400));
        primaryStage.show();

    }

    public static void main(String[] args) {
            launch(args);



    }
}



