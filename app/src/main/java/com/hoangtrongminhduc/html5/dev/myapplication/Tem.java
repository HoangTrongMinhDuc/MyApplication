package com.hoangtrongminhduc.html5.dev.myapplication;

import android.app.Dialog;
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


public class Tem extends Fragment {
    private LineChart chart;
    private RadioButton rd1, rd2, rd3, rd4;
    private MainActivity mainActivity;
    String id = mainActivity.ID_DEVICE;
    String IP = mainActivity.IP;
    Dialog dialog;
    public Tem() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tem, container, false);
        getViewContent(view);
        setEvent();
        showDialog();
        setData(1);
        return view;
    }

    public void getViewContent(View view){
        rd1 = (RadioButton)view.findViewById(R.id.rdToday1);
        rd2 = (RadioButton)view.findViewById(R.id.rd7days1);
        rd3 = (RadioButton)view.findViewById(R.id.rdMonth1);
        rd4 = (RadioButton)view.findViewById(R.id.rdAll1);
        chart = (LineChart) view.findViewById(R.id.chart1);
        Description description = new Description();
        description.setText("Nhiệt độ");
        chart.setDescription(description);
    }

    void setEvent(){
        rd4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    showDialog();
                    setData(100);
                }
            }
        });

        rd3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    showDialog();
                    setData(30);
                }
            }
        });

        rd2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    showDialog();
                    setData(7);
                }
            }
        });

        rd1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    showDialog();
                    setData(1);
                }
            }
        });
    }
    void setData(int day){
        final String url = "http://"+IP+"/getdatabase.php?id="+id+"&type="+1+"&numday="+day;
        final List<Entry> yValues2 = new ArrayList<>();
        final RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        yValues2.add(new Entry(i, Float.parseFloat(jsonObject.getString("data"))));
                        LineDataSet set2 = new LineDataSet(yValues2, "Độ C");
                        LineData lineData2 = new LineData(set2);
                        chart.setData(lineData2);
                        if (dialog.isShowing()){
                            dialog.dismiss();
                        }
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
    public void showDialog() {
        dialog = new Dialog(getActivity());
        dialog.setTitle("Đang tải dữ liệu");
        dialog.setContentView(R.layout.loading);
        dialog.show();
    }
}
