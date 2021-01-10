package org.openjfx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button registerButton;

    @FXML
    private Button backToLoginButton;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label registerMessageLabel;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    public void initialize() {
        this.registerMessageLabel.setText("");
    }

    @FXML
    void backToLoginButtonOnMouseClicked(MouseEvent event) throws IOException {
        App.setRoot("login");
    }

    @FXML
    void registerButtonOnMouseClicked(MouseEvent event) throws IOException{
        // TODO
        // 1. get all data from text field
        // 2. check null value
        // 3. check retype
        // 4. check whether this username exists in db
        // 5. insert into table USERS
        // 6. redirect to login page App.setRoot("login");
        String first_name = this.firstNameTextField.getText();
        String last_name = this.lastNameTextField.getText();
        String username = this.userNameTextField.getText();
        String pass = this.passwordTextField.getText();
        String confirm_pass = this.confirmPasswordField.getText();

        if (first_name.equals("") || last_name.equals("") || username.equals("") || pass.equals("")){
            this.registerMessageLabel.setText("You have to fill all the information boxes");
            this.emptyTextField();
            return;
        }

        if (!confirm_pass.equals(pass)){
            this.registerMessageLabel.setText("Do not match with your password");
            this.emptyTextField();
            return;
        }

        User user = new User(username, pass, first_name, last_name);
        DatabaseController dbController = new DatabaseController();
        if (dbController.checkIfExistAccount(user)) {
            this.registerMessageLabel.setText("This username has already existed");
            this.emptyTextField();
            return;
        } else {
            dbController.insertUserToTableUSERS(user);
            App.setRoot("login");
        }
    }

    public void emptyTextField() {
        this.userNameTextField.setText("");
        this.passwordTextField.setText("");
        this.firstNameTextField.setText("");
        this.lastNameTextField.setText("");
        this.confirmPasswordField.setText("");
    }
}

