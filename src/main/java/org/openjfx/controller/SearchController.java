package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.openjfx.App;
import org.openjfx.management.APIRequest;

import java.io.IOException;

/*
THIS CLASS IS FOR SEARCHING MOVIES BY THEIR TITLE, GENRES, ACTORS/ACTRESSES,...
 */
public class SearchController {

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchTextField;

    @FXML
    private ImageView movie1Image;

    @FXML
    private Label movie1Name;

    @FXML
    private ImageView movie2Image;

    @FXML
    private Label movie2Name;

    @FXML
    private Button backToDashboardButton;

    @FXML
    private ImageView movie3Image;

    @FXML
    private Label movie3Name;

    @FXML
    private ImageView movie4Image;

    @FXML
    private Label movie4Name;

    @FXML
    private ImageView movie5Image;

    @FXML
    private Label movie5Name;

    @FXML
    private ImageView movie6Image;

    @FXML
    private Label movie6Name;

    @FXML
    void backToDashboardButtonOnAction(ActionEvent event) throws IOException {
        App.searchMovies = null;
        App.setRoot("dashboard");
    }

    @FXML
    void searchButtonOnAction(ActionEvent event) {
        String searchText = this.searchTextField.getText();

        if (!searchText.equals("")) {
            APIRequest apiRequest = new APIRequest();
            App.searchMovies = apiRequest.getSearchedMovies(searchText);
            App.indexOfChosenSearchMovie = 0;
            this.setBriefMovieOnSearchPage();
        }
    }

    @FXML
    void movie1OnMouseClicked(MouseEvent event) throws IOException{
        App.indexOfChosenSearchMovie = 0;
        App.setRoot("movie-profile");
    }

    @FXML
    void movie2OnMouseClicked(MouseEvent event) throws IOException{
        App.indexOfChosenSearchMovie = 1;
        App.setRoot("movie-profile");
    }

    @FXML
    void movie3OnMouseClicked(MouseEvent event) throws IOException{
        App.indexOfChosenSearchMovie = 2;
        App.setRoot("movie-profile");
    }

    @FXML
    void movie4OnMouseClicked(MouseEvent event) throws IOException{
        App.indexOfChosenSearchMovie = 3;
        App.setRoot("movie-profile");
    }

    @FXML
    void movie5OnMouseClicked(MouseEvent event) throws IOException{
        App.indexOfChosenSearchMovie = 4;
        App.setRoot("movie-profile");
    }

    @FXML
    void movie6OnMouseClicked(MouseEvent event) throws IOException{
        App.indexOfChosenSearchMovie = 5;
        App.setRoot("movie-profile");
    }

    private void setBriefMovieOnSearchPage() {
        this.movie1Name.setText(App.searchMovies.get(0).getTitle());
        Image image1 = new Image(App.searchMovies.get(0).getPoster_path());
        this.movie1Image.setImage(image1);

        this.movie2Name.setText(App.searchMovies.get(1).getTitle());
        Image image2 = new Image(App.searchMovies.get(1).getPoster_path());
        this.movie2Image.setImage(image2);

        this.movie3Name.setText(App.searchMovies.get(2).getTitle());
        Image image3 = new Image(App.searchMovies.get(2).getPoster_path());
        this.movie3Image.setImage(image3);

        this.movie4Name.setText(App.searchMovies.get(3).getTitle());
        Image image4 = new Image(App.searchMovies.get(3).getPoster_path());
        this.movie4Image.setImage(image4);

        this.movie5Name.setText(App.searchMovies.get(4).getTitle());
        Image image5 = new Image(App.searchMovies.get(4).getPoster_path());
        this.movie5Image.setImage(image5);

        this.movie6Name.setText(App.searchMovies.get(5).getTitle());
        Image image6 = new Image(App.searchMovies.get(5).getPoster_path());
        this.movie6Image.setImage(image6);
    }
}
