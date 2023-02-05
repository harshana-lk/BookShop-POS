package com.bookiebook.pos.model;

import com.bookiebook.pos.DB.DBConnection;
import com.bookiebook.pos.to.Category;
import com.bookiebook.pos.util.CrudUtil;
import com.bookiebook.pos.view.tm.CategoryTm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryModel {
    public static String getLastOrderId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT categoryID FROM `category` ORDER BY categoryID DESC LIMIT 1");
        return rst.next() ? rst.getString("categoryID") : null;
    }

    public boolean saveCategory(Category category) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("INSERT INTO category VALUES(?,?,?)",
                category.getId(),
                category.getName(),
                category.getDescription());
    }

    public boolean updateCategory(Category category) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("UPDATE category SET name=?,description=? WHERE categoryID=?",
                category.getName(),
                category.getDescription(),
                category.getId());
    }

    public boolean deleteCategory(CategoryTm categoryTm) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM category WHERE categoryID=?",
                categoryTm.getId());
    }
}
