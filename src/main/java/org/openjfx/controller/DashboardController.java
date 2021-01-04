package org.openjfx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    private VBox cardsVbox = new VBox();

    @FXML
    public void initialize() throws IOException {
        this.headingLabel.setText("Welcome to DashBoard" + " " + this.usernameLabel.getText() + "!");
        Node[] nodes = new Node[10];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = (Node) FXMLLoader.load(App.class.getResource("card.fxml"));
            this.cardsVbox.getChildren().add(nodes[i]);
        }
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

}
