package com.aqiang.common.wiget.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected Context context;
    protected List<T> data;
    public BaseAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return createVH(viewGroup,i);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int i) {
        bindVH(vh,i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void loadData(List<T> data){
        if(data != null){
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void appendDataSource(List<T> _dataSource){
        if (this.data!=null&&_dataSource!=null){
            this.data.addAll(_dataSource);
            notifyDataSetChanged();
        }
    }

    protected abstract VH createVH(ViewGroup viewGroup, int i);

    protected abstract void bindVH(VH vh, int i);
}
