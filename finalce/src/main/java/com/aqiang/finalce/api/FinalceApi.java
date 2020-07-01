package com.aqiang.finalce.api;

import android.arch.lifecycle.LiveData;

import com.aqiang.finalce.entity.FinalceEntity;
import com.aqiang.net.BaseReposenEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FinalceApi {
    @GET("api/Product/getProcductsForType")
    LiveData<BaseReposenEntity<List<FinalceEntity>>> getFinalceByType(@Query("type") int type, @Query("currentPage")int currentPage, @Query("pageSize") int pageSize);
}
