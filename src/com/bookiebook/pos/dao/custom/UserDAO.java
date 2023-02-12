package com.bookiebook.pos.dao.custom;

import com.bookiebook.pos.dao.CrudDAO;
import com.bookiebook.pos.view.tm.UserTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<UserTm,String> {
    String setUserIDs() throws SQLException, ClassNotFoundException;

    ObservableList<String> loadEmployees() throws SQLException, ClassNotFoundException;

}
