package com.aqiang.storage.sp.impl;

public interface IStorage {
    void save(String key,Object value);

    Object get(String key);
}
