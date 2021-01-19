package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.openjfx.App;
import org.openjfx.management.APIRequest;
import org.w3c.dom.Text;

import java.io.IOException;

public class SearchController {

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

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
        App.searchText = searchTextField.getText();
        System.out.println(App.searchText);
        APIRequest apiRequest = new APIRequest();
        apiRequest.getSearchedMovies();
    }

}
