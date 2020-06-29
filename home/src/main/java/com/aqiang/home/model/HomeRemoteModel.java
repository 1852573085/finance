package com.aqiang.home.model;

import android.arch.lifecycle.LiveData;

import com.aqiang.core.model.IModel;
import com.aqiang.home.api.HomeApi;
import com.aqiang.home.entity.BannerEntity;
import com.aqiang.home.entity.ProductEntity;
import com.aqiang.home.entity.SysMsgEntity;
import com.aqiang.net.BaseReposenEntity;
import com.aqiang.net.RetrofitManager;

import java.util.List;

import retrofit2.http.GET;

public class HomeRemoteModel implements IModel {
    private HomeApi homeApi;

    public HomeRemoteModel() {
        homeApi = RetrofitManager.getInstance().create(HomeApi.class);
    }

    public LiveData<BaseReposenEntity<List<BannerEntity>>> getBanner(){
        return homeApi.getBanner();
    }

    public LiveData<BaseReposenEntity<List<SysMsgEntity>>> getSystemMsg(){
        return homeApi.getSystemMsg();
    }

    public LiveData<BaseReposenEntity<List<ProductEntity>>> getNewUserProduct(){
        return homeApi.getNewUserProduct();
    }

    public LiveData<BaseReposenEntity<List<ProductEntity>>> getProduct(){
        return homeApi.getProduct();
    }
}
