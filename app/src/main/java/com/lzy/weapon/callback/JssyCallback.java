package com.lzy.weapon.callback;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lzy.weapon.contract.JssyContract;
import com.lzy.weapon.model.Fenlei;
import com.lzy.weapon.model.Jssy;
import com.lzy.weapon.utils.Constant;
import com.lzy.weapon.utils.DbManger;
import com.lzy.weapon.utils.MySqliteHelper;
import com.lzy.weapon.view.JssyActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public class JssyCallback implements CustomCallback<List<Jssy>> {

    private List<Jssy> jssyBeans;
    Context activity;

    public JssyCallback(Context activity) {
        this.activity = activity;
    }

    private JssyCallback() {

    }

    public static JssyCallback getInstance() {
        return JssyHolder.JSSY_CALLBACK;
    }

    private static class JssyHolder {
        public static final JssyCallback JSSY_CALLBACK = new JssyCallback();
    }

    @Override
    public List<Jssy> extcute(String name, int pagenum, int pagesize) {
//        jssyBeans = DataSupport
//                .where("id>0").limit(10)
//                .find(Jssy.class);
//        jssyBeans=DataSupport.findAll(Jssy.class,1,2,3,4,5);
        //获取军事常识数据列表
        requestJssyData(name, pagenum, pagesize);

        return jssyBeans;
    }


    private void requestJssyData(String name, int pagenum, int pagesize) {
        SQLiteDatabase sqLiteDatabase = DbManger.getInstance(activity).getWritableDatabase();
        Cursor cursor = null;
        if (pagenum == 1) {

            cursor = sqLiteDatabase.rawQuery("select * from " + name + " LIMIT " + pagesize, null);
        } else {

            cursor = sqLiteDatabase.rawQuery("select * from " + name + " LIMIT " + (pagesize * (pagenum - 1) + 1 )+ " OFFSET " + pagesize, null);
        }
        Jssy jssy = null;
        jssyBeans = new ArrayList<>();
        while (cursor.moveToNext()) {
            String img = cursor.getString(cursor.getColumnIndex("img"));
            String title = cursor.getString(cursor.getColumnIndex("titlesy"));
            String desc = cursor.getString(cursor.getColumnIndex("descs"));
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            jssy = new Jssy(desc, id, img, title);
            jssyBeans.add(jssy);
        }
        cursor.close();
        sqLiteDatabase.close();
    }

}
