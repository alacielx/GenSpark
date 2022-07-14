module com.example.humansvsgoblinsjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.humansvsgoblinsjavafx to javafx.fxml;
    exports com.example.humansvsgoblinsjavafx;
}