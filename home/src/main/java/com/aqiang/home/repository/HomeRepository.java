package com.aqiang.home.repository;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.util.Log;

import com.aqiang.common.BaseApplication;
import com.aqiang.common.net.NetUtils;
import com.aqiang.common.utlis.PoolThread;
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

    public HomeRepository(LifecycleOwner owner) {
        super(owner);
        homeLoaclModel = new HomeLoaclModel();
    }

    @Override
    protected void createModel() {
        mModel = new HomeRemoteModel();
    }

    public LiveData<BaseReposenEntity<List<BannerEntity>>> getBanner(){
        if(NetUtils.netIsAvailable(BaseApplication.getContext())){
            LiveData<BaseReposenEntity<List<BannerEntity>>> banner = mModel.getBanner();
            banner.observe(owner, new Observer<BaseReposenEntity<List<BannerEntity>>>() {
                @Override
                public void onChanged(@Nullable final BaseReposenEntity<List<BannerEntity>> listBaseReposenEntity) {
                    PoolThread.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            HomeDBHelper.getInstance().getDb().homeDao().insertBannerAll(listBaseReposenEntity.getData());
                        }
                    });
                }
            });
            return banner;
        }else {
            final MutableLiveData<BaseReposenEntity<List<BannerEntity>>> mutableLiveData = new MutableLiveData<>();
            PoolThread.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<BannerEntity> liveData = homeLoaclModel.getBanner();
                    mutableLiveData.postValue(new BaseReposenEntity<List<BannerEntity>>(liveData));
                }
            });

            return mutableLiveData;
        }

    }

    public LiveData<BaseReposenEntity<List<SysMsgEntity>>> getSystemMsg(){
        if(NetUtils.netIsAvailable(BaseApplication.getContext())){
            LiveData<BaseReposenEntity<List<SysMsgEntity>>> systemMsg = mModel.getSystemMsg();
            systemMsg.observe(owner, new Observer<BaseReposenEntity<List<SysMsgEntity>>>() {
                @Override
                public void onChanged(@Nullable final BaseReposenEntity<List<SysMsgEntity>> listBaseReposenEntity) {
                    PoolThread.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            HomeDBHelper.getInstance().getDb().homeDao().insertSysMsgAll(listBaseReposenEntity.getData());
                        }
                    });
                }
            });
            return systemMsg;
        }else {

            final MutableLiveData<BaseReposenEntity<List<SysMsgEntity>>> liveData = new MutableLiveData<>();
            PoolThread.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<SysMsgEntity> sysMsgAll = homeLoaclModel.getSystemMsg();
                    liveData.setValue(new BaseReposenEntity<List<SysMsgEntity>>(sysMsgAll));
                }
            });

            return liveData;
        }
    }

    public LiveData<BaseReposenEntity<List<ProductEntity>>> getNewUserProduct(){
        if(NetUtils.netIsAvailable(BaseApplication.getContext())){
            LiveData<BaseReposenEntity<List<ProductEntity>>> userProduct = mModel.getNewUserProduct();
            userProduct.observe(owner, new Observer<BaseReposenEntity<List<ProductEntity>>>() {
                @Override
                public void onChanged(@Nullable BaseReposenEntity<List<ProductEntity>> listBaseReposenEntity) {
                    HomeDBHelper.getInstance().getDb().homeDao().insertProductAll(listBaseReposenEntity.getData());
                }
            });
            return userProduct;
        }else {
            final MutableLiveData<BaseReposenEntity<List<ProductEntity>>> liveData = new MutableLiveData<>();
            PoolThread.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<ProductEntity> newProduct = homeLoaclModel.getNewProduct();

                    liveData.setValue(new BaseReposenEntity<List<ProductEntity>>(newProduct));
                }
            });

            return liveData;
        }
    }

    public LiveData<BaseReposenEntity<List<ProductEntity>>> getProduct(){
        if(NetUtils.netIsAvailable(BaseApplication.getContext())){
            LiveData<BaseReposenEntity<List<ProductEntity>>> userProduct = mModel.getProduct();
            userProduct.observe(owner, new Observer<BaseReposenEntity<List<ProductEntity>>>() {
                @Override
                public void onChanged(@Nullable BaseReposenEntity<List<ProductEntity>> listBaseReposenEntity) {
                    HomeDBHelper.getInstance().getDb().homeDao().insertProductAll(listBaseReposenEntity.getData());
                }
            });
            return userProduct;
        }else {
            final MutableLiveData<BaseReposenEntity<List<ProductEntity>>> liveData = new MutableLiveData<>();

            // LiveData<List<ProductEntity>> newProduct = HomeDBHelper.getInstance().getDb().homeDao().getProductAll();
            PoolThread.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<ProductEntity> product = homeLoaclModel.getProduct();
                    liveData.setValue(new BaseReposenEntity<List<ProductEntity>>(product));
                }
            });

            return liveData;
        }
    }
}
