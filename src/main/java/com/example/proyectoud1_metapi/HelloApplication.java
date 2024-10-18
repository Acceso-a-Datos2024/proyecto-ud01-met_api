package com.example.proyectoud1_metapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Scene scene;
    private static Stage stage;  // Almacenar referencia al Stage principal


    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("acceso.fxml"));
        scene = new Scene(fxmlLoader.load(), 550, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    ///asgdjafs

    static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        scene.setRoot(loadFXML(fxml));
        stage.setWidth(640);
        stage.setHeight(480);
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}

