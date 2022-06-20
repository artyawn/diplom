package com.artyawn.arty.StatsAdmin;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.artyawn.arty.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Stats extends AppCompatActivity {
    private PieChart pieChart;
    private DatabaseReference myref;
    private String mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);

        pieChart = findViewById(R.id.pie_chart1);
        setupPieChart();
        loadPieChartData();



    }

    private void setupPieChart(){
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Задачи в группах");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData(){

        ArrayList<PieEntry> entries = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance().getUid();
        myref= FirebaseDatabase.getInstance().getReference("users").child(mAuth).child("groups").child("title_group");
        entries.add(new PieEntry(0.2f, "hyi"));
        entries.add(new PieEntry(0.15f, "hyi"));
        entries.add(new PieEntry(0.10f, "hyi"));
        entries.add(new PieEntry(0.25f, "hyi"));
        entries.add(new PieEntry(0.3f, "hyi"));


//        myref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
//                    GroupClass post = postSnapshot.getValue(GroupClass.class);
//                    entries.add(new PieEntry(0.25f,post.getTitle()));
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(PieChartActivity.this, "Error",Toast.LENGTH_SHORT).show();
////                Log.e("The read failed: " , error.getMessage());
//            }
//        });


        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }

        for(int color: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "groups");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);







    }
}

