package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.dao.Hoadonchitietdao;
import com.example.myapplication.model.Hoadonchitiet;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;

import static com.github.mikephil.charting.utils.ColorTemplate.MATERIAL_COLORS;

public class Thongke_activity extends AppCompatActivity {
TextView txtdate,txtyear,txtmont;
private BarChart mchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke_activity);
        Toolbar toolbar=findViewById(R.id.toolbar_thongke);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Thống kê");
        txtdate=findViewById(R.id.tvtkdate);
        txtyear=findViewById(R.id.tvtkyear);
        txtmont=findViewById(R.id.tvtkmonth);
        txtdate.setText("Theo ngày : " + Hoadonchitietdao.getDoanhThuTheoNgay(Thongke_activity.this));
        txtyear.setText("Theo năm :"+ Hoadonchitietdao.getDoanhThuTheoNam(Thongke_activity.this));
        txtmont.setText("Theo tháng :" +Hoadonchitietdao.getDoanhThuTheoThang(Thongke_activity.this));
        mchart=findViewById(R.id.combinedChartthongke);
        ArrayList<BarEntry> visitors=new ArrayList<>();
         Calendar calendar = Calendar.getInstance();
        int nhay = calendar.get(Calendar.DAY_OF_MONTH);
        Log.e("nam", "onCreate: "+nhay );
        visitors.add(new BarEntry(nhay, (float) Hoadonchitietdao.getDoanhThuTheoNgay(Thongke_activity.this)));
        visitors.add(new BarEntry(nhay-1,(float) Hoadonchitietdao.getDoanhThuTheoNgay1(Thongke_activity.this)));
        visitors.add(new BarEntry(nhay-2,(float) Hoadonchitietdao.getDoanhThuTheoNgay2(Thongke_activity.this)));
        visitors.add(new BarEntry(nhay-3,(float) Hoadonchitietdao.getDoanhThuTheoNgay3(Thongke_activity.this)));
        visitors.add(new BarEntry(nhay-4,(float) Hoadonchitietdao.getDoanhThuTheoNgay4(Thongke_activity.this)));
        visitors.add(new BarEntry(nhay-5,(float) Hoadonchitietdao.getDoanhThuTheoNgay5(Thongke_activity.this)));
        BarDataSet barDataSet=new BarDataSet(visitors, "Doanh thu theo ngày");

        int[] colors = {Color.rgb(153, 193, 12), Color.rgb(179, 130, 76),Color.GRAY,Color.BLUE,Color.GREEN,Color.CYAN,Color.rgb(148,114,133),Color.RED};

        barDataSet.setColors(colors);

        barDataSet.setValueTextColor(Color.BLUE);
        barDataSet.setValueTextSize(16f);
        BarData barData=new BarData(barDataSet);
        mchart.setFitBars(true);
        mchart.setData(barData);
        mchart.getDescription().setText("Bar Chart Example");
        mchart.animateY(2000);




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            Toast.makeText(this, "Them", Toast.LENGTH_SHORT).show();

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}