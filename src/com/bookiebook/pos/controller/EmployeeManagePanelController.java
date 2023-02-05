package com.bookiebook.pos.controller;

import com.bookiebook.pos.DB.DBConnection;
import com.bookiebook.pos.model.EmployeeModel;
import com.bookiebook.pos.to.Employee;
import com.bookiebook.pos.view.tm.EmployeeTm;
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

public class EmployeeManagePanelController {

    public TableColumn<Object, Object> colPhone;
    public TableColumn<Object, Object> colAddress;
    public TableColumn<Object, Object> colStatus;
    public TableColumn<Object, Object> colName;
    public TableColumn<Object, Object> colID;
    public TableView<EmployeeTm> tblEmployee;
    public JFXButton btnSave;
    public JFXTextField txtPhone;
    public JFXTextField txtAddress;
    public JFXTextField txtStatus;
    public JFXTextField txtName;
    public JFXTextField txtID;
    public TextField txtSearch;
    public TableColumn<Object, Object> colOption;

    private String searchText="";

    public boolean validate(){
        if (!(txtID.getText().isEmpty()||txtName.getText().isEmpty()||txtStatus.getText().isEmpty()||txtAddress.getText().isEmpty()||txtPhone.getText().isEmpty())){
            return true;
        }
        return false;
    }

    public void initialize(){
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchEmployee(searchText);

        setEmployeeID();

        tblEmployee.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (null!=newValue){// newValue!=null
                        setData(newValue);
                    }
                });

        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchText=newValue;
                    searchEmployee(searchText);
                });

    }

    private void setEmployeeID() {
        try {

            String sql = "SELECT empID FROM `employee` ORDER BY empID DESC LIMIT 1";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                String tempOrderId = set.getString(1);
                String[] array = tempOrderId.split("-");//[D,3]
                int tempNumber = Integer.parseInt(array[1]);
                int finalizeOrderId = tempNumber + 1;
                txtID.setText("EMP-" + finalizeOrderId);
            } else {
                txtID.setText("EMP-1");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData(EmployeeTm tm) {
        txtID.setText(tm.getId());
        txtName.setText(tm.getName());
        txtStatus.setText(tm.getStatus());
        txtAddress.setText(tm.getAddress());
        txtPhone.setText(String.valueOf(tm.getPhone()));
        btnSave.setText("Update");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        boolean validation = validate();

        if (validation) {
            Employee employee = new Employee(txtID.getText(), txtName.getText(),
                    txtStatus.getText(), txtAddress.getText(), Integer.parseInt(txtPhone.getText()));

            if (btnSave.getText().equalsIgnoreCase("Save")) {
                try {
                    boolean save = new EmployeeModel().saveEmployee(employee);
                    if (save) {
                        searchEmployee(searchText);
                        clearFields();
                        setEmployeeID();
                        new Alert(Alert.AlertType.INFORMATION, "Employee Saved!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

                try {
                    boolean update = new EmployeeModel().updateEmployee(employee);
                    if (update) {
                        searchEmployee(searchText);
                        clearFields();
                        setEmployeeID();
                        new Alert(Alert.AlertType.INFORMATION, "Employee Updated!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Please Fill the Unfilled Data !");
            alert.show();
        }
    }

    private void searchEmployee(String text) {
        String searchText="%"+text+"%";
        try {

            ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

            String sql = "SELECT * FROM employee WHERE empID LIKE ? || name LIKE ?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1,searchText);
            statement.setString(2,searchText);
            ResultSet set = statement.executeQuery();

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
                            String sql1 = "DELETE FROM employee WHERE empID=?";
                            PreparedStatement statement1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
                            statement1.setString(1,tm.getId());
                            if (statement1.executeUpdate()>0) {
                                searchEmployee(searchText);
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
            tblEmployee.setItems(tmList);

        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

    private void clearFields() {
        txtID.clear();
        txtName.clear();
        txtSearch.clear();
        txtAddress.clear();
        txtStatus.clear();
        txtPhone.clear();
    }
}

