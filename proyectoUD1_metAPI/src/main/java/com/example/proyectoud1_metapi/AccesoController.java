package com.example.proyectoud1_metapi;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class AccesoController {

    // Campos del formulario
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;


    @FXML
    protected void validarCredenciales() throws IOException {
        //valores que metió el usuario
        String usernameAcceso = usernameField.getText();
        String passwordAcceso = passwordField.getText();

        //Cogemos los datos de acceso del fichero acceso.properties para compararlos
        Properties prop = new Properties();
        FileInputStream input = null;
        input = new FileInputStream("acceso.properties");
        prop.load(input);
        String username = prop.getProperty("user");
        String password = prop.getProperty("password");



        if (username.equals(usernameAcceso) && password.equals(passwordAcceso)) {
            HelloApplication.setRoot("hello-view");

        }else{
            messageLabel.setText("Usuario o contraseña incorrectos");

        }


    }



}
