package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myapplication.dao.Quantridao;
import com.example.myapplication.model.User;

import java.util.ArrayList;


public class giaodien extends AppCompatActivity {
DrawerLayout drawerLayout;
    ArrayList<User> list;
    Quantridao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien);

//        drawerLayout=findViewById(R.id.drawer_layout);
//        Toolbar toolbar=findViewById(R.id.myToobar);
//
//        NavigationView navigationView=findViewById(R.id.nav_view);
//        //settext ten header trong navigation
//        View headerView=navigationView.getHeaderView(0);
//        TextView ten=headerView.findViewById(R.id.tvhearder);
//        TextView email=headerView.findViewById(R.id.tvemai_nav);
//        dao=new quantridao(this);
//        list=new ArrayList<>();
//        list=quantridao.readAll(this,new User(USER.getUsername(),USER.getPassword()));
//
//        for(int i=0;i<list.size();i++){
//            try {
//                Toast.makeText(this, "da tim thay", Toast.LENGTH_SHORT).show();
//
//                if (USER.getUsername().equals(list.get(i).getUsername()) && USER.getPassword().equals(list.get(i).getPassword())) {
//                    ten.setText(list.get(i).getTen());
//                    email.setText(list.get(i).getEmail());
//                }
//            }catch (Exception ex){}
//        }
//
//
//        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch (menuItem.getItemId()){
////                    case R.id.nav_thongitn:
////                        getSupportFragmentManager().beginTransaction().replace(R.id.framContainer,new thongtinActivity()).commit();
////                        break;
////                    case R.id.nav_key:
////                        getSupportFragmentManager().beginTransaction().replace(R.id.framContainer,new chanceMK()).commit();
////                        break;
//                }
//                return false;
//            }
//        });
//
//    }
//    public void laydu(){

    }
}
