package com.lzy.weapon.utils;

import android.content.Context;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public class DbManger {
    private static MySqliteHelper mMySqliteHelper;
    private static Context mContext;
//    public static MySqliteHelper getIntance(Context context){
//        if(mMySqliteHelper == null){
//            mMySqliteHelper = new MySqliteHelper(context);
//        }
//        return mMySqliteHelper;
//    }
    private static class HelperHolder{
        public static final MySqliteHelper HOLDER_INSTANCE=new MySqliteHelper(mContext);
    }
    public static MySqliteHelper getInstance(Context context){
        mContext=context;
        return HelperHolder.HOLDER_INSTANCE;
    }
}
