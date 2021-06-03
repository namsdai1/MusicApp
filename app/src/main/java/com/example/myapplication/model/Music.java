package com.example.myapplication.model;

public class Music {
    private int manhac;
    private String chude;
    private String title;
    private String tacgia;
    private int giamusic;
    private String File;
    private int soluong;
    private boolean thick;
    //musicplay
    public Music(String title, String file) {
        this.title = title;
        File = file;
    }


    public Music(int manhac, boolean thick) {
        this.manhac = manhac;
        this.thick = thick;
    }

    public Music(int manhac, String chude, String title, String tacgia, int giamusic, String file, int soluong, boolean thick) {
        this.manhac = manhac;
        this.chude = chude;
        this.title = title;
        this.tacgia = tacgia;
        this.giamusic = giamusic;
        File = file;
        this.soluong=soluong;
        this.thick=thick;
    }
    public Music( String chude, String title, String tacgia, int giamusic, String file,int soluong) {
        this.chude = chude;
        this.title = title;
        this.tacgia = tacgia;
        this.giamusic = giamusic;
        File = file;
        this.soluong=soluong;
    }

    public Music(int manhac, String chude, String title, String tacgia, int giamusic, String file) {
        this.manhac = manhac;
        this.chude = chude;
        this.title = title;
        this.tacgia = tacgia;
        this.giamusic = giamusic;
        File = file;
    }

    public Music(String chude, String title, String tacgia, int giamusic, String file) {
        this.chude = chude;
        this.title = title;
        this.tacgia = tacgia;
        this.giamusic = giamusic;
        File = file;
    }

    public boolean isThick() {
        return thick;
    }

    public void setThick(boolean thick) {
        this.thick = thick;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getManhac() {
        return manhac;
    }

    public void setManhac(int manhac) {
        this.manhac = manhac;
    }

    public String getChude() {
        return chude;
    }

    public void setChude(String chude) {
        this.chude = chude;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public int getGiamusic() {
        return giamusic;
    }

    public void setGiamusic(int giamusic) {
        this.giamusic = giamusic;
    }

    public Music() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
        File = file;
    }
}
