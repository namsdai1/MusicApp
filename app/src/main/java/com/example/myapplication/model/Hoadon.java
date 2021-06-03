package com.example.myapplication.model;

import java.util.Date;

public class Hoadon {
    String maSach;
    Date ngaysach;
    int soluong;
    public Hoadon() {
    }

    public Hoadon(String maSach, Date ngaysach) {
        this.maSach = maSach;
        this.ngaysach = ngaysach;
    }
    public Hoadon(String maSach, Date ngaysach,int soluong) {
        this.maSach = maSach;
        this.ngaysach = ngaysach;
        this.soluong=soluong;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public Date getNgaysach() {
        return ngaysach;
    }

    public void setNgaysach(Date ngaysach) {
        this.ngaysach = ngaysach;
    }
}
