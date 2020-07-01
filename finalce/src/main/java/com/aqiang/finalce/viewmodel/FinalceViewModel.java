package com.aqiang.finalce.viewmodel;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;

import com.aqiang.core.repository.Repository;
import com.aqiang.core.viewmodel.BaseViewModel;
import com.aqiang.finalce.api.FinalceApi;
import com.aqiang.finalce.entity.FinalceEntity;
import com.aqiang.finalce.repository.FinalceRepository;
import com.aqiang.net.BaseReposenEntity;
import com.aqiang.net.RetrofitManager;

import java.util.List;

public class FinalceViewModel extends BaseViewModel {
    public FinalceViewModel(LifecycleOwner owner) {
        super(owner);
        registerRepository(FinalceRepository.class.getSimpleName(),new FinalceRepository(owner));
    }

    public LiveData<BaseReposenEntity<List<FinalceEntity>>> getFinalceByType(int type, int currentPage, int pageSize){
        FinalceRepository repository = getRepository(FinalceRepository.class.getSimpleName());
        return repository.getFinalce(type, currentPage, pageSize);
    }
}
