package com.aqiang.core.repository;

import android.arch.lifecycle.LifecycleOwner;

import com.aqiang.core.model.IModel;

public abstract class Repository<M extends IModel> {
    protected M mModel;
    protected LifecycleOwner owner;
    public Repository(LifecycleOwner owner) {
        createModel();
        this.owner = owner;
    }

    protected abstract void createModel();
}
