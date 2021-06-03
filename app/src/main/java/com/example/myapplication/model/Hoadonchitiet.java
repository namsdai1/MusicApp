package com.example.myapplication.model;

public class Hoadonchitiet {
    int mahoadonchitiet;
    Hoadon hoadon;
    Music music;


    public Hoadonchitiet() {
    }

    public Hoadonchitiet(int mahoadonchitiet, Hoadon hoadon, Music music) {
        this.mahoadonchitiet = mahoadonchitiet;
        this.hoadon = hoadon;
        this.music = music;
    }

    public Hoadonchitiet(Hoadon hoadon, Music music) {
        this.hoadon = hoadon;
        this.music = music;
    }

    public int getMahoadonchitiet() {
        return mahoadonchitiet;
    }

    public void setMahoadonchitiet(int mahoadonchitiet) {
        this.mahoadonchitiet = mahoadonchitiet;
    }

    public Hoadon getHoadon() {
        return hoadon;
    }

    public void setHoadon(Hoadon hoadon) {
        this.hoadon = hoadon;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
