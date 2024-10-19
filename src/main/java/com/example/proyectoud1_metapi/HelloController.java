package com.example.proyectoud1_metapi;

import com.example.proyectoud1_metapi.Model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    FileChooser fileChooser= new FileChooser();
    ArtPiece artPiece;


    @FXML
    private Label nombreObra;
    @FXML
    private ImageView imagenObra;

    @FXML
    private HBox layoutData;
    @FXML
    private Label anioObra;

    @FXML
    private ComboBox departamentosBusqueda;

    @FXML
    private Label medioObra;

    @FXML
    private Label nombreAutor;
    @FXML
    private Label obraNotFound;


    @FXML
    private TextField etiquetaBusqueda;


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
    protected void buscarObra() throws IOException {
        //Quitamos el aviso de obra no encontrada que pueda haberse quedado de la anterior busqueda
        obraNotFound.setVisible(false);

        ApiRequester requester = new ApiRequester();
        String etiqueta = etiquetaBusqueda.getText();


        Departments departamentoSeleccionado = (Departments) departamentosBusqueda.getSelectionModel().getSelectedItem();
        Integer departmentId = null;
        if (departamentoSeleccionado != null) {
            departmentId = departamentoSeleccionado.getDepartmentId();
        }
        artPiece = requester.getSearchArtPiece(etiqueta, departmentId);

        if (artPiece != null) {
            layoutData.setVisible(true);
            nombreObra.setText(artPiece.getTitle());
            anioObra.setText(artPiece.getObjectDate());
            if (artPiece.getArtistDisplayName().isEmpty() || artPiece.getArtistDisplayName()== null ){
                nombreAutor.setText("Artista desconocido");
            }
            else {
                nombreAutor.setText(artPiece.getArtistDisplayName());
            }
            medioObra.setText(artPiece.getMedium());

            String imageUrl = artPiece.getPrimaryImage(); // Obtener la URL de la imagen

            if (imageUrl != null && !imageUrl.trim().isEmpty()) {
                Image imagen = new Image(artPiece.getPrimaryImage());
                imagenObra.setImage(imagen);
            }
            else{
                imagenObra.setImage(new Image(getClass().getResource("/Icon/No-Image-Placeholder.png").toExternalForm())); // Limpiar la imagen
            }
        }else {
            // Manejo de errores
            obraNotFound.setVisible(true);
            nombreObra.setText("");
            anioObra.setText("");
            nombreAutor.setText("");
            medioObra.setText("");
            imagenObra.setImage(null);
        }

    }

    @FXML
    void exportData(MouseEvent event) {
        //Añadimos las extensiones disponibles
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".txt", "*.txt"),
                new FileChooser.ExtensionFilter(".json", "*.json"),
                new FileChooser.ExtensionFilter(".xml", "*.xml"),
                new FileChooser.ExtensionFilter(".bin", "*.bin")
        );

        if (artPiece != null) {
            fileChooser.setInitialFileName(artPiece.getTitle()+"_"+artPiece.getArtistDisplayName());
            File file = fileChooser.showSaveDialog(new Stage());
            if (file !=null){
                SaveSystem.save(file, artPiece);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("No hay datos que exportar");
            alert.show();
            System.out.println("No hay datos que guardar");

        }
    }


}