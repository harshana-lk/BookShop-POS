package com.bookiebook.pos.controller;

import com.bookiebook.pos.bo.BOFactory;
import com.bookiebook.pos.bo.BOTypes;
import com.bookiebook.pos.bo.custom.DeliveryBO;
import com.bookiebook.pos.model.DeliveryModel;
import com.bookiebook.pos.to.Delivery;
import com.bookiebook.pos.view.tm.DeliveryTm;
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

public class DeliveryMethodPanelController {
    private final DeliveryBO deliveryBO = (DeliveryBO) BOFactory.getInstance().getBO(BOTypes.DELIVERY);
    public JFXTextField txtID;
    public JFXTextField txtMethod;
    public JFXTextField txtStatus;
    public JFXTextField txtPrice;
    public JFXButton btnSave;
    public TableView<DeliveryTm> tblDelivery;
    public TableColumn<Object, Object> colID;
    public TableColumn<Object, Object> colMethod;
    public TableColumn<Object, Object> colStatus;
    public TableColumn<Object, Object> colPrice;
    public TextField txtSearch;
    public TableColumn<Object, Object> colOption;
    private String searchText = "";

    public boolean validate() {
        return !(txtID.getText().isEmpty() || txtMethod.getText().isEmpty() || txtStatus.getText().isEmpty() || txtPrice.getText().isEmpty());
    }

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));

        searchDelivery(searchText);

        setDeliveryID();

        tblDelivery.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (null != newValue) {// newValue!=null
                        setData(newValue);
                    }
                });

        txtSearch.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchText = newValue;
                    searchDelivery(searchText);
                });
    }

    private void setDeliveryID() {
        try {

//            String sql = "SELECT deliveryID FROM `delivery` ORDER BY deliveryID DESC LIMIT 1";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            ResultSet set = statement.executeQuery();
//            if (set.next()) {
//                String tempOrderId = set.getString(1);
//                String[] array = tempOrderId.split("-");//[D,3]
//                int tempNumber = Integer.parseInt(array[1]);
//                int finalizeOrderId = tempNumber + 1;
//                txtID.setText("DM-" + finalizeOrderId);
//            } else {
//                txtID.setText("DM-1");
//            }


            String deliveryIDs = deliveryBO.setDeliveryIDs();
            txtID.setText(deliveryIDs);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setData(DeliveryTm tm) {
        txtID.setText(tm.getId());
        txtMethod.setText(tm.getMethod());
        txtStatus.setText(tm.getStatus());
        txtPrice.setText(String.valueOf(tm.getPrice()));
        btnSave.setText("Update");
    }

    public void saveOnAction(ActionEvent actionEvent) {
        boolean validation = validate();

        if (validation) {

            Delivery delivery = new Delivery(txtID.getText(), txtMethod.getText(), txtStatus.getText(), Double.parseDouble(txtPrice.getText()));

            if (btnSave.getText().equalsIgnoreCase("Save")) {
                try {
                    boolean save = new DeliveryModel().saveDelivery(delivery);
                    if (save) {
                        searchDelivery(searchText);
                        clearFields();
                        setDeliveryID();
                        new Alert(Alert.AlertType.INFORMATION, "Delivery Saved!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {

                try {
                    boolean update = new DeliveryModel().updateDelivery(delivery);
                    if (update) {
                        searchDelivery(searchText);
                        clearFields();
                        setDeliveryID();
                        new Alert(Alert.AlertType.INFORMATION, "Delivery Updated!").show();
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

    private void searchDelivery(String text) {
        String searchText = "%" + text + "%";
        try {

//            ObservableList<DeliveryTm> tmList = FXCollections.observableArrayList();
//
//            String sql = "SELECT * FROM delivery WHERE deliveryID LIKE ? || method LIKE ?";
//            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
//            statement.setString(1,searchText);
//            statement.setString(2,searchText);
//            ResultSet set = statement.executeQuery();
//
//            while (set.next()){
//                Button btn = new Button("Delete");
//                DeliveryTm tm = new DeliveryTm(
//                        set.getString(1),
//                        set.getString(2),
//                        set.getString(3),
//                        set.getDouble(4),
//                        btn);
//                tmList.add(tm);
//                btn.setOnAction(event -> {
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
//                            "are you sure whether do you want to delete this Delivery?",
//                            ButtonType.YES, ButtonType.NO);
//                    Optional<ButtonType> buttonType = alert.showAndWait();
//                    if (buttonType.get() == ButtonType.YES) {
//
//                        try {
//                           boolean delete = new DeliveryModel().deleteDelivery(tm);
//                            if (delete) {
//                                searchDelivery(searchText);
//                                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted!").show();
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

            ObservableList<DeliveryTm> deliveryTms = deliveryBO.DeliveryFunctions(searchText);
            tblDelivery.setItems(deliveryTms);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtID.clear();
        txtMethod.clear();
        txtStatus.clear();
        txtPrice.clear();
    }
}
