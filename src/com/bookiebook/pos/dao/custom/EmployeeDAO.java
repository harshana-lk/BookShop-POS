package com.bookiebook.pos.dao.custom;

import com.bookiebook.pos.dao.CrudDAO;
import com.bookiebook.pos.view.tm.EmployeeTm;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<EmployeeTm, String> {
    String setEmployeeIDs() throws SQLException, ClassNotFoundException;

}
