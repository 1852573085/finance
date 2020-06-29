package com.aqiang.home.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.aqiang.common.BaseApplication;
import com.aqiang.common.net.NetUtils;
import com.aqiang.core.repository.Repository;
import com.aqiang.home.entity.BannerEntity;
import com.aqiang.home.entity.ProductEntity;
import com.aqiang.home.entity.SysMsgEntity;
import com.aqiang.home.model.HomeLoaclModel;
import com.aqiang.home.model.HomeRemoteModel;
import com.aqiang.home.room.HomeDBHelper;
import com.aqiang.net.BaseReposenEntity;
import com.aqiang.net.rx.BaseObservable;
import com.aqiang.net.rx.BaseObserver;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class HomeRepository extends Repository<HomeRemoteModel> {

    private HomeLoaclModel homeLoaclModel;

    public HomeRepository() {
        homeLoaclModel = new HomeLoaclModel();
    }

    @Override
    protected void createModel() {
        mModel = new HomeRemoteModel();
    }

    public LiveData<BaseReposenEntity<List<BannerEntity>>> getBanner(){
        if(NetUtils.netIsAvailable(BaseApplication.getContext())){
            final LiveData<BaseReposenEntity<List<BannerEntity>>> banner = mModel.getBanner();
            Observable observable = Observable.create(new ObservableOnSubscribe() {
                @Override
                public void subscribe(ObservableEmitter emitter) throws Exception {
                    HomeDBHelper.getInstance().getDb().homeDao().insertBannerAll(banner.getValue().getData());
                }
            });
            BaseObservable.doObserver(new BaseObserver<Object>(),observable);
            return banner;
        }else {
            LiveData<List<BannerEntity>> liveData = homeLoaclModel.getBanner();
            MutableLiveData<BaseReposenEntity<List<BannerEntity>>> mutableLiveData = new MutableLiveData<>();
            mutableLiveData.setValue(new BaseReposenEntity<List<BannerEntity>>(liveData.getValue()));
            return mutableLiveData;
        }
        //return homeApi.getBanner();
    }

    public LiveData<BaseReposenEntity<List<SysMsgEntity>>> getSystemMsg(){
        if(NetUtils.netIsAvailable(BaseApplication.getContext())){
            final LiveData<BaseReposenEntity<List<SysMsgEntity>>> systemMsg = mModel.getSystemMsg();
            Observable observable = Observable.create(new ObservableOnSubscribe() {
                @Override
                public void subscribe(ObservableEmitter emitter) throws Exception {
                    HomeDBHelper.getInstance().getDb().homeDao().insertSysMsgAll(systemMsg.getValue().getData());
                }
            });
            BaseObservable.doObserver(new BaseObserver<Object>(),observable);
            return systemMsg;
        }else {
            LiveData<List<SysMsgEntity>> sysMsgAll = homeLoaclModel.getSystemMsg();
            MutableLiveData<BaseReposenEntity<List<SysMsgEntity>>> liveData = new MutableLiveData<>();
            liveData.setValue(new BaseReposenEntity<List<SysMsgEntity>>(sysMsgAll.getValue()));
            return liveData;
        }
    }

    public LiveData<BaseReposenEntity<List<ProductEntity>>> getNewUserProduct(){
        if(NetUtils.netIsAvailable(BaseApplication.getContext())){
            final LiveData<BaseReposenEntity<List<ProductEntity>>> userProduct = mModel.getNewUserProduct();
            Observable observable = Observable.create(new ObservableOnSubscribe() {
                @Override
                public void subscribe(ObservableEmitter emitter) throws Exception {
                    HomeDBHelper.getInstance().getDb().homeDao().insertProductAll(userProduct.getValue().getData());
                }
            });
            return userProduct;
        }else {
            LiveData<List<ProductEntity>> newProduct = homeLoaclModel.getNewProduct();
            MutableLiveData<BaseReposenEntity<List<ProductEntity>>> liveData = new MutableLiveData<>();
            liveData.setValue(new BaseReposenEntity<List<ProductEntity>>(newProduct.getValue()));
            return liveData;
        }
    }

    public LiveData<BaseReposenEntity<List<ProductEntity>>> getProduct(){
        if(NetUtils.netIsAvailable(BaseApplication.getContext())){
            final LiveData<BaseReposenEntity<List<ProductEntity>>> userProduct = mModel.getProduct();
            Observable observable = Observable.create(new ObservableOnSubscribe() {
                @Override
                public void subscribe(ObservableEmitter emitter) throws Exception {
                    HomeDBHelper.getInstance().getDb().homeDao().insertProductAll(userProduct.getValue().getData());
                }
            });
            return userProduct;
        }else {
            LiveData<List<ProductEntity>> product = homeLoaclModel.getProduct();
            // LiveData<List<ProductEntity>> newProduct = HomeDBHelper.getInstance().getDb().homeDao().getProductAll();
            MutableLiveData<BaseReposenEntity<List<ProductEntity>>> liveData = new MutableLiveData<>();
            liveData.setValue(new BaseReposenEntity<List<ProductEntity>>(product.getValue()));
            return liveData;
        }
    }
}
