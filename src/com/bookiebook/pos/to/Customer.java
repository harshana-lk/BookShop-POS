package com.bookiebook.pos.to;

public class Customer {
    private String id;
    private String name;
    private String nic;
    private String address;
    private int phone;

    public Customer() {
    }

    public Customer(String id, String name, String nic, String address, int phone) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.phone = phone;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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
}
