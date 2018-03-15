package com.lzy.weapon.callback;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public interface CustomCallback<T> {
    T extcute(String tablename,int pagenum,int pagesize);

//    void onFailed();
//
//    void onStart();
//
//    void onFinish();
}
