package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.openjfx.App;
import org.openjfx.model.User;

import java.io.IOException;

public class ProfileController {
    private User user;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Button saveChangesButton;

    @FXML
    private Button backToDashboardButton;

    @FXML
    public void initialize() {
        DatabaseController dbController = new DatabaseController();
        this.user = dbController.getUserInfoFromTableUSERS(App.username);

        this.fillTextFields();

        this.usernameTextField.setEditable(false);
    }


    @FXML
    void backToDashboardButtonOnAction(ActionEvent event) throws IOException {
        App.setRoot("dashboard");
    }

    @FXML
    void saveChangesOnAction(ActionEvent event) {
        user.setPassword(this.passwordPasswordField.getText());
        user.setFirstName(this.firstNameTextField.getText());
        user.setLastName(this.lastNameTextField.getText());

        this.fillTextFields();
    }

    private void fillTextFields() {
        this.usernameTextField.setText(user.getUsername());
        this.passwordPasswordField.setText(user.getPassword());
        this.firstNameTextField.setText(user.getFirstName());
        this.lastNameTextField.setText(user.getLastName());
    }

}
