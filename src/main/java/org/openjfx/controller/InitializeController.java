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
import java.util.List;

/**
 * THIS CLASS IS FOR THE INITIALIZATION
 * LIST ALL THE GENRES AND LET USER CHOOSES WHEN THEY FIRST REGISTER.
 * USER MUST CHOOSE AT LEAST 3 GENRES FOR PERSONALIZATION
 * @author Ngo Quoc Thai
 */
public class InitializeController {

    private List<Genre> genres;
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

    /**
     * INITIALIZE PAGE WHERE USER CHOOSE HIS/HER PREFERRED GENRES
     */
    public void initialize() {
        this.userChoices = new ArrayList<>();

        // api = https://api.themoviedb.org/3/genre/movie/list?api_key=405b7ef5e944fb61f960538017e4d88b&language=en-US
        APIRequest apiRequest = new APIRequest();

        this.genres = apiRequest.getGenreAndId();
        System.out.println(this.genres);
        this.youChooseLabel.setText("You choose: None");
    }

    @FXML
    void actionButtonOnAction(ActionEvent event) {
        this.userChoices.add(this.actionButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void adventureButtonOnAction(ActionEvent event) {
        this.addGenre(this.adventureButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void animationButtonOnAction(ActionEvent event) {
        this.addGenre(this.animationButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void comedyButtonOnAction(ActionEvent event) {
        this.addGenre(this.comedyButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void crimeButtonOnAction(ActionEvent event) {
        this.addGenre(this.crimeButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void documentaryButtonOnAction(ActionEvent event) {
        this.addGenre(this.documentaryButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void dramaButtonOnAction(ActionEvent event) {
        this.addGenre(this.dramaButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void familyButtonOnAction(ActionEvent event) {
        this.addGenre(this.familyButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void fantasyButtonOnAction(ActionEvent event) {
        this.addGenre(this.fantasyButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void historyButtonOnAction(ActionEvent event) {
        this.addGenre(this.historyButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void horrorButtonOnAction(ActionEvent event) {
        this.addGenre(this.horrorButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void musicButtonOnAction(ActionEvent event) {
        this.addGenre(this.musicButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void mysteryButtonOnAction(ActionEvent event) {
        this.addGenre(this.mysteryButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void romanceButtonOnAction(ActionEvent event) {
        this.addGenre(this.romanceButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void scienceFictionButtonOnAction(ActionEvent event) {
        this.addGenre(this.scienceFictionButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void thrillerButtonOnAction(ActionEvent event) {
        this.addGenre(this.thrillerButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void tvMovieButtonOnAction(ActionEvent event) {
        this.addGenre(this.tvMovieButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void warButtonOnAction(ActionEvent event) {
        this.addGenre(this.warButton.getText());
        this.returnChosenChoices();
    }

    @FXML
    void westernButtonOnAction(ActionEvent event) {
        this.addGenre(this.westernButton.getText());
        this.returnChosenChoices();
    }

    /**
     * GET CHOSEN GENRES FROM USER AND ADD TO DATABASE FOR INITIALIZING DASHBOARD PAGE
     */
    @FXML
    void finishButtonOnAction(ActionEvent event) throws IOException {
        String username = App.username;

        if (this.returnChosenGenres(this.userChoices).size() < 3) {
            this.youChooseLabel.setText("You must choose at least 3 genres");
            return;
        }

        DatabaseController dbController = new DatabaseController();
        dbController.insertGenreIDToTableUSERS_GENRE(this.returnChosenGenres(this.userChoices));

        // move to dashboard
        App.setRoot("dashboard");
    }

    /**
     * ADD CHOSEN GENRE TO TEMPORARY GENRE LIST
     */
    private void addGenre(String genre) {
        for (String each: this.userChoices) {
            if (each.equals(genre)) {
                this.userChoices.remove(each);
                return;
            }
        }
        this.userChoices.add(genre);
    }

    /**
     * DISPLAY CHOSEN GENRES ON THE PAGE
     */
    private void returnChosenChoices() {
        String choices = "";
        for (String each: this.userChoices) {
            choices += each + " - ";
        }
        this.youChooseLabel.setText("You choose: " + choices);
    }

    /**
     * A FUNCTION TO RETURN A LIST OF CHOSEN GENRES
     */
    private List<Genre> returnChosenGenres(List<String> choices) {
        List<Genre> results = new ArrayList<>();

        for (String each: this.userChoices) {

            results.add(new Genre(this.getGenreId(each), each));
        }
        return results;
    }

    /**
     * RETURN GENRE'S ID
     */
    private String getGenreId(String genre) {
        for (Genre each: this.genres) {
            if (each.getName().equals(genre)) {
                return each.getId();
            }
        }
        return "";
    }
}
