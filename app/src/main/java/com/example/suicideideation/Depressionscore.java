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

public class Depressionscore extends AppCompatActivity {

    AnyChartView anyChartView;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.depressionscore);

        Button s3 = findViewById(R.id.button);
        backButton =  findViewById(R.id.button2);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Chat.class);
                intent.putExtra("state",3);
                startActivity(intent);
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Therapistlist.class);

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