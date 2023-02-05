package com.bookiebook.pos.view.tm;

import javafx.scene.control.Button;

public class EmployeeTm {
    private String id;
    private String name;
    private String status;
    private String address;
    private int phone;
    private Button btn;

    public EmployeeTm() {
    }

    public EmployeeTm(String id, String name, String status, String address, int phone, Button btn) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.address = address;
        this.phone = phone;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
