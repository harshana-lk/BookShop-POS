package com.bookiebook.pos.controller;

import com.bookiebook.pos.DB.DBConnection;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CashierPanelController implements Initializable {

    public AnchorPane mainAnchorpane;
    public AnchorPane dashAnchorpane;
    public MediaView mediaView;
    public Label lblCustCount;
    public Label lblOrderCount;
    public Label lblItemCount;
    public Label lblCategoryCount;
    public Circle circleClose;
    public Circle icnProfile;
    public Label lblName;
    public JFXButton btnDashboard;
    public JFXButton btnItems;
    public JFXButton btnCategories;
    public JFXButton btnEmployee;
    public JFXButton btnSalary;
    public JFXButton btnDelivery;
    public JFXButton btnUser;
    public JFXButton btnOrder;
    public ImageView imgPlaceOrder;
    public JFXButton btnReports;
    public JFXButton btnVendors;
    public ImageView imgVendor;
    public FontAwesomeIconView icnClose;
    public JFXButton btnLogOut;
    public JFXButton btnCustomer;
    public JFXButton btnOrderDetails;
    public ImageView imgOrderDetails;
    public JFXTextField txtSearch;



    public void closeOnActions(MouseEvent mouseEvent) {
        Stage stage = (Stage) circleClose.getScene().getWindow();
        stage.close();
    }

    public void CloseOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) icnClose.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        dropDownContext.setTopAnchor((Node) dropDownContext.getChildren(),0d);
        Media media = new Media("file:///E:/Harshi.mp4");
        MediaPlayer player = new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();

        loadCountOfCustomers();

        loadCountOfOrders();

        loadCountOfItems();

        loadCountOfCategories();

    }

    private void loadCountOfCategories() {
        String count = "";
        try {
            String sql = "SELECT COUNT(categoryID) FROM category";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getString(1);
            }

            lblCategoryCount.setText(count);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCountOfItems() {
        String count = "";
        try {
            String sql = "SELECT COUNT(itemID) FROM item";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getString(1);
            }

            lblItemCount.setText(count);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCountOfOrders() {
        String count = "";
        try {
            String sql = "SELECT COUNT(orderID) FROM orders";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getString(1);
            }

            lblOrderCount.setText(count);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadCountOfCustomers() {
        String count = "";
        try {
            String sql = "SELECT COUNT(custID) FROM customer";
            PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getString(1);
            }

            lblCustCount.setText(count);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void itemOnAction(ActionEvent actionEvent) throws IOException {
        btnItems.setOpacity(1.00);
        btnCustomer.setOpacity(0.5);
        btnDashboard.setOpacity(0.5);
        btnCategories.setOpacity(0.5);
        btnDelivery.setOpacity(0.5);
        btnEmployee.setOpacity(0.5);
        btnOrder.setOpacity(0.5);
        imgPlaceOrder.setOpacity(0.5);
        btnReports.setOpacity(0.5);
        btnSalary.setOpacity(0.5);
        btnUser.setOpacity(0.5);
        btnVendors.setOpacity(0.5);
        imgVendor.setOpacity(0.5);
        btnOrderDetails.setOpacity(0.5);
        imgOrderDetails.setOpacity(0.5);
        Parent parent = FXMLLoader.load((getClass().getResource("../view/ItemManagePanel.fxml")));
        dashAnchorpane.getChildren().clear();
        dashAnchorpane.getChildren().add(parent);
    }

    public void dashBoardOnAction(ActionEvent actionEvent) throws IOException {
        btnItems.setOpacity(0.5);
        btnCustomer.setOpacity(0.5);
        btnDashboard.setOpacity(1.00);
        btnCategories.setOpacity(0.5);
        btnDelivery.setOpacity(0.5);
        btnEmployee.setOpacity(0.5);
        btnOrder.setOpacity(0.5);
        imgPlaceOrder.setOpacity(0.5);
        btnReports.setOpacity(0.5);
        btnSalary.setOpacity(0.5);
        btnUser.setOpacity(0.5);
        btnVendors.setOpacity(0.5);
        imgVendor.setOpacity(0.5);
        btnOrderDetails.setOpacity(0.5);
        imgOrderDetails.setOpacity(0.5);
        Parent parent = FXMLLoader.load((getClass().getResource("../view/DashBoardPanel.fxml")));
        dashAnchorpane.getChildren().clear();
        dashAnchorpane.getChildren().add(parent);
    }

    public void categoriesOnAction(ActionEvent actionEvent) throws IOException {
        btnItems.setOpacity(0.5);
        btnCustomer.setOpacity(0.5);
        btnDashboard.setOpacity(0.5);
        btnCategories.setOpacity(1.00);
        btnDelivery.setOpacity(0.5);
        btnEmployee.setOpacity(0.5);
        btnOrder.setOpacity(0.5);
        imgPlaceOrder.setOpacity(0.5);
        btnReports.setOpacity(0.5);
        btnSalary.setOpacity(0.5);
        btnUser.setOpacity(0.5);
        btnVendors.setOpacity(0.5);
        imgVendor.setOpacity(0.5);
        btnOrderDetails.setOpacity(0.5);
        imgOrderDetails.setOpacity(0.5);
        Parent parent = FXMLLoader.load((getClass().getResource("../view/CategoryMangePanel.fxml")));
        dashAnchorpane.getChildren().clear();
        dashAnchorpane.getChildren().add(parent);
    }

    public void vendorOnAction(ActionEvent actionEvent) throws IOException {
        btnItems.setOpacity(0.5);
        btnCustomer.setOpacity(0.5);
        btnDashboard.setOpacity(0.5);
        btnCategories.setOpacity(0.5);
        btnDelivery.setOpacity(0.5);
        btnEmployee.setOpacity(0.5);
        btnOrder.setOpacity(0.5);
        imgPlaceOrder.setOpacity(0.5);
        btnReports.setOpacity(0.5);
        btnSalary.setOpacity(0.5);
        btnUser.setOpacity(0.5);
        btnVendors.setOpacity(1.00);
        imgVendor.setOpacity(1.00);
        btnOrderDetails.setOpacity(0.5);
        imgOrderDetails.setOpacity(0.5);
        Parent parent = FXMLLoader.load((getClass().getResource("../view/VendorManagePanel.fxml")));
        dashAnchorpane.getChildren().clear();
        dashAnchorpane.getChildren().add(parent);
    }

    public void LogOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnLogOut.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load((getClass().getResource("../view/LoginScreen.fxml")))));
        stage.centerOnScreen();
    }

    public void EmployeeOnAction(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING,"You Cant't Access For this");
        alert.show();
    }

    public void salaryOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING,"You Cant't Access For this");
        alert.show();
    }

    public void userPanelOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING,"You Cant't Access For this");
        alert.show();
    }

    public void deliveryOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING,"You Cant't Access For this");
        alert.show();
    }

    public void customersOnAction(ActionEvent actionEvent) throws IOException {
        btnItems.setOpacity(0.5);
        btnCustomer.setOpacity(1.00);
        btnDashboard.setOpacity(0.5);
        btnCategories.setOpacity(0.5);
        btnDelivery.setOpacity(0.5);
        btnEmployee.setOpacity(0.5);
        btnOrder.setOpacity(0.5);
        imgPlaceOrder.setOpacity(0.5);
        btnReports.setOpacity(0.5);
        btnSalary.setOpacity(0.5);
        btnUser.setOpacity(0.5);
        btnVendors.setOpacity(0.5);
        imgVendor.setOpacity(0.5);
        btnOrderDetails.setOpacity(0.5);
        imgOrderDetails.setOpacity(0.5);
        Parent parent = FXMLLoader.load((getClass().getResource("../view/CustomerManagePanel.fxml")));
        dashAnchorpane.getChildren().clear();
        dashAnchorpane.getChildren().add(parent);
    }

    public void OrderDetailsOnAction(ActionEvent actionEvent) throws IOException {
        btnItems.setOpacity(0.5);
        btnCustomer.setOpacity(0.5);
        btnDashboard.setOpacity(0.5);
        btnCategories.setOpacity(0.5);
        btnDelivery.setOpacity(0.5);
        btnEmployee.setOpacity(0.5);
        btnOrder.setOpacity(0.5);
        btnReports.setOpacity(0.5);
        btnSalary.setOpacity(0.5);
        btnUser.setOpacity(0.5);
        btnVendors.setOpacity(0.5);
        imgVendor.setOpacity(0.5);
        btnOrder.setOpacity(0.5);
        btnOrderDetails.setOpacity(1.00);
        imgOrderDetails.setOpacity(1.00);
        imgPlaceOrder.setOpacity(0.5);
        Parent parent = FXMLLoader.load((getClass().getResource("../view/OrderDetailsPanel.fxml")));
        dashAnchorpane.getChildren().clear();
        dashAnchorpane.getChildren().add(parent);
    }

    public void profileOnAction(MouseEvent mouseEvent) {
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws IOException {
        btnItems.setOpacity(0.5);
        btnCustomer.setOpacity(0.5);
        btnDashboard.setOpacity(0.5);
        btnCategories.setOpacity(0.5);
        btnDelivery.setOpacity(0.5);
        btnEmployee.setOpacity(0.5);
        btnOrder.setOpacity(0.5);
        btnReports.setOpacity(0.5);
        btnSalary.setOpacity(0.5);
        btnUser.setOpacity(0.5);
        btnVendors.setOpacity(0.5);
        imgVendor.setOpacity(0.5);
        btnOrder.setOpacity(1.00);
        imgPlaceOrder.setOpacity(1.00);
        btnOrderDetails.setOpacity(0.5);
        imgOrderDetails.setOpacity(0.5);
        Parent parent = FXMLLoader.load((getClass().getResource("../view/PlaceOrderPanel.fxml")));
        dashAnchorpane.getChildren().clear();
        dashAnchorpane.getChildren().add(parent);
    }

    public void setName(String name) {
        lblName.setText(name);
    }
}
