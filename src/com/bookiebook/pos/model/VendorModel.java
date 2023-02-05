package com.bookiebook.pos.model;

import com.bookiebook.pos.to.Category;
import com.bookiebook.pos.to.Vendor;
import com.bookiebook.pos.util.CrudUtil;
import com.bookiebook.pos.view.tm.CategoryTm;
import com.bookiebook.pos.view.tm.VendorTm;

import java.sql.SQLException;

public class VendorModel {
    public boolean saveVendor(Vendor vendor) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("INSERT INTO vendor VALUES(?,?,?)",
                vendor.getVendorID(),
                vendor.getName(),
                vendor.getDescription());
    }

    public boolean updateVendor(Vendor vendor) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("UPDATE vendor SET name=?,description=? WHERE vendorID=?",
                vendor.getName(),
                vendor.getDescription(),
                vendor.getVendorID());
    }

    public boolean deletevendor(VendorTm vendorTm) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM vendor WHERE vendorID=?",
                vendorTm.getVendorID());
    }
}
