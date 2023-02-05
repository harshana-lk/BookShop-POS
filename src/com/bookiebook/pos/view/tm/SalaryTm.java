package com.bookiebook.pos.view.tm;

import javafx.scene.control.Button;

public class SalaryTm {
    private String id;
    private String status;
    private Double salary;
    private Button btn;

    public SalaryTm() {
    }

    public SalaryTm(String id, String status, Double salary, Button btn) {
        this.id = id;
        this.status = status;
        this.salary = salary;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
