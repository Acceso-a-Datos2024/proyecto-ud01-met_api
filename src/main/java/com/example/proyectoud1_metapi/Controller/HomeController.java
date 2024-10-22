package com.example.proyectoud1_metapi.Controller;

import com.example.proyectoud1_metapi.Model.*;
import com.example.proyectoud1_metapi.SaveSystem;
import com.fasterxml.jackson.core.type.TypeReference;
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

public class HomeController {

    FileChooser fileChooser= new FileChooser();
    static ArtPiece artPiece;


    @FXML
    private Label nombreObra;
    @FXML
    private ImageView imagenObra;

    @FXML
    private CheckBox obrasRecomendadasCheckBox;
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

    // Se ejecuta al cargar la vista y lleva a cabo las funciones básicas como cargar el comboBox o
    // sugerir cargas los datos de la última sesión
    public void initialize() {
        List<Departments> departamentos = getDepartmentsFromJson();

        // Agregar departamentos al ComboBox
        departamentosBusqueda.getItems().addAll(departamentos);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            artPiece = objectMapper.readValue(new File("src/main/resources/saves/last_sesion.json"), new TypeReference<ArtPiece>() {});

            Dialog dialog= new Dialog();
            dialog.setTitle("Welcome!");  // Set the title of the dialog
            dialog.setContentText("¿Quieres recuperar el estado de la última sesión?");  // Set the content text to display the message
            dialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.NO);
            dialog. showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES){
                    cargarDatos();
                }
                else{
                    artPiece=null;
                }
            });
        } catch (Exception e) {
            System.out.println("No se encontraron datos de sesiones anteriores");
        }
    }

    // Se ejecuta cuando el usuario cierra la página, permite guardar la consulta al usario
    public static void closing(){
        if (artPiece != null){
            System.out.println("Petición cerrar");
            Dialog dialog= new Dialog();
            dialog.setTitle("¡Espera!");  // Set the title of the dialog
            dialog.setContentText("¿Quieres guardar el estado de la sesión?");  // Set the content text to display the message
            dialog.getDialogPane().getButtonTypes().add(ButtonType.YES);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.NO);
            dialog. showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES){
                    SaveSystem.saveAsJson(new File("src/main/resources/saves/last_sesion.json"),artPiece);
                    System.out.println("Guardado estado");
                } });
        }
    }

    //Los departamentos los tenemos guardados en un json al ser algo que no se va a modificar. Esta función
    // coge esos datos para agregarlos al comboBox
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
        if (etiqueta.isEmpty()) {
            // Cambiar el campo de texto si esta vacío y no ejecurar la consulta al ser obligatorio
            etiquetaBusqueda.setStyle("-fx-border-color: #dd4444; -fx-border-width: 2px;  -fx-background-color:  #ff8484; -fx-text-fill: black;");
            nombreObra.setText("");
            anioObra.setText("");
            nombreAutor.setText("");
            medioObra.setText("");
            imagenObra.setImage(null);

            // Opcional: Mostrar un mensaje de error (puedes usar un Label o un Alert)
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Advertencia");
//            alert.setHeaderText(null);
//            alert.setContentText("El campo 'Etiqueta' es obligatorio");
//            alert.show();
//            System.out.println("El campo 'Etiqueta' es obligatorio.");

            return; // Salir del método para no realizar la búsqueda
        } else {
            // Limpiar el estilo si hay texto
            etiquetaBusqueda.setStyle("");
        }


        //Comprueba si se seleccionó o no un departamento o las obras recomendadas para mandar el valor escogido o null
        Departments departamentoSeleccionado = (Departments) departamentosBusqueda.getSelectionModel().getSelectedItem();
        Integer departmentId = null;
        if (departamentoSeleccionado != null) {
            departmentId = departamentoSeleccionado.getDepartmentId();
        }
        boolean isHighlight = obrasRecomendadasCheckBox.isSelected();

        artPiece = requester.getSearchArtPiece(etiqueta, departmentId, isHighlight);

        cargarDatos();
    }

    @FXML
    void buscarObraAleatoria(MouseEvent event) {
        //Quitamos el aviso de obra no encontrada que pueda haberse quedado de la anterior busqueda
        obraNotFound.setVisible(false);

        ApiRequester requester = new ApiRequester();
        artPiece= requester.getRandomArtPiece();
        cargarDatos();
    }

    // Vuelca los datos de una obra de arte en la vista, es una función a parte para usarlo a la hora de hacer
    // consultas o carga la última actividad
    public void cargarDatos(){
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
    // Función para exportar los datos a los diferentes ficheros requeridos, se activa con el icono en
    // la toolbar
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