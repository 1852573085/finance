package com.aqiang.usermodel.view.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.aqiang.common.router.RouterManager;
import com.aqiang.common.router.RouterPath;
import com.aqiang.core.view.BaseActivity;
import com.aqiang.core.viewmodel.BaseViewModel;
import com.aqiang.net.BaseReposenEntity;
import com.aqiang.net.RetrofitManager;
import com.aqiang.storage.sp.impl.StorageManager;
import com.aqiang.usermodel.R;
import com.aqiang.usermodel.api.UserApi;
import com.aqiang.usermodel.databinding.ActivityLoginBinding;
import com.aqiang.usermodel.entity.UserEntity;
import com.aqiang.usermodel.viewmodel.UserViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, UserViewModel> {
    private CheckBox checkBox;
    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected UserViewModel createVM() {
        return new UserViewModel(this);
    }

    @Override
    protected void setBinding() {
        binding.setVm(vm);
        binding.setLogin(this);
        checkBox = findViewById(R.id.box_act_login);
        if((Boolean) StorageManager.getInstance().get("cope",false)){
            checkBox.setChecked(true);
        }
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
                    RouterManager.getInstance().router(RouterPath.MAIN_ACTIVITY);
                }else {
                    showToast("失败");
                }
            }
        });
    }


    public void onCopeClick(View view){
        StorageManager.getInstance().save("cope",true);
    }

    public void onUpDataClick(View view){
        startActivity(new Intent(this,UpdataPwdActivity.class));
    }
}
