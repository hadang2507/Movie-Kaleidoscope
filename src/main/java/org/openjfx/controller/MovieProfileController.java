package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import org.openjfx.App;
import org.openjfx.management.APIRequest;
import org.openjfx.model.Genre;
import org.openjfx.model.Movie;

import java.io.IOException;
import java.util.List;

/**
 * A SPECIFIED MOVIE PROFILE PAGE
 * DETAILS SHOWN: MOVIE TITLE, DIRECTORS, GENRES, AVERAGE RATING, BRIEF SUMMARY, RATING BOX, 'ADD TO WISH LIST' BUTTON
 * @author Ngo Quoc Thai
 */
public class MovieProfileController {
    private Movie movie;

    @FXML
    private ImageView movieImage;

    @FXML
    private Label nameLabel;

    @FXML
    private Label directorLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label averageRateLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private ComboBox<String> rateComboBox;

    @FXML
    private Button backToHomeButton;

    @FXML
    private Button submitRateButton;

    @FXML
    private Button addToWishListButton;

    @FXML
    private Label notificationLabel;

    /**
     * FETCHING DATA FOR A PARTICULAR MOVIE AND DISPLAYING ON THE PAGE
     */
    @FXML
    public void initialize() {
        this.notificationLabel.setText("");

        // checking if user is at the search page.
        if (App.searchMovies != null) {
            this.movie = App.searchMovies.get(App.indexOfChosenSearchMovie);
        } else {
            this.movie = App.recommendedMovies.get(App.indexOfChosenMovie);
        }

        // fetching list of genres and ids
        List<Genre> genres = new APIRequest().getGenreAndId();

        this.nameLabel.setText(this.movie.getTitle());
        String genre = "";
        int countGenre = 0;
        if (this.movie.getGenres() != null) {
            for (Integer each: this.movie.getGenres()) {
                if(countGenre == (this.movie.getGenres().size()-1)){
                    genre += getGenreNameFromId(genres, each);
                }else {
                    genre += getGenreNameFromId(genres, each) + " / ";
                }
                countGenre++;
            }
        }
        Image image = new Image(this.movie.getPoster_path());
        this.movieImage.setImage(image);
        this.genreLabel.setText(genre);
        this.averageRateLabel.setText("Average Rate: " + this.movie.getVote_average() + "/10");
        this.descriptionLabel.setText(this.movie.getOverview());
        this.descriptionLabel.setMaxWidth(520);
        this.descriptionLabel.setWrapText(true);


        this.rateComboBox.getItems().add("1");
        this.rateComboBox.getItems().add("2");
        this.rateComboBox.getItems().add("3");
        this.rateComboBox.getItems().add("4");
        this.rateComboBox.getItems().add("5");
        this.rateComboBox.getItems().add("6");
        this.rateComboBox.getItems().add("7");
        this.rateComboBox.getItems().add("8");
        this.rateComboBox.getItems().add("9");
        this.rateComboBox.getItems().add("10");
        this.rateComboBox.setValue("0");
    }

    @FXML
    void backToHomeOnAction(ActionEvent event) throws IOException {
        App.setRoot("dashboard");
    }

    /**
     * A FUNCTION TO RATE THE MOVIE AND ADD THE RATING TO THE DATABASE
     */
    @FXML
    void submitRateOnAction(ActionEvent event) {
        String choice = this.rateComboBox.getSelectionModel().getSelectedItem();

        DatabaseController dbController = new DatabaseController();
        String movie_id = this.movie.getId();

        if (!dbController.checkIfMovieIsRatedFromTableUSERS_MOVIE(movie_id)) {
            dbController.insertRatedMovieFromTableUSERS_MOVIE(movie_id, choice);
            this.notificationLabel.setText("Rate movie successfully !");
        } else {
            dbController.updateRatedMovieFromTableUSERS_Movie(movie_id, choice);
            this.notificationLabel.setText("Update movie's rating successfully !");
        }
    }

    /**
     * A FUNCTION TO ADD A MOVIE TO WISH LIST
     */
    @FXML
    void addToWishListButtonOnAction(ActionEvent event) {
        DatabaseController dbController = new DatabaseController();
        String movie_id = this.movie.getId();

        if (!dbController.checkIfMovieIDIsAlreadyAddedFromTableUSERS_WISHLISTS(movie_id)) {
            dbController.insertMovieToTableUSERS_WISHLISTS(movie_id);
            this.notificationLabel.setText("Add movie to wishlist successfully !");
        }
    }

    private String getGenreNameFromId(List<Genre> list, Integer genreId) {
        for (Genre each: list) {
            if (each.getId().equals(String.valueOf(genreId))) {
                return each.getName();
            }
        }
        return null;
    }

}

