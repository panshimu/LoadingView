package com.miaozi.loadingview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * created by panshimu
 * on 2019/8/26
 */
public class CircleView extends View {
    private Paint mPaint;
    private int mColor;
    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    private void initLayout() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getHeight()/2,getHeight()/2,getHeight()/2,mPaint);
    }
    public void setColor(int color){
        mPaint.setColor(color);
        mColor = color;
        invalidate();
    }
    public int getColor(){
        return mColor;
    }
}
