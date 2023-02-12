package com.bookiebook.pos.dao.custom.impl;

import com.bookiebook.pos.dao.CrudUtil;
import com.bookiebook.pos.dao.custom.CategoryDAO;
import com.bookiebook.pos.model.CategoryModel;
import com.bookiebook.pos.view.tm.CategoryTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public String setCategoryIDs() throws SQLException, ClassNotFoundException, IOException {
        String text="";
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CategoryMangePanel.fxml"));
//        Parent parent = loader.load();
//        CategoryMangePanelController controller = loader.getController();

//        String sql = "SELECT categoryID FROM `category` ORDER BY categoryID DESC LIMIT 1";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT categoryID FROM `category` ORDER BY categoryID DESC LIMIT 1");
        if (set.next()) {
            String tempOrderId = set.getString(1);
            String[] array = tempOrderId.split("-");//[D,3]
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            return text = "CM-" + finalizeOrderId;
        } else {
            return text = "CM-1";
        }
    }


    @Override
    public ObservableList<CategoryTm> functions(String text) throws SQLException, ClassNotFoundException {
        String searchText = "%" + text + "%";

        ObservableList<CategoryTm> tmList = FXCollections.observableArrayList();

//        String sql = "SELECT * FROM category WHERE categoryID LIKE ? || name LIKE ?";
//        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//        statement.setString(1, searchText);
//        statement.setString(2, searchText);
//        ResultSet set = statement.executeQuery();
        ResultSet set = CrudUtil.execute("SELECT * FROM category WHERE categoryID LIKE ? || name LIKE ?", text, text);

        while (set.next()) {
            Button btn = new Button("Delete");
            CategoryTm tm = new CategoryTm(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    btn);
            tmList.add(tm);
            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "are you sure whether do you want to delete this category?",
                        ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.get() == ButtonType.YES) {

                    try {
                        boolean delete = new CategoryModel().deleteCategory(tm);
                        if (delete) {
                            tmList.remove(tm);
                            functions(text);
                            new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
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

//    @Override
//    public ObservableList<CategoryTm> categoryFunctions(String text) throws SQLException, ClassNotFoundException {
//        String searchText = "%" + text + "%";
//
//        ObservableList<CategoryTm> tmList = FXCollections.observableArrayList();
//
////        String sql = "SELECT * FROM category WHERE categoryID LIKE ? || name LIKE ?";
////        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
////        statement.setString(1, searchText);
////        statement.setString(2, searchText);
////        ResultSet set = statement.executeQuery();
//        ResultSet set = CrudUtil.execute("SELECT * FROM category WHERE categoryID LIKE ? || name LIKE ?",text,text);
//
//        while (set.next()) {
//            Button btn = new Button("Delete");
//            CategoryTm tm = new CategoryTm(
//                    set.getString(1),
//                    set.getString(2),
//                    set.getString(3),
//                    btn);
//            tmList.add(tm);
//            btn.setOnAction(event -> {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                        "are you sure whether do you want to delete this category?",
//                        ButtonType.YES, ButtonType.NO);
//                Optional<ButtonType> buttonType = alert.showAndWait();
//                if (buttonType.get() == ButtonType.YES) {
//
//                    try {
//                        boolean delete = new CategoryModel().deleteCategory(tm);
//                        if (delete) {
//                            tmList.remove(tm);
//                            categoryFunctions(searchText);
//                            new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
//                        } else {
//                            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
//                        }
//                    } catch (ClassNotFoundException | SQLException e) {
//                        e.printStackTrace();
//                    }
//
//
//                }
//            });
//        }
//        return tmList;
//    }


}
