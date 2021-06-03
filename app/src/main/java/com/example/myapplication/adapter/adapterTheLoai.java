package com.example.myapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.Quantridao;
import com.example.myapplication.dao.Theloaidao;
import com.example.myapplication.model.TheLoai;
import com.example.myapplication.theloai.TheLoaiActivity;
import android.widget.Filterable;
import java.util.ArrayList;

public class adapterTheLoai extends RecyclerView.Adapter<adapterTheLoai.thelaoiHoder> {
    Context context;
    ArrayList <TheLoai> list;

    public adapterTheLoai(Context context, ArrayList<TheLoai> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public thelaoiHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.theloai_item, parent, false);
        return (new adapterTheLoai.thelaoiHoder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull thelaoiHoder holder, final int position) {
        holder.txtchude.setText(list.get(position).getTentheloai());
        holder.txtmota.setText(list.get(position).getMota());
        holder.tvmatheloai.setText(list.get(position).getMatheloai());
        holder.ivxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma=list.get(position).getMatheloai();

                if(Theloaidao.delete(context,ma)){
                    Toast.makeText(context," thành công!!!",
                            Toast.LENGTH_LONG).show();
                    list.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
        holder.rltl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog(position);
            }


        });
            }


    private void dialog(final int position) {
        TextView title;
        final EditText etma,etten,etmota;
        Button btthem,bthuy;
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_theloai);
        dialog.setTitle("Cap nhat");
        title=dialog.findViewById(R.id.titletl);
        title.setText("Cập nhật");
        etma =dialog.findViewById(R.id.etmatheloai);
        etten=dialog.findViewById(R.id.tentheloai);
        etmota=dialog.findViewById(R.id.motatheloai);
        btthem=dialog.findViewById(R.id.themtheloai);
        bthuy=dialog.findViewById(R.id.huytheloai);
        etma.setText(list.get(position).getMatheloai());
        etten.setText(list.get(position).getTentheloai());
        etmota.setText(list.get(position).getMota());
        bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ma=etma.getText().toString();
                String ten=etten.getText().toString();
                String mota=etmota.getText().toString();
                Toast.makeText(context,ma,Toast.LENGTH_LONG).show();
                TheLoai theLoai=new TheLoai(ma,ten,mota);
                if(Theloaidao.update(context,theLoai)){
                    Toast.makeText(context,"thang cong",Toast.LENGTH_LONG).show();
                    list.set(position, theLoai);
                    notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context,"ko thang cong",Toast.LENGTH_LONG).show();
                }
            }

        });
        dialog.show();
    }
    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class thelaoiHoder extends RecyclerView.ViewHolder{
        TextView txtchude,txtmota,tvmatheloai;
        ImageView ivxoa;
        RelativeLayout rltl;
          public thelaoiHoder(@NonNull View itemView) {
              super(itemView);
              txtchude=itemView.findViewById(R.id.tvchudetheloai);
              txtmota=itemView.findViewById(R.id.tvmota);
              tvmatheloai=itemView.findViewById(R.id.tvmatheloai);
              ivxoa      =itemView.findViewById(R.id.rcvxoatheloai);
              rltl       =itemView.findViewById(R.id.relative_tl);
          }
      }
}
