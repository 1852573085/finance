package com.aqiang.usermodel.api;

import com.aqiang.net.BaseReposenEntity;
import com.aqiang.net.Config;
import com.aqiang.usermodel.entity.UserEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserApi {
    @Headers(Config.NEW_URL_KEY +":"+Config.NEW_URL_VALUE)
    @POST("login")
    Call<UserEntity> changer();

    @POST("api/User/login")
    Call<BaseReposenEntity<UserEntity>> login(@Body UserEntity userEntity);
}
