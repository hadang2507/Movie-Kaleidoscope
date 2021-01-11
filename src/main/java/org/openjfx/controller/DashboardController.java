package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.openjfx.App;

import java.io.IOException;

public class DashboardController{

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
    private Label movieLabel1;

    @FXML
    private Label genreLabel1;

    @FXML
    private Label voteLabel1;

    @FXML
    private Button moreButton1;

    @FXML
    private Label movieLabel2;

    @FXML
    private Label genreLabel2;

    @FXML
    private Label voteLabel2;

    @FXML
    private Button moreButton2;

    @FXML
    private Label movieLabel3;

    @FXML
    private Label genreLabel3;

    @FXML
    private Label voteLabel3;

    @FXML
    private Button moreButton3;

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

    @FXML
    private VBox cardsVbox = new VBox();

    @FXML
    public void initialize() throws IOException {
        
    }

    @FXML
    void profileSettingOnMouseClicked(MouseEvent event) throws IOException{
        App.setRoot("profile");
    }

    @FXML
    void ratedMoviesOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void recommendedMoviesOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void searchOnMouseClicked(MouseEvent event) throws IOException{
        App.setRoot("search");
    }

    @FXML
    void topRatedOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void usernameOnMouseClicked(MouseEvent event) throws IOException{
        App.setRoot("profile");
    }

    @FXML
    void wishListOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void moreButton1OnAction(ActionEvent event) {

    }

    @FXML
    void moreButton2OnAction(ActionEvent event) {

    }

    @FXML
    void moreButton3OnAction(ActionEvent event) {

    }

    @FXML
    void moreButton4OnAction(ActionEvent event) {

    }

    @FXML
    void nextButtonOnAction(ActionEvent event) {

    }

    @FXML
    void previousButtonOnAction(ActionEvent event) {

    }

}
