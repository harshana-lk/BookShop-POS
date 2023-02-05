package com.bookiebook.pos.controller;

import com.bookiebook.pos.DB.DBConnection;
import com.bookiebook.pos.to.ItemDetails;
import com.bookiebook.pos.to.Order;
import com.bookiebook.pos.view.tm.CartTm;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public class PlaceOrderPanelController {

    public AnchorPane PlaceOrderContext;
    public JFXButton btnAddToCart;
    public TableView<CartTm> tblCart;
    public TableColumn<Object, Object> colItemID;
    public TableColumn<Object, Object> colName;
    public TableColumn<Object, Object> colUnitPrice;
    public TableColumn<Object, Object> colQty;
    public TableColumn<Object, Object> colDeliveryFee;
    public TableColumn<Object, Object> colTotal;
    public TableColumn<Object, Object> colOption;
    public Label lblTotal;
    public JFXButton btnPlaceOrder;
    public JFXComboBox<String> cmbDeliverID;
    public JFXTextField txtMethod;
    public JFXTextField txtStatus;
    public JFXTextField txtDeliveryPrice;
    public JFXTextField txtDistance;
    public JFXComboBox<String> cmbItemID;
    public JFXTextField txtItemName;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtQty;
    public JFXComboBox<String> cmbCustID;
    public JFXTextField txtCustname;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCustPhone;
    public Label lblOrderID;
    public Label lblOrderDate;
    ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public boolean validate(){
        if (!(cmbCustID.getValue().isEmpty()||cmbItemID.getValue().isEmpty()||cmbDeliverID.getValue().isEmpty()||txtDistance.getText().isEmpty()||txtQty.getText().isEmpty())){
            return true;
        }
        return false;
    }

    public void initialize() {
        colItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDeliveryFee.setCellValueFactory(new PropertyValueFactory<>("deliveryFee"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("btn"));


        setOrderDate();
        setOrderID();
        loadCustomerIDs();
        loadItemIDs();
        loadDeliveryIDs();

        cmbCustID.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setCustomerDetails();
                    }
                });

        cmbItemID.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setItemDetails();
                    }
                });

        cmbDeliverID.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setDeliveryDetails();
                    }
                });


    }

    private void loadDeliveryIDs() {
        try {

            String sql = "SELECT deliveryID  FROM delivery";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet set = statement.executeQuery();

            ArrayList<String> idList = new ArrayList<>();
            while (set.next()) {
                idList.add(set.getString(1));
            }
            ObservableList<String> obList = FXCollections.observableArrayList(idList);
            cmbDeliverID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setDeliveryDetails() {
        try {


            String sql = "SELECT * FROM delivery WHERE deliveryID=?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, cmbDeliverID.getValue());
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                txtMethod.setText(set.getString(2));
                txtStatus.setText((set.getString(3)));
                txtDeliveryPrice.setText(String.valueOf(set.getDouble(4)));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setItemDetails() {
        try {
            String sql = "SELECT * FROM item WHERE itemID=?";
            int tempQ = 0;
            for (CartTm c : obList
            ) {
                if (c.getItemID().equalsIgnoreCase(cmbItemID.getValue())) {
                    tempQ += c.getQty();
                }
            }

            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, cmbItemID.getValue());
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                txtItemName.setText(set.getString(2));
                txtUnitPrice.setText(String.valueOf(set.getDouble(6)));
                txtQtyOnHand.setText(String.valueOf(set.getInt(5) - tempQ));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadItemIDs() {
        try {

            String sql = "SELECT itemID  FROM item";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet set = statement.executeQuery();

            ArrayList<String> idList = new ArrayList<>();
            while (set.next()) {
                idList.add(set.getString(1));
            }
            ObservableList<String> obList = FXCollections.observableArrayList(idList);
            cmbItemID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomerIDs() {
        try {

            String sql = "SELECT custID  FROM customer";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet set = statement.executeQuery();

            ArrayList<String> idList = new ArrayList<>();
            while (set.next()) {
                idList.add(set.getString(1));
            }
            ObservableList<String> obList = FXCollections.observableArrayList(idList);
            cmbCustID.setItems(obList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setCustomerDetails() {
        try {


            String sql = "SELECT * FROM customer WHERE custID=?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, cmbCustID.getValue());
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                txtCustname.setText(set.getString(2));
                txtCustAddress.setText(set.getString(3));
                txtCustPhone.setText((set.getString(4)));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setOrderID() {
        try {

            String sql = "SELECT orderID FROM `orders` ORDER BY orderID DESC LIMIT 1";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                String tempOrderId = set.getString(1);
                String[] array = tempOrderId.split("-");//[D,3]
                int tempNumber = Integer.parseInt(array[1]);
                int finalizeOrderId = tempNumber + 1;
                lblOrderID.setText("ORDER-" + finalizeOrderId);
            } else {
                lblOrderID.setText("ORDER-1");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void setOrderDate() {
        lblOrderDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }


    public void addToCartOnAction(ActionEvent actionEvent) {
        boolean validation = validate();

        if (validation) {

            if (!checkQty(cmbItemID.getValue(), Integer.parseInt(txtQty.getText()))) {
                new Alert(Alert.AlertType.WARNING, "Invalid Qty").show();
                return;
            }

            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            int qty = Integer.parseInt(txtQty.getText());
            double delivery = Double.parseDouble(txtDeliveryPrice.getText()) * Integer.parseInt(txtDistance.getText());
            double total = unitPrice * qty + delivery;
            Button btn = new Button("Delete");
            int row = isAlreadyExists(cmbItemID.getValue());

            if (row == -1) {
                CartTm tm = new CartTm(cmbItemID.getValue(), txtItemName.getText(), unitPrice, qty, delivery, total, btn);
                obList.add(tm);
                tblCart.setItems(obList);
            } else {
                int tempQty = obList.get(row).getQty() + qty;
                double tempTotal = unitPrice * tempQty + delivery;

                if (!checkQty(cmbItemID.getValue(), tempQty)) {
                    new Alert(Alert.AlertType.WARNING, "Invalid Qty").show();
                    return;
                }

                obList.get(row).setQty(tempQty);
                obList.get(row).setTotal(tempTotal);
                tblCart.refresh();
            }

            calculateTotal();
            clearFields();
            cmbItemID.requestFocus();


            btn.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);
                Optional<ButtonType> buttonType = alert.showAndWait();

                if (buttonType.get() == ButtonType.YES) {
                    for (CartTm tm : obList
                    ) {
                        obList.remove(tm);
                        calculateTotal();
                        tblCart.refresh();
                        return;
                    }
                }

            });
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Please Fill the Unfilled Data !");
            alert.show();
        }
    }

    private void clearFields() {
        cmbItemID.setValue(null);
        txtItemName.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtQty.clear();
    }

    private void calculateTotal() {
        double total = 0.00;
        for (CartTm tm : obList
        ) {
            total += tm.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }

    private int isAlreadyExists(String code) {
        for (int i = 0; i < obList.size(); i++) {
            if (obList.get(i).getItemID().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkQty(String code, int qty) {
        try {


            String sql = "SELECT qtyOnHand FROM item WHERE itemID=?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setString(1, code);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                int tempQty = set.getInt(1);
                if (tempQty >= qty) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void placeOrderOnAction(ActionEvent actionEvent) {
        if (obList.isEmpty()) return;
        ArrayList<ItemDetails> details = new ArrayList<>();
        for (CartTm tm : obList
        ) {
            details.add(new ItemDetails(tm.getItemID(),
                    tm.getUnitPrice(), tm.getQty()));
        }
        Order order = new Order(
                lblOrderID.getText(), new Date(),
                Double.parseDouble(lblTotal.getText()),
                cmbCustID.getValue(), details
        );

// place Order
        Connection con = null;
        try {

            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            String sql = "INSERT `orders` VALUES(?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, order.getOrderId());
            statement.setString(2, lblOrderDate.getText());
            statement.setDouble(3, order.getTotalCost());
            statement.setString(4, order.getCustomer());

            boolean isOrderSaved = statement.executeUpdate() > 0;
            if (isOrderSaved) {
                boolean isAllUpdated = manageQty(details);
                if (isAllUpdated) {
                    con.commit();
                    new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
                    clearAll();
                } else {
                    con.setAutoCommit(true);
                    con.rollback();
                    new Alert(Alert.AlertType.WARNING, "Try Again!").show();
                }

            } else {
                con.setAutoCommit(true);
                con.rollback();
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearAll() {
        obList.clear();
        calculateTotal();

        txtCustname.clear();
        txtCustAddress.clear();
        txtCustPhone.clear();
        txtStatus.clear();
        txtDeliveryPrice.clear();
        txtMethod.clear();
        txtDistance.clear();

        //=======
        cmbCustID.setValue(null);
        cmbItemID.setValue(null);
        cmbDeliverID.setValue(null);
        //========

        clearFields();
        cmbCustID.requestFocus();
        setOrderID();
    }

    private boolean manageQty(ArrayList<ItemDetails> details) {

        try {

            for (ItemDetails d : details
            ) {


                String sql = "INSERT `orderDetails` VALUES(?,?,?,?)";
                PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
                statement.setString(1, d.getId());
                statement.setString(2, lblOrderID.getText());
                statement.setDouble(3, d.getUnitPrice());
                statement.setInt(4, d.getQty());

                boolean isOrderDetailsSaved = statement.executeUpdate() > 0;

                if (isOrderDetailsSaved) {
                    boolean isQtyUpdated = update(d);
                    if (!isQtyUpdated) {
                        return false;
                    }
                } else {
                    return false;
                }


            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return true;
    }

    private boolean update(ItemDetails d) {
        try {

            String sql = "UPDATE item SET qtyOnHand=(qtyOnHand-?) WHERE itemID=?";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            statement.setInt(1, d.getQty());
            statement.setString(2, d.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


}
