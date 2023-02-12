package com.bookiebook.pos.dao.custom.impl;

import com.bookiebook.pos.dao.custom.DeliveryDAO;
import com.bookiebook.pos.model.DeliveryModel;
import com.bookiebook.pos.dao.CrudUtil;
import com.bookiebook.pos.view.tm.DeliveryTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DeliveryDAOImpl implements DeliveryDAO {
    String text = "";
    @Override
    public String setDeliveryIDs() throws SQLException, ClassNotFoundException {
//        String sql = "SELECT deliveryID FROM `delivery` ORDER BY deliveryID DESC LIMIT 1";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT deliveryID FROM `delivery` ORDER BY deliveryID DESC LIMIT 1");
        if (set.next()) {
            String tempOrderId = set.getString(1);
            String[] array = tempOrderId.split("-");//[D,3]
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            return text = "DM-" + finalizeOrderId;
        } else {
            return text = "DM-1";
        }
    }

    @Override
    public ObservableList<DeliveryTm> functions(String text) throws SQLException, ClassNotFoundException {
        String searchText="%"+text+"%";

        ObservableList<DeliveryTm> tmList = FXCollections.observableArrayList();

//        String sql = "SELECT * FROM delivery WHERE deliveryID LIKE ? || method LIKE ?";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        statement.setString(1,searchText);
//        statement.setString(2,searchText);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT * FROM delivery WHERE deliveryID LIKE ? || method LIKE ?",text,text);

        while (set.next()){
            Button btn = new Button("Delete");
            DeliveryTm tm = new DeliveryTm(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getDouble(4),
                    btn);
            tmList.add(tm);
            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "are you sure whether do you want to delete this Delivery?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {

                    try {
                        boolean delete = new DeliveryModel().deleteDelivery(tm);
                        if (delete) {
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
