package com.bookiebook.pos.bo.custom;

import com.bookiebook.pos.bo.SuperBO;
import com.bookiebook.pos.view.tm.UserTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface UserBO extends SuperBO {

    ObservableList<UserTm> UserFunctions(String searchText) throws SQLException, ClassNotFoundException;

    ObservableList<String> loadEmployees() throws SQLException, ClassNotFoundException;

    String setUserIDs() throws SQLException, ClassNotFoundException;
}
