package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Hoadonchitiet;
import com.example.myapplication.model.Music;

import java.util.ArrayList;

public class top10_adapter extends RecyclerView.Adapter<top10_adapter.top10Holer> implements Filterable {
    ArrayList<Music> list=new ArrayList<>();
    Context context;

    public top10_adapter(Context context,ArrayList<Music> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public top10Holer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top10, parent, false);
        return (new top10_adapter.top10Holer(view));
    }

    @Override
    public void onBindViewHolder(@NonNull top10Holer holder, int position) {
         holder.txtmamusic.setText("Mã nhạc : "+list.get(position).getManhac());
         holder.txtsoluon.setText("Số lượng đã bán:"+list.get(position).getSoluong());

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
                fillteredList.addAll(list);
            }else {
                String fillterPattern=constraint.toString().toLowerCase().trim();
                for(Music music: list){
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
    public static class top10Holer extends RecyclerView.ViewHolder {
        TextView txtmamusic,txtsoluon;
        public top10Holer(@NonNull View itemView) {
            super(itemView);
             txtmamusic = itemView.findViewById(R.id.tvtop10ma);
             txtsoluon = itemView.findViewById(R.id.tvsoluontop10);
        }
    }
}
