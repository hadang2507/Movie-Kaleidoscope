package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.openjfx.App;
import org.openjfx.management.APIRequest;
import org.openjfx.model.Genre;
import org.openjfx.model.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * THIS CLASS IS FOR ANYTHING CONCERNING THE DASHBOARD DISPLAY, EXCLUDED THE MOVIE PROFILE PAGE
 * @author Dang Ngan Ha, Ngo Quoc Thai
 */
public class DashboardController{
    private int count;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label recommendedMoviesLabel;

    @FXML
    private Label topRatedLabel;

    @FXML
    private Label wishListLabel;

    @FXML
    private Label ratedMoviesLabel;

    @FXML
    private Label searchLabel;

    @FXML
    private Label profileSettingLabel;

    @FXML
    private Label headingLabel;

    @FXML
    private Label logOutLabel;

    @FXML
    private ImageView imageView1;

    @FXML
    private Label movieLabel1;

    @FXML
    private Label genreLabel1;

    @FXML
    private Label voteLabel1;

    @FXML
    private Button moreButton1;

    @FXML
    private ImageView imageView2;

    @FXML
    private Label movieLabel2;

    @FXML
    private Label genreLabel2;

    @FXML
    private Label voteLabel2;

    @FXML
    private Button moreButton2;

    @FXML
    private ImageView imageView3;

    @FXML
    private Label movieLabel3;

    @FXML
    private Label genreLabel3;

    @FXML
    private Label voteLabel3;

    @FXML
    private Button moreButton3;

    @FXML
    private ImageView imageView4;

    @FXML
    private Label movieLabel4;

    @FXML
    private Label genreLabel4;

    @FXML
    private Label voteLabel4;

    @FXML
    private Button moreButton4;

    @FXML
    private Button nextButton;

    @FXML
    private Button previousButton;

    /**
     FUNCTION TO INITIALIZE USER'S DATA
     GET PERSONALIZED MOVIES FROM USER'S DATA: WISH LIST, RATED MOVIES
     */
    @FXML
    public void initialize() throws IOException {
        this.count = 0;
        App.indexOfChosenMovie = 0;

        this.usernameLabel.setText(App.username);
        this.headingLabel.setText("Homepage");

        DatabaseController dbController = new DatabaseController();
        APIRequest apiRequest = new APIRequest();

        // check if user has any rated movies ?
        if (dbController.checkIfUserHasRatedAnyMovie()) {
            String movieId = dbController.getRandomRatedMovieIdFromTableUSERS_MOVIE();
            App.recommendedMovies = apiRequest.getRecommendedMoviesByMovieId(movieId);
        }

        // if user has not rated any movies and from initialization layout
        if (App.recommendedMovies == null) {
            if (!dbController.getChosenGenreIDFromTableUSERS_GENRE(App.username).isEmpty()) {
                App.recommendedMovies = apiRequest.getMoviesFromGenres();
            }
        }

        this.setMovieBriefDetailsOnDashBoard();
    }
    /**
     * LOG OUT FUNCTION
     */
    @FXML
    void logOutOnMouseClicked(MouseEvent event) throws IOException{
        App.resetGlobalVariables();
        App.setRoot("login");
    }

    /**
     * MOVE TO USER'S PROFILE PAGE
     */
    @FXML
    void profileSettingOnMouseClicked(MouseEvent event) throws IOException{
        App.setRoot("profile");
    }

    /**
     * DISPLAY MOVIES FROM USER'S RATED LIST
     * IF USER HAS NOT RATED ANY MOVIE, A WARNING TEXT WILL BE POP UP
     */
    @FXML
    void ratedMoviesOnMouseClicked(MouseEvent event) {
        DatabaseController dbController = new DatabaseController();
        List<String> movie_ids = dbController.getMovieIDsFromUSERS_MOVIE();
        List<Movie> moviesList = new ArrayList<>();

        if (movie_ids.isEmpty()) {
            this.headingLabel.setText("Please rate at least one movie");
            this.setEmptyMovieBriefDetailsOnDashBoard();
        } else {
            this.setEmptyMovieBriefDetailsOnDashBoard();
            APIRequest apiRequest = new APIRequest();
            for (String id: movie_ids) {
                moviesList.add(apiRequest.getMovieByMovieId(id));
            }
            App.recommendedMovies = moviesList;
            this.count = 0;
            App.indexOfChosenMovie = 0;
            this.setMovieBriefDetailsOnDashBoard();
        }
    }

