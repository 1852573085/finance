package com.aqiang.finalce.repository;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.aqiang.common.BaseApplication;
import com.aqiang.common.net.NetUtils;
import com.aqiang.common.utlis.PoolThread;
import com.aqiang.core.repository.Repository;
import com.aqiang.finalce.entity.FinalceEntity;
import com.aqiang.finalce.model.FinalceLocalModel;
import com.aqiang.finalce.model.FinalceReomteModel;
import com.aqiang.finalce.room.FinalceDBHepler;
import com.aqiang.net.BaseReposenEntity;

import java.util.List;

public class FinalceRepository extends Repository<FinalceReomteModel> {

    private FinalceLocalModel finalceLocalModel;

    public FinalceRepository(LifecycleOwner owner) {
        super(owner);
        finalceLocalModel = new FinalceLocalModel();
    }

    @Override
    protected void createModel() {
        mModel = new FinalceReomteModel();
    }

    public LiveData<BaseReposenEntity<List<FinalceEntity>>> getFinalce(final int producttype, final int currentpage, final int pagesize){
        if(NetUtils.netIsAvailable(BaseApplication.getContext())){
            LiveData<BaseReposenEntity<List<FinalceEntity>>> finalceByType = mModel.getFinalceByType(producttype, currentpage, pagesize);
            finalceByType.observe(owner, new Observer<BaseReposenEntity<List<FinalceEntity>>>() {
                @Override
                public void onChanged(@Nullable final BaseReposenEntity<List<FinalceEntity>> listBaseReposenEntity) {
                    PoolThread.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            finalceLocalModel.addFinalce(listBaseReposenEntity.getData());
                        }
                    });
                }
            });
            return finalceByType;
        }else {
            final MutableLiveData<BaseReposenEntity<List<FinalceEntity>>> liveData = new MutableLiveData<>();
            PoolThread.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    List<FinalceEntity> finalce = finalceLocalModel.getFinalce(producttype, currentpage, pagesize);
                    liveData.postValue(new BaseReposenEntity<List<FinalceEntity>>(finalce));
                }
            });
            return liveData;
        }
    }

}
