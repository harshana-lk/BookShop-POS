package com.bookiebook.pos.to;


public class User {
    private String userID;
    private String empID;
    private String username;
    private String status;
    private String password;
    private String hint;

    public User() {
    }

    public User(String userID, String empID, String username, String status, String password, String hint) {
        this.userID = userID;
        this.empID = empID;
        this.username = username;
        this.status = status;
        this.password = password;
        this.hint = hint;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
