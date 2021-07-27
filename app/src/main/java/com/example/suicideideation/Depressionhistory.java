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

public class Depressionhistory extends AppCompatActivity {

    AnyChartView anyChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depressionhistory);

        Button back2 = findViewById(R.id.button2);
        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), History.class);
                startActivity(intent);
            }
        });

        anyChartView = findViewById(R.id.any_chart_view);
        setupPieChart();

    }

    public void setupPieChart() {
        Cartesian pie = AnyChart.column();
        List<DataEntry> dataEntries = new ArrayList<>();

        dataEntries.add(new ValueDataEntry("Depression", 80));

        pie.data(dataEntries);
        anyChartView.setChart(pie);
    }

}