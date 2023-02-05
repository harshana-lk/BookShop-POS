package com.bookiebook.pos.model;

import com.bookiebook.pos.to.Delivery;
import com.bookiebook.pos.util.CrudUtil;
import com.bookiebook.pos.view.tm.DeliveryTm;

import java.sql.SQLException;

public class DeliveryModel {
    public boolean saveDelivery(Delivery delivery) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("INSERT INTO delivery VALUES(?,?,?,?)",
                delivery.getId(),
                delivery.getMethod(),
                delivery.getStatus(),
                delivery.getPrice());
    }

    public boolean updateDelivery(Delivery delivery) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("UPDATE delivery SET method =?,status=?,price=? WHERE deliveryID=?",
                delivery.getMethod(),
                delivery.getStatus(),
                delivery.getPrice(),
                delivery.getId());
    }

    public boolean deleteDelivery(DeliveryTm deliveryTm) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM delivery WHERE deliveryID=?",
                deliveryTm.getId());
    }
}
