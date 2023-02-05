package com.bookiebook.pos.view.tm;

import javafx.scene.control.Button;

public class CategoryTm {
    private String id;
    private String name;
    private String description;
    private Button btn;

    public CategoryTm() {
    }

    public CategoryTm(String id, String name, String description, Button btn) {
        this.id = id;
        this.name = name;
        this.description = description;
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
