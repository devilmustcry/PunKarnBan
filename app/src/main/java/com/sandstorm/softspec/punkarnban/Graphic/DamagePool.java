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
            for (final DamageText text : damageTextList) {
                if (text.isActive()) {
//                    text.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            text.move();
//                        }
//                    });
                    text.move();
                }

            }
        }
    }

    public void addPool(Context context,RelativeLayout ll,int damage) {
        for(DamageText text : damageTextList) {
            if(!text.isActive()){
                text.active();
                text.setText("+"+damage+" word");
                text.setY(text.getStartY());
//                text.setVisibility(View.VISIBLE);
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
