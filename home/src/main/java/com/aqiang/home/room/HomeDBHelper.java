package com.aqiang.home.room;

import android.arch.persistence.room.Room;

import com.aqiang.common.BaseApplication;

public class HomeDBHelper {
    private HomeDataBase db;

    private HomeDBHelper(){
        db = Room.databaseBuilder(BaseApplication.getContext(),HomeDataBase.class,"home.db").build();
    }

    private static HomeDBHelper homeDBHelper = new HomeDBHelper();

    public static HomeDBHelper getInstance(){
        return homeDBHelper;
    }

    public HomeDataBase getDb(){
        return db;
    }

    public void closeDB(){
        if (db!=null&&db.isOpen()){
            db.close();
        }
    }
}
