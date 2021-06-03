package com.example.myapplication.music;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.adapterMusic;
import com.example.myapplication.dao.Musicdao;
import com.example.myapplication.model.Music;

import java.util.ArrayList;

public class music_fragmen extends Fragment {
    RecyclerView rcvmusic;
    ArrayList<Music> list;
    adapterMusic adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.musiclike_framen, container, false);
        rcvmusic=view.findViewById(R.id.rcvlikemusic);
        Toolbar toolbar=view.findViewById(R.id.musiclike_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Music Like");
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        rcvmusic.setLayoutManager(layoutManager);
        list=new ArrayList<>();
        list= Musicdao.readAlllike(getContext(),"true");
        adapter=new adapterMusic(getContext(),list);
        rcvmusic.setAdapter(adapter);
        return view;
    }
}
