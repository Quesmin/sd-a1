package com.a1.a1.presentation;

import com.a1.a1.controller.AgencyController;
import com.a1.a1.controller.UserController;
import com.a1.a1.dto.BookingDTO;
import com.a1.a1.dto.DestinationDTO;
import com.a1.a1.dto.PackFilterDTO;
import com.a1.a1.model.AgencyModel;
import com.a1.a1.model.DestinationModel;
import com.a1.a1.model.PackModel;
import com.a1.a1.model.UserModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class UserUI implements Initializable {
    UserController userController = new UserController();
    AgencyController agencyController = new AgencyController();
    public static UserModel user;
    public static AgencyModel agency;
    @FXML
    private ComboBox DestinationDropdown;

    @FXML
    private TableView PackageTable;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField maxPrice;

    @FXML
    private TextField minPrice;

    @FXML
    private DatePicker startDate;


    public void updateTable(TableView table, List<PackModel> newPackageList) {
        table.getColumns().clear();
        table.getItems().clear();
        for (Field field : PackModel.class.getDeclaredFields()) {
//            if (field.getName().equals("tourists"))
//                continue;
            field.setAccessible(true);
            TableColumn t = new TableColumn(field.getName());
            t.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            table.getColumns().add(t);
            t.setMinWidth(100);
            t.setStyle("-fx-alignment: CENTER;");
        }
        ContextMenu cm = new ContextMenu();
        if(UserUI.agency != null){
            MenuItem menuItem1 = new MenuItem("Delete");
            MenuItem menuItem2 = new MenuItem("Add");
            MenuItem menuItem3 = new MenuItem("Edit");
            menuItem1.setOnAction(e -> {
                agencyController.removePack( ((PackModel)table.getSelectionModel().getSelectedItem()).getId());
            });
            menuItem2.setOnAction(e->{
                PackageUI.pack = null;
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = null;
                try {
                    scene = new Scene(HelloApplication.loadFXML("add-package"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                stage.setScene(scene);
                stage.showAndWait();
            });

            menuItem3.setOnAction(e->{
                PackageUI.pack = ((PackModel) table.getSelectionModel().getSelectedItem());
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                Scene scene = null;
                try {
                    scene = new Scene(HelloApplication.loadFXML("add-package"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                stage.setScene(scene);
                stage.showAndWait();
            });
            cm.getItems().addAll(menuItem1, menuItem2, menuItem3);
        } else {
            MenuItem menuItem1 = new MenuItem("Book");
            BookingDTO bookingDTO = new BookingDTO(UserUI.user, ((PackModel)table.getSelectionModel().getSelectedItem()));
            menuItem1.setOnAction(e -> userController.bookVacationPackage(bookingDTO));
            cm.getItems().addAll(menuItem1);
        }


        table.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                cm.show(table, event.getScreenX(), event.getScreenY());
            }
        });
        table.getItems().addAll(newPackageList
                .stream()
                .filter(e -> e.getBookingsById().size() < e.getMaxSlots())
                .toList());
    }

    public void filterPackages() {
        PackFilterDTO filter = new PackFilterDTO();
        filter.setStartDate(startDate.getValue() != null ? Date.valueOf(startDate.getValue()) : null);
        filter.setEndDate(endDate.getValue() != null ? Date.valueOf(endDate.getValue()) : null);
        filter.setMinPrice( minPrice.getText().length() > 0 ? Integer.parseInt(minPrice.getText()) : null);
        filter.setMaxPrice( maxPrice.getText().length() > 0 ? Integer.parseInt(maxPrice.getText()) : null);
        filter.setDestination((DestinationModel) DestinationDropdown.getSelectionModel().getSelectedItem());

        updateTable(PackageTable, userController.filterPackages(filter));
    }

    @FXML
    void viewBooking() throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        BookingUI.user = user;
        Scene scene = new Scene(HelloApplication.loadFXML("booking-view"));
        stage.setScene(scene);
        stage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<PackModel> packs = userController.viewAllAvailablePackages();
//        System.out.println(paks);
        updateTable(PackageTable, packs);

        minPrice.textProperty().addListener((observable, oldValue, newValue) -> filterPackages());
        maxPrice.textProperty().addListener((observable, oldValue, newValue) -> filterPackages());
        startDate.valueProperty().addListener((observable, oldValue, newValue) -> filterPackages());
        endDate.valueProperty().addListener((observable, oldValue, newValue) -> filterPackages());
        DestinationDropdown.valueProperty().addListener((observable, oldValue, newValue) -> filterPackages());

        DestinationDropdown.getItems().addAll(FXCollections.observableArrayList(userController.getAllDestinations()));
    }
}