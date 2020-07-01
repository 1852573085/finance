package com.aqiang.usermodel.repository;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Looper;

import com.aqiang.core.repository.Repository;
import com.aqiang.net.BaseReposenEntity;
import com.aqiang.usermodel.entity.UserEntity;
import com.aqiang.usermodel.model.UserModel;

public class UserRepository extends Repository<UserModel> {
    public UserRepository(LifecycleOwner owner) {
        super(owner);
    }

    @Override
    protected void createModel() {
        mModel = new UserModel();
    }

    public LiveData<BaseReposenEntity<UserEntity>> login(UserEntity userEntity){
        return mModel.login(userEntity);
    }

    public LiveData<BaseReposenEntity<String>> getCode(UserEntity userEntity){
        return mModel.getCode(userEntity);
    }

    public LiveData<BaseReposenEntity<UserEntity>> register(UserEntity userEntity){
        return mModel.register(userEntity);
    }

    public LiveData<BaseReposenEntity<UserEntity>> updataPwd(UserEntity userEntity){
        return mModel.updataPwd(userEntity);
    }
}
