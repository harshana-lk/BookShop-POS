package com.bookiebook.pos.bo.custom.impl;

import com.bookiebook.pos.bo.custom.SalaryBO;
import com.bookiebook.pos.dao.DAOFactory;
import com.bookiebook.pos.dao.DAOTypes;
import com.bookiebook.pos.dao.custom.SalaryDAO;
import com.bookiebook.pos.view.tm.SalaryTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class SalaryBOImpl implements SalaryBO {
    private final SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getInstance().getDAO(DAOTypes.SALARY);

    @Override
    public ObservableList<SalaryTm> SalaryFunctions(String searchText) throws SQLException, ClassNotFoundException {
        return salaryDAO.functions(searchText);
    }

    @Override
    public ObservableList<String> setStatus() throws SQLException, ClassNotFoundException {
        return salaryDAO.setStatus();
    }

    @Override
    public String setSlaryIDs() throws SQLException, ClassNotFoundException {
        return salaryDAO.setSlaryIDs();
    }
}
