package com.aqiang.common.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.aqiang.common.R;

public class FinalceProView extends View {
    private Paint storkPaint;
    /**
     * 文本的画笔
     */
    private Paint txtPaint;
    private int defaultColor= Color.YELLOW;
    private float defaultTextSize=20.0f;
    private float defaultStorkWidth=5.0f;
    private int txtColor;
    private float txtSize;
    private int strokColor;
    private float storkWidth;
    private int default_width=100;
    private int default_height=100;

    private float sweepAngle=360;
    public FinalceProView(Context context) {
        super(context);
    }

    public FinalceProView(Context context, AttributeSet attrs) {
        super(context, attrs);
        doCustomProp(context,attrs);
        initPaint();
    }

    private void initPaint() {
        storkPaint=new Paint();
        txtPaint=new Paint();

        storkPaint.setColor(strokColor==0?defaultColor:strokColor);
        storkPaint.setStyle(Paint.Style.STROKE);
        storkPaint.setStrokeWidth(storkWidth==0?defaultStorkWidth:storkWidth);
        storkPaint.setAntiAlias(true);
        storkPaint.setDither(true);

        txtPaint.setColor(txtColor==0?defaultColor:txtColor);
        txtPaint.setTextSize(txtSize==0?defaultTextSize:txtSize);
        txtPaint.setAntiAlias(true);
        txtPaint.setTextAlign(Paint.Align.CENTER);
        txtPaint.setDither(true);
    }

    private void doCustomProp(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FinalceProView);
        if (typedArray!=null){
            txtColor = typedArray.getColor(R.styleable.FinalceProView_textColor, defaultColor);
            txtSize = typedArray.getDimension(R.styleable.FinalceProView_textSize, defaultTextSize);
            strokColor = typedArray.getColor(R.styleable.FinalceProView_stork_color, defaultColor);
            storkWidth = typedArray.getDimension(R.styleable.FinalceProView_stork_width, defaultStorkWidth);
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);

        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(default_width,default_height);
        }else if (widthMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(default_width,heightSize);
        }else if (heightMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,default_height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float left = 0+getPaddingLeft();
        float top = 0+getPaddingTop();
        float right = this.getMeasuredWidth() - getPaddingRight();
        float bottom = this.getMeasuredHeight() - getPaddingBottom();
        RectF rectF=new RectF(left,top,right,bottom);
        canvas.drawArc(rectF,0,sweepAngle,false,storkPaint);
    }

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }
}
