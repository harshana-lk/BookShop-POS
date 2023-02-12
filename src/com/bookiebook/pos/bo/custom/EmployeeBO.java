package com.bookiebook.pos.bo.custom;

import com.bookiebook.pos.bo.SuperBO;
import com.bookiebook.pos.view.tm.EmployeeTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface EmployeeBO extends SuperBO {
    ObservableList<EmployeeTm> EmployeeFunctions(String searchText) throws SQLException, ClassNotFoundException;

    String setEmployeeIDs() throws SQLException, ClassNotFoundException;
}
