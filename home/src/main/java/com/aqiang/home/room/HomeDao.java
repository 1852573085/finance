package com.aqiang.home.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.aqiang.home.entity.BannerEntity;
import com.aqiang.home.entity.ProductEntity;
import com.aqiang.home.entity.SysMsgEntity;

import java.util.List;

@Dao
public interface HomeDao {
    @Query("select * from tb_banner")
    LiveData<List<BannerEntity>> getBannerAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBannerAll(List<BannerEntity> banners);

    @Query("select * from tb_product where isnew = 0")
    LiveData<List<ProductEntity>> getProductAll();
    @Query("select * from tb_product where isnew = 1")
    LiveData<List<ProductEntity>> getNewProduct();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProductAll(List<ProductEntity> products);

    @Query("select * from tb_sysmsg")
    LiveData<List<SysMsgEntity>> getSysMsgAll();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSysMsgAll(List<SysMsgEntity> sysmsg);
}
