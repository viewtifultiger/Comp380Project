package comp380project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        Scene scene = new Scene(root,Color.WHITE); //screen color

        stage.setTitle("Stock Price Prediction System"); //change name
        //stage.setWidth(500); //changes size of screen
        //stage.setHeight(500); //changes size of screen
        stage.setResizable(false);
        //changes the location of the screen: stage.setX(); stage.setY();
        //makes the screen full stage: stage.setFullScreen(true);

        Text text = new Text();
        text.setText("HELLO");
        text.setX(50); //changes the location of text
        text.setY(50); //changes the location of text
        text.setFont(Font.font("Times New Roman", 60)); //text font & size
        text.setFill(Color.BLACK); //text color

        

        //root.getChildren().add(text);
        stage.setScene(scene);
        stage.show();
    }

}