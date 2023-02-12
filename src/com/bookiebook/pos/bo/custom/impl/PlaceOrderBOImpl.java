package com.bookiebook.pos.bo.custom.impl;

import com.bookiebook.pos.DB.DBConnection;
import com.bookiebook.pos.bo.custom.PlaceOrderBO;
import com.bookiebook.pos.to.ItemDetails;
import com.bookiebook.pos.to.Order;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    @Override
    public boolean placeOrder(Order order, ArrayList<ItemDetails> details) {
        // place Order
        Connection con = null;
        try {

            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            String sql = "INSERT `orders` VALUES(?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, order.getOrderId());
            statement.setString(2, String.valueOf(LocalDate.now()));
            statement.setDouble(3, order.getTotalCost());
            statement.setString(4, order.getCustomer());
            boolean isOrderSaved = statement.executeUpdate() > 0;
            if (isOrderSaved) {
                boolean isAllUpdated = manageQty(details, order.getOrderId());
                if (isAllUpdated) {
                    con.commit();
                    return true;
                } else {
                    con.setAutoCommit(true);
                    con.rollback();
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }

            } else {
                con.setAutoCommit(true);
                con.rollback();
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean manageQty(ArrayList<ItemDetails> details, String orderId) {

        try {

            for (ItemDetails d : details
            ) {


                String sql = "INSERT `orderDetails` VALUES(?,?,?,?)";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
                statement.setString(1, d.getId());
                statement.setString(2, orderId);
                statement.setDouble(3, d.getUnitPrice());
                statement.setInt(4, d.getQty());

                boolean isOrderDetailsSaved = statement.executeUpdate() > 0;

                if (isOrderDetailsSaved) {
                    boolean isQtyUpdated = update(d);
                    if (!isQtyUpdated) {
                        return false;
                    }
                } else {
                    return false;
                }


            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return true;
    }

    private boolean update(ItemDetails d) {
        try {

            String sql = "UPDATE item SET qtyOnHand=(qtyOnHand-?) WHERE itemID=?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, d.getQty());
            statement.setString(2, d.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
