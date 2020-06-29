package com.aqiang.home.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tb_banner")
public class BannerEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo
    private String imgurl;
    @ColumnInfo
    private String desc;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
