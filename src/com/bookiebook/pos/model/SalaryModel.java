package com.bookiebook.pos.model;

import com.bookiebook.pos.to.Salary;
import com.bookiebook.pos.dao.CrudUtil;
import com.bookiebook.pos.view.tm.SalaryTm;

import java.sql.SQLException;

public class SalaryModel {
    public boolean saveSalary(Salary salary) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("INSERT INTO salary VALUES(?,?,?)",
                salary.getId(),
                salary.getStatus(),
                salary.getSalary());
    }

    public boolean updateSalary(Salary salary) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("UPDATE salary SET status=?,salary=? WHERE salaryID=?",
                salary.getStatus(),
                salary.getSalary(),
                salary.getId());
    }

    public boolean deleteSalary(SalaryTm salaryTm) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM salary WHERE salaryID=?",
                salaryTm.getId());
    }
}
