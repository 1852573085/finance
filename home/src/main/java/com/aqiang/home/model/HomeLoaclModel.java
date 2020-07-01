package com.aqiang.home.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.aqiang.core.model.IModel;
import com.aqiang.home.entity.BannerEntity;
import com.aqiang.home.entity.ProductEntity;
import com.aqiang.home.entity.SysMsgEntity;
import com.aqiang.home.room.HomeDBHelper;
import com.aqiang.net.BaseReposenEntity;

import java.util.List;

public class HomeLoaclModel implements IModel {
    public List<BannerEntity> getBanner(){
        //return homeApi.getBanner();
        List<BannerEntity> data = HomeDBHelper.getInstance().getDb().homeDao().getBannerAll();
        return data;
    }
//
    public List<SysMsgEntity> getSystemMsg(){
        //return homeApi.getSystemMsg();
        return HomeDBHelper.getInstance().getDb().homeDao().getSysMsgAll();
    }
//
    public List<ProductEntity> getNewProduct(){
        //return homeApi.getNewUserProduct();
        return HomeDBHelper.getInstance().getDb().homeDao().getNewProduct();
    }

    public List<ProductEntity> getProduct(){
       // return homeApi.getProduct();
        return HomeDBHelper.getInstance().getDb().homeDao().getProductAll();
    }
}
