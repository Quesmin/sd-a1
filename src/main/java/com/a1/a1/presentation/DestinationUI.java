package com.a1.a1.presentation;


import com.a1.a1.controller.AgencyController;
import com.a1.a1.model.DestinationModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    void addDestination(ActionEvent event) {

        if (destination == null)
            agencyController.addVacationDestination(nameTextField.getText());
        else
            agencyController.updateDestination(destination.getId(), nameTextField.getText());

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(destination != null){
            nameTextField.setText(destination.getName());
        }
    }

}
