package com.a1.a1.presentation;

import com.a1.a1.controller.UserController;
import com.a1.a1.dto.UserDTO;
import com.a1.a1.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class RegisterUI {

    UserController userController = new UserController();
    @FXML
    private TextField checkPasswordTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    void createAccount(ActionEvent event) throws Exception {

        if(!passwordTextField.getText().equals(checkPasswordTextField.getText())){
            return;
        }

        UserModel newUser = userController.registerUser(new UserDTO(emailTextField.getText(), passwordTextField.getText()));
        if (newUser != null) {
            HelloApplication.setRoot("hello-view", 500, 500);
        }
    }

}
