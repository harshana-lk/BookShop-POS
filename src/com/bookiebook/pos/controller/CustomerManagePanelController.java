package com.bookiebook.pos.controller;

import com.bookiebook.pos.DB.DBConnection;
import com.bookiebook.pos.model.CustomerModel;
import com.bookiebook.pos.to.Customer;
import com.bookiebook.pos.util.Validator;
import com.bookiebook.pos.view.tm.CustomerTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerManagePanelController {

    public JFXTextField txtID;
    public JFXTextField txtName;
    public JFXTextField txtPhone;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXButton btnSave;
    public TableView<CustomerTm> tblCustomer;
    public TableColumn<Object, Object> colID;
    public TableColumn<Object, Object> colName;
    public TableColumn<Object, Object> colNIC;
    public TableColumn<Object, Object> colAddress;
    public TableColumn<Object, Object> colPhone;
    public TableColumn<Object, Object> colOption;
    public TextField txtSearch;

    private String searchText = "";

    public boolean validate(){
        if (!(txtID.getText().isEmpty()||txtName.getText().isEmpty()||txtNIC.getText().isEmpty()||txtAddress.getText().isEmpty()||txtPhone.getText().isEmpty())){
            return true;
        }
        return false;
    }

    public void initialize(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        setCustomerID();

        searchCustomer(searchText);



        tblCustomer.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (null!=newValue){// newValue!=null
                        setData(newValue);
                    }
                });

        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchText=newValue;
                    searchCustomer(searchText);
                });
    }

    private void setCustomerID() {
        try {

            String sql = "SELECT custID FROM `customer` ORDER BY custID DESC LIMIT 1";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                String tempOrderId = set.getString(1);
                String[] array = tempOrderId.split("-");//[D,3]
                int tempNumber = Integer.parseInt(array[1]);
                int finalizeOrderId = tempNumber + 1;
                txtID.setText("CUST-" + finalizeOrderId);
            } else {
                txtID.setText("CUST-1");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData(CustomerTm tm) {
        txtID.setText(tm.getId());
        txtName.setText(tm.getName());
        txtNIC.setText(tm.getNic());
        txtAddress.setText(tm.getAddress());
        txtPhone.setText(String.valueOf(tm.getPhone()));
        btnSave.setText("Update");

    }

    private void searchCustomer(String text) {
        String searchText="%"+text+"%";
        try {

            ObservableList<CustomerTm> tmList = FXCollections.observableArrayList();

            String sql = "SELECT * FROM customer WHERE custID LIKE ? || name LIKE ?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            ResultSet set = statement.executeQuery();

            while (set.next()){
                Button btn = new Button("Delete");
                CustomerTm tm = new CustomerTm(
                        set.getString(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getInt(5),
                        btn);
                tmList.add(tm);
                btn.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "are you sure whether do you want to delete this Customer?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {

                        try {
                            boolean delete = new CustomerModel().deleteCustomer(tm);
                            if (delete) {
                                searchCustomer(searchText);
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
            tblCustomer.setItems(tmList);

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

    public void saveOnAction(ActionEvent actionEvent) {
        boolean validation = validate();
        if (Validator.isNicMatch(txtNIC.getText())) {
            if (validation) {
                Customer customer = new Customer(txtID.getText(), txtName.getText(),
                        txtNIC.getText(), txtAddress.getText(), Integer.parseInt(txtPhone.getText()));

                if (btnSave.getText().equalsIgnoreCase("Save")) {
                    try {
                        boolean save = new CustomerModel().saveCustomer(customer);
                        if (save) {
                            searchCustomer(searchText);
                            clearFields();
                            setCustomerID();
                            new Alert(Alert.AlertType.INFORMATION, "Customer Saved!").show();
                        } else {
                            new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        boolean update = new CustomerModel().updateCustomer(customer);
                        if (update) {
                            searchCustomer(searchText);
                            clearFields();
                            setCustomerID();
                            new Alert(Alert.AlertType.INFORMATION, "Customer Updated!").show();
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
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please Enter Validate Data !");
            alert.show();
            txtNIC.requestFocus();
        }
    }

    private void clearFields() {
        txtID.clear();
        txtNIC.clear();
        txtName.clear();
        txtAddress.clear();
        txtPhone.clear();
    }
}
