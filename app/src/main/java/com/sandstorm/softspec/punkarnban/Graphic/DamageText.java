package com.sandstorm.softspec.punkarnban.Graphic;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Warata on 5/27/16 AD.
 */
public class DamageText extends TextView {

    private boolean isActive = true;
    private float startY = 0;

    public DamageText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DamageText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DamageText(Context context) {
        super(context);
    }

    public DamageText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public synchronized boolean isActive() {
        return isActive;
    }

    public synchronized void move() {
        setY(getY()-10);
        if(getY()<=0) {
            isActive = false;
            setY(getY()-50);
        }
    }

    public synchronized void active() {
        this.isActive = true;

    }

    public synchronized void setStartY(float startY) {
        this.startY = startY;
    }

    public synchronized float getStartY() {
        return startY;
    }
}