    /**
     * DIRECT TO SEARCH PAGE
     */
    @FXML
    void searchOnMouseClicked(MouseEvent event) throws IOException{
        App.setRoot("search");
    }

    /**
     * GET THE CURRENTLY POPULAR MOVIES AROUND THE WORLD FROM THE MOVIE DB AND DISPLAY THEM ON THE SCREEN
     */
    @FXML
    void topRatedOnMouseClicked(MouseEvent event) {
        this.count = 0;
        App.indexOfChosenMovie = 0;
        APIRequest apiRequest = new APIRequest();
        App.recommendedMovies = apiRequest.getTopRatedMovies();
        this.setMovieBriefDetailsOnDashBoard();
    }

    /**
     * DIRECT TO USER'S PROFILE PAGE
     */
    @FXML
    void usernameOnMouseClicked(MouseEvent event) throws IOException{
        App.setRoot("profile");
    }

    /**
     * DISPLAY THE MOVIES WHICH USER ADDED TO THEIR WISH LIST
     */
    @FXML
    void wishListOnMouseClicked(MouseEvent event) {
        DatabaseController dbController = new DatabaseController();
        APIRequest apiRequest = new APIRequest();
        this.count = 0;
        App.indexOfChosenMovie = 0;
        App.recommendedMovies = new ArrayList<>();
        List<String> movieIds = dbController.getMovieIDFromTableUSERS_WISHLISTS();
        for (int i = 0; i < movieIds.size(); i++) {
            App.recommendedMovies.add(apiRequest.getMovieByMovieId(movieIds.get(i)));
            System.out.println(App.recommendedMovies.get(i).getTitle());
        }
        this.setMovieBriefDetailsOnDashBoard();


    }

    @FXML
    void moreButton1OnAction(ActionEvent event) throws IOException{
        App.indexOfChosenMovie += this.count;
        App.setRoot("movie-profile");
    }

    @FXML
    void moreButton2OnAction(ActionEvent event) throws IOException{
        App.indexOfChosenMovie += this.count + 1;
        App.setRoot("movie-profile");
    }

    @FXML
    void moreButton3OnAction(ActionEvent event) throws IOException{
        App.indexOfChosenMovie += this.count + 2;
        App.setRoot("movie-profile");
    }

    @FXML
    void moreButton4OnAction(ActionEvent event) throws IOException{
        App.indexOfChosenMovie += this.count + 3;
        App.setRoot("movie-profile");
    }

    @FXML
    void nextButtonOnAction(ActionEvent event) {
        if (count + 4 < App.recommendedMovies.size()) {
            this.count += 4;
            this.setMovieBriefDetailsOnDashBoard();
        }
    }

