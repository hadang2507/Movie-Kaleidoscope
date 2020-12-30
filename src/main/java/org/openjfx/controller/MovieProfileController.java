package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MovieProfileController {
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
    public void initialize() {
        this.rateComboBox.getItems().add("1");
        this.rateComboBox.getItems().add("1.5");
        this.rateComboBox.getItems().add("2");
        this.rateComboBox.getItems().add("2.5");
        this.rateComboBox.getItems().add("3");
        this.rateComboBox.getItems().add("3.5");
        this.rateComboBox.getItems().add("4");
        this.rateComboBox.getItems().add("4.5");
        this.rateComboBox.getItems().add("5");
        this.rateComboBox.setValue("0");
    }

    @FXML
    void backToHomeOnAction(ActionEvent event) {
        System.out.println("hihi");
    }

    @FXML
    void submitRateOnAction(ActionEvent event) {
        String choice = this.rateComboBox.getSelectionModel().getSelectedItem();
        System.out.println(choice);
    }



}

