package com.bookiebook.pos.controller;

import com.bookiebook.pos.bo.BOFactory;
import com.bookiebook.pos.bo.BOTypes;
import com.bookiebook.pos.bo.custom.ItemBO;
import com.bookiebook.pos.model.ItemModel;
import com.bookiebook.pos.to.Item;
import com.bookiebook.pos.view.tm.ItemTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class ItemManagePanelComtroller {
    private final ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOTypes.ITEM);
    public TableView<ItemTm> tblItem;
    public JFXButton btnSave;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;
    public JFXTextField txtName;
    public JFXTextField txtID;
    public TextField txtSearch;
    public TableColumn<Object, Object> colID;
    public TableColumn<Object, Object> colCategory;
    public TableColumn<Object, Object> colQty;
    public TableColumn<Object, Object> colPrice;
    public TableColumn<Object, Object> colOption;
    public JFXComboBox<String> cmbcategory;
    public JFXComboBox<String> cmbVendor;
    public TableColumn<Object, Object> colName;
    public TableColumn<Object, Object> colVendor;
    private String searchText = "";

    public boolean validate() {
        return !(txtID.getText().isEmpty() || txtName.getText().isEmpty() || cmbVendor.getValue().isEmpty() || cmbcategory.getValue().isEmpty() || txtQty.getText().isEmpty() || txtPrice.getText().isEmpty());
    }

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("categoryID"));
        colVendor.setCellValueFactory(new PropertyValueFactory<>("vendorID"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadCategories();

        loadVendors();

        searchItem(searchText);

        setItemID();

        tblItem.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (null != newValue) {// newValue!=null
                        setData(newValue);
                    }
                });

        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchText = newValue;
                    searchItem(searchText);
                });
    }

    private void setItemID() {
        try {

//            String sql = "SELECT itemID FROM `item` ORDER BY itemID DESC LIMIT 1";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            ResultSet set = statement.executeQuery();
//            if (set.next()) {
//                String tempOrderId = set.getString(1);
//                String[] array = tempOrderId.split("-");//[D,3]
//                int tempNumber = Integer.parseInt(array[1]);
//                int finalizeOrderId = tempNumber + 1;
//                txtID.setText("ITEM-" + finalizeOrderId);
//            } else {
//                txtID.setText("ITEM-1");
//            }

            String itemIDs = itemBO.setItemIDs();
            txtID.setText(itemIDs);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadVendors() {


        try {
//            ObservableList<String> vendorTms = FXCollections.observableArrayList();
//            String sql = "SELECT name FROM vendor";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                vendorTms.add(resultSet.getString(1));
//            }

            ObservableList<String> vendors = itemBO.loadVendors();
            cmbVendor.setItems(vendors);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void loadCategories() {


        try {
//            ObservableList<String> categoryTms = FXCollections.observableArrayList();
//            String sql = "SELECT name FROM category";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                categoryTms.add(resultSet.getString(1));
//            }


            ObservableList<String> categories = itemBO.loadCategories();
            cmbcategory.setItems(categories);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData(ItemTm tm) {
        txtID.setText(tm.getItemID());
        txtName.setText(tm.getName());
        cmbcategory.setValue(tm.getCategoryID());
        cmbVendor.setValue(tm.getVendorID());
        txtQty.setText(String.valueOf(tm.getQty()));
        txtPrice.setText(String.valueOf(tm.getPrice()));
        btnSave.setText("Update");

    }

    public void saveOnAction(ActionEvent actionEvent) {
        boolean validation = validate();
        if (validation) {
            Item item = new Item(txtID.getText(), txtName.getText(),
                    String.valueOf(cmbcategory.getValue()), String.valueOf(cmbVendor.getValue()),
                    Integer.parseInt(txtQty.getText()), Double.parseDouble(txtPrice.getText()));

            if (btnSave.getText().equalsIgnoreCase("Save")) {
                try {
                    boolean save = new ItemModel().saveItem(item);
                    if (save) {
                        System.out.println("Item added");
                        searchItem(searchText);
                        clearFields();
                        setItemID();
                        new Alert(Alert.AlertType.INFORMATION, "Item Saved!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

                try {
                    boolean update = new ItemModel().updateItem(item);
                    if (update) {
                        searchItem(searchText);
                        clearFields();
                        setItemID();
                        new Alert(Alert.AlertType.INFORMATION, "Item Updated!").show();
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

    private void searchItem(String text) {
        String searchText = "%" + text + "%";

        try {

//            ObservableList<ItemTm> tmList = FXCollections.observableArrayList();
//
//            String sql = "SELECT * FROM item WHERE itemID LIKE ? || name LIKE ?";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            statement.setString(1, searchText);
//            statement.setString(2, searchText);
//            ResultSet set = statement.executeQuery();
//
//            while (set.next()) {
//                Button btn = new Button("Delete");
//                ItemTm tm = new ItemTm(
//                        set.getString(1),
//                        set.getString(2),
//                        set.getString(3),
//                        set.getString(4),
//                        set.getInt(5),
//                        set.getDouble(6),
//                        btn);
//                tmList.add(tm);
//                btn.setOnAction(event -> {
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                            "are you sure whether do you want to delete this item?",
//                            ButtonType.YES, ButtonType.NO);
//                    Optional<ButtonType> buttonType = alert.showAndWait();
//                    if (buttonType.get() == ButtonType.YES) {
//
//                        try {
//                            boolean delete = new ItemModel().deleteItem(tm);
//                            if (delete) {
//                                searchItem(searchText);
//                                new Alert(Alert.AlertType.INFORMATION, "Item Deleted!").show();
//                            } else {
//                                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
//                            }
//                        } catch (ClassNotFoundException | SQLException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }

            ObservableList<ItemTm> itemTms = itemBO.ItemFunctions(searchText);
            tblItem.setItems(itemTms);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    private void clearFields() {
        txtID.clear();
        txtName.clear();
        cmbcategory.setValue(null);
        cmbVendor.setValue(null);
        txtQty.clear();
        txtPrice.clear();
    }
}
