package com.aqiang.home.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.aqiang.home.entity.BannerEntity;
import com.aqiang.home.entity.ProductEntity;
import com.aqiang.home.entity.SysMsgEntity;

@Database(entities = {BannerEntity.class, ProductEntity.class, SysMsgEntity.class},version = 1,exportSchema = false)
public abstract class HomeDataBase extends RoomDatabase {
    public abstract HomeDao homeDao();
}
