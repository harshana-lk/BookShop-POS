package com.bookiebook.pos.controller;

import com.bookiebook.pos.bo.BOFactory;
import com.bookiebook.pos.bo.BOTypes;
import com.bookiebook.pos.bo.custom.OrderDetailsBO;
import com.bookiebook.pos.view.tm.OrderTm;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class OrderDetailsPanelController {

    private final OrderDetailsBO orderDetailsBO = (OrderDetailsBO) BOFactory.getInstance().getBO(BOTypes.ORDERDETAILS);
    private final String serachText = "";
    public TableView<OrderTm> tblOrderDetails;
    public TableColumn colOrderID;
    public TableColumn colCustomer;
    public TableColumn colDate;
    public TableColumn colTotal;
    public TableColumn colOption;

    public void initialize() {
        colOrderID.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadOrders(serachText);
    }

    private void loadOrders(String text) {
        String searchText = "%" + text + "%";

        try {
//            String sql = "SELECT * FROM `orders`";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            ResultSet set = statement.executeQuery();
//
//            ObservableList<OrderTm> tmList= FXCollections.observableArrayList();
//
//            while (set.next()){
//                Button btn = new Button("Delete");
//                OrderTm tm= new OrderTm(
//                        set.getString(1),
//                        set.getString(4),
//                        new Date(),
//                        set.getDouble(3),btn);
//                tmList.add(tm);
//
//                btn.setOnAction(e-> {
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                            "are you sure whether do you want to delete this Order?",
//                            ButtonType.YES, ButtonType.NO);
//                    Optional<ButtonType> buttonType = alert.showAndWait();
//                    if (buttonType.get() == ButtonType.YES) {
//
//                        try {
//                            boolean delete = new OrderDetailsModel().deleteOrder(tm);
//                            if (delete) {
//                                loadOrders(searchText);
//                                new Alert(Alert.AlertType.INFORMATION, "Order Deleted!").show();
//                            } else {
//                                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
//                            }
//                        } catch (ClassNotFoundException | SQLException exception) {
//                            exception.printStackTrace();
//                        }
//                    }
//                });
//            }

            ObservableList<OrderTm> orderTms = orderDetailsBO.OrderDetailsFunctions(searchText);
            tblOrderDetails.setItems(orderTms);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
