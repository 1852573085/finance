package com.aqiang.net;

import android.os.Build;
import android.text.TextUtils;

import com.aqiang.net.adapterfactory.LiveDataCallAdapterFactory;
import com.aqiang.storage.sp.impl.StorageManager;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private Retrofit retrofit;
    private RetrofitManager(){
        retrofit = createRetrofit();
    }

    private static RetrofitManager retrofitManager;

    public static RetrofitManager getInstance(){
        if(retrofitManager == null){
            synchronized (RetrofitManager.class){
                if (retrofitManager == null){
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    private Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(createHttpClient())
                .baseUrl(Config.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    private OkHttpClient createHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Config.TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(createNetInterceptor())
                .addInterceptor(createTokenInterceptor())
                .addInterceptor(createHeaderInterceptor())
                .addInterceptor(createBaseUrlInterceptor())
                .build();
        return client;
    }

    private Interceptor createBaseUrlInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl oldHttpUrl = request.url();
                Request.Builder newBuilder = request.newBuilder();
                List<String> headers = request.headers(Config.NEW_URL_KEY);
                if(headers != null && headers.size() > 0){
                    newBuilder.removeHeader(Config.NEW_URL_KEY);
                    String s = headers.get(0);
                    HttpUrl newUrl = null;
                    if(s.equals(Config.NEW_URL_VALUE)){
                        newUrl = HttpUrl.parse(Config.NEW_URL);
                    }else {
                        newUrl = oldHttpUrl;
                    }
                    HttpUrl newFullUrl = oldHttpUrl.newBuilder()
                            .port(newUrl.port())
                            .scheme(newUrl.scheme())
                            .host(newUrl.host())
                            .build();
                    return chain.proceed(newBuilder.url(newFullUrl).build());
                }
                return chain.proceed(request);
            }
        };
        return interceptor;
    }

    private Interceptor createHeaderInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest = request.newBuilder()
                        .addHeader("v0", Build.MANUFACTURER)
                        .addHeader("v1",Build.TYPE)
                        .build();
                return chain.proceed(newRequest);
            }
        };
        return interceptor;
    }

    private Interceptor createTokenInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
//                String token = (String) StorageManager.getInstance().get("token","");
//                if(!TextUtils.isEmpty(token)){
//                    return getResponse(request,token,chain);
//                }
                Response response = chain.proceed(request);
                if(response.code() == 401){
                    TokenEntity tokenEntity = getToken();
                    if(tokenEntity != null){
                        StorageManager.getInstance().save("token",tokenEntity.getAccess_token());

                        return getResponse(request,tokenEntity.getAccess_token(),chain);
                    }else {
                        throw new NullPointerException("this is token null");
                    }
                }
                return response;
            }
        };
        return interceptor;
    }

    private Response getResponse(Request request, String token, Interceptor.Chain chain){
        Request newRequest = request.newBuilder()
                .addHeader("Authorization", "bearer " + token)
                .build();
        try {
            return chain.proceed(newRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private TokenEntity getToken() {
        TokenApi tokenApi = create(TokenApi.class);
        Call<TokenEntity> token = tokenApi.getToken("password", Config.AUTO_COED, "");
        try {
            retrofit2.Response<TokenEntity> response = token.execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Interceptor createNetInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
}
