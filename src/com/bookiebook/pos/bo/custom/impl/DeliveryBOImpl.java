package com.bookiebook.pos.bo.custom.impl;

import com.bookiebook.pos.bo.custom.DeliveryBO;
import com.bookiebook.pos.dao.DAOFactory;
import com.bookiebook.pos.dao.DAOTypes;
import com.bookiebook.pos.dao.custom.DeliveryDAO;
import com.bookiebook.pos.view.tm.DeliveryTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class DeliveryBOImpl implements DeliveryBO {
    private final DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getInstance().getDAO(DAOTypes.DELIVERY);

    @Override
    public String setDeliveryIDs() throws SQLException, ClassNotFoundException {
        return deliveryDAO.setDeliveryIDs();
    }

    @Override
    public ObservableList<DeliveryTm> DeliveryFunctions(String searchText) throws SQLException, ClassNotFoundException {
        return deliveryDAO.functions(searchText);
    }
}
