package com.bookiebook.pos.bo.custom.impl;

import com.bookiebook.pos.bo.custom.UserBO;
import com.bookiebook.pos.dao.DAOFactory;
import com.bookiebook.pos.dao.DAOTypes;
import com.bookiebook.pos.dao.custom.UserDAO;
import com.bookiebook.pos.view.tm.UserTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {
    private final UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOTypes.USER);

    @Override
    public ObservableList<UserTm> UserFunctions(String searchText) throws SQLException, ClassNotFoundException {
        return userDAO.functions(searchText);
    }

    @Override
    public ObservableList<String> loadEmployees() throws SQLException, ClassNotFoundException {
        return userDAO.loadEmployees();
    }

    @Override
    public String setUserIDs() throws SQLException, ClassNotFoundException {
        return userDAO.setUserIDs();
    }
}
