package com.bookiebook.pos.dao.custom;

import com.bookiebook.pos.dao.CrudDAO;
import com.bookiebook.pos.view.tm.CategoryTm;

import java.io.IOException;
import java.sql.SQLException;

public interface CategoryDAO extends CrudDAO<CategoryTm, String> {

    String setCategoryIDs() throws SQLException, ClassNotFoundException, IOException;


}
