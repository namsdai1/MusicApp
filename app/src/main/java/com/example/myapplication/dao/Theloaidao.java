package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.dhhelper.DBhelper;
import com.example.myapplication.model.TheLoai;
import com.example.myapplication.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Theloaidao {
    public static ArrayList<TheLoai> readAll(Context context){
          ArrayList<TheLoai> list=new ArrayList<>();
        DBhelper dBhelper=new DBhelper(context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM THELOAI", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
             String matl=cs.getString(0);
             String tentl=cs.getString(1);
             String motatl=cs.getString(2);
             list.add(new TheLoai(matl,tentl,motatl));
             cs.moveToNext();

        }
        cs.close();
        return list;
    }
    public static boolean create(Context context,TheLoai theLoai){
        DBhelper dBhelper=new DBhelper(context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("MaTL",theLoai.getMatheloai());
        values.put("Chude",theLoai.getTentheloai());
        values.put("MoTa",theLoai.getMota());

        long row=db.insert("THELOAI",null,values);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean update(Context context,TheLoai theLoai) {

        DBhelper dbhelper = new DBhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MaTL", theLoai.getMatheloai());
        values.put("Chude", theLoai.getTentheloai());
        values.put("MoTa", theLoai.getMota());
        int row = db.update("THELOAI", values, "MaTL=?", new String[]{String.valueOf(theLoai.getMatheloai())});
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean delete(Context context, String ma) {
            DBhelper dbhelper =new DBhelper(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            int row = db.delete("THELOAI", "MaTL=?",new String[]{String.valueOf(ma)});
            if(row > 0){
                return true;
            }else {
                return false;
            }
        }
    }

