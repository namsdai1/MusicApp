package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.adapter.adaterHoaDon;
import com.example.myapplication.dao.HoaDondao;
import com.example.myapplication.dao.Quantridao;
import com.example.myapplication.model.Hoadon;
import com.example.myapplication.user.nguoidungactivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Hoadon_activity extends AppCompatActivity {
    RecyclerView recyclerView;
    adaterHoaDon adapter;
    ArrayList<Hoadon> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon_activity);

        recyclerView=findViewById(R.id.rcvhoadon);
        Toolbar toolbar=findViewById(R.id.toolbar_hoadon);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Hóa đơn");
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(Hoadon_activity.this);
        recyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        list= HoaDondao.readAll(Hoadon_activity.this);
        adapter=new adaterHoaDon(Hoadon_activity.this,list);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_hoadon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_hoadn){
            dialog();
            Toast.makeText(this, "Them", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==android.R.id.home){
            Toast.makeText(this, "Them", Toast.LENGTH_SHORT).show();

            finish();
        }
        return super.onContextItemSelected(item);
    }

    private void dialog() {

        final Dialog dialog=new Dialog(Hoadon_activity.this);
        dialog.setContentView(R.layout.hoadon_dialog);
        final EditText etma,etdate;
        Button chondate,them;
        them=dialog.findViewById(R.id.btthemhoadon);
        etma=dialog.findViewById(R.id.etmahoadon);
        etdate=dialog.findViewById(R.id.etngyahoadon);
        chondate=dialog.findViewById(R.id.cavender);
        chondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar calendar=Calendar.getInstance();
                int nhay = calendar.get(Calendar.DAY_OF_MONTH);
                int thang=calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePicker =new DatePickerDialog(Hoadon_activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(year,month,dayOfMonth);

                        etdate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },nam,thang,nhay);
                datePicker.show();

            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ma = etma.getText().toString();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String ngay1=(etdate.getText().toString());

//                Date ngay = null;
//                try {
//                    ngay = sdf.parse(etdate.getText().toString());
//                } catch (ParseException e) {
//                    Toast.makeText(Hoadon_activity.this, "không đúng định dạng yyyy-MM-dd", Toast.LENGTH_SHORT).show();
//                }

                    Log.e("kobk", "onClick: "+etdate.getText().toString());
                try {
                    if (HoaDondao.create(Hoadon_activity.this,new Hoadon(ma,sdf.parse(etdate.getText().toString()))))
                 {
                    Toast.makeText(Hoadon_activity.this, " Thanh Cong ", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Hoadon_activity.this,hoadonchitiet_activity.class);
                    intent.putExtra("mahoadon",etma.getText().toString());
                    startActivity(intent);
                    list.clear();
                    list.addAll(HoaDondao.readAll(Hoadon_activity.this));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();

                }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
        dialog.show();
    }
}