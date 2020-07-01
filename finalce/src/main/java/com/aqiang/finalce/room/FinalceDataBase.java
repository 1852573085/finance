package com.aqiang.finalce.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.aqiang.finalce.entity.FinalceEntity;

@Database(entities = FinalceEntity.class,version = 1)
public abstract class FinalceDataBase extends RoomDatabase {
    public abstract FinalceDao finalceDao();
}
