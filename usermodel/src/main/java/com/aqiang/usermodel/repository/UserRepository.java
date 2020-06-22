package com.aqiang.usermodel.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Looper;

import com.aqiang.core.repository.Repository;
import com.aqiang.net.BaseReposenEntity;
import com.aqiang.usermodel.entity.UserEntity;
import com.aqiang.usermodel.model.UserModel;

public class UserRepository extends Repository<UserModel> {
    @Override
    protected void createModel() {
        mModel = new UserModel();
    }

    public LiveData<BaseReposenEntity<UserEntity>> login(UserEntity userEntity){
        return mModel.login(userEntity);
    }
}
