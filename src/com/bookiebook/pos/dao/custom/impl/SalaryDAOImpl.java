package com.bookiebook.pos.dao.custom.impl;

import com.bookiebook.pos.dao.CrudUtil;
import com.bookiebook.pos.dao.custom.SalaryDAO;
import com.bookiebook.pos.model.SalaryModel;
import com.bookiebook.pos.view.tm.SalaryTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public String setSlaryIDs() throws SQLException, ClassNotFoundException {
        String text = "";
//        String sql = "SELECT salaryID FROM `salary` ORDER BY salaryID DESC LIMIT 1";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT salaryID FROM `salary` ORDER BY salaryID DESC LIMIT 1");
        if (set.next()) {
            String tempOrderId = set.getString(1);
            String[] array = tempOrderId.split("-");//[D,3]
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            return text = "SM-" + finalizeOrderId;
        } else {
            return text = "SM-1";
        }
    }

    @Override
    public ObservableList<String> setStatus() throws SQLException, ClassNotFoundException {
        ObservableList<String> salaryTms = FXCollections.observableArrayList();
//        String sql = "SELECT status FROM employee";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet resultSet = statement.executeQuery();
        ResultSet resultSet = CrudUtil.execute("SELECT status FROM employee");

        while (resultSet.next()) {
            salaryTms.add(resultSet.getString(1));
        }
        return salaryTms;
    }


    @Override
    public ObservableList<SalaryTm> functions(String text) throws SQLException, ClassNotFoundException {
        String searchText = "%" + text + "%";

        ObservableList<SalaryTm> tmList = FXCollections.observableArrayList();

//        String sql = "SELECT * FROM salary WHERE salaryID LIKE ? || status LIKE ?";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        statement.setString(1,searchText);
//        statement.setString(2,searchText);
//        ResultSet set = statement.executeQuery();

        ResultSet set = CrudUtil.execute("SELECT * FROM salary WHERE salaryID LIKE ? || status LIKE ?", text, text);
        while (set.next()) {
            Button btn = new Button("Delete");
            SalaryTm tm = new SalaryTm(
                    set.getString(1),
                    set.getString(2),
                    set.getDouble(3),
                    btn);
            tmList.add(tm);
            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "are you sure whether do you want to delete this Salary?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {

                    try {
                        boolean delete = new SalaryModel().deleteSalary(tm);
                        if (delete) {
                            tmList.remove(tm);
                            functions(searchText);
                            new Alert(Alert.AlertType.INFORMATION, "Salary Deleted!").show();
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
