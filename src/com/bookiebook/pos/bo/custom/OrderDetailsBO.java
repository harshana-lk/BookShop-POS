package com.bookiebook.pos.bo.custom;

import com.bookiebook.pos.bo.SuperBO;
import com.bookiebook.pos.view.tm.OrderTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface OrderDetailsBO extends SuperBO {

    ObservableList<OrderTm> OrderDetailsFunctions(String searchText) throws SQLException, ClassNotFoundException;
}
