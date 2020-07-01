package com.aqiang.core.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;

import com.aqiang.core.R;
import com.aqiang.core.repository.Repository;

import java.util.HashMap;
import java.util.Map;

public class BaseViewModel extends ViewModel implements LifecycleObserver {
    //protected R mRepository;
    protected Map<String,Repository> repositoryMap;

    public BaseViewModel(LifecycleOwner owner) {
        repositoryMap = new HashMap<>();
    }

    protected void registerRepository(String key,Repository repository){
        if(repositoryMap.containsKey(key)){
            return;
        }
        repositoryMap.put(key,repository);
    }

    protected void nuRegisterRepository(String key){
        if(repositoryMap.containsKey(key)){
            repositoryMap.remove(key);
        }
    }

    protected <Sub extends Repository> Sub getRepository(String key){
       if(repositoryMap.containsKey(key)){
           return (Sub) repositoryMap.get(key);
       }
       return null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop(){
        repositoryMap.clear();
        repositoryMap = null;
    }
}
