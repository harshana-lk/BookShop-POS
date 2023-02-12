package com.bookiebook.pos.dao.custom.impl;

import com.bookiebook.pos.dao.custom.ItemDAO;
import com.bookiebook.pos.model.ItemModel;
import com.bookiebook.pos.dao.CrudUtil;
import com.bookiebook.pos.view.tm.ItemTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public String  setItemIDs() throws SQLException, ClassNotFoundException {
        String text = "";
//        String sql = "SELECT itemID FROM `item` ORDER BY itemID DESC LIMIT 1";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT itemID FROM `item` ORDER BY itemID DESC LIMIT 1");
        if (set.next()) {
            String tempOrderId = set.getString(1);
            String[] array = tempOrderId.split("-");//[D,3]
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            return text = "ITEM-" + finalizeOrderId;
        } else {
            return text = "ITEM-1";
        }
    }

    @Override
    public  ObservableList<String> loadVendors() throws SQLException, ClassNotFoundException {
        ObservableList<String> vendorTms = FXCollections.observableArrayList();
//        String sql = "SELECT name FROM vendor";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet resultSet = statement.executeQuery();
        ResultSet resultSet = CrudUtil.execute("SELECT name FROM vendor");

        while (resultSet.next()) {
            vendorTms.add(resultSet.getString(1));
        }
        return vendorTms;
    }

    @Override
    public ObservableList<String> loadCategories() throws SQLException, ClassNotFoundException {
        ObservableList<String> categoryTms = FXCollections.observableArrayList();
//        String sql = "SELECT name FROM category";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet resultSet = statement.executeQuery();
        ResultSet resultSet = CrudUtil.execute("SELECT name FROM category");

        while (resultSet.next()) {
            categoryTms.add(resultSet.getString(1));
        }
        return categoryTms;
    }

    @Override
    public ObservableList<ItemTm> functions(String text) throws SQLException, ClassNotFoundException {
        String searchText = "%" + text + "%";

        ObservableList<ItemTm> tmList = FXCollections.observableArrayList();

//        String sql = "SELECT * FROM item WHERE itemID LIKE ? || name LIKE ?";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        statement.setString(1, searchText);
//        statement.setString(2, searchText);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT * FROM item WHERE itemID LIKE ? || name LIKE ?",text,text);

        while (set.next()) {
            Button btn = new Button("Delete");
            ItemTm tm = new ItemTm(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getInt(5),
                    set.getDouble(6),
                    btn);
            tmList.add(tm);
            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "are you sure whether do you want to delete this item?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {

                    try {
                        boolean delete = new ItemModel().deleteItem(tm);
                        if (delete) {
                            tmList.remove(tm);
                            functions(searchText);
                            new Alert(Alert.AlertType.INFORMATION, "Item Deleted!").show();
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
