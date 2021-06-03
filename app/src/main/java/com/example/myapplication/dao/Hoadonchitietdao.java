package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.dhhelper.DBhelper;
import com.example.myapplication.model.Hoadon;
import com.example.myapplication.model.Hoadonchitiet;
import com.example.myapplication.model.Music;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Hoadonchitietdao {
    public static ArrayList<Hoadonchitiet> reaAll(Context context){
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
        ArrayList<Hoadonchitiet> data=new ArrayList<>();
        DBhelper dBhelper=new DBhelper(context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT MaHDCT,HOADON.MaHoaDon,HOADON.Ngayhoadon," +
                "MUSIC.MaMusic,MUSIC.ChudeMusic,MUSIC.tenMusic,MUSIC.tacgia,MUSIC.giasanpham,MUSIC.file " +
                "FROM HOADONCHITIET INNER JOIN MUSIC on HOADONCHITIET.MaMusic_FK=MUSIC.MaMusic " +
                "INNER JOIN HOADON on HOADONCHITIET.MaHD_Fk=HOADON.MaHoaDon", null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            try {
                 Hoadonchitiet ee=new Hoadonchitiet();
                ee.setMahoadonchitiet(cs.getInt(0));
                ee.setHoadon(new Hoadon(cs.getString(1),sdf.parse(cs.getString(2))));
                ee.setMusic(new Music(cs.getInt(3),cs.getString(4),cs.getString(5),cs.getString(6),cs.getInt(7),cs.getString(8)));
                data.add(ee);
                Log.d("//=====", ee.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cs.moveToNext();
        }
        cs.close();
        return data;
    }
    public static ArrayList<Hoadonchitiet> reaAll(Context context,String mahoadon){
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");
        ArrayList<Hoadonchitiet> data=new ArrayList<>();
        DBhelper dBhelper=new DBhelper(context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT MaHDCT,HOADON.MaHoaDon,HOADON.Ngayhoadon," +
                "MUSIC.MaMusic,MUSIC.ChudeMusic,MUSIC.tenMusic,MUSIC.tacgia,MUSIC.giasanpham,MUSIC.file " +
                "FROM HOADONCHITIET INNER JOIN MUSIC on HOADONCHITIET.MaMusic_FK=MUSIC.MaMusic " +
                "INNER JOIN HOADON on HOADONCHITIET.MaHD_Fk=HOADON.MaHoaDon WHERE HOADONCHITIET.MaHD_Fk='"+mahoadon+"' ", null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            try {
                Hoadonchitiet ee=new Hoadonchitiet();
                ee.setMahoadonchitiet(cs.getInt(0));
                ee.setHoadon(new Hoadon(cs.getString(1),sdf.parse(cs.getString(2))));
                ee.setMusic(new Music(cs.getInt(3),cs.getString(4),cs.getString(5),cs.getString(6),cs.getInt(7),cs.getString(8)));
                data.add(ee);
                Log.d("//=====", ee.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cs.moveToNext();
        }
        cs.close();
        return data;
    }
    public static boolean create(Context context, Hoadonchitiet hoadon){
        DBhelper dBhelper=new DBhelper(context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("MaHD_Fk",hoadon.getHoadon().getMaSach());
        values.put("MaMusic_FK",hoadon.getMusic().getManhac());
        long row=db.insert("HOADONCHITIET",null,values);
        if (row > 0) {
            Log.e("TAG", "create: thang cong" );
            return true;
        } else {
            return false;
        }
    }
    public static boolean delete(Context context,int mahoadon){
        DBhelper dbhelper =new DBhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        int row = db.delete("HOADONCHITIET", "MaHDCT=?",new String[]{String.valueOf(mahoadon)});
        if(row > 0){
            return true;
        }else {
            return false;
        }
    }
    public static int thanhtoan(Context context,String mahoadon){
        int tongTien=0;
        DBhelper dbhelper=new DBhelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String str= " select SUM(giasanpham) as TongTien " +
                "from HOADONCHITIET INNER JOIN MUSIC on HOADONCHITIET.MaMusic_FK=MUSIC.MaMusic" +
                "  INNER JOIN HOADON on HOADONCHITIET.MaHD_Fk=HOADON.MaHoaDon " +
                "WHERE HOADONCHITIET.MaHD_Fk='"+mahoadon+"'";
        Cursor cs = db.rawQuery(str, null);
        cs.moveToFirst();
        if(cs.getCount()>=0){
            tongTien=cs.getInt(0);
        }
        return tongTien;
    }
    public static double getDoanhThuTheoNgay(Context context){
        double doanhThu=0;
        DBhelper dbhelper=new DBhelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(MUSIC.giasanpham) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN MUSIC on HOADONCHITIET.MaMusic_FK=MUSIC.MaMusic WHERE HOADON.Ngayhoadon=date('now')" +
                "GROUP By HOADONCHITIET.MaMusic_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }

    public static double getDoanhThuTheoNgay1(Context context){
        double doanhThu=0;
        DBhelper dbhelper=new DBhelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(MUSIC.giasanpham) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN MUSIC on HOADONCHITIET.MaMusic_FK=MUSIC.MaMusic WHERE HOADON.Ngayhoadon=date('now','-1 day')" +
                "GROUP By HOADONCHITIET.MaMusic_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }
    public static double getDoanhThuTheoNgay2(Context context){
        double doanhThu=0;
        DBhelper dbhelper=new DBhelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(MUSIC.giasanpham) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN MUSIC on HOADONCHITIET.MaMusic_FK=MUSIC.MaMusic WHERE HOADON.Ngayhoadon=date('now','-2 day')" +
                "GROUP By HOADONCHITIET.MaMusic_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }
    public static double getDoanhThuTheoNgay3(Context context){
        double doanhThu=0;
        DBhelper dbhelper=new DBhelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(MUSIC.giasanpham) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN MUSIC on HOADONCHITIET.MaMusic_FK=MUSIC.MaMusic WHERE HOADON.Ngayhoadon=date('now','-3 day')" +
                "GROUP By HOADONCHITIET.MaMusic_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }
    public static double getDoanhThuTheoNgay4(Context context){
        double doanhThu=0;
        DBhelper dbhelper=new DBhelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(MUSIC.giasanpham) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN MUSIC on HOADONCHITIET.MaMusic_FK=MUSIC.MaMusic WHERE HOADON.Ngayhoadon=date('now','-4 day')" +
                "GROUP By HOADONCHITIET.MaMusic_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }
    public static double getDoanhThuTheoNgay5(Context context){
        double doanhThu=0;
        DBhelper dbhelper=new DBhelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(MUSIC.giasanpham) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN MUSIC on HOADONCHITIET.MaMusic_FK=MUSIC.MaMusic WHERE HOADON.Ngayhoadon=date('now','-5 day')" +
                "GROUP By HOADONCHITIET.MaMusic_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }
    public static double getDoanhThuTheoThang(Context context){
        double doanhThu=0;
        DBhelper dbhelper=new DBhelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(MUSIC.giasanpham) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN MUSIC on HOADONCHITIET.MaMusic_FK=MUSIC.MaMusic WHERE strftime('%m',HOADON.Ngayhoadon)=strftime('%m','now')" +
                "GROUP By HOADONCHITIET.MaMusic_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }
    public static double getDoanhThuTheoNam(Context context){
        double doanhThu=0;
        DBhelper dbhelper=new DBhelper(context);
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql="SELECT SUM(tongtien) from (SELECT SUM(MUSIC.giasanpham) as tongtien FROM HOADON INNER JOIN HOADONCHITIET on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk INNER JOIN MUSIC on HOADONCHITIET.MaMusic_FK=MUSIC.MaMusic WHERE strftime('%Y',HOADON.Ngayhoadon)=strftime('%Y','now')" +
                "GROUP By HOADONCHITIET.MaMusic_FK)tmp";
        Cursor cs=db.rawQuery(sql, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            doanhThu=cs.getDouble(0);
            cs.moveToNext();
        }
        cs.close();
        return doanhThu;
    }

}
