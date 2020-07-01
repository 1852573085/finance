package com.aqiang.finalce.model;

import android.arch.lifecycle.LiveData;

import com.aqiang.core.model.IModel;
import com.aqiang.finalce.api.FinalceApi;
import com.aqiang.finalce.entity.FinalceEntity;
import com.aqiang.net.BaseReposenEntity;
import com.aqiang.net.RetrofitManager;

import java.util.List;

import retrofit2.http.Query;

public class FinalceReomteModel implements IModel {
    public LiveData<BaseReposenEntity<List<FinalceEntity>>> getFinalceByType(int type,int currentPage,int pageSize){
        FinalceApi finalceApi = RetrofitManager.getInstance().create(FinalceApi.class);
        return finalceApi.getFinalceByType(type, currentPage, pageSize);
    }
}
