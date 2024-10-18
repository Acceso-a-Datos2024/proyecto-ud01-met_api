package com.example.proyectoud1_metapi;

import com.example.proyectoud1_metapi.Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label imagenTexto;

    @FXML
    private Label nombreObra;
    @FXML
    private ImageView imagenObra;


    @FXML
    private Label añoObra;

    @FXML
    private ComboBox departamentosBusqueda;

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


    public void initialize() {
        List<Departments> departamentos = getDepartmentsFromJson();

        // Agregar departamentos al ComboBox
        departamentosBusqueda.getItems().addAll(departamentos);

    }
    private List<Departments> getDepartmentsFromJson(){
        List<Departments> departments = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/departamentos.json");
            Response response = mapper.readValue(inputStream, Response.class);
            departments = response.getDepartments();

        } catch (Exception e) {
            System.out.println("Error al cargar departamentos: " + e.getMessage());
        }

        return departments;
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
            String imageUrl = artPiece.getPrimaryImage(); // Obtener la URL de la imagen
            if (imageUrl != null && !imageUrl.trim().isEmpty()) {
            Image imagen = new Image(artPiece.getPrimaryImage());
            imagenObra.setImage(imagen);}
            else{
                imagenObra.setImage(null); // Limpiar la imagen
                imagenTexto.setText("No hay imagen disponible."); // Mostrar texto alternativo
                imagenTexto.setVisible(true);
            }
        }else {
            // Manejo de errores
            nombreObra.setText("No se encontró la obra.");
            añoObra.setText("");
            nombreAutor.setText("");
            medioObra.setText("");

        }

    }

}