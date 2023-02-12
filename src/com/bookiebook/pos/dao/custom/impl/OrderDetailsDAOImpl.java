package com.bookiebook.pos.dao.custom.impl;

import com.bookiebook.pos.dao.CrudUtil;
import com.bookiebook.pos.dao.custom.OrderDetailsDAO;
import com.bookiebook.pos.model.OrderDetailsModel;
import com.bookiebook.pos.view.tm.OrderTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {

    @Override
    public ObservableList<OrderTm> functions(String text) throws SQLException, ClassNotFoundException {
        String searchText = "%" + text + "%";

//        String sql = "SELECT * FROM `orders`";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT * FROM `orders`");

        ObservableList<OrderTm> tmList = FXCollections.observableArrayList();

        while (set.next()) {
            Button btn = new Button("Delete");
            OrderTm tm = new OrderTm(
                    set.getString(1),
                    set.getString(4),
                    new Date(),
                    set.getDouble(3), btn);
            tmList.add(tm);

            btn.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "are you sure whether do you want to delete this Order?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {

                    try {
                        boolean delete = new OrderDetailsModel().deleteOrder(tm);
                        if (delete) {
                            tmList.remove(tm);
                            functions(searchText);
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
        return tmList;
    }
}
