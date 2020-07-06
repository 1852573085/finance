package com.aqiang.account.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.aqiang.account.entity.CardEntity;

@Database(entities = CardEntity.class,version = 1)
public abstract class CardDataBase extends RoomDatabase {
    public abstract CardDao cardDao();
}
