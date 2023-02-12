package com.bookiebook.pos.bo.custom;

import com.bookiebook.pos.bo.SuperBO;
import com.bookiebook.pos.view.tm.DeliveryTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DeliveryBO extends SuperBO {

    String setDeliveryIDs() throws SQLException, ClassNotFoundException;

    ObservableList<DeliveryTm> DeliveryFunctions(String searchText) throws SQLException, ClassNotFoundException;
}
