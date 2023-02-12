package com.bookiebook.pos.bo.custom.impl;

import com.bookiebook.pos.bo.custom.OrderBO;
import com.bookiebook.pos.dao.custom.impl.OrderDAOImpl;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {

    @Override
    public boolean qtyCheck(String code, int qty) throws SQLException, ClassNotFoundException {
        return new OrderDAOImpl().qtyCheck(code, qty);
    }
}
