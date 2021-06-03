package com.example.myapplication.theloai;

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
import com.example.myapplication.adapter.adapterTheLoai;
import com.example.myapplication.adapter.adapterUser;

import com.example.myapplication.dao.Quantridao;
import com.example.myapplication.dao.Theloaidao;
import com.example.myapplication.model.TheLoai;
import com.example.myapplication.user.nguoidungactivity;


import java.util.ArrayList;

public class TheLoaiActivity extends AppCompatActivity {
    RecyclerView rcv;
    ArrayList<TheLoai> list;
    adapterTheLoai adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai);
        Toolbar toolbar=findViewById(R.id.toolbar_theloai);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Thể Loại");
        rcv=findViewById(R.id.rcvtheloai);
        //
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(TheLoaiActivity.this);
        rcv.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        list= Theloaidao.readAll(TheLoaiActivity.this);
        adapter=new adapterTheLoai(TheLoaiActivity.this,list);
        rcv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_theloai,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.menu_theloai){
            dialog();
            Toast.makeText(this, "Them", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==android.R.id.home){
            Toast.makeText(this, "Them", Toast.LENGTH_SHORT).show();

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void dialog() {
        TextView title;
        final EditText etma,etten,etmota;
        Button btthem,bthuy;
        final Dialog dialog=new Dialog(TheLoaiActivity.this);
        dialog.setContentView(R.layout.dialog_theloai);
        title=dialog.findViewById(R.id.titletl);
        title.setText("Thêm thể loại");
        etma =dialog.findViewById(R.id.etmatheloai);
        etten=dialog.findViewById(R.id.tentheloai);
        etmota=dialog.findViewById(R.id.motatheloai);
        btthem=dialog.findViewById(R.id.themtheloai);
        bthuy=dialog.findViewById(R.id.huytheloai);
        bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma=etma.getText().toString();
                String ten=etten.getText().toString();
                String mota=etmota.getText().toString();
                Toast.makeText(TheLoaiActivity.this,ma,Toast.LENGTH_LONG).show();
                if(Theloaidao.create(TheLoaiActivity.this,new TheLoai(ma,ten,mota))){
                    Toast.makeText(TheLoaiActivity.this,"thang cong",Toast.LENGTH_LONG).show();
                    list.clear();
                    list.addAll(Theloaidao.readAll(TheLoaiActivity.this));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(TheLoaiActivity.this,"ko thang cong",Toast.LENGTH_LONG).show();
                }
                }

        });
        dialog.show();
    }
}
