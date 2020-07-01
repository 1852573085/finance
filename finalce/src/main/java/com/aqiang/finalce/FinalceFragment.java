package com.aqiang.finalce;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
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
import com.aqiang.net.BaseReposenEntity;
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
    private int currentType = 0;
    /**
     * 当前页码
     */
    private int currentPage = 0;
    /**
     * 每页数据行数
     */
    private int pagesize = 10;
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
        initRv();
        mRvFragFinalce.setAdapter(finalceAdapter);
    }

    private void initRv() {
        LiveData<BaseReposenEntity<List<FinalceEntity>>> finalceByType = vm.getFinalceByType(currentType, currentPage, pagesize);
        finalceByType.observe(this, new Observer<BaseReposenEntity<List<FinalceEntity>>>() {
            @Override
            public void onChanged(@Nullable BaseReposenEntity<List<FinalceEntity>> listBaseReposenEntity) {
                finalceAdapter.loadData(listBaseReposenEntity.getData());
            }
        });
    }

    @Override
    protected void initEvent() {
        mRefreshFragFinalce.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mRefreshFragFinalce.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                mRefreshFragFinalce.finishRefresh();
            }
        });
        mTabFragFinalce.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    currentType = 0;
                }else if(tab.getPosition() == 1){
                    currentType = 1;
                }else if(tab.getPosition() == 2){
                    currentType = 2;
                }
                initRv();
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
