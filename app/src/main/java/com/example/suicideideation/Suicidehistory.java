package com.example.suicideideation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.SingleValueDataSet;
import com.anychart.charts.LinearGauge;
import com.anychart.enums.Anchor;
import com.anychart.enums.Layout;
import com.anychart.enums.MarkerType;
import com.anychart.enums.Position;
import com.anychart.scales.OrdinalColor;


public class Suicidehistory extends AppCompatActivity {

    AnyChartView anyChartView0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suicidehistory);

        anyChartView0 = findViewById(R.id.any_chart_view0);
        anyChartView0.setProgressBar(findViewById(R.id.progress_bar));

        LinearGauge linearGauge = AnyChart.linear();
        linearGauge.data(new SingleValueDataSet(new Double[]{7.5D}));

        linearGauge.layout(Layout.HORIZONTAL);


        linearGauge.label(1)
                .position(Position.LEFT_CENTER)
                .anchor(Anchor.LEFT_CENTER)
                .offsetY("40px")
                .offsetX("50px")
                .fontColor("#777777")
                .fontSize(17);
        linearGauge.label(1).text("Not At Risk");

        linearGauge.label(2)
                .position(Position.RIGHT_CENTER)
                .anchor(Anchor.RIGHT_CENTER)
                .offsetY("40px")
                .offsetX("50px")
                .fontColor("#777777")
                .fontSize(17);
        linearGauge.label(2).text("At Risk");

        OrdinalColor scaleBarColorScale = OrdinalColor.instantiate();
        scaleBarColorScale.ranges(new String[]{
                "{ from: 0, to: 5, color: ['yellow 0.68'] }",
                "{ from: 5, to: 10, color: ['red 0.68'] }"
        });

        linearGauge.scaleBar(0)
                .width("10%")
                .colorScale(scaleBarColorScale);

        linearGauge.marker(0)
                .type(MarkerType.TRIANGLE_DOWN)
                .color("yellow")

                .zIndex(50);

        linearGauge.scale()
                .minimum(0)
                .maximum(10);
//        linearGauge.scale().ticks

        linearGauge.padding(0, 40, 0, 40);

        anyChartView0.setChart(linearGauge);
    }

}