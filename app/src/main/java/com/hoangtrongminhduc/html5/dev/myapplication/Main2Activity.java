package com.hoangtrongminhduc.html5.dev.myapplication;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TableLayout;

public class Main2Activity extends AppCompatActivity {
//    private LineChart chart, chart2;
    private TableLayout tabLayout;
    private HorizontalScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        chart = (LineChart) findViewById(R.id.chart1);
//        List<Entry> yValues = new ArrayList<>();
//        yValues.add(new Entry(0,34f));
//        yValues.add(new Entry(1,36f));
//        yValues.add(new Entry(2,33f));
//        yValues.add(new Entry(3,38f));
//        yValues.add(new Entry(4,38f));
//        LineDataSet set = new LineDataSet(yValues, "Độ C");
//        LineData lineData = new LineData(set);
//        chart.setData(lineData);
//        chart2 = (LineChart) findViewById(chart2);
//        List<Entry> yValues2 = new ArrayList<>();
//        yValues2.add(new Entry(0,87f));
//        yValues2.add(new Entry(1,76f));
//        yValues2.add(new Entry(2,90f));
//        yValues2.add(new Entry(3,92f));
//        yValues2.add(new Entry(4,86f));
//        LineDataSet set2 = new LineDataSet(yValues2, "Phần trăm (%)");
//        LineData lineData2 = new LineData(set2);
//        chart2.setData(lineData2);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Nhiệt độ"));
        tabLayout.addTab(tabLayout.newTab().setText("Độ ẩm"));
        tabLayout.addTab(tabLayout.newTab().setText("Không khí"));
        tabLayout.addTab(tabLayout.newTab().setText("Lượng nước"));
        tabLayout.addTab(tabLayout.newTab().setText("Chất nước"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
//        scrollView = (HorizontalScrollView) findViewById(R.id.scrv);

        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ImageView back = (ImageView)findViewById(R.id.imgBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
