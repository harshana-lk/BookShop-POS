package com.bookiebook.pos.controller;

import com.bookiebook.pos.bo.BOFactory;
import com.bookiebook.pos.bo.BOTypes;
import com.bookiebook.pos.bo.custom.VendorBO;
import com.bookiebook.pos.model.VendorModel;
import com.bookiebook.pos.to.Vendor;
import com.bookiebook.pos.view.tm.VendorTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class VendorManagePanelController {

    private final VendorBO vendorBO = (VendorBO) BOFactory.getInstance().getBO(BOTypes.VENDOR);
    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXButton btnSave;
    public TableColumn<Object, Object> colID;
    public TableColumn<Object, Object> colName;
    public TableColumn<Object, Object> colDescription;
    public TextField txtSearch;
    public TableView<VendorTm> tblVendor;
    public TableColumn<Object, Object> colOption;
    public JFXTextField txtDescription;
    private String searchText = "";

    public boolean validate() {
        return !(txtID.getText().isEmpty() || txtName.getText().isEmpty() || txtDescription.getText().isEmpty());
    }

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("vendorID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchVendor(searchText);

        setVendorID();

        tblVendor.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (null != newValue) {// newValue!=null
                        setData(newValue);
                    }
                });

        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchText = newValue;
                    searchVendor(searchText);
                });
    }

    private void setVendorID() {
        try {

//            String sql = "SELECT vendorID FROM `vendor` ORDER BY vendorID DESC LIMIT 1";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            ResultSet set = statement.executeQuery();
//            if (set.next()) {
//                String tempOrderId = set.getString(1);
//                String[] array = tempOrderId.split("-");//[D,3]
//                int tempNumber = Integer.parseInt(array[1]);
//                int finalizeOrderId = tempNumber + 1;
//                txtID.setText("VENDOR-" + finalizeOrderId);
//            } else {
//                txtID.setText("VENDOR-1");
//            }

            String vendorIDs = vendorBO.setVendorIDs();
            txtID.setText(vendorIDs);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData(VendorTm tm) {
        txtID.setText(tm.getVendorID());
        txtName.setText(tm.getName());
        txtDescription.setText(tm.getDescription());
        btnSave.setText("Update");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        boolean validation = validate();

        if (validation) {
            Vendor vendor = new Vendor(txtID.getText(), txtName.getText(), txtDescription.getText());

            if (btnSave.getText().equalsIgnoreCase("Save")) {
                try {
                    boolean save = new VendorModel().saveVendor(vendor);
                    if (save) {
                        searchVendor(searchText);
                        clearFields();
                        setVendorID();
                        new Alert(Alert.AlertType.INFORMATION, "Vendor Saved!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

                try {
                    boolean update = new VendorModel().updateVendor(vendor);
                    if (update) {
                        searchVendor(searchText);
                        clearFields();
                        setVendorID();
                        new Alert(Alert.AlertType.INFORMATION, "Vendor Updated!").show();
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

    private void searchVendor(String text) {
        String searchText = "%" + text + "%";
        try {

//            ObservableList<VendorTm> tmList = FXCollections.observableArrayList();
//
//            String sql = "SELECT * FROM vendor WHERE vendorID LIKE ? || name LIKE ?";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            statement.setString(1,searchText);
//            statement.setString(2,searchText);
//            ResultSet set = statement.executeQuery();
//
//            while (set.next()){
//                Button btn = new Button("Delete");
//                VendorTm tm = new VendorTm(
//                        set.getString(1),
//                        set.getString(2),
//                        set.getString(3),
//                        btn);
//                tmList.add(tm);
//                btn.setOnAction(event -> {
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                            "are you sure whether do you want to delete this Vendor?",
//                            ButtonType.YES, ButtonType.NO);
//                    Optional<ButtonType> buttonType = alert.showAndWait();
//                    if (buttonType.get() == ButtonType.YES) {
//
//                        try {
//                            boolean delete = new VendorModel().deletevendor(tm);
//                            if (delete) {
//                                searchVendor(searchText);
//                                new Alert(Alert.AlertType.INFORMATION, "Vendor Deleted!").show();
//                            } else {
//                                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
//                            }
//                        }catch (ClassNotFoundException | SQLException e){
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                });
//            }

            ObservableList<VendorTm> vendorTms = vendorBO.VendorFunctions(searchText);
            tblVendor.setItems(vendorTms);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtID.clear();
        txtName.clear();
        txtDescription.clear();
    }
}
