package com.bookiebook.pos.dao.custom;

import com.bookiebook.pos.dao.CrudDAO;
import com.bookiebook.pos.view.tm.VendorTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface VenderDAO extends CrudDAO<VendorTm,String> {
    String setVendorIDs() throws SQLException, ClassNotFoundException;

}
