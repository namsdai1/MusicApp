package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.Hoadonchitietdao;
import com.example.myapplication.model.Hoadonchitiet;

import java.util.ArrayList;

public class adapterHoadonchitiet extends RecyclerView.Adapter<adapterHoadonchitiet.HoaDonCTHolder> {
    Context context;
    ArrayList<Hoadonchitiet> list;

    public adapterHoadonchitiet(Context context, ArrayList<Hoadonchitiet> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HoaDonCTHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hoadonct_item, parent, false);
        return (new adapterHoadonchitiet.HoaDonCTHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull final HoaDonCTHolder holder, final int position) {
         holder.maSach.setText("Ma MUSIC:"+list.get(position).getMusic().getManhac());
         holder.giaMusic.setText("Buy MUSIC" +list.get(position).getMusic().getGiamusic());
         holder.ivdelete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                   if(Hoadonchitietdao.delete(context,list.get(position).getMahoadonchitiet())){
                       Toast.makeText(context, "Thành công", Toast.LENGTH_SHORT).show();
                       list.remove(position);
                       notifyDataSetChanged();
                   }else {
                       Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show();
                   }
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HoaDonCTHolder extends RecyclerView.ViewHolder{
        TextView maSach,giaMusic;
        ImageView ivdelete;
        public HoaDonCTHolder(@NonNull View itemView) {
            super(itemView);
            maSach =itemView.findViewById(R.id.txtmamusichdct);
            giaMusic=itemView.findViewById(R.id.buymusichdct);
            ivdelete=itemView.findViewById(R.id.deletehdct);


        }
    }
}
