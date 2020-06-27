package com.aqiang.common.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aqiang.common.R;

public class TitleBar extends RelativeLayout {

    private TextView left;
    private TextView title;
    private TextView right;
    private OnListenClick listenClick;

    public void setListenClick(OnListenClick listenClick) {
        this.listenClick = listenClick;
    }

    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.view_title_bar, null);
        left = view.findViewById(R.id.view_titlebar_left);
        title = view.findViewById(R.id.view_titlebar_title);
        right = view.findViewById(R.id.view_titlebar_right);
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listenClick.onLeftClick();
            }
        });
        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listenClick.onRightClick();
            }
        });
        addView(view);
        initData(context,attrs);
    }

    private void initData(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        String leftText = typedArray.getString(R.styleable.TitleBar_leftText);
        int leftIcon = typedArray.getResourceId(R.styleable.TitleBar_leftIcon, 0);
        String title = typedArray.getString(R.styleable.TitleBar_title);
        String rightText = typedArray.getString(R.styleable.TitleBar_rightText);
        int rightIcon = typedArray.getResourceId(R.styleable.TitleBar_rightIcon, 0);
        if(!TextUtils.isEmpty(leftText)){
            left.setText(leftText);
        }
        if(leftIcon != 0){
            Drawable drawable = ContextCompat.getDrawable(context, leftIcon);
            drawable.setBounds(0,0,50,50);
            left.setCompoundDrawables(drawable,null,null,null);
        }
        if(!TextUtils.isEmpty(title)){
            this.title.setText(title);
        }
        if(!TextUtils.isEmpty(rightText)){
            right.setText(rightText);
        }
        if(rightIcon != 0){
            Drawable drawable = ContextCompat.getDrawable(context, rightIcon);
            drawable.setBounds(0,0,50,50);
            right.setCompoundDrawables(drawable,null,null,null);
        }
        typedArray.recycle();
    }

    public interface OnListenClick{
        void onLeftClick();
        void onRightClick();
    }

    public void setTilte(String tilte){
        title.setText(tilte);
    }

    public void setLeftIcon(Context context,int icon){
        if(icon == 0){
            left.setCompoundDrawables(null,null,null,null);
            return;
        }
        Drawable drawable = ContextCompat.getDrawable(context, icon);
        drawable.setBounds(0,0,50,50);
        left.setCompoundDrawables(drawable,null,null,null);
    }

    public void setRightIcon(Context context,int icon){
        if(icon == 0){
            right.setCompoundDrawables(null,null,null,null);
            return;
        }
        Drawable drawable = ContextCompat.getDrawable(context, icon);
        drawable.setBounds(0,0,50,50);
        right.setCompoundDrawables(drawable,null,null,null);
    }
}
