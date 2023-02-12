package com.bookiebook.pos.dao.custom.impl;

import com.bookiebook.pos.dao.custom.EmployeeDAO;
import com.bookiebook.pos.dao.CrudUtil;
import com.bookiebook.pos.view.tm.EmployeeTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public String setEmployeeIDs() throws SQLException, ClassNotFoundException {
        String text = "";
//        String sql = "SELECT empID FROM `employee` ORDER BY empID DESC LIMIT 1";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT empID FROM `employee` ORDER BY empID DESC LIMIT 1");
        if (set.next()) {
            String tempOrderId = set.getString(1);
            String[] array = tempOrderId.split("-");//[D,3]
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            return text = "EMP-" + finalizeOrderId;
        } else {
            return text = "EMP-1";
        }
    }
    @Override
    public ObservableList<EmployeeTm> functions(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text+"%";

        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

//        String sql = "SELECT * FROM employee WHERE empID LIKE ? || name LIKE ?";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        statement.setString(1,searchText);
//        statement.setString(2,searchText);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT * FROM employee WHERE empID LIKE ? || name LIKE ?",text,text);

        while (set.next()){
            Button btn = new Button("Delete");
            EmployeeTm tm = new EmployeeTm(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getInt(5),
                    btn);
            tmList.add(tm);
            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "are you sure whether do you want to delete this employee?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {

                    try {
//                        String sql1 = "DELETE FROM employee WHERE empID=?";
//                        PreparedStatement statement1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
//                        statement1.setString(1,tm.getId());
                        PreparedStatement statement1 = CrudUtil.execute("DELETE FROM employee WHERE empID=?");
                        if (statement1.executeUpdate()>0) {
                            tmList.remove(tm);
                            functions(searchText);
                            new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                        }
                    }catch (ClassNotFoundException | SQLException e){
                        e.printStackTrace();
                    }


                }
            });
        }
        return tmList;
    }
}
