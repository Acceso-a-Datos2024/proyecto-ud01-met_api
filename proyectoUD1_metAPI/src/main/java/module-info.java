module com.example.proyectoud1_metapi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.proyectoud1_metapi to javafx.fxml;
    exports com.example.proyectoud1_metapi;
}