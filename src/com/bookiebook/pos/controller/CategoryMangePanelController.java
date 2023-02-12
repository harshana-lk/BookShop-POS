package com.bookiebook.pos.controller;

import com.bookiebook.pos.bo.BOFactory;
import com.bookiebook.pos.bo.BOTypes;
import com.bookiebook.pos.bo.custom.CategoryBO;
import com.bookiebook.pos.bo.custom.impl.CategoryBOImpl;
import com.bookiebook.pos.model.CategoryModel;
import com.bookiebook.pos.to.Category;
import com.bookiebook.pos.view.tm.CategoryTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;

public class CategoryMangePanelController {

    private final CategoryBO categoryBO = (CategoryBO) BOFactory.getInstance().getBO(BOTypes.CATEGORY);
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXButton btnSave;
    public TextField txtSearch;
    public TableColumn<Object, Object> colID;
    public TableColumn<Object, Object> colName;
    public TableColumn<Object, Object> colDescription;
    public TableColumn<Object, Object> colOption;
    public JFXTextField txtDescription;
    public TableView<CategoryTm> tblcategory;
    private String searchText = "";

    public boolean validate() {
        return !(txtID.getText().isEmpty() || txtName.getText().isEmpty() || txtDescription.getText().isEmpty());
    }

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchCategory(searchText);

        setCategoryID();

        tblcategory.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (null != newValue) {// newValue!=null
                        setData(newValue);
                    }
                });

        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchText = newValue;
                    searchCategory(searchText);
                });
    }

    private void setCategoryID() {
        try {
            String categoryIDs = categoryBO.setCategoryIDs();
            txtID.setText(categoryIDs);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void setData(CategoryTm tm) {
        txtID.setText(tm.getId());
        txtName.setText(tm.getName());
        txtDescription.setText(tm.getDescription());
        btnSave.setText("Update");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        boolean validation = validate();

        if (validation) {
            Category category = new Category(txtID.getText(), txtName.getText(), txtDescription.getText());

            if (btnSave.getText().equalsIgnoreCase("Save")) {
                try {
                    boolean save = new CategoryModel().saveCategory(category);
                    if (save) {
                        searchCategory(searchText);
                        clearFields();
                        setCategoryID();
                        new Alert(Alert.AlertType.INFORMATION, "Category Saved!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

                try {
                    boolean update = new CategoryModel().updateCategory(category);
                    if (update) {
                        searchCategory(searchText);
                        clearFields();
                        setCategoryID();
                        new Alert(Alert.AlertType.INFORMATION, "Category Updated!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please Fill the Unfilled Data !");
            alert.show();
        }
    }

    private void searchCategory(String text) {
        String searchText = "%" + text + "%";
        try {

//            ObservableList<CategoryTm> tmList = FXCollections.observableArrayList();
//
//            String sql = "SELECT * FROM category WHERE categoryID LIKE ? || name LIKE ?";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            statement.setString(1, searchText);
//            statement.setString(2, searchText);
//            ResultSet set = statement.executeQuery();
//
//            while (set.next()) {
//                Button btn = new Button("Delete");
//                CategoryTm tm = new CategoryTm(
//                        set.getString(1),
//                        set.getString(2),
//                        set.getString(3),
//                        btn);
//                tmList.add(tm);
//                btn.setOnAction(event -> {
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                            "are you sure whether do you want to delete this category?",
//                            ButtonType.YES, ButtonType.NO);
//                    Optional<ButtonType> buttonType = alert.showAndWait();
//                    if (buttonType.get() == ButtonType.YES) {
//
//                        try {
//                            boolean delete = new CategoryModel().deleteCategory(tm);
//                            if (delete) {
//                                searchCategory(searchText);
//                                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
//                            } else {
//                                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
//                            }
//                        } catch (ClassNotFoundException | SQLException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });


            ObservableList<CategoryTm> functions = categoryBO.CategoryFunctions(searchText);
            tblcategory.setItems(functions);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }


    }

    private void clearFields() {
        txtID.clear();
        txtName.clear();
        txtDescription.clear();
    }
}
