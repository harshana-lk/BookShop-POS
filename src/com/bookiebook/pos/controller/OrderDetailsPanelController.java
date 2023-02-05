package com.bookiebook.pos.controller;

import com.bookiebook.pos.DB.DBConnection;
import com.bookiebook.pos.model.OrderDetailsModel;
import com.bookiebook.pos.view.tm.OrderTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class OrderDetailsPanelController {

    public TableView<OrderTm> tblOrderDetails;
    public TableColumn colOrderID;
    public TableColumn colCustomer;
    public TableColumn colDate;
    public TableColumn colTotal;
    public TableColumn colOption;

    private String serachText = "";

    public void initialize(){
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadOrders(serachText);
    }

    private void loadOrders(String text) {
        String searchText="%"+text+"%";

        try{
            String sql = "SELECT * FROM `orders`";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet set = statement.executeQuery();

            ObservableList<OrderTm> tmList= FXCollections.observableArrayList();

            while (set.next()){
                Button btn = new Button("Delete");
                OrderTm tm= new OrderTm(
                        set.getString(1),
                        set.getString(4),
                        new Date(),
                        set.getDouble(3),btn);
                tmList.add(tm);

                btn.setOnAction(e-> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "are you sure whether do you want to delete this Order?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {

                        try {
                            boolean delete = new OrderDetailsModel().deleteOrder(tm);
                            if (delete) {
                                loadOrders(searchText);
                                new Alert(Alert.AlertType.INFORMATION, "Order Deleted!").show();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                            }
                        } catch (ClassNotFoundException | SQLException exception) {
                            exception.printStackTrace();
                        }
                    }
                });
            }
            tblOrderDetails.setItems(tmList);

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
