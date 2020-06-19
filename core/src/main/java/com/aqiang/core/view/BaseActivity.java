package com.aqiang.core.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.aqiang.common.utlis.ShowToast;
import com.aqiang.core.viewmodel.BaseViewModel;

public abstract class BaseActivity<Binding extends ViewDataBinding,VM extends BaseViewModel> extends AppCompatActivity {
    protected Binding binding;
    protected VM vm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,bindLayout());
        vm = createVM();
        setBinding();
    }

    protected void showToast(String msg){
        ShowToast.INSTANCE.toast(this,msg);
    }

    protected abstract int bindLayout();

    protected abstract VM createVM();

    protected abstract void setBinding();
}
