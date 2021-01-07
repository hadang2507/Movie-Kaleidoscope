package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.openjfx.management.APIRequest;
import org.openjfx.model.Genre;

import java.util.HashMap;
import java.util.List;

public class InitializeController {

    private HashMap<String, String> genres;

    @FXML
    private Button actionButton;

    @FXML
    private Button adventureButton;

    @FXML
    private Button animationButton;

    @FXML
    private Button comedyButton;

    @FXML
    private Button crimeButton;

    @FXML
    private Button documentaryButton;

    @FXML
    private Button dramaButton;

    @FXML
    private Button familyButton;

    @FXML
    private Button fantasyButton;

    @FXML
    private Button historyButton;

    @FXML
    private Button horrorButton;

    @FXML
    private Button musicButton;

    @FXML
    private Button mysteryButton;

    @FXML
    private Button romanceButton;

    @FXML
    private Button scienceFictionButton;

    @FXML
    private Button tvMovieButton;

    @FXML
    private Button thrillerButton;

    @FXML
    private Button warButton;

    @FXML
    private Button westernButton;

    @FXML
    private Button finishButton;

    public void initialize() {
        // api = https://api.themoviedb.org/3/genre/movie/list?api_key=405b7ef5e944fb61f960538017e4d88b&language=en-US
        APIRequest apiRequest = new APIRequest();

        List<Genre> genreAndIDs = apiRequest.getGenreAndId();



    }

    @FXML
    void actionButtonOnAction(ActionEvent event) {

    }

    @FXML
    void adventureButtonOnAction(ActionEvent event) {

    }

    @FXML
    void animationButtonOnAction(ActionEvent event) {

    }

    @FXML
    void comedyButtonOnAction(ActionEvent event) {

    }

    @FXML
    void crimeButtonOnAction(ActionEvent event) {

    }

    @FXML
    void documentaryButtonOnAction(ActionEvent event) {

    }

    @FXML
    void dramaButtonOnAction(ActionEvent event) {

    }

    @FXML
    void familyButtonOnAction(ActionEvent event) {

    }

    @FXML
    void fantasyButtonOnAction(ActionEvent event) {

    }

    @FXML
    void finishButtonOnAction(ActionEvent event) {

    }

    @FXML
    void historyButtonOnAction(ActionEvent event) {

    }

    @FXML
    void horrorButtonOnAction(ActionEvent event) {

    }

    @FXML
    void musicButtonOnAction(ActionEvent event) {

    }

    @FXML
    void mysteryButtonOnAction(ActionEvent event) {

    }

    @FXML
    void romanceButtonOnAction(ActionEvent event) {

    }

    @FXML
    void scienceFictionButtonOnAction(ActionEvent event) {

    }

    @FXML
    void thrillerButtonOnAction(ActionEvent event) {

    }

    @FXML
    void tvMovieButtonOnAction(ActionEvent event) {

    }

    @FXML
    void warButtonOnAction(ActionEvent event) {

    }

    @FXML
    void westernButtonOnAction(ActionEvent event) {

    }

}
