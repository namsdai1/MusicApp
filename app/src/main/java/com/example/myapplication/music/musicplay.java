package com.example.myapplication.music;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dao.Musicdao;
import com.example.myapplication.model.Music;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class musicplay extends AppCompatActivity {
TextView txttitle,txttimestart,txttimefinsh,txttacgia;
SeekBar sksong;
ImageView ivstop,ivplay,ivprev,ivnext,ivrotate;
ArrayList<Music> list;
MediaPlayer mediaPlayer;
Animation animation;
int position= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musicplay);
        anhxa();
        addSong();
        Bundle bundle=getIntent().getBundleExtra("data");
        int position1=bundle.getInt("vitri");
        position=position1;
        Toast.makeText(musicplay.this,String.valueOf(position),Toast.LENGTH_SHORT).show();
        animation=AnimationUtils.loadAnimation(this, R.anim.rotate_music);
        KhoitaoMediaPlayer();
        SetTimeTotal();

        ivplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    ivplay.setImageResource(R.drawable.play);
                    ivrotate.clearAnimation();
                }else {
                    mediaPlayer.start();
                    ivplay.setImageResource(R.drawable.pause);
                    ivrotate.startAnimation(animation);
                }
                SetTimeTotal();
                Updatetimesong();

            }
        });
        ivstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                ivplay.setImageResource(R.drawable.play);
                KhoitaoMediaPlayer();
                ivrotate.clearAnimation();
                finish();

            }
        });
        ivnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if(position>list.size()-1){
                    position=0;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoitaoMediaPlayer();
                mediaPlayer.start();
                ivplay.setImageResource(R.drawable.pause);
                SetTimeTotal();
                Updatetimesong();
                ivrotate.startAnimation(animation);
            }
        });
        ivprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if(position<0){
                    position=list.size()-1;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                KhoitaoMediaPlayer();
                mediaPlayer.start();
                ivplay.setImageResource(R.drawable.pause);
                SetTimeTotal();
                Updatetimesong();
                ivrotate.startAnimation(animation);
            }
        });
        sksong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                     mediaPlayer.seekTo(sksong.getProgress());
            }
        });
    }
    private void Updatetimesong(){
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf=new SimpleDateFormat("mm:ss");
                txttimestart.setText(sdf.format(mediaPlayer.getCurrentPosition()));
                //update sksong
                sksong.setProgress(mediaPlayer.getCurrentPosition());
                //song het ->next
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        int ma=list.get(position).getManhac();
                        String chude=list.get(position).getChude();
                        String ten=list.get(position).getTitle();
                        String tacgia=list.get(position).getTacgia();
                        int giasan=list.get(position).getGiamusic();
                        String file=list.get(position).getFile();
                        int soluong=list.get(position).getSoluong()+1;
                        Log.e("er", "onCompletion: " +soluong);
                        if(Musicdao.updateSL(musicplay.this,new Music(ma,chude,ten,tacgia,giasan,file,soluong,list.get(position).isThick())))
                        {
                            Toast.makeText(musicplay.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(musicplay.this, "Fall", Toast.LENGTH_SHORT).show();
                        }
                        finish();

                    }
                });

                handler.postDelayed(this,500);
            }
        },100);
    }
    private void SetTimeTotal(){
        SimpleDateFormat sdf=new SimpleDateFormat("mm:ss");
        txttimefinsh.setText(sdf.format(mediaPlayer.getDuration()));

        sksong.setMax(mediaPlayer.getDuration());
    }
    private void addSong() {
        list=new ArrayList<>();
        list= Musicdao.readAll(musicplay.this);



    }

    private void anhxa() {
        txttitle      =findViewById(R.id.tvmusic);
        txttimestart  =findViewById(R.id.tvtimestart);
        txttimefinsh  =findViewById(R.id.tvtimefinish);
        txttacgia     =findViewById(R.id.tvtacgia);
        sksong        =findViewById(R.id.sbmusic);
        ivstop        =findViewById(R.id.tvstop);
        ivnext        =findViewById(R.id.tvnext);
        ivplay        =findViewById(R.id.tvplay);
        ivprev        =findViewById(R.id.tvprev);
        ivrotate      =findViewById(R.id.ivrotate);

    }
    private void KhoitaoMediaPlayer(){

        Log.i("Namsadi", String.valueOf(list.get(position).getFile() ));
        int resid=getResources().getIdentifier(list.get(position).getFile(),"raw",getPackageName());
        Log.i("Namsadi", String.valueOf(resid));
        mediaPlayer=MediaPlayer.create(musicplay.this,resid);
        txttitle.setText(list.get(position).getTitle());
        txttacgia.setText(list.get(position).getTacgia());
    }



}
