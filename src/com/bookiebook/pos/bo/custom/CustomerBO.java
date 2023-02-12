package com.bookiebook.pos.bo.custom;

import com.bookiebook.pos.bo.SuperBO;
import com.bookiebook.pos.view.tm.CustomerTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CustomerBO extends SuperBO {
    ObservableList<CustomerTm> customerFunctions(String searchText) throws SQLException, ClassNotFoundException;

    String setCustomerIDs() throws SQLException, ClassNotFoundException;
}
