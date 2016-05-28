package com.sandstorm.softspec.punkarnban.etc;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sandstorm.softspec.punkarnban.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Warata on 5/27/16 AD.
 */
public class DamagePool implements Runnable {

    private List<DamageText> damageTextList;


    public DamagePool() {
        this.damageTextList = new ArrayList<DamageText>();
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (DamageText text : damageTextList) {
                if (text.isActive()) {
                    text.move();
                }

            }
        }
    }

    public void addPool(Context context,RelativeLayout ll,int damage) {
        for(DamageText text : damageTextList) {
            if(!text.isActive()){
                text.active();
                text.setY(text.getStartY());
//                text.setVisibility(View.VISIBLE);
                Log.i("Test","active old text");
                return;
            }
        }

        DamageText text = new DamageText(context);
        text.setText(damage+"");
        text.setTextColor(Color.RED);
        text.setX((float) (ll.getWidth() * 0.75));
        text.setY((float) (ll.getHeight() * 0.25));
        ll.addView(text);
        text.setStartY((float)(ll.getHeight() * 0.25));
        damageTextList.add(text);
    }
}
