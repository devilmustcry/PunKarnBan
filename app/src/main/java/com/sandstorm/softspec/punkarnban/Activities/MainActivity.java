package com.sandstorm.softspec.punkarnban.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sandstorm.softspec.punkarnban.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends Activity {


    private ImageView tap;

    private TextView valText;

    int val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initComponents();

    }

    private void initComponents() {

        tap = (ImageView) findViewById(R.id.tap);
        valText = (TextView) findViewById(R.id.test_text);

        tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(val%2==0){
                    tap.setImageResource(R.drawable.tap_tapping);

                }
                else
                    tap.setImageResource(R.drawable.tap_default);

                valText.setText(val++ + "");
            }
        });

    }

}
