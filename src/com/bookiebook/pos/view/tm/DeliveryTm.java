package com.bookiebook.pos.view.tm;

import javafx.scene.control.Button;

public class DeliveryTm {
    private String id;
    private String method;
    private String status;
    private Double price;
    private Button btn;

    public DeliveryTm() {
    }

    public DeliveryTm(String id, String method, String status, Double price, Button btn) {
        this.id = id;
        this.method = method;
        this.status = status;
        this.price = price;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
