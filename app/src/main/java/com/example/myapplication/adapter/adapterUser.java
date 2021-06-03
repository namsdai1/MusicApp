package com.example.myapplication.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dao.Quantridao;
import com.example.myapplication.dao.Theloaidao;
import com.example.myapplication.model.User;
import com.example.myapplication.theloai.TheLoaiActivity;
import com.example.myapplication.user.nguoidungactivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class adapterUser extends RecyclerView.Adapter<adapterUser.nguoidungHolder> {
    Context context;
    ArrayList <User> list;
    SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd");

    public adapterUser(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public nguoidungHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return (new adapterUser.nguoidungHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull nguoidungHolder holder, final int position) {
        holder.txttenuser.setText(list.get(position).getTen());
        holder.txtngayuser.setText(sdf.format(list.get(position).getNgay()));
        holder.txtemaiuser.setText(list.get(position).getEmail());
        holder.ivxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ma=list.get(position).getMatc();
                if(Quantridao.delete(context,ma)){
                    Toast.makeText(context," thành công!!!",
                            Toast.LENGTH_LONG).show();
                    list.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
        holder.hinhanhuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dialog(position);
            }
        });

    }
    private void dialog(final int position){
        final EditText etname,etngay,etphone,etemail,etusername,etpassword;
        Button btthem,bthuy;
        TextView title;
        final Dialog dialog=new Dialog(context);
        dialog.setContentView(R.layout.dialog_user);
        title=dialog.findViewById(R.id.titleuser);
        bthuy=dialog.findViewById(R.id.btuserhuy);
        etname=dialog.findViewById(R.id.etnameuser);
        etngay=dialog.findViewById(R.id.etdateuser);
        etphone=dialog.findViewById(R.id.etphoneuser);
        etemail=dialog.findViewById(R.id.etemailuser);
        etusername=dialog.findViewById(R.id.etadminuser);
        etpassword=dialog.findViewById(R.id.etpwduser);
        btthem=dialog.findViewById(R.id.btuser);
        etname.setText(list.get(position).getTen());
        etngay.setText(sdf.format(list.get(position).getNgay()));
        etphone.setText(String.valueOf(list.get(position).getPhone()));
        etemail.setText(list.get(position).getEmail());
        etusername.setText(list.get(position).getUsername());
        etpassword.setText(list.get(position).getPassword());
        title.setText("Cập nhật");
        bthuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                int ma=list.get(position).getMatc();
                String name = etname.getText().toString();
                  Date ngay = sdf.parse(etngay.getText().toString());
                int phone = Integer.parseInt(etphone.getText().toString());
                String email = etemail.getText().toString();
                String username = etusername.getText().toString();
                String password = etpassword.getText().toString();

                User user = new User(ma,name, ngay, phone, email, username, password);
                if (Quantridao.update(context, user)) {
                    Toast.makeText(context, "thanh cong", Toast.LENGTH_LONG).show();
                    list.set(position, user);
                    notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(context, "Fall", Toast.LENGTH_LONG).show();
                }
                } catch (Exception e) {
                    Toast.makeText(context, "date sai", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class nguoidungHolder extends RecyclerView.ViewHolder{
        ImageView hinhanhuser,ivxoa;
        TextView txttenuser,txtngayuser,txtemaiuser;
        CardView cardView;
        public nguoidungHolder(@NonNull View itemView) {
            super(itemView);
            hinhanhuser=itemView.findViewById(R.id.rcvimguser);
            ivxoa=itemView.findViewById(R.id.rcvxoauser);
            txttenuser=itemView.findViewById(R.id.rcvtenuser);
            txtngayuser=itemView.findViewById(R.id.rcvdateuser);
            txtemaiuser=itemView.findViewById(R.id.rcvemail);
            cardView=itemView.findViewById(R.id.user_item);
        }
    }
}
