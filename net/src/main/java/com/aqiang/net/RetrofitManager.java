package com.aqiang.net;

import android.os.Build;

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
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
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
                // 获取request的创建者builder
                Request.Builder builder = request.newBuilder();
                // 从request中获取headers，通过给定的键url_name
                List<String> headerValues = request.headers("url_name");
                if (headerValues != null && headerValues.size() > 0)
                {
                    // 如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
                    builder.removeHeader("url_name");
                    // 匹配获得新的BaseUrl
                    String headerValue = headerValues.get(0);
                    HttpUrl newBaseUrl = null;
                    if ("weather".equals(headerValue))
                    {
                        newBaseUrl = HttpUrl.parse("");
                    }
                    else if ("book".equals(headerValue))
                    {
                        newBaseUrl = HttpUrl.parse("");
                    }
                    else
                    {
                        newBaseUrl = oldHttpUrl;
                    }
                    // 重建新的HttpUrl，修改需要修改的url部分
                    HttpUrl newFullUrl = oldHttpUrl
                            .newBuilder()
                            // 更换网络协议
                            .scheme(newBaseUrl.scheme())
                            // 更换主机名
                            .host(newBaseUrl.host())
                            // 更换端口
                            .port(newBaseUrl.port())
                            .build();
                    // 重建这个request，通过builder.url(newFullUrl).build()；
                    // 然后返回一个response至此结束修改
                    return chain.proceed(builder.url(newFullUrl).build());
                }
                return chain.proceed(request);
            }
        };
        return null;
    }

    private Interceptor createHeaderInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest = request.newBuilder()
                        .addHeader("v1", Build.BOARD)
                        .build();
                return chain.proceed(newRequest);
            }
        };
        return null;
    }

    private Interceptor createTokenInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                if(response.code() == 401){
                    TokenEntity tokenEntity = getToken();
                    if(tokenEntity != null){
                        Request newRequest = request.newBuilder()
                                .addHeader("Authorization", tokenEntity.getToken_type() + " " + tokenEntity.getAccess_token())
                                .build();
                        return chain.proceed(newRequest);
                    }else {
                        throw new NullPointerException("this is token null");
                    }
                }
                return null;
            }
        };
        return interceptor;
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
