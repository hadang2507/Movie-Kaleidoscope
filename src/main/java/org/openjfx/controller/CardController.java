package org.openjfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CardController {

    @FXML
    private ImageView imageViewMovie;

    @FXML
    private Label nameMovieLabel;

    @FXML
    private Label detailLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    public void initialize(){
        this.nameMovieLabel.setText("Test");
        this.detailLabel.setText("test");
        this.descriptionLabel.setText("test");
        System.out.println(this.nameMovieLabel.getText() + " " + this.detailLabel.getText() + " " + this.descriptionLabel.getText());

    }
}
