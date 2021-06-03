package com.example.myapplication.adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Hoadon_activity;
import com.example.myapplication.R;
import com.example.myapplication.dao.HoaDondao;
import com.example.myapplication.hoadonchitiet_activity;
import com.example.myapplication.hoadonchitiet_rcv2;
import com.example.myapplication.model.Hoadon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class adaterHoaDon extends RecyclerView.Adapter<adaterHoaDon.HoaDonHoler> {
    Context context;
    ArrayList<Hoadon> list=new ArrayList<>();
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

    public adaterHoaDon(Context context, ArrayList<Hoadon> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HoaDonHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.hoadon_item, parent, false);
        return (new adaterHoaDon.HoaDonHoler(view));
    }

    @Override
    public void onBindViewHolder(@NonNull HoaDonHoler holder, final int position) {
        holder.tvma.setText(list.get(position).getMaSach());
        holder.tvngya.setText(simpleDateFormat.format(list.get(position).getNgaysach()));
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  String ma=list.get(position).getMaSach();
                  if(HoaDondao.delete(context,ma)){
                      list.remove(position);
                      notifyDataSetChanged();
                      Toast.makeText(context, "Thanh cong", Toast.LENGTH_SHORT).show();
                  }else {
                      Toast.makeText(context, "That bai", Toast.LENGTH_SHORT).show();
                  }
            }
        });
        holder.rlthoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, hoadonchitiet_rcv2.class);
                intent.putExtra("mahoadon2",list.get(position).getMaSach());
                context.startActivity(intent);
            }
        });
    }

    private void dialog(final int position) {
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.hoadon_dialog);
        final EditText etma,etdate;
        Button chondate,them;
        them=dialog.findViewById(R.id.btthemhoadon);
        etma=dialog.findViewById(R.id.etmahoadon);
        etdate=dialog.findViewById(R.id.etngyahoadon);
        chondate=dialog.findViewById(R.id.cavender);
        etma.setText(list.get(position).getMaSach());
        etma.setEnabled(false);
        etdate.setText(simpleDateFormat.format(list.get(position).getNgaysach()));
        chondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                int nhay = calendar.get(Calendar.DAY_OF_MONTH);
                int thang=calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);
                DatePickerDialog datePicker =new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calendar.set(year,month,dayOfMonth);

                        etdate.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },nam,thang,nhay);
                datePicker.show();

            }
        });
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String ma = etma.getText().toString();
                    Date ngay=simpleDateFormat.parse(etdate.getText().toString());
                    Hoadon hoadon=new Hoadon(ma,ngay);
                    if (HoaDondao.update(context,hoadon)){
                        Toast.makeText(context, " Thanh Cong ", Toast.LENGTH_SHORT).show();
                        list.set(position, hoadon);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                } catch (ParseException e) {
                    Toast.makeText(context, "không đúng định dạng yyyy-MM-dd", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HoaDonHoler extends RecyclerView.ViewHolder {
        RelativeLayout rlthoadon;
        TextView tvma,tvngya;
        ImageView btndelete;
        public HoaDonHoler(@NonNull View itemView) {
            super(itemView);
            tvma =itemView.findViewById(R.id.tvmahoadon);
            tvngya =itemView.findViewById(R.id.tvngayhoadon);
            btndelete=itemView.findViewById(R.id.btdeletehoadon);
            rlthoadon=itemView.findViewById(R.id.rlthoadon);
        }
    }
}
