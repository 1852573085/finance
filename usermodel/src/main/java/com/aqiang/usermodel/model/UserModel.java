package com.aqiang.usermodel.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Looper;

import com.aqiang.core.model.IModel;
import com.aqiang.net.BaseReposenEntity;
import com.aqiang.net.RetrofitManager;
import com.aqiang.net.rx.BaseObservable;
import com.aqiang.net.rx.BaseObserver;
import com.aqiang.usermodel.api.UserApi;
import com.aqiang.usermodel.entity.UserEntity;

import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserModel implements IModel {
    public LiveData<BaseReposenEntity<UserEntity>> login(final UserEntity userEntity){
        UserApi userApi = RetrofitManager.getInstance().create(UserApi.class);
        LiveData<BaseReposenEntity<UserEntity>> data = userApi.login(userEntity);
//        Observable<BaseReposenEntity<UserEntity>> observable = Observable.create(new ObservableOnSubscribe<BaseReposenEntity<UserEntity>>() {
//            @Override
//            public void subscribe(ObservableEmitter<BaseReposenEntity<UserEntity>> emitter) throws Exception {
//
//                emitter.onNext(data.getValue());
//            }
//        });

        //BaseObservable.doObserver(new BaseObserver<BaseReposenEntity<UserEntity>>(),observable);
//
        return data;
    }
}
