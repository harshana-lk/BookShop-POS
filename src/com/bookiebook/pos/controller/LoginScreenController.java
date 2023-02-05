package com.bookiebook.pos.controller;

import com.bookiebook.pos.util.CrudUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginScreenController {
    public JFXButton btnLogin;
    public Label lblFPassword;
    public JFXTextField txtUserName;
    public FontAwesomeIconView icnClose;
    public Circle circleClose;
    public ImageView icnFacebook;
    public ImageView icnTwitter;
    public ImageView icnGoogle;
    public Label passwordStatus;
    public JFXPasswordField txtPassword;

    public static int login(String user, String pass) throws SQLException, ClassNotFoundException {
        ResultSet res = CrudUtil.execute("SELECT status FROM user WHERE username=? AND password=?",
                user, pass
        );
        if (res.next()) {
            if (res.getString("status").equals("Admin")) {
                return 1;
            } else if (res.getString("status").equals("Cashier")) return 0;
        }
        return -1;
    }

    public void logOnAction(ActionEvent actionEvent) throws IOException {

        try {
            int loginStatus = LoginScreenController.login(txtUserName.getText(), txtPassword.getText());
            if (loginStatus == -1) {
                passwordStatus.setText("Username or Password Incorrect!");
            } else if (loginStatus == 1) {
                setAdminName(actionEvent);
            } else {
                setCashierName(actionEvent);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setAdminName(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(".././../pos/view/AdminPanel.fxml"));
        Parent parent = loader.load();
        AdminPanelController controller = loader.getController();
        controller.setName(txtUserName.getText());
        stage.setScene(new Scene(parent));
        stage.show();
        stage.centerOnScreen();

    }

    public void setCashierName(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(".././../pos/view/CashierPanel.fxml"));
        Parent parent = loader.load();
        CashierPanelController controller = loader.getController();
        controller.setName(txtUserName.getText());
        stage.setScene(new Scene(parent));
        stage.show();
        stage.centerOnScreen();
    }

    public void closeOnActions(MouseEvent mouseEvent) {
        Stage stage = (Stage) icnClose.getScene().getWindow();
        stage.close();
    }


    public void closeOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) circleClose.getScene().getWindow();
        stage.close();
    }

    public void facebookOnAction(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://m.facebook.com/bookie.book.5/"));
    }

    public void twitterOnAction(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://twitter.com/book_bop"));
    }

    public void googleOnAction(MouseEvent mouseEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://citybasementbooks.com.au/dump/books-0020/89168.html"));
    }

    public void LogOnActionByKey(ActionEvent actionEvent) throws IOException {
        logOnAction(actionEvent);
    }

}
