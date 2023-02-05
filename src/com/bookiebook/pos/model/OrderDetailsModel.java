package com.bookiebook.pos.model;

import com.bookiebook.pos.util.CrudUtil;
import com.bookiebook.pos.view.tm.OrderTm;

import java.sql.SQLException;

public class OrderDetailsModel {
    public boolean deleteOrder(OrderTm orderTm) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM orders WHERE orderID=?",
                orderTm.getOrderId());
    }
}
