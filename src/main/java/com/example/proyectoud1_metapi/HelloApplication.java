package com.example.proyectoud1_metapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
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

        Image icon= new Image(getClass().getResource("/Icon/icon.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setTitle("proyecto-ud01-met_api");
        stage.setScene(scene);
        stage.show();
    }


    static void setRoot(String fxml) throws IOException {
        Parent root = FXMLLoader.load(HelloApplication.class.getResource(fxml + ".fxml"));
        stage.setOpacity(0);
        scene.setRoot(root);
        stage.setWidth(920);
        stage.setHeight(650);
        stage.centerOnScreen();
        stage.setOpacity(1);
    }


    public static void main(String[] args) {
        launch();
    }
}

