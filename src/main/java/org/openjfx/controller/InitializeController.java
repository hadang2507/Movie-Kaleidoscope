package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.openjfx.App;
import org.openjfx.management.APIRequest;
import org.openjfx.model.Genre;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InitializeController {

    private HashMap<String, String> genres;
    private List<String> userChoices;

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

    @FXML
    private Label youChooseLabel;

    public void initialize() {
        this.userChoices = new ArrayList<>();

        // api = https://api.themoviedb.org/3/genre/movie/list?api_key=405b7ef5e944fb61f960538017e4d88b&language=en-US
        APIRequest apiRequest = new APIRequest();

        List<Genre> genreAndIDs = apiRequest.getGenreAndId();
        System.out.println(genreAndIDs);
        this.youChooseLabel.setText("You choose: None");
    }

    @FXML
    void actionButtonOnAction(ActionEvent event) {
        this.userChoices.add(this.actionButton.getText());
        this.returnChoices();
    }

    @FXML
    void adventureButtonOnAction(ActionEvent event) {
        this.addGenre(this.adventureButton.getText());
        this.returnChoices();
    }

    @FXML
    void animationButtonOnAction(ActionEvent event) {
        this.addGenre(this.animationButton.getText());
        this.returnChoices();
    }

    @FXML
    void comedyButtonOnAction(ActionEvent event) {
        this.addGenre(this.comedyButton.getText());
        this.returnChoices();
    }

    @FXML
    void crimeButtonOnAction(ActionEvent event) {
        this.addGenre(this.crimeButton.getText());
        this.returnChoices();
    }

    @FXML
    void documentaryButtonOnAction(ActionEvent event) {
        this.addGenre(this.documentaryButton.getText());
        this.returnChoices();
    }

    @FXML
    void dramaButtonOnAction(ActionEvent event) {
        this.addGenre(this.dramaButton.getText());
        this.returnChoices();
    }

    @FXML
    void familyButtonOnAction(ActionEvent event) {
        this.addGenre(this.familyButton.getText());
        this.returnChoices();
    }

    @FXML
    void fantasyButtonOnAction(ActionEvent event) {
        this.addGenre(this.fantasyButton.getText());
        this.returnChoices();
    }

    @FXML
    void finishButtonOnAction(ActionEvent event) throws IOException {
        String username = App.username;

        // call function that make query
        // TODO


        // move to dashboard
        App.setRoot("dashboard");
    }

    @FXML
    void historyButtonOnAction(ActionEvent event) {
        this.addGenre(this.historyButton.getText());
        this.returnChoices();
    }

    @FXML
    void horrorButtonOnAction(ActionEvent event) {
        this.addGenre(this.horrorButton.getText());
        this.returnChoices();
    }

    @FXML
    void musicButtonOnAction(ActionEvent event) {
        this.addGenre(this.musicButton.getText());
        this.returnChoices();
    }

    @FXML
    void mysteryButtonOnAction(ActionEvent event) {
        this.addGenre(this.mysteryButton.getText());
        this.returnChoices();
    }

    @FXML
    void romanceButtonOnAction(ActionEvent event) {
        this.addGenre(this.romanceButton.getText());
        this.returnChoices();
    }

    @FXML
    void scienceFictionButtonOnAction(ActionEvent event) {
        this.addGenre(this.scienceFictionButton.getText());
        this.returnChoices();
    }

    @FXML
    void thrillerButtonOnAction(ActionEvent event) {
        this.addGenre(this.thrillerButton.getText());
        this.returnChoices();
    }

    @FXML
    void tvMovieButtonOnAction(ActionEvent event) {
        this.addGenre(this.tvMovieButton.getText());
        this.returnChoices();
    }

    @FXML
    void warButtonOnAction(ActionEvent event) {
        this.addGenre(this.warButton.getText());
        this.returnChoices();
    }

    @FXML
    void westernButtonOnAction(ActionEvent event) {
        this.addGenre(this.westernButton.getText());
        this.returnChoices();
    }


    private void addGenre(String genre) {
        for (String each: this.userChoices) {
            if (each.equals(genre)) {
                this.userChoices.remove(each);
                return;
            }
        }
        this.userChoices.add(genre);
    }

    private void returnChoices() {
        String choices = "";
        for (String each: this.userChoices) {
            choices += each + " - ";
        }
        this.youChooseLabel.setText("You choose: " + choices);
    }
}
