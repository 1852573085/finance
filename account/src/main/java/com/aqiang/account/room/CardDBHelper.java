package com.aqiang.account.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;

import com.aqiang.common.BaseApplication;

public class CardDBHelper {

    private CardDataBase db;

    private CardDBHelper(){
        db = Room.databaseBuilder(BaseApplication.getContext(), CardDataBase.class, "card_db").build();
    }

    private static CardDBHelper cardDBHelper = new CardDBHelper();


    public static CardDBHelper getInstance(){
        return cardDBHelper;
    }

    public CardDataBase getDb(){
        return db;
    }
}
