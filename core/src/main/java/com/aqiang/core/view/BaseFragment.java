package com.aqiang.core.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aqiang.common.utlis.ShowToast;
import com.aqiang.core.viewmodel.BaseViewModel;

public abstract class BaseFragment<Binding extends ViewDataBinding,VM extends BaseViewModel> extends Fragment {
    protected Binding binding;
    protected VM vm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,bindLayout(),container,false);
        vm = createVM();
        View view = binding.getRoot();
        initView(view);
        setBinding();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initEvent();
    }



    protected void showToast(String msg){
        ShowToast.INSTANCE.toast(getContext(),msg);
    }

    protected abstract int bindLayout();

    protected abstract VM createVM();

    protected abstract void initView(View view);

    protected abstract void setBinding();

    protected abstract void initData();

    protected abstract void initEvent();
}
