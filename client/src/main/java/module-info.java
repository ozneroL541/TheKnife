module it.uninsubria.client {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;

    opens it.uninsubria.client to javafx.fxml;
    exports it.uninsubria.client;
}