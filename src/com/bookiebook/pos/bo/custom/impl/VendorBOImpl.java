package com.bookiebook.pos.bo.custom.impl;

import com.bookiebook.pos.bo.custom.VendorBO;
import com.bookiebook.pos.dao.DAOFactory;
import com.bookiebook.pos.dao.DAOTypes;
import com.bookiebook.pos.dao.custom.VenderDAO;
import com.bookiebook.pos.view.tm.VendorTm;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class VendorBOImpl implements VendorBO {
    private final VenderDAO vendorDAO = (VenderDAO) DAOFactory.getInstance().getDAO(DAOTypes.VENDOR);

    @Override
    public ObservableList<VendorTm> VendorFunctions(String searchText) throws SQLException, ClassNotFoundException {
        return vendorDAO.functions(searchText);
    }

    @Override
    public String setVendorIDs() throws SQLException, ClassNotFoundException {
        return vendorDAO.setVendorIDs();
    }
}
