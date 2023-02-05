package com.bookiebook.pos.to;

public class Vendor {
    private String vendorID;
    private String name;
    private String description;

    public Vendor() {
    }

    public Vendor(String vendorID, String name, String description) {
        this.vendorID = vendorID;
        this.name = name;
        this.description = description;
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
}
