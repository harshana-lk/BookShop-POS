package com.bookiebook.pos.model;

import com.bookiebook.pos.to.User;
import com.bookiebook.pos.util.CrudUtil;
import com.bookiebook.pos.view.tm.UserTm;

import java.sql.SQLException;

public class UserModel {
    public boolean saveUser(User user) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("INSERT INTO user VALUES(?,?,?,?,?,?)",
                user.getUserID(),
                user.getEmpID(),
                user.getUsername(),
                user.getStatus(),
                user.getPassword(),
                user.getHint());
    }

    public boolean updateUser(User user) throws SQLException, ClassNotFoundException {

        return CrudUtil.execute("UPDATE user SET empID=?,username=?,status=?,password=?,hint=? WHERE userID=?",
                user.getEmpID(),
                user.getUsername(),
                user.getStatus(),
                user.getPassword(),
                user.getHint(),
                user.getUserID());
    }

    public boolean deleteUser(UserTm user) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM user WHERE userID=?",
                user.getUserID());
    }
}
