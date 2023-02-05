package com.bookiebook.pos.to;

public class Delivery {
    private String id;
    private String method;
    private String status;
    private Double price;

    public Delivery() {
    }

    public Delivery(String id, String method, String status, Double price) {
        this.id = id;
        this.method = method;
        this.status = status;
        this.price = price;
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
}
