package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class chao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chao);

        sangmanin();
    }
    private void sangmanin(){
       Thread thread=new Thread(){
           @Override
           public void run() {
               try {
                   Thread.sleep(2000);

                   Intent i=new Intent(chao.this, MainActivity.class);
                   startActivity(i);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       };
       thread.start();
    }
}
