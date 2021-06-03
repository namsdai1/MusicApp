package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapplication.adapter.adapterUser;
import com.example.myapplication.adapter.top10_adapter;
import com.example.myapplication.dao.Musicdao;
import com.example.myapplication.dao.Quantridao;
import com.example.myapplication.model.Music;
import com.example.myapplication.user.nguoidungactivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class biudo_activity extends AppCompatActivity implements OnChartValueSelectedListener {
    private PieChart mChart;
    private EditText ettop;
    private Button btnseach;
    RecyclerView recyclerView;
    top10_adapter adapter;
    ArrayList<Music> list=new ArrayList<>();
    private boolean kobk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biudo_activity);
        Toolbar toolbar=findViewById(R.id.toolbar_biudo);
        recyclerView=(RecyclerView) findViewById(R.id.rcv_top10) ;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ettop=findViewById(R.id.ed_top10);
        btnseach=findViewById(R.id.btntop10);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(biudo_activity.this);
        recyclerView.setLayoutManager(layoutManager);
        list=new ArrayList<>();


        getSupportActionBar().setTitle("Biểu đồ");
        mChart = (PieChart) findViewById(R.id.piechart);
        mChart.setRotationEnabled(true);
        mChart.setDescription(new Description());
        mChart.setHoleRadius(35f);
        mChart.setTransparentCircleAlpha(0);
        mChart.setCenterText("Music");
        mChart.setCenterTextSize(25);
        mChart.setDrawEntryLabels(true);
        addDataSet(mChart);
        mChart.setOnChartValueSelectedListener(this);

        btnseach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int month=Integer.parseInt(ettop.getText().toString());
                if(month<12){
                    Toast.makeText(biudo_activity.this, "Thang từ 1->12", Toast.LENGTH_SHORT).show();
                }else if(month>12){
                    list= Musicdao.getSachtop10(biudo_activity.this,String.valueOf(month));
                    adapter=new top10_adapter(biudo_activity.this,list);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
        Log.e("kobk", ""+kobk );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_nguoidung,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            Toast.makeText(this, "Them", Toast.LENGTH_SHORT).show();

            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(this, "Value: " + e.getY()
                + ", index: " + h.getX()
                + ", DataSet index: "
                + h.getDataSetIndex()
                , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

    private void addDataSet(PieChart pieChart) {
        ArrayList<Music> list=new ArrayList<>();
        list= Musicdao.readAll(biudo_activity.this);
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        float[] yData = {25, 40, 70};
        String[] xData = {"January", "February", "January"};

//        for (int i = 0; i < yData.length; i++) {
//            yEntrys.add(new PieEntry(yData[i], i));
//            Log.e("biudo", "addDataSet: "+yData[i] );
//        }
//        for (int i = 0; i < xData.length; i++) {
//            xEntrys.add(xData[i]);
//        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).getSoluong()!=0){
                yEntrys.add(new PieEntry(list.get(i).getSoluong(), list.get(i).getTitle()));
            }

        }
        for (int i=0;i<list.size();i++){
            xEntrys.add(list.get(i).getTitle());
        }

        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Employee Sales");

        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLACK);
        colors.add(Color.rgb(148,114,133));
        colors.add(Color.YELLOW);
        colors.add(Color.CYAN);
        colors.add(Color.rgb(68,66,133));

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

}

