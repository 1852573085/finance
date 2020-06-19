package com.aqiang.usermodel.viewmodel;

import android.arch.lifecycle.LiveData;

import com.aqiang.core.repository.Repository;
import com.aqiang.core.viewmodel.BaseViewModel;
import com.aqiang.usermodel.entity.UserEntity;
import com.aqiang.usermodel.repository.UserRepository;

public class UserViewModel extends BaseViewModel {
    public UserEntity userEntity;

    public UserViewModel() {
        userEntity = new UserEntity("","");
        registerRepository(UserRepository.class.getSimpleName(),new UserRepository());
    }

    public LiveData<Boolean> login(){
        UserRepository repository = getRepository(UserRepository.class.getSimpleName());
        return repository.login();
    }
}
