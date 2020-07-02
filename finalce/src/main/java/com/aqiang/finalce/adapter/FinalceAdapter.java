package com.aqiang.finalce.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;

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
        RatingBar ratingBar = inflate.getRoot().findViewById(R.id.rb_bar);
        changeFoot(ratingBar);
        return new BaseViewHolder(inflate);
    }

    private void changeFoot(RatingBar ratingBar) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.start_checked);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,bitmap.getHeight());
        ratingBar.setLayoutParams(params);
    }

    @Override
    protected void bindVH(BaseViewHolder baseViewHolder, int i) {
        baseViewHolder.getBinding().setVariable(BR.item,data.get(i));
    }
}
