package com.sandstorm.softspec.punkarnban.Graphic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Zen on 5/28/16.
 */
public class TapImage extends ImageView {

    private String time = "";
    private Paint textPaint;


    public TapImage(Context context) {
        super(context);
        textPaint = new TextPaint();
    }

    public TapImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        textPaint = new TextPaint();

    }

    public TapImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textPaint = new TextPaint();

    }

    public TapImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        textPaint = new TextPaint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect bound = new Rect();
        textPaint.getTextBounds(time, 0, time.length(), bound);
        textPaint.setColor(Color.BLACK);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setTextSize(50);
        int x = (int)(getWidth()/2.35  - bound.centerX());
        int y = (int)(getHeight()*0.5 - bound.centerY());
        canvas.drawText(time, x, y, textPaint);

    }

    public void setTime(String time) {
        this.time = time;

    }
}
