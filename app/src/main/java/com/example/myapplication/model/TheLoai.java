package com.example.myapplication.model;

public class TheLoai {
    private String matheloai;
    private String tentheloai;
    private String mota;

    public TheLoai() {
    }

    public TheLoai(String matheloai, String tentheloai, String mota) {
        this.matheloai = matheloai;
        this.tentheloai = tentheloai;
        this.mota = mota;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getTentheloai() {
        return tentheloai;
    }

    public void setTentheloai(String tentheloai) {
        this.tentheloai = tentheloai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
