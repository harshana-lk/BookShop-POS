package com.bookiebook.pos.dao.custom;

import com.bookiebook.pos.dao.CrudDAO;
import com.bookiebook.pos.view.tm.CustomerTm;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<CustomerTm, String> {
    String setCustomerIDs() throws SQLException, ClassNotFoundException;

}
