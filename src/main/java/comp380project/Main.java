package comp380project;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException { 
        //to create new stage: Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root,Color.BLACK); //change color

        stage.setTitle("Stock Price Prediction System"); //change name
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setResizable(false);
        //stage.setX();
        //stage.setY();
        //stage.setFullScreen(true);

        stage.show();
    }

}