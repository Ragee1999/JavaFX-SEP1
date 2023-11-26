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

    @FXML
    private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextfield;
    @FXML
    private PasswordField passwordPasswordField;

    public void loginButtonOnAction() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();

        if (!usernameTextfield.getText().isBlank() && !passwordPasswordField.getText().isBlank()) {
            if (validateLogin()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));  // relative path --> no directory needed here
                    Parent root = loader.load();
                    Scene scene = new Scene(root, 800, 550);
                    stage.setScene(scene);
                    stage.centerOnScreen();
            } else {
                loginMessageLabel.setText("Wrong, try again!");
            }
        } else {
            loginMessageLabel.setText("Enter name & password");
        }
    }
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