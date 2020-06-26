package com.aqiang.storage.sp.impl;

public class StorageManager {
    private IStorage iStorage;
    private StorageManager(){
        iStorage = new SPStorage();
    }
    private static StorageManager storageManager = new StorageManager();
    public static StorageManager getInstance(){
        return storageManager;
    }

    public void init(int type){
        if(type == 0){
            iStorage = new SPStorage();
        }
    }

    public void save(String key, Object value) {
        iStorage.save(key,value);
    }

    public Object get(String key,Object defaultValue) {
        return iStorage.get(key,defaultValue);
    }

    public void cloer(String key){
        iStorage.coler(key);
    }
}
