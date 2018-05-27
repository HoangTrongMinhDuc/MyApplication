package com.hoangtrongminhduc.html5.dev.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Air extends Fragment {
    private LineChart chart;
    private RadioButton rd1, rd2, rd3, rd4;
    private MainActivity mainActivity;
    String id = mainActivity.ID_DEVICE;
    String IP = mainActivity.IP;
    public Air() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_air, container, false);
        getViewContent(view);
        setEvent();
        setData(1);
        return view;
    }

    public void getViewContent(View view){
        rd1 = (RadioButton)view.findViewById(R.id.rdToday3);
        rd2 = (RadioButton)view.findViewById(R.id.rd7days3);
        rd3 = (RadioButton)view.findViewById(R.id.rdMonth3);
        rd4 = (RadioButton)view.findViewById(R.id.rdAll3);
        chart = (LineChart) view.findViewById(R.id.chart3);
        Description description = new Description();
        description.setText("Không khí");
        chart.setDescription(description);
    }

    void setEvent(){
        rd4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    setData(100);
                }
            }
        });

        rd3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    setData(30);
                }
            }
        });

        rd2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    setData(7);
                }
            }
        });

        rd1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    setData(1);
                }
            }
        });
    }
    void setData(int day){
        final String url = "http://"+IP+"/getdatabase.php?id="+id+"&type="+3+"&numday="+day;
        final List<Entry> yValues2 = new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        yValues2.add(new Entry(i, Float.parseFloat(jsonObject.getString("data"))));
                        LineDataSet set2 = new LineDataSet(yValues2, "Phần triệu (ppm)");
                        LineData lineData2 = new LineData(set2);
                        chart.setData(lineData2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
