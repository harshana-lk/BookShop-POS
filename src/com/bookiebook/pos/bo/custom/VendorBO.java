package com.bookiebook.pos.bo.custom;

import com.bookiebook.pos.bo.SuperBO;
import com.bookiebook.pos.view.tm.VendorTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface VendorBO extends SuperBO {
    ObservableList<VendorTm> VendorFunctions(String searchText) throws SQLException, ClassNotFoundException;

    String  setVendorIDs() throws SQLException, ClassNotFoundException;
}
