package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.openjfx.App;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label loginMessage;

    public void initialize() {
        this.loginMessage.setText("");

    }

    @FXML
    void loginButtonOnAction(ActionEvent event) {

    }

    @FXML
    void registerButtonOnAction(ActionEvent event) throws IOException {
        App.setRoot("register");
    }

}
