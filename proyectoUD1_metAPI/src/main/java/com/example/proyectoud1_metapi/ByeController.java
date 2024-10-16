package com.example.proyectoud1_metapi;

import com.example.proyectoud1_metapi.Model.ApiRequester;
import com.example.proyectoud1_metapi.Model.ArtPiece;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ByeController{
    @FXML
    private ImageView imageView;
    
    @FXML
    private Label welcomeText;

    @FXML
    protected void byeCambiar() throws IOException {
        HelloApplication.setRoot("hello-view");
    }
    
    @FXML
    protected void randomImg() {
        ApiRequester requester = new ApiRequester();
        ArtPiece artPiece = requester.getRandomArtPiece();
        Image image = new Image(artPiece.getPrimaryImage());
        imageView.setImage(image);
        welcomeText.setText(artPiece.getTitle());
    }


}
