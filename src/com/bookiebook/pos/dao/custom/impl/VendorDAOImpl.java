package com.bookiebook.pos.dao.custom.impl;

import com.bookiebook.pos.dao.custom.VenderDAO;
import com.bookiebook.pos.model.VendorModel;
import com.bookiebook.pos.dao.CrudUtil;
import com.bookiebook.pos.view.tm.VendorTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VendorDAOImpl implements VenderDAO {
    public String setVendorIDs() throws SQLException, ClassNotFoundException {
        String text = "";
//        String sql = "SELECT vendorID FROM `vendor` ORDER BY vendorID DESC LIMIT 1";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT vendorID FROM `vendor` ORDER BY vendorID DESC LIMIT 1");
        if (set.next()) {
            String tempOrderId = set.getString(1);
            String[] array = tempOrderId.split("-");//[D,3]
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            return text = "VENDOR-" + finalizeOrderId;
        } else {
            return text = "VENDOR-1";
        }
    }

    @Override
    public ObservableList<VendorTm> functions(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text+"%";

        ObservableList<VendorTm> tmList = FXCollections.observableArrayList();

//        String sql = "SELECT * FROM vendor WHERE vendorID LIKE ? || name LIKE ?";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        statement.setString(1,searchText);
//        statement.setString(2,searchText);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT * FROM vendor WHERE vendorID LIKE ? || name LIKE ?",text,text);

        while (set.next()){
            Button btn = new Button("Delete");
            VendorTm tm = new VendorTm(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    btn);
            tmList.add(tm);
            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "are you sure whether do you want to delete this Vendor?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {

                    try {
                        boolean delete = new VendorModel().deletevendor(tm);
                        if (delete) {
                            tmList.remove(tm);
                            functions(searchText);
                            new Alert(Alert.AlertType.INFORMATION, "Vendor Deleted!").show();
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
