package com.hoangtrongminhduc.html5.dev.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.hoangtrongminhduc.html5.dev.myapplication.R.id.chart2;


public class Humi extends Fragment {
    private LineChart chart;

    public Humi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_humi, container, false);
        chart = (LineChart) view.findViewById(chart2);
        XAxis xAxis = chart.getXAxis();
//        xAxis.setValueFormatter(new );
        List<Entry> yValues2 = new ArrayList<>();
        yValues2.add(new Entry(0,87f));
        yValues2.add(new Entry(1,76f));
        yValues2.add(new Entry(2,90f));
        yValues2.add(new Entry(3,92f));
        yValues2.add(new Entry(4,86f));
        for(int i = 0; i < 200; i++){
            Random rn = new Random();

            yValues2.add(new Entry(i+4, 70+rn.nextInt(20)));
        }

        LineDataSet set2 = new LineDataSet(yValues2, "Phần trăm (%)");
        LineData lineData2 = new LineData(set2);
        chart.setData(lineData2);
        return view;
    }
//    public class MyXAxisValueFormatter implements IAxisValueFormatter{
//
//        private String[] mValues;
//        public MyXAxisValueFormatter(String[] values){
//            this.mValues = values;
//        }
//
//        @Override
//        public String getFormattedValue(float value, AxisBase axis) {
//            return mValues;
//        }
//    }


}
