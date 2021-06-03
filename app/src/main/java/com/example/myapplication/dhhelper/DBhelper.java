package com.example.myapplication.dhhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "ZingMP3", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE QUANTRI(MaTc integer primary key autoincrement,TenKhoanTc text,Ngay Date,phone integer,email text,username text, password text)";
        db.execSQL(sql);
        sql="INSERT INTO QUANTRI(TenKhoanTc,Ngay,phone,email,username,password) VALUES('Nam','2020-1-1',0567663194,'namps12382','admin','admin')";
        db.execSQL(sql);
        sql="INSERT INTO QUANTRI(TenKhoanTc,Ngay,phone,email,username,password) VALUES('Nu','2020-1-1',0567663194,'namps12382','admin123','1')";
        db.execSQL(sql);

        sql="CREATE TABLE THELOAI(MaTL text primary key,Chude text,MoTa text)";
        db.execSQL(sql);
        sql="INSERT INTO THELOAI VALUES('KPOP','Nhac K_POP','Nhac Han Quoc')";
        db.execSQL(sql);
        sql="INSERT INTO THELOAI VALUES('VPOP','Nhac V_POP','nhac Viet Nam')";
        db.execSQL(sql);
        sql="INSERT INTO THELOAI VALUES('USPOP','Nhac US_POP','nhac My')";
        db.execSQL(sql);

        sql="CREATE TABLE MUSIC(MaMusic integer primary key autoincrement,ChudeMusic text,tenMusic text,tacgia text,giasanpham integer,file text,soluong integer,thich text)";
        db.execSQL(sql);
            sql="INSERT INTO MUSIC(ChudeMusic,tenMusic,tacgia,giasanpham,file,soluong,thich) VALUES('Nhac K_POP','lac troi','Son tung mtp',500,'teaser',0,'false')";
        db.execSQL(sql);
        sql="INSERT INTO MUSIC(ChudeMusic,tenMusic,tacgia,giasanpham,file,soluong,thich) VALUES('Nhac K_POP','lac troi oc cko','Son tung mtp',400,'teaser2',0,'false')";
        db.execSQL(sql);
        sql="INSERT INTO MUSIC(ChudeMusic,tenMusic,tacgia,giasanpham,file,soluong,thich) VALUES('Nhac V_POP','Lac troi','Son tung mtp',400,'vn147',0,'false')";
        db.execSQL(sql);
        sql="INSERT INTO MUSIC(ChudeMusic,tenMusic,tacgia,giasanpham,file,soluong,thich) VALUES('Nhac V_POP','Tuy am','Masew',300,'tuyam',0,'false')";
        db.execSQL(sql);
        sql="INSERT INTO MUSIC(ChudeMusic,tenMusic,tacgia,giasanpham,file,soluong,thich) VALUES('Nhac V_POP','Yeu 5','Rhymastic',500,'yeu5',0,'false')";
        db.execSQL(sql);
        sql="INSERT INTO MUSIC(ChudeMusic,tenMusic,tacgia,giasanpham,file,soluong,thich) VALUES('Nhac V_POP','Kem Duyen','Rum & Masew',450,'kemduyen',0,'true')";
        db.execSQL(sql);

        sql="CREATE TABLE HOADON(MaHoaDon text primary key ,Ngayhoadon Date,soluong2 integer)";
        db.execSQL(sql);
        sql="INSERT INTO HOADON VALUES('E0','2020-9-2',1)";
        db.execSQL(sql);

        sql="CREATE TABLE HOADONCHITIET(MaHDCT integer primary key autoincrement," +
                    "MaHD_Fk text references HOADON(MaHoaDon)," +
                "MaMusic_FK integer references MUSIC(MaMusic)," +
                "giabia double)";
        db.execSQL(sql);
        sql="INSERT INTO HOADONCHITIET(MaHD_Fk,MaMusic_FK) VALUES('E0',1)";
        db.execSQL(sql);
        sql="INSERT INTO HOADONCHITIET(MaHD_Fk,MaMusic_FK) VALUES('E1',2)";
        db.execSQL(sql);
        sql="INSERT INTO HOADONCHITIET(MaHD_Fk,MaMusic_FK) VALUES('E2',3)";
        db.execSQL(sql);
        sql="INSERT INTO HOADONCHITIET(MaHD_Fk,MaMusic_FK) VALUES('E3',4)";
        db.execSQL(sql);
        sql="INSERT INTO HOADONCHITIET(MaHD_Fk,MaMusic_FK) VALUES('E4',5)";
        db.execSQL(sql);
        sql="INSERT INTO HOADONCHITIET(MaHD_Fk,MaMusic_FK) VALUES('E5',2)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
        db.execSQL("drop table if exists QUANTRI");
        db.execSQL("drop table if exists THELOAI");
        db.execSQL("drop table if exists MUSIC");
        db.execSQL("drop table if exists HOADON");
        db.execSQL("drop table if exists HOADONCHITIET");
    }
}
