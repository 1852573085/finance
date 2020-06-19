package com.aqiang.usermodel.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Looper;

import com.aqiang.core.repository.Repository;
import com.aqiang.usermodel.model.UserModel;

public class UserRepository extends Repository<UserModel> {
    @Override
    protected void createModel() {
        mModel = new UserModel();
    }

    public LiveData<Boolean> login(){
        return mModel.login();
    }
}
