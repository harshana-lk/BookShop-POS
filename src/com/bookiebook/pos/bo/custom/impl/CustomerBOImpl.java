package com.bookiebook.pos.bo.custom.impl;

import com.bookiebook.pos.bo.custom.CustomerBO;
import com.bookiebook.pos.dao.DAOFactory;
import com.bookiebook.pos.dao.DAOTypes;
import com.bookiebook.pos.dao.custom.CustomerDAO;
import com.bookiebook.pos.view.tm.CustomerTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);

    @Override
    public ObservableList<CustomerTm> customerFunctions(String searchText) throws SQLException, ClassNotFoundException {
        return customerDAO.functions(searchText);
    }

    @Override
    public String setCustomerIDs() throws SQLException, ClassNotFoundException {
        return customerDAO.setCustomerIDs();
    }
}
