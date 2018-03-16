package com.hoangtrongminhduc.html5.dev.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;


public class Tem extends Fragment {
    private LineChart chart;

    public Tem() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tem, container, false);
        chart = (LineChart) view.findViewById(R.id.chart1);
        List<Entry> yValues = new ArrayList<>();
        yValues.add(new Entry(0,34f));
        yValues.add(new Entry(1,36f));
        yValues.add(new Entry(2,33f));
        yValues.add(new Entry(3,38f));
        yValues.add(new Entry(4,38f));
        LineDataSet set = new LineDataSet(yValues, "Độ C");
        LineData lineData = new LineData(set);
        chart.setData(lineData);
        return view;

    }


}