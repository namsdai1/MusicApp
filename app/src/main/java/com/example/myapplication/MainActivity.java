package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.dao.Quantridao;
import com.example.myapplication.model.User;


public class MainActivity extends AppCompatActivity {
    EditText etUsername,etPass;
    Button btDangNhap,btHuy,btnMy;
    LinearLayout ggg;
    CheckBox ckRem;
    public static User USER =null;
    Quantridao qtdao;
    AnimationDrawable animationDrawable;
    AnimationDrawable animationDrawable2;
    RelativeLayout gg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qtdao=new Quantridao(MainActivity.this);
        gg=findViewById(R.id.ggg);
        //Tham chieu
        //anim img poly
        ImageView imgbee=findViewById(R.id.ivbee);
        Animation animation= AnimationUtils.loadAnimation(MainActivity.this
                , R.anim.sile);
        imgbee.startAnimation(animation);
        ImageView imglogo=findViewById(R.id.imageView);
        imglogo.setBackgroundResource(R.drawable.anim_poly);
        animationDrawable=(AnimationDrawable) imglogo.getBackground();
        animationDrawable.start();
// anim layout
        animationDrawable2=(AnimationDrawable)gg.getBackground();
        animationDrawable2.setEnterFadeDuration(5000);
        animationDrawable2.setExitFadeDuration(2000);
        animationDrawable2.start();



        etUsername= findViewById(R.id.txtUsername);
        etPass= findViewById(R.id.txtPassword);
        btDangNhap= findViewById(R.id.btnDangNhap);

        ckRem=findViewById(R.id.checkbox);
        loadData();




        btDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username=etUsername.getText().toString();
                String pass=etPass.getText().toString();
                boolean check=ckRem.isChecked();
                User user=new User(username,pass);
                if(qtdao.checkLogin(user)){
                    luuTT(username,pass,check);
                    USER =user;
                    Toast.makeText(MainActivity.this,"Dang nhap thanh cong!!!",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(MainActivity.this,bottom_giaodien.class);
                    startActivity(i);

                }else{
                    Toast.makeText(MainActivity.this,"Dang nhap that bai!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private  void luuTT(String un,String pwd,boolean check){
        SharedPreferences preferences=getSharedPreferences("thongtin.dat",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        if(check){
            editor.putString("username",un);
            editor.putString("password",pwd);
            editor.putBoolean("check",check);
        }else{
            editor.clear();
        }
        editor.commit();
    }

    private void loadData(){
        SharedPreferences pref=getSharedPreferences("thongtin.dat",MODE_PRIVATE);
        boolean check=pref.getBoolean("check",false);
        if(check){
            etUsername.setText(pref.getString("username",""));
            etPass.setText(pref.getString("password",""));
            ckRem.setChecked(check);

        }
    }



}