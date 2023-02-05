package com.bookiebook.pos.controller;

import com.bookiebook.pos.DB.DBConnection;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;

public class ReportsPanelController {
    public JFXButton btnViewCustomer;
    public JFXButton btnViewOrders;
    public JFXButton btnViewItem;

    public void viewCustomerReportsOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign jd = JRXmlLoader.load("E:\\Projects\\BokieBook\\src\\com\\bookiebook\\pos\\reports\\Customers.jrxml");
            JRDesignQuery newQuery = new JRDesignQuery();
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void viewOrderReportsOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign jd = JRXmlLoader.load("E:\\Projects\\BokieBook\\src\\com\\bookiebook\\pos\\reports\\Customers.jrxml");
            JRDesignQuery newQuery = new JRDesignQuery();
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void viewItemReportsOnAction(ActionEvent actionEvent) {
        try {
            JasperDesign jd = JRXmlLoader.load("E:\\Projects\\BokieBook\\src\\com\\bookiebook\\pos\\reports\\Customers.jrxml");
            JRDesignQuery newQuery = new JRDesignQuery();
            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
