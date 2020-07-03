package com.aqiang.finalce.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aqiang.common.router.RouterPath;
import com.aqiang.finalce.R;
@Route(path = RouterPath.FINALCE)
public class BuyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
    }
}
