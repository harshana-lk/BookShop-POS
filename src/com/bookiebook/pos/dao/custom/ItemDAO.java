package com.bookiebook.pos.dao.custom;

import com.bookiebook.pos.dao.CrudDAO;
import com.bookiebook.pos.view.tm.ItemTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ItemDAO extends CrudDAO<ItemTm,String> {
    String setItemIDs() throws SQLException, ClassNotFoundException;

    ObservableList<String> loadVendors() throws SQLException, ClassNotFoundException;

    ObservableList<String> loadCategories() throws SQLException, ClassNotFoundException;

}
