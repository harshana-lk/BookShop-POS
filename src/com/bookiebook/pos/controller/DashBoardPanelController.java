package com.bookiebook.pos.controller;

import com.bookiebook.pos.DB.DBConnection;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DashBoardPanelController implements Initializable {
    public MediaView mediaView;
    public Label lblCustomerCount;
    public Label lblOrderCount;
    public Label lblItemCount;
    public Label lblCategoryCount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

            lblCustomerCount.setText(count);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
