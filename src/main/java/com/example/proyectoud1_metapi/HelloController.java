package com.example.proyectoud1_metapi;

import com.example.proyectoud1_metapi.Model.ApiRequester;
import com.example.proyectoud1_metapi.Model.ArtPiece;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label nombreObra;


    @FXML
    private Label añoObra;


    @FXML
    private Label medioObra;

    @FXML
    private Label nombreAutor;


    @FXML
    private TextField etiquetaBusqueda;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void helloCambiar() throws IOException {
        HelloApplication.setRoot("bye-view");
    }

    @FXML
    protected void buscarObra() throws IOException {
        ApiRequester requester = new ApiRequester();
        ArtPiece artPiece = requester.getSearchArtPiece(etiquetaBusqueda.getText());
        if (artPiece != null) {
            nombreObra.setText(artPiece.getTitle());
            añoObra.setText(artPiece.getObjectDate());
            nombreAutor.setText(artPiece.getArtistDisplayName());
            medioObra.setText(artPiece.getMedium());
        }else {
            // Manejo de errores
            nombreObra.setText("No se encontró la obra.");
            añoObra.setText("");
            nombreAutor.setText("");
            medioObra.setText("");

        }

    }

}