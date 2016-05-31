package com.sandstorm.softspec.punkarnban.Graphic;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Warata on 5/27/16 AD.
 */
public class DamagePool extends Thread{

    private List<DamageText> damageTextList;

    public DamagePool(Context context,RelativeLayout ll) {
        this.damageTextList = new ArrayList<DamageText>();
        for(int i = 0 ; i < 10 ; i++) {
            DamageText text = new DamageText(context);
//            text.setText("+" + 0 + " word");
            text.setTextColor(Color.RED);
            text.setX((float) (ll.getWidth() * 0.55));
//            text.setY((float) (ll.getHeight() * 0.5));
            text.setActive(false);
            ll.addView(text);
            text.setStartY((float) (ll.getHeight() * 0.5));
            Log.e("Hello", String.valueOf(ll.getWidth()));
            damageTextList.add(text);
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (final DamageText text : damageTextList) {
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
                text.setText("+" + damage + " word");
                text.setY(text.getStartY());
                return;
            }
        }

        DamageText text = new DamageText(context);
        text.setText("+"+damage+" word");
        text.setTextColor(Color.RED);
        text.setX((float) (ll.getWidth() * 0.55));
        text.setY((float) (ll.getHeight() * 0.5));
        ll.addView(text);
        text.setStartY((float)(ll.getHeight() * 0.5));
        damageTextList.add(text);
    }

    private int getNumText() {
        return damageTextList.size();
    }
}
