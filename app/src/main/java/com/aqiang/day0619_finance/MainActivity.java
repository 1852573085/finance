package com.aqiang.day0619_finance;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.aqiang.common.router.RouterManager;
import com.aqiang.common.router.RouterPath;
import com.aqiang.common.utlis.ShowToast;
import com.aqiang.common.wiget.TitleBar;
import com.aqiang.day0619_finance.adapter.VpAdapter;

@Route(path = RouterPath.MAIN_ACTIVITY)
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDlActMain;
    private ViewPager mVpActMain;
    private BottomNavigationView mBoviewActMain;
    private TitleBar mTitlebarActMain;
    private NavigationView mNaviActMain;
    private Button headerLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        mVpActMain.setAdapter(new VpAdapter(getSupportFragmentManager()));
    }

    private void initView() {
        mDlActMain = (DrawerLayout) findViewById(R.id.dl_act_main);
        mVpActMain = (ViewPager) findViewById(R.id.vp_act_main);
        mBoviewActMain = (BottomNavigationView) findViewById(R.id.boview_act_main);
        mTitlebarActMain = (TitleBar) findViewById(R.id.titlebar_act_main);
        mDlActMain.setScrimColor(Color.TRANSPARENT);

        mNaviActMain = (NavigationView) findViewById(R.id.navi_act_main);
        View view = mNaviActMain.getHeaderView(0);
        headerLogin = view.findViewById(R.id.bt_header_login);
    }

    private void initEvent() {
        headerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ShowToast.INSTANCE.toast(MainActivity.this,"hh");
                RouterManager.getInstance().router(RouterPath.USER_ACTIVITY);
            }
        });
        mTitlebarActMain.setListenClick(new TitleBar.OnListenClick() {
            @Override
            public void onLeftClick() {
                mDlActMain.openDrawer(Gravity.LEFT);
            }

            @Override
            public void onRightClick() {

            }
        });
        mVpActMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mBoviewActMain.getMenu().getItem(i).setChecked(true);
                if (i == 0) {
                    setTitle("梧桐金融", R.drawable.msg2, R.drawable.user2);
                } else if (i == 1) {
                    setTitle("梧桐金融", 0, 0);
                } else if (i == 2) {
                    setTitle("个人中心", 0, 0);
                } else if (i == 3) {
                    setTitle("更多", 0, 0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mBoviewActMain.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_bottombar_home:
                        mVpActMain.setCurrentItem(0);
                        setTitle("梧桐金融", R.drawable.msg2, R.drawable.user2);
                        break;
                    case R.id.menu_bottombar_finalce:
                        mVpActMain.setCurrentItem(1);
                        setTitle("梧桐金融", 0, 0);
                        break;
                    case R.id.menu_bottombar_account:
                        mVpActMain.setCurrentItem(2);
                        setTitle("个人中心", 0, 0);
                        break;
                    case R.id.menu_bottombar_more:
                        mVpActMain.setCurrentItem(3);
                        setTitle("更多", 0, 0);
                        break;
                    default:

                        break;
                }
                return false;
            }
        });
    }

    public void setTitle(String title, int rightIcon, int leftIcon) {
        mTitlebarActMain.setTilte(title);
        mTitlebarActMain.setLeftIcon(this, leftIcon);
        mTitlebarActMain.setRightIcon(this, rightIcon);
    }
}
