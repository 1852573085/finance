package com.aqiang.usermodel.api;

import android.arch.lifecycle.LiveData;

import com.aqiang.net.BaseReposenEntity;
import com.aqiang.net.Config;
import com.aqiang.usermodel.entity.UserEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {
    @Headers(Config.NEW_URL_KEY +":"+Config.NEW_URL_VALUE)
    @POST("login")
    Call<UserEntity> changer();

    @POST("api/User/login")
    LiveData<BaseReposenEntity<UserEntity>> login(@Body UserEntity userEntity);

    @GET("api/User/getAuthCode?")
    LiveData<BaseReposenEntity<String>> getCode(@Query("phoneNumber") String phoneNumber);

    @POST("api/User/register")
    LiveData<BaseReposenEntity<UserEntity>> register(@Body UserEntity userEntity);

    @POST("api/User/modifyUser")
    LiveData<BaseReposenEntity<UserEntity>> updataPwd(@Body UserEntity userEntity);
}
