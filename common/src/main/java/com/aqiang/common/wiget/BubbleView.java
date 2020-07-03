package com.aqiang.common.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.aqiang.common.R;

public class BubbleView extends View {

    private String bubbleText;
    private Paint roundPaint;
    private Paint textPaint;
    public BubbleView(Context context) {
        super(context);
    }

    public BubbleView(Context context,AttributeSet attrs) {
        super(context, attrs);
        initAttr(context,attrs);
        initPaint();
    }

    private void initPaint() {
        roundPaint = new Paint();
        roundPaint.setColor(Color.RED);

        textPaint = new Paint();
        textPaint.setTextSize(15);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(30,30);
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(30,heightSize);
        }else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,30);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,15,roundPaint);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float d = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        if(bubbleText != null){
            canvas.drawText(bubbleText,getMeasuredWidth()/2,getMeasuredHeight()/2+d,textPaint);
        }
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BubbleView);
        bubbleText = typedArray.getString(R.styleable.BubbleView_bubbleText);
        typedArray.recycle();
    }

}
