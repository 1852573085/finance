package com.aqiang.common.wiget.adapter;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;
    public BaseViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.binding = viewDataBinding;
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
