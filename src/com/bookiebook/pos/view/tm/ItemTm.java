package com.bookiebook.pos.view.tm;

import javafx.scene.control.Button;

public class ItemTm {
    private String itemID;
    private String name;
    private String categoryID;
    private String vendorID;
    private int qty;
    private Double price;
    private Button btn;

    public ItemTm() {
    }

    public ItemTm(String itemID, String name, String categoryID, String vendorID, int qty, Double price, Button btn) {
        this.itemID = itemID;
        this.name = name;
        this.categoryID = categoryID;
        this.vendorID = vendorID;
        this.qty = qty;
        this.price = price;
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

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
