package com.example.proyectoud1_metapi;

import com.example.proyectoud1_metapi.Controller.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeApplication extends Application {

    private static Scene scene;
    private static Stage stage;  // Almacenar referencia al Stage principal


    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("acceso.fxml"));
        scene = new Scene(fxmlLoader.load(), 350, 240);

        Image icon= new Image(getClass().getResource("/Icon/icon.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setTitle("Museo metropolitano");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            HomeController.closing();

        });
        stage.show();
    }

    //Función para cambiar de vista, además reconfigura el tamaño al ser usada solo para pasar del login a la home
    public static void setRoot(String fxml) throws IOException {
        Parent root = FXMLLoader.load(HomeApplication.class.getResource(fxml + ".fxml"));
        stage.setOpacity(0);
        scene.setRoot(root);
        stage.setWidth(700);
        stage.setHeight(650);
        stage.centerOnScreen();
        stage.setOpacity(1);
    }

    public static void main(String[] args) {
        launch();
    }
}

