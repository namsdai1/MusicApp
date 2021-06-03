package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.adapter.adapterHoadonchitiet;
import com.example.myapplication.adapter.adaterHoaDon;
import com.example.myapplication.dao.HoaDondao;
import com.example.myapplication.dao.Hoadonchitietdao;
import com.example.myapplication.dao.Musicdao;
import com.example.myapplication.dao.Quantridao;
import com.example.myapplication.model.Hoadon;
import com.example.myapplication.model.Hoadonchitiet;
import com.example.myapplication.model.Music;
import com.example.myapplication.user.nguoidungactivity;

import java.util.ArrayList;
import java.util.Date;

public class hoadonchitiet_activity extends AppCompatActivity {
RecyclerView rcvhdct;
Button btnthanhtoan,btnthem;
EditText mahoadon,etmamusic;
TextView thanhtoan;
adapterHoadonchitiet adapter;
ArrayList<Hoadonchitiet> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadonchitiet_activity);
        rcvhdct=findViewById(R.id.rcvhoadonct);
        mahoadon=findViewById(R.id.edmahoadon);
        etmamusic=findViewById(R.id.edmasach);
        btnthanhtoan=findViewById(R.id.btthanhtoan);
        btnthem=findViewById(R.id.btaddhdct);
        thanhtoan=findViewById(R.id.txthdcttong);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(hoadonchitiet_activity.this);
        rcvhdct.setLayoutManager(layoutManager);
        final String mahoadon1=getIntent().getExtras().getString("mahoadon");
        mahoadon.setText(mahoadon1);
        Log.e("loi", "onCreate: "+mahoadon1);
        list=new ArrayList<>();
        list= Hoadonchitietdao.reaAll(hoadonchitiet_activity.this,mahoadon1);
        adapter=new adapterHoadonchitiet(hoadonchitiet_activity.this,list);
        rcvhdct.setAdapter(adapter);
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Music music= Musicdao.getSachbyID(hoadonchitiet_activity.this,Integer.parseInt(etmamusic.getText().toString()));
                   if(Hoadonchitietdao.create(hoadonchitiet_activity.this,new Hoadonchitiet(new Hoadon(mahoadon.getText().toString(),new Date()),music)));
                Toast.makeText(hoadonchitiet_activity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                       list.clear();
                       list.addAll(Hoadonchitietdao.reaAll(hoadonchitiet_activity.this,mahoadon1));
                       adapter.notifyDataSetChanged();
            }
        });

        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tong= (int) Hoadonchitietdao.thanhtoan(hoadonchitiet_activity.this,mahoadon.getText().toString());
                thanhtoan.setText("Thanh toan: "+tong);
            }
        });



    }
}