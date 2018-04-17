package com.hoangtrongminhduc.html5.dev.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
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

public class MainActivity extends AppCompatActivity {

    private TextView tvUpdate, tvAcc, tvID, tvWater, tvLightTime, tvDayUp, tvTimeUpdate, tvTem, tvHumi, tvTimeUp, tvSttLight, tvSttEng, tvSttFan, tvDetail, tvProfile;
    private Switch swLight, swEng, swFan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        tvSttFan = (TextView)findViewById(R.id.tvSttFan);
        tvDetail = (TextView)findViewById(R.id.tvDetail);
        tvProfile = (TextView)findViewById(R.id.tvProfile);
        swLight = (Switch)findViewById(R.id.swLight);
        swEng = (Switch)findViewById(R.id.swEngine);
        swFan =(Switch)findViewById(R.id.swFan);
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
                if(isChecked){
                    tvSttLight.setText("Đang bật");
                    tvSttLight.setTextColor(Color.parseColor("#228B22"));
                }else {
                    tvSttLight.setText("Đang tắt");
                    tvSttLight.setTextColor(Color.parseColor("#ff537e"));
                }
            }
        });
        swEng.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tvSttEng.setText("Đang bật");
                    tvSttEng.setTextColor(Color.parseColor("#228B22"));
                }else {
                    tvSttEng.setText("Đang tắt");
                    tvSttEng.setTextColor(Color.parseColor("#ff537e"));
                }
            }
        });
        swFan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tvSttFan.setText("Đang bật");
                    tvSttFan.setTextColor(Color.parseColor("#228B22"));

                }else {
                    tvSttFan.setText("Đang tắt");
                    tvSttFan.setTextColor(Color.parseColor("#ff537e"));
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

        GetData("http://smartgardenbanana.tk/status_api.php?id=123");

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























