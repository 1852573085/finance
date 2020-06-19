package com.aqiang.usermodel.view.activity;

import android.arch.lifecycle.LiveData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.aqiang.core.view.BaseActivity;
import com.aqiang.core.viewmodel.BaseViewModel;
import com.aqiang.usermodel.R;
import com.aqiang.usermodel.databinding.ActivityLoginBinding;
import com.aqiang.usermodel.viewmodel.UserViewModel;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, UserViewModel> {

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected UserViewModel createVM() {
        return new UserViewModel();
    }

    @Override
    protected void setBinding() {
        binding.setVm(vm);
        binding.setLogin(this);
    }

    public void loginOnClick(View view){
        if(vm.userEntity.getUsername().length() <= 0){
            showToast("请输入用户名");
            return;
        }
        if(vm.userEntity.getPwd().length() <= 0){
            showToast("请输入密码");
            return;
        }
        LiveData<Boolean> login = vm.login();
        if(login.getValue()){
            showToast("成功");
        }else {
            showToast("失败");
        }
    }
}
