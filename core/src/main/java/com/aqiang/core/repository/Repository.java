package com.aqiang.core.repository;

import com.aqiang.core.model.IModel;

public abstract class Repository<M extends IModel> {
    protected M mModel;

    public Repository() {
        createModel();
    }

    protected abstract void createModel();
}
