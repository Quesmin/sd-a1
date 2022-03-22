package com.a1.a1.presentation;

import com.a1.a1.controller.AgencyController;
import com.a1.a1.controller.UserController;
import com.a1.a1.dto.PackDTO;
import com.a1.a1.model.AgencyModel;
import com.a1.a1.model.DestinationModel;
import com.a1.a1.model.PackModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class PackageUI implements Initializable {

    public static AgencyModel agency;
    public static PackModel pack;
    private final AgencyController agencyController = new AgencyController();
    private final UserController userController = new UserController();
    private final UserUI userUI = new UserUI();
    @FXML
    private ComboBox<DestinationModel> destinationDropdown;

    @FXML
    private Label errorMessageLabel;

    @FXML
    private TextArea detailsTextField;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField maxSlotsTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private DatePicker startDate;

    @FXML
    void addPackage(ActionEvent event) {
        errorMessageLabel.setVisible(false);

        try{
            PackDTO packDTO = new PackDTO(nameTextField.getText(),
                    Integer.parseInt(priceTextField.getText()),
                    Date.valueOf(startDate.getValue().toString()),
                    Date.valueOf(endDate.getValue().toString()),
                    detailsTextField.getText(),
                    Integer.parseInt(maxSlotsTextField.getText()),
                    destinationDropdown.getValue(),
                    agency
            );
            if (pack == null)
                agencyController.addPack(packDTO);
            else
                agencyController.changePack(pack.getId(), packDTO);
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }catch (Exception e){
            errorMessageLabel.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorMessageLabel.setVisible(false);
        destinationDropdown.getItems().addAll(userController.getAllDestinations());
        if (pack != null){
            nameTextField.setText(pack.getName());
            priceTextField.setText(String.valueOf(pack.getPrice()));
            startDate.setValue(pack.getStartDate().toLocalDate());
            endDate.setValue(pack.getEndDate().toLocalDate());
            detailsTextField.setText(pack.getExtraDetails());
            destinationDropdown.getSelectionModel().select(pack.getDestinationByDestinationId());
            maxSlotsTextField.setText(String.valueOf(pack.getMaxSlots()));
        }
    }
}
