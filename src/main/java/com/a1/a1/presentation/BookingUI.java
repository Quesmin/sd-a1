package com.a1.a1.presentation;

import com.a1.a1.controller.UserController;
import com.a1.a1.model.PackModel;
import com.a1.a1.model.UserModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookingUI implements Initializable {



    public static UserModel user;
    private static UserController userController = new UserController();

    @FXML
    private TableView bookingsTable;

    public void updateTable(TableView table, List<PackModel> newPackagesList) {
        table.getColumns().clear();
        table.getItems().clear();
        for (Field field : PackModel.class.getDeclaredFields()) {
            if (field.getName().contains("By"))
                continue;
            field.setAccessible(true);
            TableColumn t = new TableColumn(field.getName());
            t.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
            table.getColumns().add(t);
            t.setMinWidth(100);
            t.setStyle("-fx-alignment: CENTER;");
        }
        table.getItems().addAll(newPackagesList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateTable(bookingsTable, userController.viewAllBookedVacations(user.getId()).stream().map(e -> e.getPackByPackId()).toList());
    }
}
