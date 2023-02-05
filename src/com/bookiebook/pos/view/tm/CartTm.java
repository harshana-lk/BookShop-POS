package com.bookiebook.pos.view.tm;

import javafx.scene.control.Button;

public class CartTm {
    private String itemID;
    private String name;
    private double unitPrice;
    private int Qty;
    private double deliveryFee;
    private double total;
    private Button btn;

    public CartTm() {
    }

    public CartTm(String itemID, String name, double unitPrice, int qty, double deliveryFee, double total, Button btn) {
        this.itemID = itemID;
        this.name = name;
        this.unitPrice = unitPrice;
        Qty = qty;
        this.deliveryFee = deliveryFee;
        this.total = total;
        this.btn = btn;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
