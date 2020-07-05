package com.aqiang.finalce.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aqiang.common.router.RouterPath;
import com.aqiang.finalce.R;

@Route(path = RouterPath.FINALCE)
public class BuyActivity extends AppCompatActivity {
    private RatingBar mRatingActBuy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        initView();
        changeRating();
    }

    private void initView() {
        mRatingActBuy = (RatingBar) findViewById(R.id.rating_act_buy);
    }

    private void changeRating() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.start_checked);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, bitmap.getHeight());
        layoutParams.gravity = Gravity.CENTER;
        mRatingActBuy.setLayoutParams(layoutParams);
    }
}
