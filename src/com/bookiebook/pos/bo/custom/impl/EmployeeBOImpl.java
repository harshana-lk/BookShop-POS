package com.bookiebook.pos.bo.custom.impl;

import com.bookiebook.pos.bo.custom.EmployeeBO;
import com.bookiebook.pos.dao.DAOFactory;
import com.bookiebook.pos.dao.DAOTypes;
import com.bookiebook.pos.dao.custom.EmployeeDAO;
import com.bookiebook.pos.view.tm.EmployeeTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class EmployeeBOImpl implements EmployeeBO {
    private final EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getInstance().getDAO(DAOTypes.EMPLOYEE);

    @Override
    public ObservableList<EmployeeTm> EmployeeFunctions(String searchText) throws SQLException, ClassNotFoundException {
        return employeeDAO.functions(searchText);
    }

    @Override
    public String setEmployeeIDs() throws SQLException, ClassNotFoundException {
        return employeeDAO.setEmployeeIDs();
    }
}
