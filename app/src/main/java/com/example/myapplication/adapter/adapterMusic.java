package com.example.myapplication.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Filter;

import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.Musicdao;
import com.example.myapplication.dao.Theloaidao;
import com.example.myapplication.model.Music;
import com.example.myapplication.model.TheLoai;
import com.example.myapplication.music.MusicActivity;
import com.example.myapplication.music.musicplay;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class adapterMusic extends RecyclerView.Adapter<adapterMusic.musicHoler> implements Filterable {
    Context context;
    private ArrayList<Music> list;
    private ArrayList<Music> list2;
    ArrayAdapter adapter2;

    public adapterMusic(Context context, ArrayList<Music> list) {
        this.context = context;
        this.list = list;
        list2=new ArrayList<>(list);
    }

    @NonNull
    @Override
    public musicHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_music, parent, false);
        return (new adapterMusic.musicHoler(view));
    }

    @Override
    public void onBindViewHolder(@NonNull final musicHoler holder, final int position) {
        holder.tenmusic.setText(list.get(position).getTitle());
        holder.maid.setText("Mã:"+list.get(position).getManhac());
        holder.chudemusic.setText(list.get(position).getChude());

        holder.playnhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context, musicplay.class);
                Bundle bundle=new Bundle();
                bundle.putInt("vitri",position);
                i.putExtra("data",bundle);
                (context).startActivity(i);
            }
        });

        holder.rltmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(position);
            }
        });


//load data
//        String thongtin="thongtin";
//        String file=".dat";
//        for(int i=0;i<list.size();i++){
//
//            String thongtindat=thongtin+i+file;
//            SharedPreferences pref = context.getSharedPreferences(thongtindat, MODE_PRIVATE);
//            final boolean check = pref.getBoolean("check", false);
//            Log.e("Loi", "onBindViewHolder: "+check +(pref.getInt("positon",0)));
//            if(pref.getInt("positon",0)==position){
//                if (check==true) {
//                    holder.heart.setChecked(true);
//                    holder.heart.setBackgroundResource(R.drawable.heart2);
//
//                }else {
//                    holder.heart.setChecked(false);
//                    holder.heart.setBackgroundResource(R.drawable.heart1);
//                }
//            }
//        }
        if(list.get(position).isThick()==true){
            holder.heart.setChecked(true);
            holder.heart.setBackgroundResource(R.drawable.heart2);
        }else {
            holder.heart.setChecked(false);
            holder.heart.setBackgroundResource(R.drawable.heart1);
        }


        holder.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.heart.isChecked()) {
                    Toast.makeText(context, "true", Toast.LENGTH_SHORT).show();
                    holder.heart.setBackgroundResource(R.drawable.heart2);
                    boolean checs=true;
                    Musicdao.updateLike(context,new Music(list.get(position).getManhac(), checs));
//                    chech(checs,position);//true

                }else {
                    Toast.makeText(context, "flase", Toast.LENGTH_SHORT).show();
                    holder.heart.setBackgroundResource(R.drawable.heart1);
                    boolean checs=false;
                    Musicdao.updateLike(context,new Music(list.get(position).getManhac(), checs));
//                    chech(checs,position);//false
                }

            }
        });



        if(position%3==0){
            holder.rltmusic.setBackgroundResource(R.drawable.mussicbg);
        }else if(position%2==0){
            holder.rltmusic.setBackgroundResource(R.drawable.musicbk2);
        }

    }
