package com.aqiang.account.view;

import android.arch.persistence.room.Room;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aqiang.account.MySql;
import com.aqiang.account.R;
import com.aqiang.account.adapter.CardAdapter;
import com.aqiang.account.entity.CardEntity;
import com.aqiang.account.room.CardDBHelper;
import com.aqiang.account.room.CardDataBase;
import com.aqiang.common.router.RouterManager;
import com.aqiang.common.router.RouterPath;
import com.aqiang.common.utlis.PoolThread;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterPath.ACCOUNT_ACTIVITY)
public class CardActivity extends AppCompatActivity {
    private CheckBox mBoxActCardAll;
    private TextView mTvActCardDel;
    private FloatingActionButton mBtActCardAdd;
    private RecyclerView mRvActCard;
    private CardAdapter cardAdapter;
    private List<CardEntity> list;
    private CardDataBase card_db;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        initView();
        initData();
        initEvent();
    }


    private void initView() {
        mBoxActCardAll = (CheckBox) findViewById(R.id.box_act_card_all);
        mTvActCardDel = (TextView) findViewById(R.id.tv_act_card_del);
        mBtActCardAdd = (FloatingActionButton) findViewById(R.id.bt_act_card_add);
        mRvActCard = (RecyclerView) findViewById(R.id.rv_act_card);

        mRvActCard.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        MySql mySql = new MySql(this);
        db = mySql.getReadableDatabase();
        list = new ArrayList<>();
        Cursor cursor = db.query("card", null, null, null, null, null, null);
        if(cursor != null){
            while (cursor.moveToNext()){
                String c_name = cursor.getString(cursor.getColumnIndex("c_name"));
                String c_id = cursor.getString(cursor.getColumnIndex("c_id"));
                int c_select = cursor.getInt(cursor.getColumnIndex("c_select"));
                CardEntity cardEntity = new CardEntity();
                cardEntity.setCardName(c_name);
                cardEntity.setCardId(c_id);
                cardEntity.setSelect(c_select);
                if(!list.contains(cardEntity)){
                    list.add(cardEntity);
                }
            }
        }
//        PoolThread.getInstance().execute(new Runnable() {
//            @Override
//            public void run() {
//                list = card_db.cardDao().getAll();
//                //list = CardDBHelper.getInstance().getDb().cardDao().getAll();
//                Log.d("swq","aaa"+list.size());
//            }
//        });
        if(list != null){
            cardAdapter = new CardAdapter(R.layout.item,list);
            mRvActCard.setAdapter(cardAdapter);
        }
    }

    private void initEvent() {
        mBtActCardAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterManager.getInstance().router(RouterPath.ACCOUNT_ADD);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(cardAdapter != null){
            cardAdapter.notifyDataSetChanged();
        }
    }
}
