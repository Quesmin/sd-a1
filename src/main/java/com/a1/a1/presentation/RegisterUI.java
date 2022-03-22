package com.a1.a1.presentation;

import com.a1.a1.controller.UserController;
import com.a1.a1.dto.UserDTO;
import com.a1.a1.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterUI implements Initializable {

    UserController userController = new UserController();
    @FXML
    private TextField checkPasswordTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    void createAccount(ActionEvent event) {

        try {

            if (!passwordTextField.getText().equals(checkPasswordTextField.getText())) {
                errorMessageLabel.setVisible(true);
                return;
            }

            UserModel newUser = userController.registerUser(new UserDTO(emailTextField.getText(), passwordTextField.getText()));
            if (newUser != null) {
                HelloApplication.setRoot("login-view", 500, 500);
            }
        } catch (Exception e) {
            errorMessageLabel.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorMessageLabel.setVisible(false);
    }

}
