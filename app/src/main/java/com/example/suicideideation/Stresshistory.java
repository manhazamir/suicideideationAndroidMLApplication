package com.example.suicideideation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;

import java.util.ArrayList;
import java.util.List;

public class Stresshistory extends AppCompatActivity {

    AnyChartView anyChartView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stressshistory);
        Button back3 = findViewById(R.id.button2);
        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), History.class);
                startActivity(intent);
            }
        });

        anyChartView2 = findViewById(R.id.any_chart_view2);
        setupPieChart2();
    }

    public void setupPieChart2() {
        Cartesian pie2 = AnyChart.column();
        List<DataEntry> dataEntries2 = new ArrayList<>();

        dataEntries2.add(new ValueDataEntry("Stress", 30));

        pie2.data(dataEntries2);
        anyChartView2.setChart(pie2);

    }
}