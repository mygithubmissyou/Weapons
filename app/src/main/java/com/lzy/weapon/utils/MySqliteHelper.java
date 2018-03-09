package com.lzy.weapon.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public class MySqliteHelper extends SQLiteOpenHelper {
    public MySqliteHelper(Context context) {
        super(context, Constant.DATABASE_NAME, null, Constant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建表结构
//        String sql = "create table "+Constant.TABLE_NAME+"("+
//                Constant.ID+" integer primary key autoincrement,"+
//                Constant.NAME+" varchar(20)," +
//                Constant.AGE+" integer)";
//        sqLiteDatabase.execSQL(sql);//执行sql语句

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
