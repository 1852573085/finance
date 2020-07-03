package com.aqiang.day0619_finance;

import com.aqiang.common.BaseApplication;
import com.aqiang.common.router.RouterManager;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        RouterManager.getInstance().init(this,true);
    }
}
