package com.aqiang.finalce.model;

import com.aqiang.core.model.IModel;
import com.aqiang.finalce.entity.FinalceEntity;
import com.aqiang.finalce.room.FinalceDBHepler;

import java.util.List;

public class FinalceLocalModel implements IModel {
    public List<FinalceEntity> getFinalce(int producttype,int currentpage,int pagesize){
        return FinalceDBHepler.getInstance().getDb().finalceDao().getFinalce(producttype, currentpage, pagesize);
    }

    public void addFinalce(List<FinalceEntity> list){
        FinalceDBHepler.getInstance().getDb().finalceDao().addFinalceAll(list);
    }
}
