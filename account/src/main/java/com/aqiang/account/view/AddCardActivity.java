package com.aqiang.account.view;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aqiang.account.MySql;
import com.aqiang.account.R;
import com.aqiang.account.room.CardDataBase;
import com.aqiang.common.router.RouterPath;
import com.aqiang.common.wiget.CodeEditText;
import com.aqiang.common.wiget.TitleBar;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;

@Route(path = RouterPath.ACCOUNT_ADD)
public class AddCardActivity extends AppCompatActivity {

    private TitleBar mTitleActAddCard;
    private Spinner mSpActAddCard;
    private FloatingActionButton mBtActAddCard;
    //private EditText mEtAddCard;
    //private CardDBHelper instance;
    private CardDataBase card_db;
    private SQLiteDatabase db;
    private CodeEditText mEt01;
    private CodeEditText mEt02;
    private CodeEditText mEt03;
    private CodeEditText mEt04;
    private CodeEditText mEt05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        mTitleActAddCard = (TitleBar) findViewById(R.id.title_act_add_card);
        mSpActAddCard = (Spinner) findViewById(R.id.sp_act_add_card);
        mBtActAddCard = (FloatingActionButton) findViewById(R.id.bt_act_add_card);
        //mEtAddCard = (EditText) findViewById(R.id.et_add_card);
        mEt01 = (CodeEditText) findViewById(R.id.et_01);
        mEt02 = (CodeEditText) findViewById(R.id.et_02);
        mEt03 = (CodeEditText) findViewById(R.id.et_03);
        mEt04 = (CodeEditText) findViewById(R.id.et_04);
        mEt05 = (CodeEditText) findViewById(R.id.et_05);
    }

    private void initData() {
        mEt01.setNext(mEt02);
        mEt02.setNext(mEt03);
        mEt03.setNext(mEt04);
        mEt04.setNext(mEt05);
        mEt01.setCodeText("0011 2433 5666 7892 666");
        initSp();
        MySql mySql = new MySql(this);
        db = mySql.getReadableDatabase();
        //card_db = Room.databaseBuilder(AddCardActivity.this, CardDataBase.class, "card_db").build();
        //instance = CardDBHelper.getInstance();
    }

    private void initEvent() {
        mTitleActAddCard.setListenClick(new TitleBar.OnListenClick() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightClick() {
                Intent intent = new Intent(AddCardActivity.this, CaptureActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        mBtActAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AddCardActivity.this, "aa", Toast.LENGTH_SHORT).show();
//                CardEntity cardEntity = new CardEntity();
//                cardEntity.setCardId(mEtAddCard.getText().toString());
//                cardEntity.setCardName(mSpActAddCard.getSelectedItem().toString());
//                cardEntity.setSelect(0);
                ContentValues values = new ContentValues();
                values.put("c_name", mSpActAddCard.getSelectedItem().toString());
                values.put("c_id", mEt01.getText().toString()+mEt02.getText().toString()+mEt03.getText().toString()+mEt04.getText().toString()+mEt05.getText().toString());
                values.put("c_select", 0);
                db.insert("card", null, values);
//                PoolThread.getInstance().execute(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        //instance.getDb().cardDao().insertCard(cardEntity);
//
//                        card_db.cardDao().insertCard(cardEntity);
//                        //Log.d("swq","11");
//                    }
//                });
            }
        });
    }

    private void initSp() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("中国建设银行");
        strings.add("中国农业银行");
        strings.add("中国邮政银行");
        strings.add("浙江商行银行");
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, strings);
        mSpActAddCard.setAdapter(stringArrayAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                    //mEtAddCard.setText(result);
                    mEt01.setCodeText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(AddCardActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
