package com.aqiang.finalce.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aqiang.common.wiget.adapter.BaseAdapter;
import com.aqiang.common.wiget.adapter.BaseViewHolder;
import com.aqiang.finalce.BR;
import com.aqiang.finalce.R;
import com.aqiang.finalce.entity.FinalceEntity;

public class FinalceAdapter extends BaseAdapter<FinalceEntity, BaseViewHolder> {
    public FinalceAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder createVH(ViewGroup viewGroup, int i) {
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_finalce, viewGroup, false);
        return new BaseViewHolder(inflate);
    }

    @Override
    protected void bindVH(BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.getBinding().setVariable(BR.item,data.get(i));
    }
}
