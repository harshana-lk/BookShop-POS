package com.bookiebook.pos.dao.custom;

import com.bookiebook.pos.dao.CrudDAO;
import com.bookiebook.pos.view.tm.SalaryTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface SalaryDAO extends CrudDAO<SalaryTm,String> {
    String setSlaryIDs() throws SQLException, ClassNotFoundException;

    ObservableList<String> setStatus() throws SQLException, ClassNotFoundException;

}
