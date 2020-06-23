package com.aqiang.net.adapterfactory;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Looper;

import com.aqiang.net.BaseReposenEntity;
import com.aqiang.net.R;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<BaseReposenEntity<R>>> {
    private Type type;

    public LiveDataCallAdapter(Type type) {
        this.type = type;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public LiveData<BaseReposenEntity<R>> adapt(Call<R> call) {
        final MutableLiveData<BaseReposenEntity<R>> liveData = new MutableLiveData<>();
        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if(Looper.getMainLooper().getThread() == Thread.currentThread()){
                    liveData.setValue((BaseReposenEntity<R>) response.body());
                }else {
                    liveData.postValue((BaseReposenEntity<R>) response.body());
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                if(Looper.getMainLooper().getThread() == Thread.currentThread()){
                    liveData.setValue(null);
                }else {
                    liveData.postValue(null);
                }
            }
        });
        return liveData;
    }
}
