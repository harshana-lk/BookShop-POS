package com.bookiebook.pos.controller;

import com.bookiebook.pos.bo.BOFactory;
import com.bookiebook.pos.bo.BOTypes;
import com.bookiebook.pos.bo.custom.SalaryBO;
import com.bookiebook.pos.model.SalaryModel;
import com.bookiebook.pos.to.Salary;
import com.bookiebook.pos.view.tm.SalaryTm;
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

public class SalaryManagePanelController {
    private final SalaryBO salaryBO = (SalaryBO) BOFactory.getInstance().getBO(BOTypes.SALARY);
    public JFXTextField txtID;
    public JFXTextField txtSalary;
    public JFXButton btnSave;
    public TableColumn<Object, Object> colID;
    public TableColumn<Object, Object> colStatus;
    public TextField txtSearch;
    public TableColumn<Object, Object> colSalary;
    public JFXComboBox<String> cmbStatus;
    public TableColumn<Object, Object> colOption;
    public TableView<SalaryTm> tblSalary;
    private String searchText = "";

    public boolean validate() {
        return !(txtID.getText().isEmpty() || cmbStatus.getValue().equals(null) || txtSalary.getText().isEmpty());
    }

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchSalary(searchText);

        setStatusData();

        setSalaryID();

        tblSalary.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (null != newValue) {// newValue!=null
                        setData(newValue);
                    }
                });

        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchText = newValue;
                    searchSalary(searchText);
                });
    }

    private void setSalaryID() {
        try {

//            String sql = "SELECT salaryID FROM `salary` ORDER BY salaryID DESC LIMIT 1";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            ResultSet set = statement.executeQuery();
//            if (set.next()) {
//                String tempOrderId = set.getString(1);
//                String[] array = tempOrderId.split("-");//[D,3]
//                int tempNumber = Integer.parseInt(array[1]);
//                int finalizeOrderId = tempNumber + 1;
//                txtID.setText("SM-" + finalizeOrderId);
//            } else {
//                txtID.setText("SM-1");
//            }

            String slaryIDs = salaryBO.setSlaryIDs();
            txtID.setText(slaryIDs);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setStatusData() {


        try {
//            ObservableList<String> salaryTms = FXCollections.observableArrayList();
//            String sql = "SELECT status FROM employee";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            ResultSet resultSet = statement.executeQuery();
//
//            while(resultSet.next()){
//                salaryTms.add(resultSet.getString(1));
//            }

            ObservableList<String> status = salaryBO.setStatus();

            cmbStatus.setItems(status);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData(SalaryTm tm) {
        txtID.setText(tm.getId());
        cmbStatus.setValue(tm.getStatus());
        txtSalary.setText(String.valueOf(tm.getSalary()));
        btnSave.setText("Update");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        boolean validation = validate();

        if (validation) {
            Salary salary = new Salary(txtID.getText(), cmbStatus.getValue(), Double.parseDouble(txtSalary.getText()));

            if (btnSave.getText().equalsIgnoreCase("Save")) {
                try {
                    boolean save = new SalaryModel().saveSalary(salary);
                    if (save) {
                        searchSalary(searchText);
                        clearFields();
                        setSalaryID();
                        new Alert(Alert.AlertType.INFORMATION, "Salary Saved!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

                try {
                    boolean update = new SalaryModel().updateSalary(salary);
                    if (update) {
                        searchSalary(searchText);
                        clearFields();
                        setSalaryID();
                        new Alert(Alert.AlertType.INFORMATION, "Salary Updated!").show();
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

    private void clearFields() {
        txtID.clear();
        cmbStatus.setValue(null);
        txtSalary.clear();
    }

    private void searchSalary(String text) {
        String searchText = "%" + text + "%";
        try {

//                ObservableList<SalaryTm> tmList = FXCollections.observableArrayList();
//
//                String sql = "SELECT * FROM salary WHERE salaryID LIKE ? || status LIKE ?";
//                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//                statement.setString(1,searchText);
//                statement.setString(2,searchText);
//                ResultSet set = statement.executeQuery();
//
//                while (set.next()){
//                    Button btn = new Button("Delete");
//                    SalaryTm tm = new SalaryTm(
//                            set.getString(1),
//                            set.getString(2),
//                            set.getDouble(3),
//                            btn);
//                    tmList.add(tm);
//                    btn.setOnAction(event -> {
//                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                                "are you sure whether do you want to delete this Salary?",
//                                ButtonType.YES, ButtonType.NO);
//                        Optional<ButtonType> buttonType = alert.showAndWait();
//                        if (buttonType.get() == ButtonType.YES) {
//
//                            try {
//                                boolean delete = new SalaryModel().deleteSalary(tm);
//                                if (delete) {
//                                    searchSalary(searchText);
//                                    new Alert(Alert.AlertType.INFORMATION, "Salary Deleted!").show();
//                                } else {
//                                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
//                                }
//                            }catch (ClassNotFoundException | SQLException e){
//                                e.printStackTrace();
//                            }
//
//
//                        }
//                    });
//                }


            ObservableList<SalaryTm> salaryTms = salaryBO.SalaryFunctions(searchText);
            tblSalary.setItems(salaryTms);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
