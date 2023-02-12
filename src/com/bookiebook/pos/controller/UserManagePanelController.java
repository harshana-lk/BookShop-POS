package com.bookiebook.pos.controller;

import com.bookiebook.pos.bo.BOFactory;
import com.bookiebook.pos.bo.BOTypes;
import com.bookiebook.pos.bo.custom.UserBO;
import com.bookiebook.pos.model.UserModel;
import com.bookiebook.pos.to.User;
import com.bookiebook.pos.view.tm.UserTm;
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

public class UserManagePanelController {
    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOTypes.USER);
    public JFXTextField txtID;
    public JFXTextField txtStatus;
    public JFXTextField txtPassword;
    public JFXTextField txtHint;
    public JFXButton btnSave;
    public TableView<UserTm> tblUser;
    public TableColumn<Object, Object> colEmpID;
    public TableColumn<Object, Object> colStatus;
    public TableColumn<Object, Object> colPassword;
    public TableColumn<Object, Object> colHint;
    public TextField txtSearch;
    public TableColumn<Object, Object> colOption;
    public JFXComboBox<String> cmbID;
    public TableColumn<Object, Object> colUserID;
    public TableColumn<Object, Object> colusername;
    public JFXTextField txtUserName;
    private String searchText = "";

    public boolean validate() {
        return !(txtID.getText().isEmpty() || cmbID.getValue().isEmpty() || txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty() || txtHint.getText().isEmpty() || txtStatus.getText().isEmpty());
    }

    public void initialize() {
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("empID"));
        colusername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colHint.setCellValueFactory(new PropertyValueFactory<>("hint"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchUser(searchText);

        setUserID();

        tblUser.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (null != newValue) {// newValue!=null
                        setData(newValue);
                    }
                });

        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchText = newValue;
                    searchUser(searchText);
                });

        loadEmpIDs();
    }

    private void setUserID() {
        try {

//            String sql = "SELECT userID FROM `user` ORDER BY userID DESC LIMIT 1";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            ResultSet set = statement.executeQuery();
//            if (set.next()) {
//                String tempOrderId = set.getString(1);
//                String[] array = tempOrderId.split("-");//[D,3]
//                int tempNumber = Integer.parseInt(array[1]);
//                int finalizeOrderId = tempNumber + 1;
//                txtID.setText("USER-" + finalizeOrderId);
//            } else {
//                txtID.setText("USER-1");
//            }

            String userIDs = userBO.setUserIDs();
            txtID.setText(userIDs);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadEmpIDs() {


        try {
//            ObservableList<String> userTms = FXCollections.observableArrayList();
//            String sql = "SELECT empID FROM employee";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                userTms.add(resultSet.getString(1));
//            }

            ObservableList<String> loadEmployees = userBO.loadEmployees();

            cmbID.setItems(loadEmployees);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData(UserTm tm) {
        txtID.setText(tm.getUserID());
        cmbID.setValue(tm.getEmpID());
        txtUserName.setText(tm.getUsername());
        txtStatus.setText(tm.getStatus());
        txtPassword.setText(tm.getPassword());
        txtHint.setText(tm.getHint());
        btnSave.setText("Update");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        boolean validation = validate();

        if (validation) {

            User user = new User(txtID.getText(), cmbID.getValue(), txtUserName.getText(), txtStatus.getText(), txtPassword.getText(), txtHint.getText());

            if (btnSave.getText().equalsIgnoreCase("Save")) {
                try {
                    boolean save = new UserModel().saveUser(user);
                    if (save) {
                        new Alert(Alert.AlertType.INFORMATION, "User Saved!").show();
                        searchUser(searchText);
                        clearFields();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {

                try {
                    boolean update = new UserModel().updateUser(user);
                    if (update) {
                        searchUser(searchText);
                        clearFields();
                        new Alert(Alert.AlertType.INFORMATION, "User Updated!").show();
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

    private void searchUser(String text) {
        String searchText = "%" + text + "%";
        try {

//            ObservableList<UserTm> tmList = FXCollections.observableArrayList();
//
//            String sql = "SELECT * FROM user WHERE userID LIKE ? || empID LIKE ?";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            statement.setString(1, searchText);
//            statement.setString(2, searchText);
//            ResultSet set = statement.executeQuery();
//
//            while (set.next()) {
//                Button btn = new Button("Delete");
//                UserTm tm = new UserTm(
//                        set.getString(1),
//                        set.getString(2),
//                        set.getString(3),
//                        set.getString(4),
//                        set.getString(5),
//                        set.getString(6),
//                        btn);
//                tmList.add(tm);
//                btn.setOnAction(event -> {
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                            "are you sure whether do you want to delete this User?",
//                            ButtonType.YES, ButtonType.NO);
//                    Optional<ButtonType> buttonType = alert.showAndWait();
//                    if (buttonType.get() == ButtonType.YES) {
//
//                        try {
////                            String sql1 = "DELETE FROM user WHERE userID=?";
////                            PreparedStatement statement1 = DBConnection.getInstance().getConnection().prepareStatement(sql1);
////                            statement1.setString(1, tm.getUserID());
//                            boolean delete = new UserModel().deleteUser(tm);
//                            if (delete) {
//                                searchUser(searchText);
//                                new Alert(Alert.AlertType.INFORMATION, "User Deleted!").show();
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
//            }

            ObservableList<UserTm> userTms = userBO.UserFunctions(searchText);
            tblUser.setItems(userTms);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtID.clear();
        cmbID.setValue(null);
        txtUserName.clear();
        txtStatus.clear();
        txtHint.clear();
        txtPassword.clear();
    }
}
