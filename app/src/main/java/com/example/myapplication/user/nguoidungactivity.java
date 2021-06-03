package com.example.myapplication.user;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.adapterUser;
import com.example.myapplication.dao.Quantridao;
import com.example.myapplication.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class nguoidungactivity extends AppCompatActivity {
RecyclerView rcv;
ArrayList<User> list;
adapterUser adapter;
Quantridao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoidungactivity);
        rcv=findViewById(R.id.srcuser);

        Toolbar toolbar=findViewById(R.id.toolbar_user);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //recycleView
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(nguoidungactivity.this);
        rcv.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        list=Quantridao.getAll(nguoidungactivity.this);
        adapter=new adapterUser(nguoidungactivity.this,list);
        rcv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_nguoidung,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.menu_user){
            dialog();
            Toast.makeText(this, "Them", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==android.R.id.home){
            Toast.makeText(this, "Them", Toast.LENGTH_SHORT).show();

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void dialog(){
        final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        final EditText etname,etngay,etphone,etemail,etusername,etpassword;
        TextView title;
        Button btthem,bthuy;
        final Dialog dialog=new Dialog(nguoidungactivity.this);
        dialog.setContentView(R.layout.dialog_user);
        title=dialog.findViewById(R.id.titleuser);
        bthuy=dialog.findViewById(R.id.btuserhuy);
        etname=dialog.findViewById(R.id.etnameuser);
        etngay=dialog.findViewById(R.id.etdateuser);
        etphone=dialog.findViewById(R.id.etphoneuser);
        etemail=dialog.findViewById(R.id.etemailuser);
        etusername=dialog.findViewById(R.id.etadminuser);
        etpassword=dialog.findViewById(R.id.etpwduser);
        btthem=dialog.findViewById(R.id.btuser);
        title.setText("Thêm User");
        bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                 String ten=etname.getText().toString();
                 Date ngay;

                 ngay=sdf.parse(etngay.getText().toString());

                 int phone = 0;
                 try {
                      phone=Integer.parseInt(etphone.getText().toString());
                 }catch (Exception ex){
                     Toast.makeText(nguoidungactivity.this, "Phone định dạng number.Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                 }

                 String email = etemail.getText().toString();
                 String username = etusername.getText().toString();
                 String pass = etpassword.getText().toString();
                 if(Quantridao.create(nguoidungactivity.this,new User(ten,ngay,phone,email,username,pass))){
                     Toast.makeText(nguoidungactivity.this,"thang cong",Toast.LENGTH_LONG).show();
                     list.clear();
                     list.addAll(Quantridao.getAll(nguoidungactivity.this));
                     adapter.notifyDataSetChanged();
                     dialog.dismiss();
                 }else {
                     Toast.makeText(nguoidungactivity.this,"ko thang cong",Toast.LENGTH_LONG).show();
                 }
                } catch (ParseException e) {
                    Toast.makeText(nguoidungactivity.this, "Bạn nhập ko đúng định dạng ngày \n Định dạng ngày:yyyy_", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setCancelable(false);
        dialog.show();

    }
}
