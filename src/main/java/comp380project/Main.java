package comp380project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Main extends Application { //Main would change to StockPrediction

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException { 
        //to create new stage: Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        Scene scene = new Scene(root); 
        String css = this.getClass().getResource("/main.css").toExternalForm();
        scene.getStylesheets().add(css);
       

        stage.setTitle("Stock Price Prediction System"); //change name
        stage.setResizable(false);
        //makes the screen full stage: stage.setFullScreen(true);

        stage.setScene(scene);
        stage.show();
    }

}