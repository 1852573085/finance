package com.aqiang.usermodel.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aqiang.core.view.BaseActivity;
import com.aqiang.core.viewmodel.BaseViewModel;
import com.aqiang.usermodel.R;
import com.aqiang.usermodel.databinding.ActivityMainBinding;
import com.aqiang.usermodel.viewmodel.UserViewModel;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class MainActivity extends BaseActivity<ActivityMainBinding, UserViewModel> {

    @Override
    protected int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected UserViewModel createVM() {
        return new UserViewModel(this);
    }

    @Override
    protected void setBinding() {
        binding.setActivity(this);
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
    }

    public void onRegisterClick(View view){
        startActivity(new Intent(this,RegisterActivity.class));
    }

    public void onQQClick(View view){
        Platform plat = ShareSDK.getPlatform(QQ.NAME);
//移除授权状态和本地缓存，下次授权会重新授权
        plat.removeAccount(true);
//SSO授权，传false默认是客户端授权
        plat.SSOSetting(false);
//授权回调监听，监听oncomplete，onerror，oncancel三种状态
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
//抖音登录适配安卓9.0
//ShareSDK.setActivity(MainActivity.this);
//要数据不要功能，主要体现在不会重复出现授权界面
        plat.showUser(null);
    }
}
