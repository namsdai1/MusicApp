package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.myapplication.dao.Quantridao;
import com.example.myapplication.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static com.example.myapplication.MainActivity.USER;

public class thongtinActivity extends Fragment {
    ArrayList<User> list;
    Quantridao dao;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_thongtin, container, false);
        setHasOptionsMenu(true);
        Toolbar toolbar=view.findViewById(R.id.toolbar3);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Thông tin");
        TextView tvname,tvngya,tvphone,tvemail,tvusername,tvpassword;
        tvname=view.findViewById(R.id.tvten);
        tvngya=view.findViewById(R.id.tvngaysinh);
        tvphone=view.findViewById(R.id.tvphone);
        tvemail=view.findViewById(R.id.tvemail);
        tvusername=view.findViewById(R.id.tvnusername);
        tvpassword=view.findViewById(R.id.tvpass);
        dao=new Quantridao(getContext());
        list=new ArrayList<>();
        list= dao.readAll(new User(USER.getUsername(),USER.getPassword()));
        for(int i=0;i<list.size();i++){
            try {
                Toast.makeText(getContext(), "da tim thay", Toast.LENGTH_SHORT).show();
                if (USER.getUsername().equals(list.get(i).getUsername()) && USER.getPassword().equals(list.get(i).getPassword())) {

                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                    tvname.setText(list.get(i).getTen());
                    tvngya.setText("Ngày sinh"+(sdf.format(list.get(i).getNgay())));
                    tvphone.setText("Phone"+String.valueOf(list.get(i).getPhone()));
                    tvemail.setText("Email:"+list.get(i).getEmail());
                    tvusername.setText("User"+list.get(i).getUsername());
                    tvpassword.setText("Password"+list.get(i).getPassword());
                }
            }catch (Exception ex){}
        }

        return view;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu,menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.dmk: {
                Intent i = new Intent(getContext(), chanceMK.class);
                startActivity(i);
                return true;
            }
            case R.id.out: {
                Intent i = new Intent(getContext(),MainActivity.class);
                startActivity(i);
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }




//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//
//
//        if (item.getItemId() == R.id.dmk) {
//            Toast.makeText(getContext(), "dmk", Toast.LENGTH_SHORT).show();
//            Intent i = new Intent(getContext(), chanceMK.class);
//            startActivity(i);
//        }
//        if (item.getItemId() == R.id.gioithieu)
//            Toast.makeText(getContext(), "gioi thieu", Toast.LENGTH_SHORT).show();
//
//        if (item.getItemId() == R.id.out) {
//            Toast.makeText(getContext(), "out", Toast.LENGTH_SHORT).show();
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }

    }


