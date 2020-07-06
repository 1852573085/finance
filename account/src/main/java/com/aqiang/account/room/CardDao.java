package com.aqiang.account.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aqiang.account.entity.CardEntity;

import java.util.List;

@Dao
public interface CardDao {
    @Query("select * from tb_card")
    List<CardEntity> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCard(CardEntity cardEntity);
}
