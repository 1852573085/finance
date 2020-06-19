package com.aqiang.usermodel.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Looper;

import com.aqiang.core.model.IModel;

public class UserModel implements IModel {
    public LiveData<Boolean> login(){
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();
        if(Looper.getMainLooper().getThread() == Thread.currentThread()){
            mutableLiveData.setValue(true);
        }else {
            mutableLiveData.postValue(true);
        }
        return mutableLiveData;
    }
}
