package com.bookiebook.pos.bo.custom;

import com.bookiebook.pos.bo.SuperBO;
import com.bookiebook.pos.view.tm.CategoryTm;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public interface CategoryBO extends SuperBO {
    String setCategoryIDs() throws SQLException, IOException, ClassNotFoundException;

    ObservableList<CategoryTm> CategoryFunctions(String searchText) throws SQLException, ClassNotFoundException;

}
