package org.openjfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.openjfx.App;
import org.openjfx.model.User;

import java.io.IOException;

/**
 * THIS CLASS IS FOR LOG IN PROCEDURE
 * REQUIRED TEXT FIELDS INCLUDING: USERNAME, PASSWORD
 * @author Nguyen Phan Yen Ngan
 */
public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label loginMessage;

    @FXML
    private PasswordField passwordField;

    public void initialize() {
        this.loginMessage.setText("");
    }

    @FXML
    void forgetPasswordButtonOnAction(ActionEvent event) {

    }

    /**
     * A FUNCTION TO LOG THE USER IN THE SYSTEM
     * CHECKING USERNAME, PASSWORD FORMAT
     */
    @FXML
    void loginButtonOnAction(ActionEvent event) throws IOException{
        // 1. get input data
        // 2. check null values
        // 3. check if account exist in database, if not tell user to register
        // additional: forgot password
        String username = this.usernameTextField.getText();
        String pass = this.passwordField.getText();

        if (username.equals("") || pass.equals("")){
            this.loginMessage.setText("You forget to fill username/password");
            this.emptyTextField();
            return;
        }

        User user = new User(username, pass, " ", " ");
        DatabaseController dbController = new DatabaseController();

        if (dbController.checkIfExistAccountFromTableUSERS(user) && dbController.getPasswordFromTableUSERS(user) ){
            App.username = username;

            if (dbController.getChosenGenreIDFromTableUSERS_GENRE(username).isEmpty()) {
                App.setRoot("initialization");
            } else {
                App.setRoot("dashboard");
            }
        } else {
            this.loginMessage.setText("Incorrect username or password");
        }
    }

    /**
     * DIRECT USER TO REGISTER PAGE
     */
    @FXML
    void registerButtonOnAction(ActionEvent event) throws IOException {
        App.setRoot("register");
    }

    /**
     * EMPTY ALL FILLED TEXT FIELDS
     */
    public void emptyTextField() {
        this.usernameTextField.setText("");
        this.passwordField.setText("");
    }
}
