package com.bookiebook.pos.dao.custom.impl;

import com.bookiebook.pos.DB.DBConnection;
import com.bookiebook.pos.dao.custom.OrderDAO;
import com.bookiebook.pos.to.Order;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public ObservableList<Order> functions(String text) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean qtyCheck(String code, int qty) throws SQLException, ClassNotFoundException {

        String sql = "SELECT qtyOnHand FROM item WHERE itemID=?";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        statement.setString(1, code);
        ResultSet set = statement.executeQuery();

        if (set.next()) {
            int tempQty = set.getInt(1);
            return tempQty >= qty;
        } else {
            return false;
        }
    }

    public String setOrderIDs() throws SQLException, ClassNotFoundException {
        String text = "";
        String sql = "SELECT orderID FROM `orders` ORDER BY orderID DESC LIMIT 1";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet set = statement.executeQuery();
        if (set.next()) {
            String tempOrderId = set.getString(1);
            String[] array = tempOrderId.split("-");//[D,3]
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            return text = "ORDER-" + finalizeOrderId;
        } else {
            return text = "ORDER-1";
        }
    }

    public ArrayList<String> loadDeliveryIDs() throws SQLException, ClassNotFoundException {

        String sql = "SELECT deliveryID  FROM delivery";
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet set = statement.executeQuery();

        ArrayList<String> idList = new ArrayList<>();
        while (set.next()) {
            idList.add(set.getString(1));
        }
        return idList;
    }


}
