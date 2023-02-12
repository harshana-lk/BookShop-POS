package com.bookiebook.pos.bo.custom.impl;

import com.bookiebook.pos.bo.custom.ItemBO;
import com.bookiebook.pos.dao.DAOFactory;
import com.bookiebook.pos.dao.DAOTypes;
import com.bookiebook.pos.dao.custom.ItemDAO;
import com.bookiebook.pos.view.tm.ItemTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOTypes.ITEM);

    @Override
    public ObservableList<ItemTm> ItemFunctions(String searchText) throws SQLException, ClassNotFoundException {
        return itemDAO.functions(searchText);
    }

    @Override
    public String setItemIDs() throws SQLException, ClassNotFoundException {
        return itemDAO.setItemIDs();
    }

    @Override
    public ObservableList<String> loadCategories() throws SQLException, ClassNotFoundException {
        return itemDAO.loadCategories();
    }

    @Override
    public ObservableList<String> loadVendors() throws SQLException, ClassNotFoundException {
        return itemDAO.loadVendors();
    }
}
