package com.bookiebook.pos.to;

public class ItemDetails {
    private String id;
    private double unitPrice;
    private int qty;

    public ItemDetails() {
    }

    public ItemDetails(String id, double unitPrice, int qty) {
        this.id = id;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
