package com.aqiang.account.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;

import com.aqiang.account.R;
import com.aqiang.account.entity.CardEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class CardAdapter extends BaseQuickAdapter<CardEntity, BaseViewHolder> {
    public CardAdapter(int layoutResId, @Nullable List<CardEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardEntity item) {
        CardView cardView = helper.getView(R.id.cv_item);
        if(helper.getLayoutPosition() % 3 == 0){
            cardView.setCardBackgroundColor(Color.BLUE);
        }else if(helper.getLayoutPosition() % 3 == 1){
            cardView.setCardBackgroundColor(Color.YELLOW);
        }else if(helper.getLayoutPosition() % 3 == 2){
            cardView.setCardBackgroundColor(Color.RED);
        }
        helper.setText(R.id.tv_item_name,item.getCardName());
        helper.setText(R.id.tv_item_id,item.getCardId());
        CheckBox checkBox = helper.getView(R.id.box_item);
        if(item.getSelect() == 0){
            checkBox.setChecked(false);
        }else {
            checkBox.setChecked(true);
        }
    }
}
