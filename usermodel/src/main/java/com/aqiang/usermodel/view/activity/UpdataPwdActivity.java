package com.aqiang.usermodel.view.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.aqiang.common.wiget.TitleBar;
import com.aqiang.core.view.BaseActivity;
import com.aqiang.core.viewmodel.BaseViewModel;
import com.aqiang.net.BaseReposenEntity;
import com.aqiang.storage.sp.impl.StorageManager;
import com.aqiang.usermodel.R;
import com.aqiang.usermodel.databinding.ActivityUpdataPwdBinding;
import com.aqiang.usermodel.entity.UserEntity;
import com.aqiang.usermodel.viewmodel.UserViewModel;

public class UpdataPwdActivity extends BaseActivity<ActivityUpdataPwdBinding, UserViewModel> {
    private TitleBar titleBar;
    private final ObservableField<String> code = new ObservableField<>();
    private final ObservableField<String> rePwd = new ObservableField<>();
    @Override
    protected int bindLayout() {
        return R.layout.activity_updata_pwd;
    }

    @Override
    protected UserViewModel createVM() {
        return new UserViewModel(this);
    }

    @Override
    protected void setBinding() {
        binding.setVm(vm);
        binding.setActivity(this);
        binding.setCode(code);
        binding.setRePwd(rePwd);
        titleBar = findViewById(R.id.act_updata_cope);
        titleBar.setListenClick(new TitleBar.OnListenClick() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightClick() {
                if(TextUtils.isEmpty(binding.getVm().userEntity.getUsername())){
                    showToast("请输入用户名");
                    return;
                }
                if(!binding.getCode().get().equals(code.get())){
                    showToast("验证码不对");
                    return;
                }
                if(TextUtils.isEmpty(binding.getVm().userEntity.getPwd())){
                    showToast("请输入密码");
                    return;
                }
                if(!binding.getVm().userEntity.getPwd().equals(binding.getRePwd().get())){
                    showToast("请重新输入,两次密码不对");
                    return;
                }
                LiveData<BaseReposenEntity<UserEntity>> data = vm.updataPwd();
                data.observe(UpdataPwdActivity.this, new Observer<BaseReposenEntity<UserEntity>>() {
                    @Override
                    public void onChanged(@Nullable BaseReposenEntity<UserEntity> userEntityBaseReposenEntity) {
                        if(userEntityBaseReposenEntity.getCode() == 200){
                            StorageManager.getInstance().cloer("cope");
                            startActivity(new Intent(UpdataPwdActivity.this,MainActivity.class));
                        }
                    }
                });
            }
        });
    }

    public void onCodeClick(View view){
        LiveData<BaseReposenEntity<String>> data = vm.getCode();
        data.observe(this, new Observer<BaseReposenEntity<String>>() {
            @Override
            public void onChanged(@Nullable BaseReposenEntity<String> stringBaseReposenEntity) {
                if(stringBaseReposenEntity.getCode() == 200){
                    code.set(stringBaseReposenEntity.getData());
                }
            }
        });
    }


}
