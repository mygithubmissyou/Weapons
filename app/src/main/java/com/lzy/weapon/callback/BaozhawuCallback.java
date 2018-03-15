package com.lzy.weapon.callback;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lzy.weapon.model.CustomBean;
import com.lzy.weapon.model.Jssy;
import com.lzy.weapon.utils.DbManger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public class BaozhawuCallback implements CustomCallback<List<CustomBean>> {

    private List<CustomBean> jssyBeans;
    Context activity;

    public BaozhawuCallback(Context activity) {
        this.activity = activity;
    }

    private BaozhawuCallback() {

    }

    public static BaozhawuCallback getInstance() {
        return JssyHolder.JSSY_CALLBACK;
    }

    private static class JssyHolder {
        public static final BaozhawuCallback JSSY_CALLBACK = new BaozhawuCallback();
    }

    @Override
    public List<CustomBean> extcute(String tablename, int pagenum, int pagesize) {
//        jssyBeans = DataSupport
//                .where("id>0").limit(10)
//                .find(Jssy.class);
//        jssyBeans=DataSupport.findAll(Jssy.class,1,2,3,4,5);
        //获取军事常识数据列表
        requestJssyData(tablename, pagenum, pagesize);

        return jssyBeans;
    }


    private void requestJssyData(String tablename, int pagenum, int pagesize) {
        SQLiteDatabase sqLiteDatabase = DbManger.getInstance(activity).getWritableDatabase();
        Cursor cursor = null;
        if (pagenum == 1) {
            cursor = sqLiteDatabase.rawQuery("select * from " + tablename + " LIMIT " + pagesize
                    , null);
        } else {
            cursor = sqLiteDatabase.rawQuery("select * from " + tablename + " LIMIT " +(pagesize * (pagenum - 1)+1)+ " OFFSET " + pagesize
                    , null);
        }

        CustomBean jssy = null;
        jssyBeans = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                String img = "";
                if (cursor.getString(cursor.getColumnIndex("img")) != null
                        && !cursor.getString(cursor.getColumnIndex("img")).equals("")) {
                    String temp = cursor.getString(cursor.getColumnIndex("img"));
                    img = temp.substring(temp.indexOf("src") + 5
                            , temp.indexOf("alt") - 2);
                }
                String countrypic = "";
                if (cursor.getString(cursor.getColumnIndex("countrypic")) != null
                        && !cursor.getString(cursor.getColumnIndex("countrypic")).equals("")) {
                    String temp = cursor.getString(cursor.getColumnIndex("countrypic"));
                    countrypic = temp.substring(temp.indexOf("src") + 5
                            , temp.indexOf("alt") - 2);
                }
                String title = cursor.getString(cursor.getColumnIndex("titles"));
                String desc = cursor.getString(cursor.getColumnIndex("descs"));
                String country = cursor.getString(cursor.getColumnIndex("country"));
                String pingfen = cursor.getString(cursor.getColumnIndex("pingfen"));
                String updatetime = cursor.getString(cursor.getColumnIndex("updatetime"));

                String type = cursor.getString(cursor.getColumnIndex("type"));
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                jssy = new CustomBean(country, countrypic, desc, id, img, pingfen, title, type, updatetime);
                jssyBeans.add(jssy);
            }
        } catch (Exception e) {

        }

        cursor.close();
        sqLiteDatabase.close();
    }

}
