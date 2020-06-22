package com.aqiang.net.rx;


import android.arch.lifecycle.AndroidViewModel;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BaseObservable {
    public static <T> void doObserver(BaseObserver<T> observer, Observable<T> observable){
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
