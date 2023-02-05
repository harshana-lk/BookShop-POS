package com.bookiebook.pos.to;

public class Salary {
    private String id;
    private String status;
    private Double salary;

    public Salary() {
    }

    public Salary(String id, String status, Double salary) {
        this.id = id;
        this.status = status;
        this.salary = salary;
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
}
