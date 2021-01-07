module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens org.openjfx to javafx.fxml, com.google.gson;
    opens org.openjfx.controller;
    opens org.openjfx.model to com.google.gson;
    exports org.openjfx;
    exports org.openjfx.model;

}