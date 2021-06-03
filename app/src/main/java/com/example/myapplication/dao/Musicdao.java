package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;

import com.example.myapplication.dhhelper.DBhelper;
import com.example.myapplication.model.Music;
import com.example.myapplication.model.TheLoai;
import com.example.myapplication.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Musicdao {
    public static ArrayList<Music> readAll(Context context){
        ArrayList<Music> list=new ArrayList<>();
        DBhelper dBhelper=new DBhelper(context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM MUSIC", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int matl=cs.getInt(0);
            String chude=cs.getString(1);
            String tenmusic=cs.getString(2);
            String tacgia=cs.getString(3);
            int gia=cs.getInt(4);
            String img=cs.getString(5);
            int soluong=cs.getInt(6);
            boolean thick=Boolean.parseBoolean(cs.getString(7));
            list.add(new Music(matl,chude,tenmusic,tacgia,gia,img,soluong,thick));
            cs.moveToNext();

        }
        cs.close();
        return list;
    }
    public static ArrayList<Music> readAllSl(Context context){
        ArrayList<Music> list=new ArrayList<>();
        DBhelper dBhelper=new DBhelper(context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM MUSIC", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int matl=cs.getInt(0);
            String chude=cs.getString(1);
            String tenmusic=cs.getString(2);
            String tacgia=cs.getString(3);
            int gia=cs.getInt(4);
            String img=cs.getString(5);
            int soluong=cs.getInt(6);
            boolean thick=Boolean.parseBoolean(cs.getString(7));
            list.add(new Music(matl,chude,tenmusic,tacgia,gia,img,soluong,thick));
            cs.moveToNext();

        }
        cs.close();
        return list;
    }
    public static boolean updateSL(Context context,Music music) {
        DBhelper dbhelper = new DBhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ChudeMusic",music.getChude());
        values.put("tenMusic",music.getTitle());
        values.put("tacgia",music.getTacgia());
        values.put("giasanpham",music.getGiamusic());
        values.put("file",music.getFile());
        values.put("soluong",music.getSoluong());

        int row = db.update("MUSIC", values, "MaMusic=?", new String[]{String.valueOf(music.getManhac())});
        if (row > 0) {
            return true;
        } else {
            Log.e("er", "updateSL: false" );
            return false;

        }
    }
    public static boolean update(Context context,Music music) {
        DBhelper dbhelper = new DBhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ChudeMusic",music.getChude());
        values.put("tenMusic",music.getTitle());
        values.put("tacgia",music.getTacgia());
        values.put("giasanpham",music.getGiamusic());
        values.put("file",music.getFile());
        values.put("soluong",0);
        values.put("thich",music.isThick());
        int row = db.update("MUSIC", values, "MaMusic=?", new String[]{String.valueOf(music.getManhac())});
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }
    public  static boolean updateLike(Context context,Music music){
        DBhelper dbhelper = new DBhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("thich",String.valueOf(music.isThick()));
        int row = db.update("MUSIC", values, "MaMusic=?", new String[]{String.valueOf(music.getManhac())});
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }
public static boolean create(Context context,Music music){
    DBhelper dBhelper=new DBhelper(context);
    SQLiteDatabase db=dBhelper.getReadableDatabase();
    ContentValues values=new ContentValues();
    values.put("tenMusic",music.getTitle());
    values.put("ChudeMusic",music.getChude());
    values.put("tacgia",music.getTacgia());
    values.put("giasanpham",music.getGiamusic());
    values.put("file",music.getFile());
    values.put("soluong",0);

    long row=db.insert("MUSIC",null,values);
    if (row > 0) {
        return true;
    } else {
        return false;
    }
}
    public static boolean delete(Context context, int ma) {
        DBhelper dbhelper =new DBhelper(context);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        int row = db.delete("MUSIC", "MaMusic=?",new String[]{String.valueOf(ma)});
        if(row > 0){
            return true;
        }else {
            return false;
        }
    }
public  static Music getSachbyID(Context context,int mamusic){
        Music s=null;
    DBhelper dbhelper =new DBhelper(context);
    SQLiteDatabase db=dbhelper.getReadableDatabase();
    Cursor cs=db.rawQuery("select * from MUSIC" +
            " WHERE MaMusic ='" + mamusic + "'",null);
    cs.moveToFirst();
    while (!cs.isAfterLast()){
        s=new Music();
        s.setManhac(cs.getInt(0));
        s.setChude(cs.getString(1));
        s.setTitle(cs.getString(2));
        s.setTacgia(cs.getString(3));
        s.setGiamusic(cs.getInt(4));
        s.setFile(cs.getString(5));
        break;
    }
    cs.close();
    return s;
}
    public static ArrayList<Music> readAlllike(Context context,String like){
        ArrayList<Music> list=new ArrayList<>();
        DBhelper dBhelper=new DBhelper(context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM MUSIC Where MUSIC.thich ='" + like + "'", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int matl=cs.getInt(0);
            String chude=cs.getString(1);
            String tenmusic=cs.getString(2);
            String tacgia=cs.getString(3);
            int gia=cs.getInt(4);
            String img=cs.getString(5);
            int soluong=cs.getInt(6);
            boolean thick=Boolean.parseBoolean(cs.getString(7));
            list.add(new Music(matl,chude,tenmusic,tacgia,gia,img,soluong,thick));
            cs.moveToNext();

        }
        cs.close();
        return list;
    }
    public static ArrayList<Music> getSachtop10(Context context,String month){
        DBhelper dBhelper=new DBhelper(context);
        SQLiteDatabase db=dBhelper.getReadableDatabase();
        ArrayList<Music> list=new ArrayList<>();
//        if(Integer.parseInt(month)<10){
//            month="0"+month;
//        }
        Cursor cs=db.rawQuery("SELECT MaMusic_FK,SUM(soluong2) as soluong2 FROM HOADONCHITIET INNER JOIN HOADON on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk WHERE " +
                "strftime('%Y',HOADON.Ngayhoadon)='"+month+"' GROUP BY MaMusic_FK ORDER BY soluong2 DESC LIMIT 10",null);
//        Cursor cs = db.rawQuery("SELECT MaMusic,SUM(soluong) as soluong FROM HOADONCHITIET INNER JOIN MUSIC on MUSIC.MaMusic=HOADONCHITIET.MaMusic_FK INNER JOIN HOADON on HOADON.MaHoaDon=HOADONCHITIET.MaHD_Fk where '"+month+"'=strftime('%m',HOADON.Ngayhoadon) GROUP BY MaMusic ORDER BY soluong DESC LIMIT 10", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int matl=cs.getInt(0);
            String chude="";
            String tenmusic="";
            String tacgia="";
            int gia=0;
            String img="";
            int soluong=cs.getInt(1);
            boolean thick= Boolean.parseBoolean("");
            list.add(new Music(matl,chude,tenmusic,tacgia,gia,img,soluong,thick));
            cs.moveToNext();
        }
        cs.close();
        return list;
    }


}