//    private void chech(boolean check,int positon1){
//        String thongtin="thongtin";
//        String file=".dat";
//        for(int i=0;i<list.size();i++) {
//            if (positon1 == i) {
//                String thongtindat = thongtin + i + file;
//                Log.e("thua", "chech: " + thongtindat);
//                SharedPreferences preferences = context.getSharedPreferences(thongtindat, MODE_PRIVATE);
//                Editor editor = preferences.edit();
//                if (check) {
//                    editor.putBoolean("check", check);
//                    editor.putInt("positon", positon1);
//                    Log.e("chech", "chech: " + check + positon1);
//                } else {
//                    editor.clear();
//                }
//                editor.commit();
//            }
//        }
//        }



    private void dialog(final int position) {
//        final AlertDialog dialog = new AlertDialog.Builder(context)
//                .setTitle("Cap nhat")
//                .setView(R.layout.dialog_music)
//                .create();
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_music);

        final EditText tenmusic,giamusic,tacgiamusic;
        Button btthem,bthuy;
        final Spinner spchude,splinkmusic;
        TextView title;
        title=dialog.findViewById(R.id.titlemusic);
        title.setText("Cập nhật Music");
        tenmusic=dialog.findViewById(R.id.tenmusic);
        bthuy=dialog.findViewById(R.id.bthuymusic);
        spchude=dialog.findViewById(R.id.sptheloai);
        giamusic=dialog.findViewById(R.id.buymusic);
        tacgiamusic=dialog.findViewById(R.id.tacgiamusic);
        splinkmusic=dialog.findViewById(R.id.spmusic);
        btthem=dialog.findViewById(R.id.btthemmusic);
        tacgiamusic.setText(list.get(position).getTacgia());
        tenmusic.setText(list.get(position).getTitle());
        giamusic.setText(String.valueOf(list.get(position).getGiamusic()));
        bthuy.setText("Xóa");
        //spiner the loai
        ArrayList<TheLoai> listtl=new ArrayList<>();
        listtl= Theloaidao.readAll(context);
        ArrayList<String> listspinner =new ArrayList<>();
        for (int i=0;i<listtl.size();i++){
            listspinner.add(listtl.get(i).getTentheloai());
        }

        adapter2=new ArrayAdapter(context,android.R.layout.simple_spinner_item , listspinner);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spchude.setAdapter(adapter2);
        for (int i=0;i<listtl.size();i++){
            if (listtl.get(i).equals(list.get(position).getChude())){
                spchude.setSelection(i);
            }
        }

        //spinner link music
        ArrayList<String> list2 = new ArrayList<>();
        Field[] fields=R.raw.class.getFields();
        for (int i=0;i<fields.length;i++){
            list2.add(fields[i].getName());
            Log.i("Nam",fields[i].getName());
            Log.i("Namkobk",list2.get(i));
        }

        ArrayAdapter adapter1=new ArrayAdapter(context,android.R.layout.simple_spinner_item , list2);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        splinkmusic.setAdapter(adapter1);
        for (int i=0;i<list2.size();i++){
            if (list2.get(i).equals(list.get(position).getFile())){
                splinkmusic.setSelection(i);
            }
        }
        //


        btthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int manhac=list.get(position).getManhac();
                String tenbaihat=tenmusic.getText().toString();
                String chude=spchude.getSelectedItem().toString();
                int gia = Integer.parseInt(giamusic.getText().toString());
                String tacgia = tacgiamusic.getText().toString();
                String file=splinkmusic.getSelectedItem().toString();
                Music music=new Music(manhac,chude,tenbaihat,tacgia,gia,file);
                if(Musicdao.update(context,music)){
                    Toast.makeText(context, "Thanh cong", Toast.LENGTH_SHORT).show();
                    list.set(position, music);
                    notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.setTitle("Music Play");
        bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ma=list.get(position).getManhac();
                if(Musicdao.delete(context,ma)){
                    Toast.makeText(context, "xoa thanh cong", Toast.LENGTH_SHORT).show();
                    list.remove(position);
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return listFilter;
    }
    private Filter listFilter =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Music> fillteredList=new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                fillteredList.addAll(list2);
            }else {
                String fillterPattern=constraint.toString().toLowerCase().trim();
                for(Music music: list2){
                    if(music.getTitle().toLowerCase().contains(fillterPattern)){
                        fillteredList.add(music);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=fillteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public static class musicHoler extends RecyclerView.ViewHolder {
        TextView tenmusic,maid,chudemusic;
        ImageView playnhac;
        CheckBox heart;
        FrameLayout rltmusic;
        public musicHoler(@NonNull View itemView) {
            super(itemView);
            tenmusic=itemView.findViewById(R.id.txtmusic);
            maid=itemView.findViewById(R.id.txtid);
            chudemusic=itemView.findViewById(R.id.txtchude);
            playnhac=itemView.findViewById(R.id.playnhac);
            rltmusic=itemView.findViewById(R.id.relative_music);
            heart=itemView.findViewById(R.id.heart1);
        }
    }
}
