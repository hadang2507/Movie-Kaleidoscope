package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.openjfx.App;

import java.io.IOException;

public class SearchController {

    @FXML
    private Button searchButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button prevButton;

    @FXML
    private Button backToDashboardButton;

    @FXML
    void backToDashboardButtonOnAction(ActionEvent event) throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    void nextButtonOnAction(ActionEvent event) {

    }

    @FXML
    void prevButtonOnAction(ActionEvent event) {

    }

    @FXML
    void searchButtonOnAction(ActionEvent event) {

    }

}
