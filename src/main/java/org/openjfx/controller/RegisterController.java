package org.openjfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.openjfx.App;
import org.openjfx.model.User;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField userNameTextField;

    @FXML
    private TextField confirmPasswordTextField;

    @FXML
    private Button registerButton;

    @FXML
    private Button backToLoginButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    void backToLoginButtonOnMouseClicked(MouseEvent event) throws IOException {
        App.setRoot("login");
    }

    @FXML
    void registerButtonOnMouseClicked(MouseEvent event) {
        // TODO
        // 1. get all data from text field
        // 2. check null value
        // 3. check retype
        // 4. check whether this username exists in db
        // 5. insert into table USERS
        // 6. redirect to login page App.setRoot("login");
        String username = this.userNameTextField.getText();
        String pass = this.passwordTextField.getText();
        User user = new User();
    }


}

