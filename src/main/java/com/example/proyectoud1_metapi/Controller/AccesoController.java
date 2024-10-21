package com.example.proyectoud1_metapi.Controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.example.proyectoud1_metapi.HomeApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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

        //Cogemos los datos de acceso del fichero acceso.properties para compararlos
        Properties prop = new Properties();
        FileInputStream input = null;
        input = new FileInputStream("acceso.properties");
        prop.load(input);
        String username = prop.getProperty("user");
        String password = prop.getProperty("password");


        //valores que metió el usuario
        String usernameAcceso = usernameField.getText();
        String passwordAcceso = passwordField.getText();
        String passwordaAccesoHaseada=hashPassword(passwordAcceso);

        //Si coincide con las credenciales guardadas accede a la vista principal, si no muestra un mensaje de error
        if (username.equals(usernameAcceso) && password.equals(passwordaAccesoHaseada)) {
            HomeApplication.setRoot("hello-view");

        }else{
            messageLabel.setText("Usuario o contraseña incorrectos");
            //usernameField.setText(passwordaAccesoHaseada);

        }


    }

    // Para mayor seguridad la contraseña está guardada hasheada, por ello hay que hacer lo mismo con la que
    // introduce el usuario
    public static String hashPassword(String password) {
        try {
            MessageDigest  digest = MessageDigest.getInstance("SHA-256");

            // Hashear la contraseña
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // Agregar un cero si es necesario
                }
                hexString.append(hex);
            }

            // Retornar el hash como cadena hexadecimal
            return hexString.toString();


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }



}
