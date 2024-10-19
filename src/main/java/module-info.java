module com.example.proyectoud1_metapi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.compiler;
    requires java.net.http;
    requires com.fasterxml.jackson.dataformat.xml;
    requires com.fasterxml.jackson.databind;

    opens com.example.proyectoud1_metapi to javafx.fxml;
    exports com.example.proyectoud1_metapi;
    exports com.example.proyectoud1_metapi.Model;
    exports org.example.Model;
}