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
    @FXML
    private ComboBox<DestinationModel> destinationDropdown;

    @FXML
    private TextField availableField;

    @FXML
    private TextArea detailsField;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private DatePicker startDate;

    @FXML
    void addPackage(ActionEvent event) {
        PackDTO packDTO = new PackDTO(nameField.getText(),
                Integer.parseInt(priceField.getText()),
                Date.valueOf(startDate.getValue()),
                Date.valueOf(endDate.getValue()),
                detailsField.getText(),
                Integer.parseInt(availableField.getText()),
                destinationDropdown.getValue(),
                agency
        );
        if (pack == null)
            agencyController.addPack(packDTO);
        else
            agencyController.changePack(pack.getId(), packDTO);
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        destinationDropdown.getItems().addAll(userController.getAllDestinations());
        if(pack != null){
            nameField.setText(pack.getName());
            priceField.setText(String.valueOf(pack.getPrice()));
            startDate.setValue(pack.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            endDate.setValue(pack.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            detailsField.setText(pack.getExtraDetails());
            destinationDropdown.getSelectionModel().select(pack.getDestinationByDestinationId());
        }
    }
}
