package com.sandstorm.softspec.punkarnban.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sandstorm.softspec.punkarnban.Adapters.PagerAdapter;
import com.sandstorm.softspec.punkarnban.Models.Game.Game;
import com.sandstorm.softspec.punkarnban.Models.Works.Homework;
import com.sandstorm.softspec.punkarnban.Models.Works.Work;
import com.sandstorm.softspec.punkarnban.R;
import com.sandstorm.softspec.punkarnban.etc.DamagePool;
import com.sandstorm.softspec.punkarnban.etc.HealthBar;

import org.w3c.dom.Text;

import java.util.Observable;
import java.util.Observer;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity implements Observer{

    Animation animMove;

    private ImageView tap;

    private TabLayout upgradeTab;

    private ViewPager upgradeDetail;

    private PagerAdapter adapter;

    private HealthBar healthBar;

    private Game game;


    int val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

    }

    private void initComponents() {

        game = new Game();
        game.addObserver(this);

//        final TextView wps_tap = (TextView)findViewById(R.id.wps_tap);
//        wps_tap.setText("10");

        tap = (ImageView) findViewById(R.id.tap);

        final DamagePool damagePool = new DamagePool();
        final RelativeLayout ll = (RelativeLayout)findViewById(R.id.relative);


        Thread t = new Thread(damagePool);

        t.start();
        tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage();
                game.tap();
                damagePool.addPool(getApplicationContext(),ll);


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
        Work work = game.initWork();
        healthBar.setNameText(work.getName());
        healthBar.setHealthText("0/" + work.getHp());
        healthBar.setMax(work.getHp());





    }

    private void changeImage() {

        if (val % 2 == 0) {
            tap.setImageResource(R.drawable.tap_tapping);
            val = 1;

        } else{
            tap.setImageResource(R.drawable.tap_default);
            val = 2;
        }

    }

    @Override
    public void update(Observable observable, Object data) {


        if(data == null) return;

        if(data.getClass() == Homework.class) {
           
            Work work = (Homework) data;
            healthBar.setProgress(0);
            healthBar.setHealthText("0/" + work.getHp());
            healthBar.setNameText(work.getName());
            healthBar.setMax(work.getHp());
        }
        if(data.getClass() == Integer.class) {

            int process = (int) data;
            healthBar.setProgress(process);
            healthBar.setHealthText(process + "/" + healthBar.getText().split("/")[1]);
        }



    }
}
