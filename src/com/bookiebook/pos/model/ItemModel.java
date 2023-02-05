package com.bookiebook.pos.model;

import com.bookiebook.pos.to.Category;
import com.bookiebook.pos.to.Item;
import com.bookiebook.pos.util.CrudUtil;
import com.bookiebook.pos.view.tm.CategoryTm;
import com.bookiebook.pos.view.tm.ItemTm;

import java.sql.SQLException;

public class ItemModel {
    public boolean saveItem(Item item) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("INSERT INTO item VALUES(?,?,?,?,?,?)",
                item.getItemID(),
                item.getName(),
                item.getCategoryID(),
                item.getVendorID(),
                String.valueOf(item.getQty()),
                String.valueOf(item.getPrice()));
    }

    public boolean updateItem(Item item) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("UPDATE item SET name=?,categoryID=?,vendorID=?,qtyOnHand=?,price=? WHERE itemID=?",
                item.getName(),
                item.getCategoryID(),
                item.getVendorID(),
                String.valueOf(item.getQty()),
                String.valueOf(item.getPrice()),
                item.getItemID());
    }

    public boolean deleteItem(ItemTm itemTm) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM item WHERE itemID=?",
                itemTm.getItemID());
    }
}
