package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.myapplication.music.MusicActivity;
import com.example.myapplication.theloai.TheLoaiActivity;
import com.example.myapplication.user.nguoidungactivity;

public class giaodienoption extends Fragment {
    ImageView ivuser, ivmusic, ivthememusic, ivkobk, ivthongke, ivbuy;
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_giaodienoption, container, false);

         toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");
        ivuser = view.findViewById(R.id.ivgd1);
        ivmusic = view.findViewById(R.id.ivgd2);
        ivthememusic = view.findViewById(R.id.ivgd3);
        ivkobk = view.findViewById(R.id.ivgd4);
        ivthongke = view.findViewById(R.id.ivgd5);
        ivbuy = view.findViewById(R.id.ivgd6);
        ivuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), nguoidungactivity.class);
                startActivity(i);
            }
        });
        ivthememusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), TheLoaiActivity.class);
                startActivity(i);
            }
        });
        ivmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MusicActivity.class);
                startActivity(i);
            }
        });
        ivkobk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Hoadon_activity.class);
                startActivity(i);
            }
        });
        ivthongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Thongke_activity.class);
                startActivity(i);
            }
        });
        ivbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), biudo_activity.class);
                startActivity(i);
            }
        });
        return view;
    }




//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.refresh) {
//            Intent i = new Intent(giaodienoption.this, thongtinActivity.class);
//            startActivity(i);
//            Toast.makeText(this, "Thông tin", Toast.LENGTH_SHORT).show();
//        }
//        if (item.getItemId() == R.id.dmk) {
//            Toast.makeText(this, "dmk", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(giaodienoption.this, chanceMK.class);
//            startActivity(i);
//        }
//        if (item.getItemId() == R.id.gioithieu)
//            Toast.makeText(this, "gioi thieu", Toast.LENGTH_SHORT).show();
//
//        if (item.getItemId() == R.id.out) {
//            Toast.makeText(this, "out", Toast.LENGTH_SHORT).show();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}



