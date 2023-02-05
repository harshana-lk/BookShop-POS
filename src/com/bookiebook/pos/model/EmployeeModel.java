package com.bookiebook.pos.model;

import com.bookiebook.pos.to.Employee;
import com.bookiebook.pos.util.CrudUtil;
import com.bookiebook.pos.view.tm.EmployeeTm;

import java.sql.SQLException;

public class EmployeeModel {
    public boolean saveEmployee(Employee employee) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("INSERT INTO employee VALUES(?,?,?,?,?)",
                employee.getId(),
                employee.getName(),
                employee.getStatus(),
                employee.getAddress(),
                employee.getPhone());
    }

    public boolean updateEmployee(Employee employee) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("UPDATE employee SET name=?,status=?,address=?,phone=? WHERE empID=?",
                employee.getName(),
                employee.getStatus(),
                employee.getAddress(),
                employee.getPhone(),
                employee.getId());
    }

    public boolean deleteCategory(EmployeeTm employeeTm) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM employee WHERE empID=?",
                employeeTm.getId());
    }
}
