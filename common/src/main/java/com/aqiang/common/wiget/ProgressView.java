package com.aqiang.common.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.aqiang.common.R;

public class ProgressView extends View {
    private Paint backPaint;
    private Paint proPaint;
    private Paint textPaint;
    private int backColor;
    private int proColor;
    private int textColor;
    private float progress;
    private int defaultWidth = 300;
    private int defaultHeight = 100;
    private float pro;
    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context,AttributeSet attrs) {
        super(context, attrs);
        doPro(context,attrs);
        initPaint();
    }

    private void doPro(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        if(typedArray != null){
            backColor = typedArray.getColor(R.styleable.ProgressView_backColor, Color.GRAY);
            proColor = typedArray.getColor(R.styleable.ProgressView_proColor, Color.RED);
            textColor = typedArray.getColor(R.styleable.ProgressView_proTextColor, Color.rgb(204, 153, 51));
            progress = typedArray.getFloat(R.styleable.ProgressView_ProProgress, 0);
            typedArray.recycle();
        }
    }

    private void initPaint() {
        backPaint = new Paint();
        backPaint.setColor(backColor);
        //backPaint.setStrokeWidth(20);

        proPaint = new Paint();
        //proPaint.setStrokeWidth(20);
        proPaint.setColor(proColor);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(25);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode==MeasureSpec.AT_MOST&&heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,defaultHeight);
        }else if (widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(defaultWidth,heightSize);
        }else if (heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,defaultHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.d("swq","12");
        RectF rectF=new RectF(0+getPaddingLeft(),
                0+50+getPaddingTop(),
                getMeasuredWidth()-getPaddingRight(),
                getMeasuredHeight()-getPaddingBottom());
        canvas.drawRect(rectF,backPaint);

        //前面矩形的右侧坐标
        pro=calculateRightLocation(rectF);

        RectF currentRectF=new RectF(0+getPaddingLeft(),0+50+getPaddingTop(),pro,getMeasuredHeight()-getPaddingBottom());
        canvas.drawRect(currentRectF,proPaint);

        canvas.drawText(progress+"%",pro,currentRectF.top-5F,textPaint);
    }

    private float calculateRightLocation(RectF rectF) {
        float left = rectF.left;
        float relWidth = (float) ((rectF.right - rectF.left) / 100.00 * progress);
        return left+relWidth;
    }
}
