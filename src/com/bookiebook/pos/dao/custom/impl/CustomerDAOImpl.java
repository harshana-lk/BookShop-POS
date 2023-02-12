package com.bookiebook.pos.dao.custom.impl;

import com.bookiebook.pos.dao.CrudUtil;
import com.bookiebook.pos.dao.custom.CustomerDAO;
import com.bookiebook.pos.model.CustomerModel;
import com.bookiebook.pos.view.tm.CustomerTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    String text = "";
    @Override
    public String setCustomerIDs() throws SQLException, ClassNotFoundException {
//        String sql = "SELECT custID FROM `customer` ORDER BY custID DESC LIMIT 1";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT custID FROM `customer` ORDER BY custID DESC LIMIT 1");
        if (set.next()) {
            String tempOrderId = set.getString(1);
            String[] array = tempOrderId.split("-");//[D,3]
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
           return text = "CUST-" + finalizeOrderId;
        } else {
           return text = "CUST-1";
        }
    }

    @Override
    public ObservableList<CustomerTm> functions(String text) throws SQLException, ClassNotFoundException {
        String searchText = "%" + text + "%";

        ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

//        String sql = "SELECT * FROM customer WHERE custID LIKE ? || name LIKE ?";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        statement.setString(1,searchText);
//        statement.setString(2,searchText);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT * FROM customer WHERE custID LIKE ? || name LIKE ?", text, text);

        while (set.next()) {
            Button btn = new Button("Delete");
            CustomerTm tm = new CustomerTm(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getInt(5),
                    btn);
            tmList.add(tm);
            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "are you sure whether do you want to delete this Customer?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {

                    try {
                        boolean delete = new CustomerModel().deleteCustomer(tm);
                        if (delete) {
                            tmList.remove(tm);
                            functions(searchText);
                            new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                        }
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }


                }
            });
        }
        return tmList;
    }
}
