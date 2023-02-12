package com.bookiebook.pos.bo.custom;

import com.bookiebook.pos.bo.SuperBO;
import com.bookiebook.pos.view.tm.ItemTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ItemBO extends SuperBO {
    ObservableList<ItemTm> ItemFunctions(String searchText) throws SQLException, ClassNotFoundException;

    String  setItemIDs() throws SQLException, ClassNotFoundException;

    ObservableList<String> loadCategories() throws SQLException, ClassNotFoundException;

    ObservableList<String> loadVendors() throws SQLException, ClassNotFoundException;
}
