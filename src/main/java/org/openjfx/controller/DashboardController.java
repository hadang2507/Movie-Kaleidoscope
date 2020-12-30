package org.openjfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DashboardController {

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
    public void initialize() {

    }

    @FXML
    void profileSettingOnMouseClicked(MouseEvent event) {
        System.out.println("hello");
    }

    @FXML
    void ratedMoviesOnMouseClicked(MouseEvent event) {
        System.out.println("hello");
    }

    @FXML
    void recommendedMoviesOnMouseClicked(MouseEvent event) {
        System.out.println("hello");
    }

    @FXML
    void searchOnMouseClicked(MouseEvent event) {
        System.out.println("hello");
    }

    @FXML
    void topRatedOnMouseClicked(MouseEvent event) {
        System.out.println("hello");
    }

    @FXML
    void usernameOnMouseClicked(MouseEvent event) {
        System.out.println("hello");
    }

    @FXML
    void wishListOnMouseClicked(MouseEvent event) {
        System.out.println("hello");
    }

}
