package com.aqiang.storage.sp.impl;

import com.aqiang.common.BaseApplication;
import com.aqiang.storage.sp.SPUtlis;

public class SPStorage implements IStorage {
    @Override
    public void save(String key, Object value) {
        SPUtlis.put(BaseApplication.getContext(),key,value);
    }

    @Override
    public Object get(String key,Object defaultValue) {
        return SPUtlis.get(BaseApplication.getContext(),key,defaultValue);
    }

    @Override
    public void coler(String key) {
        SPUtlis.remove(BaseApplication.getContext(),key);
    }
}
