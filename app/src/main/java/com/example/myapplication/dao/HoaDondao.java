package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.dhhelper.DBhelper;
import com.example.myapplication.model.Hoadon;
import com.example.myapplication.model.Music;
import com.example.myapplication.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HoaDondao {
    public static ArrayList<Hoadon> readAll(Context context){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Hoadon> list=new ArrayList<>();
        DBhelper dBhelper=new DBhelper(context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM HOADON", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            String mahd=cs.getString(0);
            String ngayhoadon=cs.getString(1);
            int soluong=cs.getInt(2);
            try {
                list.add(new Hoadon(mahd,simpleDateFormat.parse(ngayhoadon),soluong));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cs.moveToNext();
        }
        cs.close();
        return list;
    }
    public static boolean create(Context context, Hoadon hoadon){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        DBhelper dBhelper=new DBhelper(context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("MaHoaDon",hoadon.getMaSach());
        values.put("Ngayhoadon",simpleDateFormat.format(hoadon.getNgaysach()));
        values.put("soluong2",1);
        long row=db.insert("HOADON",null,values);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean delete(Context context,String mahd){
        DBhelper dbhelper =new DBhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        int row = db.delete("HOADON", "MaHoaDon=?",new String[]{String.valueOf(mahd)});
        if(row > 0){
            return true;
        }else {
            return false;
        }
    }
    public static boolean update(Context context,Hoadon hoadon) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        DBhelper dbhelper = new DBhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaHoaDon",hoadon.getMaSach());
        values.put("Ngayhoadon",simpleDateFormat.format(hoadon.getNgaysach()));

        int row = db.update("HOADON", values, "MaHoaDon=?", new String[]{String.valueOf(hoadon.getMaSach())});
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }
}
