package com.aqiang.home.api;

import android.arch.lifecycle.LiveData;

import com.aqiang.home.entity.BannerEntity;
import com.aqiang.home.entity.ProductEntity;
import com.aqiang.home.entity.SysMsgEntity;
import com.aqiang.net.BaseReposenEntity;

import java.util.List;

import retrofit2.http.GET;

public interface HomeApi {
    @GET("api/Img/getBannerImg")
    LiveData<BaseReposenEntity<List<BannerEntity>>> getBanner();

    /**
     * 获取系统消息
     * @return
     */
    @GET("api/SystemMsg/getSystemMsg")
    LiveData<BaseReposenEntity<List<SysMsgEntity>>> getSystemMsg();

    /**
     * 获取新用户的推荐产品
     * @return
     */
    @GET("api/Product/getNewUserProcducts")
    LiveData<BaseReposenEntity<List<ProductEntity>>> getNewUserProduct();

    /**
     * 获取推荐核心产品数据
     * @return
     */
    @GET("api/Product/getProcducts")
    LiveData<BaseReposenEntity<List<ProductEntity>>> getProduct();
}
