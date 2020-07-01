package com.aqiang.finalce.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aqiang.finalce.entity.FinalceEntity;

import java.util.List;

@Dao
public interface FinalceDao {
    @Insert()
    void addFinalceAll(List<FinalceEntity> list);

    @Query("select * from tb_finalce where producttype= :producttype limit (:currentpage*:pagesize),:pagesize")
    List<FinalceEntity> getFinalce(int producttype,int currentpage,int pagesize);
}
