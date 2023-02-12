package com.bookiebook.pos.bo.custom;

import com.bookiebook.pos.bo.SuperBO;
import com.bookiebook.pos.view.tm.SalaryTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface SalaryBO extends SuperBO {

    ObservableList<SalaryTm> SalaryFunctions(String searchText) throws SQLException, ClassNotFoundException;

    ObservableList<String> setStatus() throws SQLException, ClassNotFoundException;

    String  setSlaryIDs() throws SQLException, ClassNotFoundException;
}
