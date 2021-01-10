package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.openjfx.App;
import org.openjfx.model.User;

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

    @FXML
    private Button forgetPasswordButton;

    public void initialize() {
        this.loginMessage.setText("");
    }

    @FXML
    void forgetPasswordButtonOnAction(ActionEvent event) {

    }

    @FXML
    void loginButtonOnAction(ActionEvent event) throws IOException{
        // 1. get input data
        // 2. check null values
        // 3. check if account exist in database, if not tell user to register
        // additional: forgot password
        String username = this.usernameTextField.getText();
        String pass = this.passwordTextField.getText();
        if (username.equals("") || pass.equals("")){
            this.loginMessage.setText("You forget to fill username/password");
            this.emptyTextField();
            return;
        }
        User user = new User(username, pass, " ", " ");
        DatabaseController dbController = new DatabaseController();
        if (dbController.checkIfExistAccount(user) && dbController.checkPassword(user) ){
            App.username = username;
            App.setRoot("dashboard");
        } else {
            this.loginMessage.setText("Incorrect username or password");
        }
    }

    @FXML
    void registerButtonOnAction(ActionEvent event) throws IOException {
        App.setRoot("register");
    }

    public void emptyTextField() {
        this.usernameTextField.setText("");
        this.passwordTextField.setText("");
    }
}
