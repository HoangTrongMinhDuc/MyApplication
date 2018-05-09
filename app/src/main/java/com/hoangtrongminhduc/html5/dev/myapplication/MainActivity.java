package com.hoangtrongminhduc.html5.dev.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String ID_DEVICE = "100";
    private String DEF_WATER_LEVEL = "700";
    private String DEF_TIME_LIGHT_ON = "18:00:00";
    private String DEF_TIME_LIGHT_OFF = "05:00:00";
    private TextView tvUpdate, tvAcc, tvID, tvDayUp, tvTimeUpdate, tvTem, tvHumi, tvTimeUp, tvSttLight, tvSttEng, tvDetail, tvProfile;
    private TextView tvControl, tvWaterLevel, tvWaterQuality, tvAirQuality, tvUpdateMode;
    private EditText edtWater;
    private LinearLayout ln;
    private Spinner spn1, spn2, spn3, spn4;
    private Switch swLight, swEng, swControl;
    private int WATER_MAX = 0;
    private int controlMode = 0;
    private int light = 0;
    private int engine = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getView();
        setEvent();
        GetData("http://192.168.56.1/status_api.php?id="+ID_DEVICE);
    }

    void getView(){
        tvUpdate = (TextView)findViewById(R.id.tvUpdate);
        tvAcc = (TextView)findViewById(R.id.tvOwner);
        tvID = (TextView)findViewById(R.id.tvID);
        tvDayUp = (TextView)findViewById(R.id.tvDayUp);
        tvTimeUpdate = (TextView)findViewById(R.id.tvTimeUpdate);
        tvTem = (TextView)findViewById(R.id.tvTem);
        tvHumi = (TextView)findViewById(R.id.tvHumi);
        tvTimeUp = (TextView)findViewById(R.id.tvTimeUp);
        tvSttLight = (TextView)findViewById(R.id.tvSttLight);
        tvSttEng = (TextView)findViewById(R.id.tvSttEng);
        tvDetail = (TextView)findViewById(R.id.tvDetail);
        tvProfile = (TextView)findViewById(R.id.tvProfile);
        swLight = (Switch)findViewById(R.id.swLight);
        swEng = (Switch)findViewById(R.id.swEngine);
        tvControl = (TextView)findViewById(R.id.tvControl);
        tvWaterLevel = (TextView)findViewById(R.id.tvWaterLevel);
        tvWaterQuality = (TextView)findViewById(R.id.tvWaterQuality);
        tvAirQuality = (TextView)findViewById(R.id.tvAirQuality);
        tvUpdateMode = (TextView)findViewById(R.id.tvUpdateMode);
        swControl = (Switch)findViewById(R.id.swControl);
        ln = (LinearLayout)findViewById(R.id.lncontrol);
        edtWater = (EditText)findViewById(R.id.edtWater);
        spn1 = (Spinner)findViewById(R.id.timePicker1);
        spn2 = (Spinner)findViewById(R.id.timePicker2);
        spn3 = (Spinner)findViewById(R.id.timePicker3);
        spn4 = (Spinner)findViewById(R.id.timePicker4);
        setSpinnerTime();
    }


    void setSpinner(Spinner view, int start, int end){
        List<String> list = new ArrayList<>();
        for (int i = start; i <= end; i++){
            if(i < 10) list.add("0"+i);
            else list.add(i+"");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        view.setAdapter(adapter);
        view.setEnabled(false);
    }

    void setSpinnerTime(){
        setSpinner(spn1,0,23);
        setSpinner(spn2,0,59);
        setSpinner(spn3,0,23);
        setSpinner(spn4,0,59);
    }

    void setEvent(){
        tvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, login.class);
                startActivityForResult(intent2, 5000);
            }
        });

        swLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!swControl.isChecked()){
                    if(isChecked){
                        tvSttLight.setText("Đang bật");
                        tvSttLight.setTextColor(Color.parseColor("#228B22"));
                    }else {
                        tvSttLight.setText("Đang tắt");
                        tvSttLight.setTextColor(Color.parseColor("#ff537e"));
                    }
                }else {
                    if(light == 1){
                        swLight.setChecked(true);
                    }else {
                        swLight.setChecked(false);
                        Toast.makeText(MainActivity.this, "Bạn phải chuyển chế độ thủ công để bật đèn", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });
        swEng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!swControl.isChecked()){
                    if(isChecked){
                        swEng.setText("Đang bật");
                        swEng.setTextColor(Color.parseColor("#228B22"));
                    }else {
                        swEng.setText("Đang tắt");
                        swEng.setTextColor(Color.parseColor("#ff537e"));
                    }
                }else {

                    swEng.setChecked(false);
                    Toast.makeText(MainActivity.this, "Bạn phải chuyển chế độ thủ công để bật bơm", Toast.LENGTH_SHORT).show();
                }
            }
        });

        swControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tvControl.setText("Tự động");
                    tvControl.setTextColor(Color.parseColor("#228B22"));
                    edtWater.setEnabled(false);
                    spn1.setEnabled(false);
                    spn2.setEnabled(false);
                    spn3.setEnabled(false);
                    spn4.setEnabled(false);
                    edtWater.setText(DEF_WATER_LEVEL);
                    setTime(DEF_TIME_LIGHT_ON, DEF_TIME_LIGHT_OFF);
                }else {
                    tvControl.setText("Thủ công");
                    tvControl.setTextColor(Color.parseColor("#ff537e"));
                    edtWater.setEnabled(true);
                    spn1.setEnabled(true);
                    spn2.setEnabled(true);
                    spn3.setEnabled(true);
                    spn4.setEnabled(true);
                }
            }
        });
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Đang cập nhật...",Toast.LENGTH_LONG).show();
            }
        });
        tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        tvUpdateMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swControl.isChecked()){
                    Toast.makeText(MainActivity.this, "Không thực hiện dược thao tác này khi đang ở chế độ tự động", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Cập nhật chế độ ", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5000){
            String id = data.getStringExtra("ID");
            tvID.setText(id);
        }
    }

    private  void GetData(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            for(int i = 0; i < response.length(); i++){
                try {
                    JSONObject jsonObject = response.getJSONObject(i);
                    tvID.setText(jsonObject.getString("id"));
                    tvTimeUp.setText(jsonObject.getString("dayPlanted"));
                    tvTimeUpdate.setText(jsonObject.getString("timeUpdate"));
                    String acc = jsonObject.getString("owner");
                    if(acc.length() > 20)
                        acc = acc.substring(0,16)+"...";
                    tvAcc.setText(acc);
                    tvTem.setText(jsonObject.getString("temperature"));
                    tvHumi.setText(jsonObject.getString("humidity"));
                    tvAirQuality.setText(jsonObject.getString("airQuality"));
                    tvWaterLevel.setText(jsonObject.getString("waterLevel"));
                    tvWaterQuality.setText(jsonObject.getString("waterQuality"));
                    tvDayUp.setText(jsonObject.getString("numDay"));
                    edtWater.setText(jsonObject.getString("waterLevelOn"));
                    WATER_MAX = Integer.parseInt(jsonObject.getString("waterMax"));
                    if (jsonObject.getString("controlMode").equals("1")){
                        swControl.setChecked(true);
                        controlMode = 1;
                    }else {
                        swControl.setChecked(false);
                        controlMode = 0;
                    }
                    if(jsonObject.getString("lightOn").equals("1")){
                        swLight.setChecked(true);
                        light = 1;
                    }else {
                        swLight.setChecked(false);
                        light = 0;
                    }
                    if(jsonObject.getString("engineOn").equals("1")){
                        swEng.setChecked(true);
                        engine = 1;
                    }else {
                        swEng.setChecked(false);
                        engine = 0;
                    }
                    String timeOn = jsonObject.getString("timeLightOn");
                    String timeOff = jsonObject.getString("timeLightOff");
                    setTime(timeOn,timeOff);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            }
        },
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public void setTime(String timeStart, String timeEnd){
        String[] start = timeStart.split(":");
        String[] end = timeEnd.split(":");
        spn1.setSelection(Integer.parseInt(start[0]));
        spn2.setSelection(Integer.parseInt(start[1]));
        spn3.setSelection(Integer.parseInt(end[0]));
        spn4.setSelection(Integer.parseInt(end[1]));
    }

    public String getTimeStart(){
        return spn1.getSelectedItem() + ":" + spn2.getSelectedItem() + ":00";
    }

    public String getTimeEnd(){
        return spn3.getSelectedItem() + ":" + spn3.getSelectedItem() + ":00";
    }

    public void setElementStatus(int status, String element){
        String url = "http://192.168.56.1/set_api.php?" + element + "=" + status;
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, "", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Toast.makeText(MainActivity.this, response.getJSONObject(0).getString("reply"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

}























