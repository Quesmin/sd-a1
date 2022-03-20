package com.a1.a1.presentation;

import com.a1.a1.controller.UserController;
import com.a1.a1.dto.UserDTO;
import com.a1.a1.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginUI {

    private final UserController userController = new UserController();
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    void login(ActionEvent event) throws Exception {
        UserDTO userDTO = new UserDTO(emailTextField.getText(), passwordTextField.getText());
        UserModel user = userController.loginUser(userDTO);
        if (user == null) {
            return;
        }
        userDTO.setAgency(user.getAgencyByAgencyId());
        UserUI.agency = userController.getAssociatedAgency(userDTO);
        UserUI.user = user;

        if(UserUI.agency == null){
            HelloApplication.setRoot("user-view", 1100, 700);
        } else {
            HelloApplication.setRoot("agency", 1100, 700);
        }

    }

    @FXML
    void switchToNewAccountScreen(ActionEvent event) throws IOException {
        HelloApplication.setRoot("new-account", 500, 500);
    }


}
