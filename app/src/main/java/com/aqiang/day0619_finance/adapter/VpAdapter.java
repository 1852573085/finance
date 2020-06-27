package com.aqiang.day0619_finance.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.aqiang.account.AccountFragment;
import com.aqiang.finalce.FinalceFragment;
import com.aqiang.home.HomeFragment;
import com.aqiang.more.MoreFragment;

public class VpAdapter extends FragmentStatePagerAdapter {
    public VpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if(i == 0){
            return new HomeFragment();
        }else if(i == 1){
            return new FinalceFragment();
        }else if(i == 2){
            return new AccountFragment();
        }else if(i == 3){
            return new MoreFragment();
        }else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
