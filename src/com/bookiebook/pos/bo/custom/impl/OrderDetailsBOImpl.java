package com.bookiebook.pos.bo.custom.impl;

import com.bookiebook.pos.bo.custom.OrderDetailsBO;
import com.bookiebook.pos.dao.DAOFactory;
import com.bookiebook.pos.dao.DAOTypes;
import com.bookiebook.pos.dao.custom.OrderDetailsDAO;
import com.bookiebook.pos.view.tm.OrderTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getInstance().getDAO(DAOTypes.ORDERDETAILS);

    @Override
    public ObservableList<OrderTm> OrderDetailsFunctions(String searchText) throws SQLException, ClassNotFoundException {
        return orderDetailsDAO.functions(searchText);
    }
}
