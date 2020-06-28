package com.aqiang.home;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.aqiang.common.BaseApplication;
import com.aqiang.core.view.BaseFragment;
import com.aqiang.home.databinding.FragmentHomeBinding;
import com.aqiang.home.viewmodel.HomeViewModel;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    private Banner mBannerFragHome;
    private ViewFlipper mVfFragHome;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeViewModel createVM() {
        return new HomeViewModel();
    }

    @Override
    protected void initView(View view) {
        mBannerFragHome = (Banner) view.findViewById(R.id.banner_frag_home);
        mVfFragHome = (ViewFlipper) view.findViewById(R.id.vf_frag_home);
    }

    @Override
    protected void setBinding() {
        binding.setImgPic("http://hbimg.b0.upaiyun.com/0cdfedffcedb13445e4def3f2d6891bb32cb03de828b-m2zK4U_fw658");
    }

    @Override
    protected void initData() {
        List<String> imgPath=new ArrayList<>();
        imgPath.add("http://hbimg.b0.upaiyun.com/0cdfedffcedb13445e4def3f2d6891bb32cb03de828b-m2zK4U_fw658");
        imgPath.add("http://hbimg.b0.upaiyun.com/8a75ab36c175489634b6c8621eea02fd8c83bb82c3869-Waz6eO_fw658");
        imgPath.add("http://hbimg.b0.upaiyun.com/a2a321fb4e128e2327674fee6c3be76bb7d6f70929ca3-IVhr33_fw658");
        imgPath.add("http://hbimg.b0.upaiyun.com/861f92e7514b297b0cd5833b3ffb52f8df37b4ec218f8-BmVyhw_fw658");

        List<String> strings=new ArrayList<>();
        strings.add("金融产品1");
        strings.add("金融产品2");
        strings.add("金融产品3");
        strings.add("金融产品4");

        mBannerFragHome.setImages(imgPath);
        mBannerFragHome.setBannerTitles(strings);
        mBannerFragHome.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        mBannerFragHome.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
        mBannerFragHome.start();

        for (int i = 0; i < 10 ; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_flipper, null);
            TextView textView = view.findViewById(R.id.tv_item_flipper);
            textView.setText("item"+i);
            mVfFragHome.addView(view);
        }
        mVfFragHome.setFlipInterval(2000);
        mVfFragHome.startFlipping();
    }

    @Override
    protected void initEvent() {

    }

    @BindingAdapter("imgPic")
    public static void imgPic(ImageView imageView,String url){
        Glide.with(BaseApplication.getContext())
                .load(url)
                .into(imageView);
    }
}
