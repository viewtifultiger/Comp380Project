package comp380project;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void open_signin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SignIn.fxml")); 
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void open_signup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SignUp.fxml")); 
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    // FXML's controls
    @FXML
    private Button invalidLogin;
    @FXML
    private Button invalidSignup;
    //@FXML
    //private Button cancelButton;
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private TextField signUpTextField;
    @FXML
    private TextField signUpEmailTextField;
    @FXML
    private PasswordField signUpPasswordField;
    @FXML
    private PasswordField signUpRepeatPasswordField;
    
    //@FXML
    //protected void onCancelButtonClick() {
       // Stage stage = (Stage) cancelButton.getScene().getWindow();
       // stage.close();
    //}
    protected
    String successMessage = String.format("-fx-text-fill: black;");
    String errorMessage = String.format("-fx-text-fill: black;");
    String errorStyle = String.format("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5;");
    String successStyle = String.format("-fx-border-color: #A9A9A9; -fx-border-width: 2; -fx-border-radius: 5;");

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        if (loginTextField.getText().isBlank() || loginPasswordField.getText().isBlank()) {
            invalidLogin.setText("Login fields are required!");
            invalidLogin.setStyle(errorMessage);

            if (loginTextField.getText().isBlank()) {
                loginTextField.setStyle(errorStyle);
            } else if (loginPasswordField.getText().isBlank()) {
                loginPasswordField.setStyle(errorStyle);
            }
        } else {
            invalidLogin.setText("Login Successful!");
            invalidLogin.setStyle(successMessage);
            loginTextField.setStyle(successStyle);
            loginPasswordField.setStyle(successStyle);
            root = FXMLLoader.load(getClass().getClassLoader().getResource("StockSelection.fxml")); 
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void onSignUpButtonClick(ActionEvent event) throws IOException {
        if (signUpTextField.getText().isBlank() || signUpEmailTextField.getText().isBlank() || signUpPasswordField.getText().isBlank() || signUpRepeatPasswordField.getText().isBlank()) {
            invalidSignup.setText("Please fill in all fields!");
            invalidSignup.setStyle(errorMessage);

            if (signUpTextField.getText().isBlank()) {
                signUpTextField.setStyle(errorStyle);
            } else if (signUpEmailTextField.getText().isBlank()) {
                signUpEmailTextField.setStyle(errorStyle);
            } else if (signUpPasswordField.getText().isBlank()) {
                signUpPasswordField.setStyle(errorStyle);
            } else if (signUpRepeatPasswordField.getText().isBlank()) {
                signUpRepeatPasswordField.setStyle(errorStyle);
            }
        } else if (signUpRepeatPasswordField.getText().equals(signUpPasswordField.getText())) {
            invalidSignup.setText("You are set!");
            invalidSignup.setStyle(successMessage);
            signUpTextField.setStyle(successStyle);
            signUpEmailTextField.setStyle(successStyle);
            signUpPasswordField.setStyle(successStyle);
            signUpRepeatPasswordField.setStyle(successStyle);
            root = FXMLLoader.load(getClass().getClassLoader().getResource("StockSelection.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            invalidSignup.setText("Passwords don't match!");
            invalidSignup.setStyle(errorMessage);
            signUpPasswordField.setStyle(errorStyle);
            signUpRepeatPasswordField.setStyle(errorStyle);
        }
    }
}               