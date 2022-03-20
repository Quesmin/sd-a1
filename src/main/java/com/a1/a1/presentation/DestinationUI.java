package com.a1.a1.presentation;


import com.a1.a1.controller.AgencyController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DestinationUI {

    private final AgencyController agencyController = new AgencyController();
    @FXML
    TextField nameField;

    @FXML
    void addDestination(ActionEvent event) {
        agencyController.addVacationDestination(nameField.getText());
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}
