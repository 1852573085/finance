package com.aqiang.account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aqiang.common.router.RouterManager;
import com.aqiang.common.router.RouterPath;

public class AccountFragment extends Fragment {

    private View view;
    private TextView mTvFragmentAccountManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account, container, false);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        mTvFragmentAccountManager = (TextView) view.findViewById(R.id.tv_fragment_account_manager);
        mTvFragmentAccountManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterManager.getInstance().router(RouterPath.ACCOUNT_ACTIVITY);
            }
        });
    }
}
