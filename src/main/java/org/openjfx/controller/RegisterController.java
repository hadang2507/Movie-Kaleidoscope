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
import java.util.Locale;

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
        String confirmPass = this.confirmPasswordField.getText();

        if (first_name.equals("") || last_name.equals("") || username.equals("") || pass.equals("")){
            this.registerMessageLabel.setText("You have to fill all the information boxes");
            this.emptyTextField();
            return;
        }

        boolean lengthRule = pass.length() >= 6 && pass.length() < 20;
        boolean upperRule = !pass.equals(pass.toLowerCase());
        boolean charRule = pass.matches("(.*)[a-z](.*)");
        boolean numRule = pass.matches("(.*)[0-9](.*)");
        boolean nonAlphaRule = pass.matches("(.*)[^A-Za-z0-9](.*)");
        int ruleCount = (lengthRule? 1 : 0) + (upperRule? 1 : 0) + (charRule? 1 : 0) + (numRule? 1 : 0) + (nonAlphaRule? 1 : 0);

        if (ruleCount < 5){
            this.registerMessageLabel.setText("Incorrect Password Format!");
            this.emptyTextField();
            return;
        }

        if (!confirmPass.equals(pass)){
            this.registerMessageLabel.setText("Do not match with your password");
            this.emptyTextField();
            return;
        }

        User user = new User(username, pass, first_name, last_name);
        DatabaseController dbController = new DatabaseController();
        if (dbController.checkIfExistAccountFromTableUSERS(user)) {
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

