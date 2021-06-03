package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.dhhelper.DBhelper;
import com.example.myapplication.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Quantridao {
    DBhelper dBhelper;
    User user;

    public Quantridao(Context context) {
        dBhelper = new DBhelper(context);
    }
  public  ArrayList<User> readAll(User user){
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       ArrayList<User> data=new ArrayList<>();
       SQLiteDatabase db=dBhelper.getWritableDatabase();
       String sql="Select * from QUANTRI WHERE username=? AND password=?";
       Cursor cs=db.rawQuery(sql, new String[]{user.getUsername(), user.getPassword()});
      cs.moveToFirst();
      while (!cs.isAfterLast()) {
          int MaTc = cs.getInt(0);
          String TenKhoanTc = cs.getString(1);
          String Ngay = cs.getString(2);
          int Tien = cs.getInt(3);
          String email = cs.getString(4);
          String username=cs.getString(5);
          String password=cs.getString(6);
          try {
              data.add(new User(MaTc,TenKhoanTc,sdf.parse(Ngay),Tien,email,username,password));
          } catch (ParseException e) {
              e.printStackTrace();
          }
          cs.moveToNext();
      }
      cs.close();
      return data;
  }
    public static ArrayList<User> getAll(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<User> data=new ArrayList<>();
        DBhelper dBhelper =new DBhelper(context);

        SQLiteDatabase db=dBhelper.getWritableDatabase();

        String sql="Select * from QUANTRI";
        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int MaTc = cs.getInt(0);
            String TenKhoanTc = cs.getString(1);
            String Ngay = cs.getString(2);
            int phone = cs.getInt(3);
            String email = cs.getString(4);
            String username=cs.getString(5);
            String password=cs.getString(6);
            try {
                data.add(new User(MaTc,TenKhoanTc,sdf.parse(Ngay),phone,email,username,password));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cs.moveToNext();
        }
        cs.close();
        return data;
    }
    public static boolean create(Context context,User user){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        DBhelper dBhelper=new DBhelper(context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("TenKhoanTc",user.getTen());
        values.put("Ngay",simpleDateFormat.format(user.getNgay()));
        values.put("phone",user.getPhone());
        values.put("email",user.getEmail());
        values.put("username",user.getUsername());
        values.put("password",user.getPassword());
        long row=db.insert("QUANTRI",null,values);
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean delete(Context context,int Matc){
        DBhelper dbhelper =new DBhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        int row = db.delete("QUANTRI", "MaTc=?",new String[]{String.valueOf(Matc)});
        if(row > 0){
            return true;
        }else {
            return false;
        }
    }
    public static boolean update(Context context,User user) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DBhelper dbhelper = new DBhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenKhoanTc", user.getTen());
        values.put("Ngay", sdf.format(user.getNgay()));
        values.put("phone", user.getPhone());
        values.put("email", user.getEmail());
        values.put("username", user.getUsername());
        values.put("password", user.getPassword());
        int row = db.update("QUANTRI", values, "MaTC=?", new String[]{String.valueOf(user.getMatc())});
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkLogin(User user) {
        SQLiteDatabase db = dBhelper.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM QUANTRI WHERE username=? AND password=?",
                new String[]{user.getUsername(), user.getPassword()});
        if (cs.getCount() <= 0) {
            return false;
        }
        return true;
    }

    public boolean chancepassword(String username,String password){
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        ContentValues values=new ContentValues();
        values.put("password",password);
        int row=db.update("QUANTRI",values,"username=?",new String[]{username});
        if(row<=0){
            return false;
        }
        return true;
    }
    public void chancepassword2(String username,String password){
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        db.execSQL("UPDATE QUANTRI SET password='" + password + "' WHERE username= '" + username +"'");
    }

}
