package com.example.javafxSEP;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application { // Main is a subclass(inherits from parent) of application, and that is a class from the framework of JavaFX

    @Override
    public void start(Stage stage) throws IOException {  // An input and output error handler, that is part of the JavaFX extended Application
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml")); //Loads the login.fxml and the path is relative(inside resource) otherwise you need directory path.
        Scene scene = new Scene(fxmlLoader.load(), 249, 309); // This creates the scene from the FXML as well as adding the size
        stage.setTitle("LOGIN");
        stage.setScene(scene); // sets the scene
        stage.show(); // This is how we can see the scene, the application or runtime makes it visible.
    }

    public static void main(String[] args) { // Launch application
        launch();
    }
}