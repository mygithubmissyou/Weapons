package com.lzy.weapon.view;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lzy.weapon.R;
import com.lzy.weapon.adaptor.MenuAdaptor;
import com.lzy.weapon.adaptor.MyViewPagerAdaptor;
import com.lzy.weapon.contract.JssyContract;
import com.lzy.weapon.model.CustomBean;
import com.lzy.weapon.model.Fenlei;
import com.lzy.weapon.pensenter.BaozhawuPresenter;
import com.lzy.weapon.pensenter.JssyPresenter;
import com.lzy.weapon.utils.Constant;
import com.lzy.weapon.utils.DBUtils;
import com.lzy.weapon.utils.DbManger;
import com.lzy.weapon.utils.PermissionUtil;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TabPageIndicator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JssyActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String LOGTAG = "JssyActivity";
    private JssyPresenter mPresenter;
    public SlidingMenu mSlidingMenu;
    private ImageButton btn_left;
    private ListView listView;
    public ViewPager viewPager;
    private JssyFragment jssyFragment;
    private BaozhawuFragment baozhawuFragment;
    private List<Fragment> views;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        PermissionUtil.requestAllPermissions(this);
        setContentView(R.layout.activity_jssy);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!preferences.getBoolean("isfirst", false)) {
            //数据库写入存储卡
            DBUtils.writeDBFile(getApplicationInfo().packageName, getApplicationContext());
        }
        initFragments();
        initView();
        initFunc();
    }

    private void initFragments() {
        views = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (jssyFragment == null) {
            jssyFragment = new JssyFragment();
            mPresenter = new JssyPresenter(jssyFragment, "jssy");
            jssyFragment.setPresenter(mPresenter);
            views.add(jssyFragment);
//            fragmentTransaction.add(jssyFragment, "军事术语");
        }
        if (baozhawuFragment == null) {
            baozhawuFragment = new BaozhawuFragment();
            baozhawuFragment.setTitle("爆炸物");
            BaozhawuPresenter baozhawuPresenter = new BaozhawuPresenter(baozhawuFragment, "baozhawu");
            baozhawuFragment.setPresenter(baozhawuPresenter);
            views.add(baozhawuFragment);
//            fragmentTransaction.add(baozhawuFragment,"爆炸物");
        }

        baozhawuFragment = new BaozhawuFragment();
        BaozhawuPresenter feixinqiPresenter = new BaozhawuPresenter(baozhawuFragment, "feixingqi");
        baozhawuFragment.setPresenter(feixinqiPresenter);
        views.add(baozhawuFragment);
        baozhawuFragment.setTitle("飞行器");
//        fragmentTransaction.add(baozhawuFragment,"飞行器");

        baozhawuFragment = new BaozhawuFragment();
        BaozhawuPresenter danbingPresenter = new BaozhawuPresenter(baozhawuFragment, "danbing");
        baozhawuFragment.setPresenter(danbingPresenter);
        views.add(baozhawuFragment);
        baozhawuFragment.setTitle("单兵装备");
//        fragmentTransaction.add(baozhawuFragment,"单兵装备");

        baozhawuFragment = new BaozhawuFragment();
        BaozhawuPresenter daodanPresenter = new BaozhawuPresenter(baozhawuFragment, "daodan");
        baozhawuFragment.setPresenter(daodanPresenter);
        views.add(baozhawuFragment);
        baozhawuFragment.setTitle("导弹");
//        fragmentTransaction.add(baozhawuFragment,"导弹");

        baozhawuFragment = new BaozhawuFragment();
        BaozhawuPresenter huopaoPresenter = new BaozhawuPresenter(baozhawuFragment, "huopao");
        baozhawuFragment.setPresenter(huopaoPresenter);
        views.add(baozhawuFragment);
        baozhawuFragment.setTitle("火炮");
//        fragmentTransaction.add(baozhawuFragment,"火炮");

        baozhawuFragment = new BaozhawuFragment();
        BaozhawuPresenter jianchuanPresenter = new BaozhawuPresenter(baozhawuFragment, "jianchuan");
        baozhawuFragment.setPresenter(jianchuanPresenter);
        views.add(baozhawuFragment);
        baozhawuFragment.setTitle("舰船");
//        fragmentTransaction.add(baozhawuFragment,"舰船");

        baozhawuFragment = new BaozhawuFragment();
        BaozhawuPresenter tangkePresenter = new BaozhawuPresenter(baozhawuFragment, "tangke");
        baozhawuFragment.setPresenter(tangkePresenter);
        views.add(baozhawuFragment);
        baozhawuFragment.setTitle("坦克");
//        fragmentTransaction.add(baozhawuFragment,"坦克");

        baozhawuFragment = new BaozhawuFragment();
        BaozhawuPresenter taikongPresenter = new BaozhawuPresenter(baozhawuFragment, "taikong");
        baozhawuFragment.setPresenter(taikongPresenter);
        views.add(baozhawuFragment);
        baozhawuFragment.setTitle("太空装备");
//        fragmentTransaction.add(baozhawuFragment,"太空装备");
//
//        fragmentTransaction.commit();
    }

    public TabPageIndicator indicator;

    private void initView() {
//        indicator = findViewById(R.id.indicator);
        viewPager = findViewById(R.id.viewpager);

        MyViewPagerAdaptor myViewPagerAdaptor = new MyViewPagerAdaptor(fragmentManager, views);
        viewPager.setAdapter(myViewPagerAdaptor);

//        indicator.setViewPager(viewPager);
        viewPager.setCurrentItem(0);

        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setMode(SlidingMenu.LEFT);     //设置从左弹出/滑出SlidingMenu
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);   //设置占满屏幕
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);    //绑定到哪一个Activity对象
        mSlidingMenu.setMenu(R.layout.slidingmenulayout);                   //设置弹出的SlidingMenu的布局文件
        mSlidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_offset);       //设置SlidingMenu所占的偏移
        btn_left = findViewById(R.id.menu_left_btn);

    }


    //请求分类列表数据
    private void requestMenuData() {
        SQLiteDatabase sqLiteDatabase = DbManger.getInstance(this).getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from fenlei", null);
        List<Fenlei> fenleis = new ArrayList<>();
        Fenlei fenlei = null;
        while (cursor.moveToNext()) {
            String order = cursor.getString(cursor.getColumnIndex("order"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            fenlei = new Fenlei(id, name, order);
            fenleis.add(fenlei);
        }
        Constant.fenleiList = fenleis;
    }

    private void initFunc() {
        requestMenuData();
        listView = mSlidingMenu.findViewById(R.id.lv_list_weapon);
        MenuAdaptor menuAdaptor = new MenuAdaptor(Constant.fenleiList, this);
        listView.setAdapter(menuAdaptor);
        menuAdaptor.notifyDataSetChanged();

        btn_left.setOnClickListener(this);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
// 当当天条目是0的时候，设置可以在任意位置拖拽出SlidingMenu
                if (position == 0) {
                    mSlidingMenu.setTouchModeAbove(
                            SlidingMenu.TOUCHMODE_FULLSCREEN);
                } else {
                    // 当在其他位置的时候，设置不可以拖拽出来(SlidingMenu.TOUCHMODE_NONE)，或只有在边缘位置才可以拖拽出来TOUCHMODE_MARGIN
                    mSlidingMenu.setTouchModeAbove(
                            SlidingMenu.TOUCHMODE_NONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //权限请求结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length != 0 && requestCode == PermissionUtil.PERMISSION_ALL) {
            for (int i : grantResults) {
                if (i != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "你必须给予相关权限", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "请给予权限", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_left_btn:
                mSlidingMenu.toggle();
                break;
        }
    }
}
