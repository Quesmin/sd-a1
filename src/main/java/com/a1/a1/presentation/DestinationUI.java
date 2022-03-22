package com.a1.a1.presentation;


import com.a1.a1.controller.AgencyController;
import com.a1.a1.model.DestinationModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DestinationUI implements Initializable {

    private final AgencyController agencyController = new AgencyController();
    public static DestinationModel destination;
    @FXML
    private TextField nameTextField;

    @FXML
    private Label errorMessageLabel;

    @FXML
    void addDestination(ActionEvent event) {
        errorMessageLabel.setVisible(false);
        try{
            if (destination == null){
                agencyController.addVacationDestination(nameTextField.getText());
            } else {
                agencyController.updateDestination(destination.getId(), nameTextField.getText());
            }
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        } catch (Exception e){
            errorMessageLabel.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorMessageLabel.setVisible(false);
        if(destination != null){
            nameTextField.setText(destination.getName());
        }
    }

}
