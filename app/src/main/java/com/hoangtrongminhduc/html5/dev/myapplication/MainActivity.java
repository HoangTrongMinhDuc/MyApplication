package com.hoangtrongminhduc.html5.dev.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
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

    private TextView tvUpdate, tvAcc, tvID, tvWater, tvLightTime, tvDayUp, tvTimeUpdate, tvTem, tvHumi, tvTimeUp, tvSttLight, tvSttEng, tvDetail, tvProfile;
    private TextView tvControl, tvUpdateMode;
    private EditText edtWater;
    private Spinner spn1, spn2, spn3;
    private Switch swLight, swEng, swControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getView();
        setEvent();
//        GetData("http://192.168.56.1/status_api.php?id=123");
    }

    void getView(){
        tvUpdate = (TextView)findViewById(R.id.tvUpdate);
        tvAcc = (TextView)findViewById(R.id.tvOwner);
        tvID = (TextView)findViewById(R.id.tvID);
        tvWater = (TextView)findViewById(R.id.tvWater);
        tvLightTime = (TextView)findViewById(R.id.tvLightTime);
        tvDayUp = (TextView)findViewById(R.id.tvDayUp);
        tvTimeUpdate = (TextView)findViewById(R.id.tvTimeUp);
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
        tvUpdateMode = (TextView)findViewById(R.id.tvUpdateMode);
        swControl = (Switch)findViewById(R.id.swControl);
        edtWater = (EditText)findViewById(R.id.edtWater);
        spn1 = (Spinner)findViewById(R.id.timePicker1);
        spn2 = (Spinner)findViewById(R.id.timePicker2);
        spn3 = (Spinner)findViewById(R.id.timePicker3);
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
        setSpinner(spn1,1,12);
        setSpinner(spn2,0,59);
        List<String> list = new ArrayList<>();
        list.add("AM");
        list.add("PM");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn3.setAdapter(adapter);
        spn3.setEnabled(false);
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
                    swLight.setChecked(false);
                    Toast.makeText(MainActivity.this, "Bạn phải chuyển chế độ thủ công để bật", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(MainActivity.this, "Bạn phải chuyển chế độ thủ công để bật", Toast.LENGTH_SHORT).show();
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
                }else {
                    tvControl.setText("Thủ công");
                    tvControl.setTextColor(Color.parseColor("#ff537e"));
                    edtWater.setEnabled(true);
                    spn1.setEnabled(true);
                    spn2.setEnabled(true);
                    spn3.setEnabled(true);
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
                    Toast.makeText(MainActivity.this, "Cập nhật chế độ", Toast.LENGTH_SHORT).show();
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

//    private void SetSW(int idSW){
//        String url = "";
//        final RequestQueue requestQueue = Volley.newRequestQueue(this);
//        if(idSW == 1){
//            url = "";
//        }
//        if(idSW == 2){
//            url = "";
//        }
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>(){
//
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    private  void GetData(String url){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Toast.makeText(MainActivity.this,response.toString(),Toast.LENGTH_LONG).show();
                for(int i = 0; i < response.length(); i++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        tvID.setText(jsonObject.getString("id"));
//                        tvUpdate.setText(jsonObject.getString("ngaygiocapnhat"));
                        tvHumi.setText(jsonObject.getString("humidity"));
                        String acc = jsonObject.getString("owner");
                        if(acc.length() > 20)
                            acc = acc.substring(0,16)+"...";
                        tvAcc.setText(acc);
                        tvWater.setText(jsonObject.getString("waterLevel"));
                        if(jsonObject.getString("lightOn").equals("1"))
                            swLight.setChecked(true);
                        if(jsonObject.getString("engineOn").equals("1"))
                            swEng.setChecked(true);
                        tvTem.setText(jsonObject.getString("temperature"));
                        tvDayUp.setText(jsonObject.getString("numDay"));



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
}























