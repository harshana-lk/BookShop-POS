package com.bookiebook.pos.model;

import com.bookiebook.pos.to.Customer;
import com.bookiebook.pos.dao.CrudUtil;
import com.bookiebook.pos.view.tm.CustomerTm;

import java.sql.SQLException;

public class CustomerModel {
    public boolean saveCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("INSERT INTO customer VALUES(?,?,?,?,?)",
                customer.getId(),
                customer.getName(),
                customer.getNic(),
                customer.getAddress(),
                customer.getPhone());
    }

    public boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("UPDATE customer SET name=?,nic=?,address=?,phone=? WHERE custID=?",
                customer.getName(),
                customer.getNic(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getId());
    }

    public boolean deleteCustomer(CustomerTm customerTm) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM customer WHERE custID=?",
                customerTm.getId());
    }
}
