package com.bookiebook.pos.dao.custom.impl;

import com.bookiebook.pos.dao.CrudUtil;
import com.bookiebook.pos.dao.custom.UserDAO;
import com.bookiebook.pos.model.UserModel;
import com.bookiebook.pos.view.tm.UserTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    @Override
    public String setUserIDs() throws SQLException, ClassNotFoundException {
        String text = "";
//        String sql = "SELECT userID FROM `users` ORDER BY userID DESC LIMIT 1";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT userID FROM `users` ORDER BY userID DESC LIMIT 1");
        if (set.next()) {
            String tempOrderId = set.getString(1);
            String[] array = tempOrderId.split("-");//[D,3]
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            return text = "USER-" + finalizeOrderId;
//            txtID.setText("USER-" + finalizeOrderId);
        } else {
//            txtID.setText("USER-1");
            return text = "USER-1";
        }
    }

    @Override
    public ObservableList<String> loadEmployees() throws SQLException, ClassNotFoundException {
        ObservableList<String> userTms = FXCollections.observableArrayList();
//        String sql = "SELECT empID FROM employee";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet resultSet = statement.executeQuery();
        ResultSet resultSet = CrudUtil.execute("SELECT empID FROM employee");

        while (resultSet.next()) {
            userTms.add(resultSet.getString(1));
        }
        return userTms;
    }

    @Override
    public ObservableList<UserTm> functions(String text) throws SQLException, ClassNotFoundException {
        String searchText = "%" + text + "%";

        ObservableList<UserTm> tmList = FXCollections.observableArrayList();

//        String sql = "SELECT * FROM users WHERE userID LIKE ? || empID LIKE ?";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        statement.setString(1, searchText);
//        statement.setString(2, searchText);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT * FROM users WHERE userID LIKE ? || empID LIKE ?", text, text);

        while (set.next()) {
            Button btn = new Button("Delete");
            UserTm tm = new UserTm(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6),
                    btn);
            tmList.add(tm);
            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "are you sure whether do you want to delete this User?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {

                    try {
//                            String sql1 = "DELETE FROM user WHERE userID=?";
//                            PreparedStatement statement1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
//                            statement1.setString(1, tm.getUserID());
                        PreparedStatement statement1 = CrudUtil.execute("DELETE FROM users WHERE userID=?");
                        boolean delete = new UserModel().deleteUser(tm);
                        if (delete) {
                            tmList.remove(tm);
                            functions(searchText);
                            new Alert(Alert.AlertType.INFORMATION, "User Deleted!").show();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                        }
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }


                }
            });
        }
        return tmList;
    }
}
