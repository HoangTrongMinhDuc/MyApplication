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

public class MainActivity extends AppCompatActivity {

    private TextView tvUpdate, tvAcc, tvID, tvWater, tvLightTime, tvDayUp, tvTimeUpdate, tvTem, tvHumi, tvTimeUp, tvSttLight, tvSttEng, tvSttFan, tvDetail;
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
        swLight = (Switch)findViewById(R.id.swLight);
        swEng = (Switch)findViewById(R.id.swEngine);
        swFan =(Switch)findViewById(R.id.swFan);
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
    }
}
