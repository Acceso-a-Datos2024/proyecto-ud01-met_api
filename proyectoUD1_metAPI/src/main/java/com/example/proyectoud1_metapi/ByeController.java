package com.example.proyectoud1_metapi;

import javafx.fxml.FXML;
import java.io.IOException;

import javafx.scene.control.Label;

public class ByeController{


    @FXML
    protected void byeCambiar() throws IOException {
        HelloApplication.setRoot("hello-view");
    }


}
