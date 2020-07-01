package com.aqiang.finalce.room;

import android.arch.persistence.room.Room;

import com.aqiang.common.BaseApplication;

public class FinalceDBHepler {

    private FinalceDataBase db;

    private FinalceDBHepler(){
        db = Room.databaseBuilder(BaseApplication.getContext(), FinalceDataBase.class, "finalce_tb").build();
    }

    private static FinalceDBHepler finalceDBHepler = new FinalceDBHepler();

    public static FinalceDBHepler getInstance(){
        return finalceDBHepler;
    }

    public FinalceDataBase getDb(){
        return db;
    }
}

