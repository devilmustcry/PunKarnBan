package com.sandstorm.softspec.punkarnban.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Canvas;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sandstorm.softspec.punkarnban.Adapters.PagerAdapter;
import com.sandstorm.softspec.punkarnban.R;
import com.sandstorm.softspec.punkarnban.etc.HealthBar;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {


    private ImageView tap;

    private TextView valText;

    private TabLayout upgradeTab;

    private ViewPager upgradeDetail;

    private PagerAdapter adapter;

    private HealthBar healthBar;



    int val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

    }

    private void initComponents() {

        tap = (ImageView) findViewById(R.id.tap);
        valText = (TextView) findViewById(R.id.work_health_text);

        tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (val % 2 == 0) {
                    tap.setImageResource(R.drawable.tap_tapping);

                } else
                    tap.setImageResource(R.drawable.tap_default);

                if (val >= healthBar.getMax()) {
                    healthBar.setProgress(0);
                    healthBar.setText("0/100");
                    val = 0;
                }
                healthBar.setProgress(++val);
//                valText.setText(val + "/100");
                healthBar.setText(val + "/100");

            }
        });

        upgradeTab = (TabLayout) findViewById(R.id.upgrade_layout);
        upgradeTab.addTab(upgradeTab.newTab().setText("Shop"));
        upgradeTab.addTab(upgradeTab.newTab().setText("Recruit"));
        upgradeTab.addTab(upgradeTab.newTab().setText("Skill"));
        upgradeTab.setTabGravity(TabLayout.GRAVITY_FILL);

        upgradeDetail = (ViewPager) findViewById(R.id.upgrade_detail);
        adapter = new PagerAdapter(getSupportFragmentManager(), upgradeTab.getTabCount() );
        upgradeDetail.setAdapter(adapter);
        upgradeDetail.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(upgradeTab));
        upgradeTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                upgradeDetail.setCurrentItem(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        healthBar = (HealthBar) findViewById(R.id.work_health_bar);
        healthBar.setMax(100);





    }

}
