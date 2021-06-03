package com.example.myapplication.model;

import java.util.Date;

public class User {
    private  String username;
    private  String password;
    private String ten;
    private int matc;
    private Date ngay;
    private int phone;
    private String email;

    public User(int matc, String ten, Date ngay, int phone, String email, String username, String password) {
        this.username = username;
        this.password = password;
        this.ten = ten;
        this.matc = matc;
        this.ngay = ngay;
        this.phone = phone;
        this.email = email;
    }

    public User(String ten, Date ngay, int phone, String email, String username, String password) {
        this.username = username;
        this.password = password;
        this.ten = ten;
        this.ngay = ngay;
        this.phone = phone;
        this.email = email;
    }

    public User(String ten, int phone, String email) {
        this.ten = ten;
        this.phone = phone;
        this.email = email;
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getMatc() {
        return matc;
    }

    public void setMatc(int matc) {
        this.matc = matc;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}