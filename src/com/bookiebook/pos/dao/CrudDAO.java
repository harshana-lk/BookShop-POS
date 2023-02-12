package com.bookiebook.pos.dao;

import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CrudDAO<T,ID> extends SuperDAO{
    ObservableList<T> functions(ID text) throws SQLException, ClassNotFoundException;

}
