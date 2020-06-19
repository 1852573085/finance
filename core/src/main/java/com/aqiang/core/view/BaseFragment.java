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
        setBinding();
        return  binding.getRoot();
    }

    protected void showToast(String msg){
        ShowToast.INSTANCE.toast(getContext(),msg);
    }

    protected abstract int bindLayout();

    protected abstract VM createVM();

    protected abstract void setBinding();
}
