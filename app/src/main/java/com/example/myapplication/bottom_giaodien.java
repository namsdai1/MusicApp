package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.music.music_fragmen;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottom_giaodien extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giaodienchinh);
                BottomNavigationView bottomNavigationView=findViewById(R.id.navibottom);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment seclected=null;
//                switch (item.getItemId()){
//                    case R.id.navigation_home:
//                        seclected=new giaodienoption();
//                        break;
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,seclected).commit();
//                return true;
//            }
//        });
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(savedInstanceState == null){
            bottomNavigationView.setSelectedItemId(R.id.gdhome);
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_frament,new giaodienoption()).commit();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.gdhome:
                    fragment = new giaodienoption();
                    loadFrament(fragment);
                    return true;
                case R.id.nav_musiclike:
                    fragment = new music_fragmen();
                    loadFrament(fragment);
                    return true;
                case R.id.nav_thongtin1:
                    fragment = new thongtinActivity();
                    loadFrament(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFrament(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_frament, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
