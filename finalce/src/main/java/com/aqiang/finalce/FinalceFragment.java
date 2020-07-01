package com.aqiang.finalce;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aqiang.core.view.BaseFragment;
import com.aqiang.finalce.adapter.FinalceAdapter;
import com.aqiang.finalce.databinding.FragmentFinalceBinding;
import com.aqiang.finalce.entity.FinalceEntity;
import com.aqiang.finalce.viewmodel.FinalceViewModel;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

public class FinalceFragment extends BaseFragment<FragmentFinalceBinding, FinalceViewModel> {

    private TabLayout mTabFragFinalce;
    private SmartRefreshLayout mRefreshFragFinalce;
    private RecyclerView mRvFragFinalce;
    private FinalceAdapter finalceAdapter;
    private List<FinalceEntity> list;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_finalce;
    }

    @Override
    protected FinalceViewModel createVM() {
        return new FinalceViewModel(this);
    }

    @Override
    protected void initView(View view) {
        mTabFragFinalce = (TabLayout) view.findViewById(R.id.tab_frag_finalce);
        mRefreshFragFinalce = (SmartRefreshLayout) view.findViewById(R.id.refresh_frag_finalce);
        mRvFragFinalce = (RecyclerView) view.findViewById(R.id.rv_frag_finalce);
        mRvFragFinalce.setLayoutManager(new LinearLayoutManager(getContext()));
        mRefreshFragFinalce.setRefreshHeader(new DeliveryHeader(getContext()));
    }

    @Override
    protected void setBinding() {

    }

    @Override
    protected void initData() {
        finalceAdapter = new FinalceAdapter(getContext());
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            FinalceEntity finalceEntity = new FinalceEntity("item" + i);
            list.add(finalceEntity);
        }
        finalceAdapter.loadData(list);
        mRvFragFinalce.setAdapter(finalceAdapter);
    }

    @Override
    protected void initEvent() {
        mRefreshFragFinalce.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                finalceAdapter.appendDataSource(list);
                mRefreshFragFinalce.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                finalceAdapter.loadData(list);
                mRefreshFragFinalce.finishRefresh();
            }
        });
        mTabFragFinalce.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){

                }else if(tab.getPosition() == 1){

                }else if(tab.getPosition() == 2){

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
