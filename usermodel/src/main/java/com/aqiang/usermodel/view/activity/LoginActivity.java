package com.aqiang.usermodel.view.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aqiang.core.view.BaseActivity;
import com.aqiang.core.viewmodel.BaseViewModel;
import com.aqiang.net.BaseReposenEntity;
import com.aqiang.net.RetrofitManager;
import com.aqiang.usermodel.R;
import com.aqiang.usermodel.api.UserApi;
import com.aqiang.usermodel.databinding.ActivityLoginBinding;
import com.aqiang.usermodel.entity.UserEntity;
import com.aqiang.usermodel.viewmodel.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        LiveData<BaseReposenEntity<UserEntity>> login = vm.login();
        login.observe(this, new Observer<BaseReposenEntity<UserEntity>>() {
            @Override
            public void onChanged(@Nullable BaseReposenEntity<UserEntity> userEntityBaseReposenEntity) {
                if(userEntityBaseReposenEntity.getCode() == 200){
                    showToast("成功");
                }else {
                    showToast("失败");
                }
            }
        });
    }

    public void changerUrl(View view){
        UserApi userApi = RetrofitManager.getInstance().create(UserApi.class);
        Call<UserEntity> changer = userApi.changer();
        changer.enqueue(new Callback<UserEntity>() {
            @Override
            public void onResponse(Call<UserEntity> call, Response<UserEntity> response) {

            }

            @Override
            public void onFailure(Call<UserEntity> call, Throwable t) {

            }
        });
    }
}
