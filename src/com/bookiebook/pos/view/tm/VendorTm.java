package com.bookiebook.pos.view.tm;

import javafx.scene.control.Button;

public class VendorTm {
    private String vendorID;
    private String name;
    private String description;
    private Button btn;

    public VendorTm() {
    }

    public VendorTm(String vendorID, String name, String description, Button btn) {
        this.vendorID = vendorID;
        this.name = name;
        this.description = description;
        this.btn = btn;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
