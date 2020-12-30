module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens org.openjfx to javafx.fxml, com.google.gson;
    opens org.openjfx.controller;
    exports org.openjfx;

}