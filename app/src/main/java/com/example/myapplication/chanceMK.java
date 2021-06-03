package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.dao.Quantridao;

import static com.example.myapplication.MainActivity.USER;

public class chanceMK extends AppCompatActivity {
EditText etmkc,etmkm,etmkm2;
Quantridao qtdao;
Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chance_m_k);
        etmkc=findViewById(R.id.etmkc);
        etmkm=findViewById(R.id.etmkm);
        etmkm2=findViewById(R.id.etmkm2);
        Button btchance = findViewById(R.id.btchance);
        qtdao=new Quantridao(chanceMK.this);
        final String usernamecu=USER.getUsername();
        final String passwordcu=USER.getPassword();
        btchance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mkc=etmkc.getText().toString();
                String mkm=etmkm.getText().toString();
                String mkm2=etmkm2.getText().toString();
                  if(!passwordcu.equals(mkc)){
                      Toast.makeText(chanceMK.this,"Sai mật khẩu cũ",Toast.LENGTH_LONG).show();
                  }else {
                      if(mkm.equals(mkm2)) {
                          if (qtdao.chancepassword(usernamecu, mkm)) {
                              Toast.makeText(chanceMK.this, "Thanh công", Toast.LENGTH_LONG).show();
                          }
                      }else {
                          Toast.makeText(chanceMK.this, "mật khẫu mới không trùng khớp", Toast.LENGTH_LONG).show();
                      }
                  }
            }
        });

    }
}
