package com.bookiebook.pos.dao.custom;

import com.bookiebook.pos.dao.CrudDAO;
import com.bookiebook.pos.view.tm.DeliveryTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DeliveryDAO extends CrudDAO<DeliveryTm , String> {
    String setDeliveryIDs() throws SQLException, ClassNotFoundException;

}
