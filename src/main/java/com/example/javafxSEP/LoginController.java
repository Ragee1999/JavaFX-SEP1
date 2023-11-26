package com.example.javafxSEP;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    // USERNAME: bob
    // PASSWORD: via

    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnAction() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow(); // Here we retrieve the stage related to the loginButton
        // So all login actions will happen in this login window

        if (!usernameTextfield.getText().isBlank() && !passwordPasswordField.getText().isBlank()) { // If both name and pass fields are not blank code will continue
            if (validateLogin()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));  // relative path --> no directory needed here
                    Parent root = loader.load(); // Retrieves the root UI Component through the FXML file
                    Scene scene = new Scene(root, 800, 550); // sets scene based on the width and height
                    stage.setScene(scene); // Sets the scene
                    stage.centerOnScreen(); // Centers the AppController to the login screen
            } else {
                loginMessageLabel.setText("Wrong, try again!");  // Wrong password and name
            }
        } else {
            loginMessageLabel.setText("Enter name & password"); // If user forgot to input message in either textFields
        }
    }


    // Simple login logic. If password and names match, validateLogin becomes true.
    public boolean validateLogin() {
        String username = usernameTextfield.getText();
        String password = passwordPasswordField.getText();
        if (username.equals("bob") && password.equals("via")){
            // Normally you would make the password and username secure by using a database and probably hashing.
            return true;
        } else{
            return false;
        }
    }
}