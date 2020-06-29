package com.aqiang.home.viewmodel;

import android.arch.lifecycle.LiveData;

import com.aqiang.core.repository.Repository;
import com.aqiang.core.viewmodel.BaseViewModel;
import com.aqiang.home.entity.BannerEntity;
import com.aqiang.home.entity.ProductEntity;
import com.aqiang.home.entity.SysMsgEntity;
import com.aqiang.home.repository.HomeRepository;
import com.aqiang.net.BaseReposenEntity;

import java.util.List;

public class HomeViewModel extends BaseViewModel {
    public HomeViewModel() {
        registerRepository(HomeRepository.class.getSimpleName(),new HomeRepository());
    }

    public LiveData<BaseReposenEntity<List<BannerEntity>>> getBanner(){
        HomeRepository repository = getRepository(HomeRepository.class.getSimpleName());
        return repository.getBanner();
    }

    public LiveData<BaseReposenEntity<List<SysMsgEntity>>> getSystemMsg(){
        HomeRepository repository = getRepository(HomeRepository.class.getSimpleName());
        return repository.getSystemMsg();
    }

    public LiveData<BaseReposenEntity<List<ProductEntity>>> getNewUserProduct(){
        HomeRepository repository = getRepository(HomeRepository.class.getSimpleName());
        return repository.getNewUserProduct();
    }

    public LiveData<BaseReposenEntity<List<ProductEntity>>> getProduct(){
        HomeRepository repository = getRepository(HomeRepository.class.getSimpleName());
        return repository.getProduct();
    }
}
