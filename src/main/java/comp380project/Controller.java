package comp380project;

import comp380project.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

//code to open up login and signup pages in FXML
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

    public void setWelcomeMessage(String message) {
        if (nameLabel != null) {
            nameLabel.setText(message);
        }
    }

//code for signup and login pages
    @FXML
    private TextField loginTextField;
    @FXML
    private PasswordField loginPasswordField;
    @FXML
    private Button invalidLogin;
    @FXML
    private TextField signUpTextField;
    @FXML
    private TextField signUpEmailTextField;
    @FXML
    private PasswordField signUpPasswordField;
    @FXML
    private PasswordField signUpRepeatPasswordField;
    @FXML
    private Button invalidSignup;
    @FXML 
    private Button onBackButtonClick;
    @FXML
    private Label nameLabel;

    private static final String ERROR_MESSAGE_STYLE = "-fx-text-fill: red;";
    private static final String SUCCESS_MESSAGE_STYLE = "-fx-text-fill: green;";
    private static final String ERROR_STYLE = "-fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 5;";
    private static final String SUCCESS_STYLE = "-fx-border-color: green; -fx-border-width: 2; -fx-border-radius: 5;";

    @FXML
    protected void onLoginButtonClick(ActionEvent event) throws IOException {
        String email = loginTextField.getText();
        String password = loginPasswordField.getText();

        if (email.isBlank() || password.isBlank()) {
            invalidLogin.setText("Login fields are required!");
            invalidLogin.setStyle(ERROR_MESSAGE_STYLE);

            if (email.isBlank()) {
                loginTextField.setStyle(ERROR_STYLE);
            } else {
                loginTextField.setStyle(SUCCESS_STYLE);
            }

            if (password.isBlank()) {
                loginPasswordField.setStyle(ERROR_STYLE);
            } else {
                loginPasswordField.setStyle(SUCCESS_STYLE);
            }
        } else if (Database.checkCredentials(email, password)) {
            invalidLogin.setText("Login Successful!");
            invalidLogin.setStyle(SUCCESS_MESSAGE_STYLE);
            loginTextField.setStyle(SUCCESS_STYLE);
            loginPasswordField.setStyle(SUCCESS_STYLE);

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Homepage.fxml"));
            Parent root = loader.load();

            // Get the label and set the welcome message
            Controller controller = loader.getController();
            controller.setWelcomeMessage("Welcome back, " + email + "!");

            //Needed to load a new scene
           // Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Homepage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            invalidLogin.setText("Invalid credentials!");
            invalidLogin.setStyle(ERROR_MESSAGE_STYLE);
            loginTextField.setStyle(ERROR_STYLE);
            loginPasswordField.setStyle(ERROR_STYLE);
        }
    }

    @FXML
    protected void onSignUpButtonClick(ActionEvent event) throws IOException {
        String username = signUpTextField.getText();
        String email = signUpEmailTextField.getText();
        String password = signUpPasswordField.getText();
        String confirmPassword = signUpRepeatPasswordField.getText();

        if (username.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            invalidSignup.setText("Please fill in all fields!");
            invalidSignup.setStyle(ERROR_MESSAGE_STYLE);

            if (username.isBlank()) {
                signUpTextField.setStyle(ERROR_STYLE);
            } else {
                signUpTextField.setStyle(SUCCESS_STYLE);
            }

            if (email.isBlank()) {
                signUpEmailTextField.setStyle(ERROR_STYLE);
            } else {
                signUpEmailTextField.setStyle(SUCCESS_STYLE);
            }

            if (password.isBlank()) {
                signUpPasswordField.setStyle(ERROR_STYLE);
            } else {
                signUpPasswordField.setStyle(SUCCESS_STYLE);
            }

            if (confirmPassword.isBlank()) {
                signUpRepeatPasswordField.setStyle(ERROR_STYLE);
            } else {
                signUpRepeatPasswordField.setStyle(SUCCESS_STYLE);
            }
        } else if (password.equals(confirmPassword)) {
            if (Database.createAccount(email, password)) {
                invalidSignup.setText("Account created successfully!");
                invalidSignup.setStyle(SUCCESS_MESSAGE_STYLE);
                signUpTextField.setStyle(SUCCESS_STYLE);
                signUpEmailTextField.setStyle(SUCCESS_STYLE);
                signUpPasswordField.setStyle(SUCCESS_STYLE);
                signUpRepeatPasswordField.setStyle(SUCCESS_STYLE);

                // Load new scene
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SignIn.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                invalidSignup.setText("Account already exists!");
                invalidSignup.setStyle(ERROR_MESSAGE_STYLE);
                signUpEmailTextField.setStyle(ERROR_STYLE);
            }
        } else {
            invalidSignup.setText("Passwords don't match!");
            invalidSignup.setStyle(ERROR_MESSAGE_STYLE);
            signUpPasswordField.setStyle(ERROR_STYLE);
            signUpRepeatPasswordField.setStyle(ERROR_STYLE); }
        }
        @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        // Load the main page
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
        // Get the current stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Set the new scene and show it
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    Button predictor;
    @FXML
    Button news;

    @FXML
    public void onPredictorButtonClick(ActionEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("StockPredictorpage.fxml"));
     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
     Scene scene = new Scene(root);
     stage.setScene(scene);
     stage.show();   
    }
    
    @FXML
    public void onNewsButtonClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Stocknewspage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   
    }

    @FXML
    private Button logoutButton;
    @FXML
    private void handleLogoutAction() {
        // Handle the logout action
        System.out.println("Logging out...");

        // Close the current window
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }   

    @FXML
    private TextField stockNameField;
    @FXML
    private Stock stock;

    @FXML
    public void predict() {
        String ticker = stockNameField.getText();
        if (ticker != null && !ticker.isEmpty()) {
            stock = new Stock(ticker);
            stock.predict();
        }
    }


}
