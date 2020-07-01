package com.aqiang.usermodel.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;

import com.aqiang.core.repository.Repository;
import com.aqiang.core.viewmodel.BaseViewModel;
import com.aqiang.net.BaseReposenEntity;
import com.aqiang.usermodel.entity.UserEntity;
import com.aqiang.usermodel.repository.UserRepository;

public class UserViewModel extends BaseViewModel {
    public UserEntity userEntity;

    public UserViewModel(LifecycleOwner owner) {
        super(owner);
        userEntity = new UserEntity();
        registerRepository(UserRepository.class.getSimpleName(),new UserRepository(owner));
    }

    public LiveData<BaseReposenEntity<UserEntity>> login(){
        UserRepository repository = getRepository(UserRepository.class.getSimpleName());
        return repository.login(userEntity);
    }

    public LiveData<BaseReposenEntity<String>> getCode(){
        UserRepository repository = getRepository(UserRepository.class.getSimpleName());
        return repository.getCode(userEntity);
    }

    public LiveData<BaseReposenEntity<UserEntity>> register(){
        UserRepository repository = getRepository(UserRepository.class.getSimpleName());
        return repository.register(userEntity);
    }

    public LiveData<BaseReposenEntity<UserEntity>> updataPwd(){
        UserRepository repository = getRepository(UserRepository.class.getSimpleName());
        return repository.updataPwd(userEntity);
    }
}
