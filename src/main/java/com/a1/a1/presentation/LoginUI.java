package com.a1.a1.presentation;

import com.a1.a1.controller.UserController;
import com.a1.a1.dto.UserDTO;
import com.a1.a1.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class LoginUI implements Initializable {

    private final UserController userController = new UserController();
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    void login() {
        try{
            UserDTO userDTO = new UserDTO(emailTextField.getText(), passwordTextField.getText());
            UserModel user = userController.loginUser(userDTO);
            if (user == null) {
                return;
            }
            userDTO.setAgency(user.getAgencyByAgencyId());
            UserUI.agency = userController.getAssociatedAgency(userDTO);
            UserUI.user = user;

            if(UserUI.agency == null){
                HelloApplication.setRoot("user-view", 1150, 520 );
            } else {
                HelloApplication.setRoot("agency-view", 986, 657);
            }

        } catch (Exception e){
            Arrays.stream(e.getStackTrace()).forEach(err -> System.out.println(err));
            errorMessageLabel.setVisible(true);
        }

    }

    @FXML
    void switchToNewAccountScreen(ActionEvent event) throws IOException {
        HelloApplication.setRoot("register-view", 320, 380);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorMessageLabel.setVisible(false);
    }


}
