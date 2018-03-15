package com.hoangtrongminhduc.html5.dev.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private LineChart chart, chart2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        chart = (LineChart) findViewById(R.id.chart1);
        List<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0,34f));
        yValues.add(new Entry(1,36f));
        yValues.add(new Entry(2,33f));
        yValues.add(new Entry(3,38f));
        yValues.add(new Entry(4,38f));
        LineDataSet set = new LineDataSet(yValues, "Độ C");
        LineData lineData = new LineData(set);
        chart.setData(lineData);
        chart2 = (LineChart) findViewById(R.id.chart2);
        List<Entry> yValues2 = new ArrayList<>();
        yValues2.add(new Entry(0,87f));
        yValues2.add(new Entry(1,76f));
        yValues2.add(new Entry(2,90f));
        yValues2.add(new Entry(3,92f));
        yValues2.add(new Entry(4,86f));
        LineDataSet set2 = new LineDataSet(yValues2, "Phần trăm (%)");
        LineData lineData2 = new LineData(set2);
        chart2.setData(lineData2);
    }
}