    @FXML
    void previousButtonOnAction(ActionEvent event) {
        if (this.count - 4 >= 0) {
            this.count -= 4;
            this.setMovieBriefDetailsOnDashBoard();
        }
    }
    /*
    DISPLAY MOVIES IN SMALL SIZE ON THE DASHBOARD
    EACH PAGE CONTAINS 4 MOVIES
    ONLY BRIEF MOVIE DETAILS: TITLE, GENRES, THE AVERAGE RATING FROM THE AUDIENCE
     */
    private void setMovieBriefDetailsOnDashBoard() {
        List<Genre> genres = new APIRequest().getGenreAndId();
        this.movieLabel1.setText(App.recommendedMovies.get(count).getTitle());
        String genres1 = "";
        int i1 = 0;
        if (App.recommendedMovies.get(count).getGenres() != null) {
            for (Integer each: App.recommendedMovies.get(count).getGenres()) {
                if (i1++ == App.recommendedMovies.get(count).getGenres().size() - 1){
                    genres1 += getGenreNameFromId(genres, each);
                } else {
                    genres1 += getGenreNameFromId(genres, each) + " / ";
                }
            }
        }

        this.genreLabel1.setText(genres1);
        this.voteLabel1.setText(App.recommendedMovies.get(count).getVote_average() + "/10");
        Image image1 = new Image(App.recommendedMovies.get(count).getPoster_path());
        this.imageView1.setImage(image1);
        this.moreButton1.setVisible(true);


        if (App.recommendedMovies.size() % 2 == 0) {
            this.movieLabel2.setText(App.recommendedMovies.get(count + 1).getTitle());
            String genres2 = "";
            int i2 = 0;
            if (App.recommendedMovies.get(count + 1).getGenres() != null) {
                for (Integer each: App.recommendedMovies.get(count + 1).getGenres()) {
                    if(i2++ == App.recommendedMovies.get(count + 1).getGenres().size() - 1){
                        genres2 += getGenreNameFromId(genres, each);
                    }else{
                        genres2 += getGenreNameFromId(genres, each) + " / ";
                    }
                }
            }

            this.genreLabel2.setText(genres2);
            this.voteLabel2.setText(App.recommendedMovies.get(count + 1).getVote_average() + "/10");
            Image image2 = new Image(App.recommendedMovies.get(count + 1).getPoster_path());
            this.imageView2.setImage(image2);
            this.moreButton2.setVisible(true);

        }

        if (App.recommendedMovies.size() == 3 || App.recommendedMovies.size() >= 3) {
            this.movieLabel3.setText(App.recommendedMovies.get(count + 2).getTitle());
            String genres3 = "";
            int i3 = 0;

            if (App.recommendedMovies.get(count + 2).getGenres() != null) {
                for (Integer each: App.recommendedMovies.get(count + 2).getGenres()) {
                    if(i3++ == App.recommendedMovies.get(count + 2).getGenres().size() - 1){
                        genres3 += getGenreNameFromId(genres, each);
                    }else{
                        genres3 += getGenreNameFromId(genres, each) + " / ";
                    }
                }
            }
            this.genreLabel3.setText(genres3);
            this.voteLabel3.setText(App.recommendedMovies.get(count + 2).getVote_average() + "/10");
            Image image3 = new Image(App.recommendedMovies.get(count + 2).getPoster_path());
            this.imageView3.setImage(image3);
            this.moreButton3.setVisible(true);

        }

        if (App.recommendedMovies.size() == 4 || App.recommendedMovies.size() >= 4) {
            this.movieLabel4.setText(App.recommendedMovies.get(count + 3).getTitle());
            String genres4 = "";
            int i4 = 0;
            if (App.recommendedMovies.get(count + 3).getGenres() != null) {
                for (Integer each: App.recommendedMovies.get(count + 3).getGenres()) {
                    if(i4++ == App.recommendedMovies.get(count + 3).getGenres().size() - 1){
                        genres4 += getGenreNameFromId(genres, each);
                    }else{
                        genres4 += getGenreNameFromId(genres, each) + " / ";
                    }
                }
            }

            this.genreLabel4.setText(genres4);
            this.voteLabel4.setText(App.recommendedMovies.get(count + 3).getVote_average() + "/10");
            Image image4 = new Image(App.recommendedMovies.get(count + 3).getPoster_path());
            this.imageView4.setImage(image4);
            this.moreButton4.setVisible(true);

        }
    }
    /*
    FUNCTION TO SET THE EMPTY POSITIONS ON THE DASHBOARD IN WHICH NO MOVIE IS DISPLAYED
     */
    private void setEmptyMovieBriefDetailsOnDashBoard() {
        this.movieLabel1.setText("");
        this.genreLabel1.setText("");
        this.voteLabel1.setText("");
        this.imageView1.setImage(null);

        this.movieLabel2.setText("");
        this.genreLabel2.setText("");
        this.voteLabel2.setText("");
        this.imageView2.setImage(null);

        this.movieLabel3.setText("");
        this.genreLabel3.setText("");
        this.voteLabel3.setText("");
        this.imageView3.setImage(null);

        this.movieLabel4.setText("");
        this.genreLabel4.setText("");
        this.voteLabel4.setText("");
        this.imageView4.setImage(null);

        // hide all the button
        this.moreButton1.setVisible(false);
        this.moreButton2.setVisible(false);
        this.moreButton3.setVisible(false);
        this.moreButton4.setVisible(false);
    }

    /*
    RETURN THE MOVIE GENRES NAME FROM THE ID RETRIEVED FROM THE MOVIE DB
     */
    private String getGenreNameFromId(List<Genre> list, Integer genreId) {
        for (Genre each: list) {
            if (each.getId().equals(String.valueOf(genreId))) {
                return each.getName();
            }
        }
        return null;
    }
}
