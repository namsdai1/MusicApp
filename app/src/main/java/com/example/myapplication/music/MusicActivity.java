package com.example.myapplication.music;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.adapterMusic;
import com.example.myapplication.dao.Musicdao;
import com.example.myapplication.dao.Theloaidao;
import com.example.myapplication.model.Music;
import com.example.myapplication.model.TheLoai;
import com.example.myapplication.theloai.TheLoaiActivity;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {
RecyclerView rcvmusic;
    ArrayList<Music> list;
    adapterMusic adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        Toolbar toolbar=findViewById(R.id.toolbar_music);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Music");

        rcvmusic=findViewById(R.id.rcvmusic);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MusicActivity.this);
        rcvmusic.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        list= Musicdao.readAll(MusicActivity.this);
        adapter=new adapterMusic(MusicActivity.this,list);
        rcvmusic.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_music1,menu);
        MenuItem seachItem=menu.findItem(R.id.svmusic);
        SearchView searchView=(SearchView) seachItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MusicActivity.this, query, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.menu_music1){
            dialog();
            Toast.makeText(this, "Them", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==android.R.id.home){
            Toast.makeText(this, "Them", Toast.LENGTH_SHORT).show();

            finish();
        }
        return super.onContextItemSelected(item);
    }
    private void dialog(){
        final EditText tenmusic,giamusic,tacgiamusic;
        Button btthem,bthuy;
        TextView title;
        final Spinner spchude,splinkmusic;
        final Dialog dialog=new Dialog(MusicActivity.this);
        dialog.setContentView(R.layout.dialog_music);

        title=dialog.findViewById(R.id.titlemusic);
        title.setText("Thêm Music");
        tenmusic=dialog.findViewById(R.id.tenmusic);
        spchude=dialog.findViewById(R.id.sptheloai);
        giamusic=dialog.findViewById(R.id.buymusic);
        tacgiamusic=dialog.findViewById(R.id.tacgiamusic);
        splinkmusic=dialog.findViewById(R.id.spmusic);
        btthem=dialog.findViewById(R.id.btthemmusic);
        //spiner the loai
        ArrayList<TheLoai> listtl=new ArrayList<>();
        listtl= Theloaidao.readAll(MusicActivity.this);
        ArrayList<String> listspinner =new ArrayList<>();
        for (int i=0;i<listtl.size();i++){
            listspinner.add(listtl.get(i).getTentheloai());
        }

        ArrayAdapter adapter2=new ArrayAdapter(this,android.R.layout.simple_spinner_item , listspinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spchude.setAdapter(adapter2);

        //spinner link music
        ArrayList<String> list2 = new ArrayList<>();
        Field[] fields=R.raw.class.getFields();
        for (int i=0;i<fields.length;i++){
            list2.add(fields[i].getName());
            Log.i("Nam",fields[i].getName());
            Log.i("Namkobk",list2.get(i));
        }

        ArrayAdapter adapter1=new ArrayAdapter(this,android.R.layout.simple_spinner_item , list2);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        splinkmusic.setAdapter(adapter1);

        btthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenbaihat=tenmusic.getText().toString();
                String chude=spchude.getSelectedItem().toString();
                int gia = Integer.parseInt(giamusic.getText().toString());
                String tacgia = tacgiamusic.getText().toString();
                String file=splinkmusic.getSelectedItem().toString();

                if(Musicdao.create(MusicActivity.this,new Music(chude,tenbaihat,tacgia,gia,file))){
                    Toast.makeText(MusicActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(Musicdao.readAll(MusicActivity.this));
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(MusicActivity.this, "That bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bthuy=dialog.findViewById(R.id.bthuymusic);
        dialog.setTitle("Music Play");
        bthuy.setText("Huỷ");
        bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }
}
