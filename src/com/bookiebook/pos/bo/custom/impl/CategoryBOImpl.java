package com.bookiebook.pos.bo.custom.impl;

import com.bookiebook.pos.bo.custom.CategoryBO;
import com.bookiebook.pos.dao.DAOFactory;
import com.bookiebook.pos.dao.DAOTypes;
import com.bookiebook.pos.dao.custom.CategoryDAO;
import com.bookiebook.pos.view.tm.CategoryTm;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.SQLException;

public class CategoryBOImpl implements CategoryBO {
    private final CategoryDAO categoryDAO = (CategoryDAO) DAOFactory.getInstance().getDAO(DAOTypes.CATEGORY);

    @Override
    public String setCategoryIDs() throws SQLException, IOException, ClassNotFoundException {
        return categoryDAO.setCategoryIDs();
    }

    @Override
    public ObservableList<CategoryTm> CategoryFunctions(String searchText) throws SQLException, ClassNotFoundException {
        return categoryDAO.functions(searchText);
    }

}
