package com.bookiebook.pos.bo.custom;

import com.bookiebook.pos.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {
    public boolean qtyCheck(String code, int qty) throws SQLException, ClassNotFoundException;

}
