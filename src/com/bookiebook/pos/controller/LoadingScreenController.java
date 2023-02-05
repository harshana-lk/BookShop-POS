package com.bookiebook.pos.controller;

import com.jfoenix.controls.JFXProgressBar;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingScreenController implements Initializable {

    public AnchorPane loadingScreen;
    public JFXProgressBar progBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       new  splashScreen().start();
    }

    class splashScreen extends Thread{
        public void run(){
            try {
                Thread.sleep(3000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("../view/LoginScreen.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.setScene(scene);
                        stage.show();
                        stage.centerOnScreen();
                        stage.setTitle("BookieBook");
                        Image icon = new Image(getClass().getResourceAsStream("/com/bookiebook/pos/assets/02.png"));
                        stage.getIcons().add( icon);

                        loadingScreen.getScene().getWindow().hide();
                    }
                });


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
