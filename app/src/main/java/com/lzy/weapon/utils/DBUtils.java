package com.lzy.weapon.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public class DBUtils {

    public static String dbPath;
//    public static String dbName="wqdq.db";
    public static void writeDBFile(String packagename,Context c){
        dbPath="data/data/"+packagename+"/databases";
        File file=new File(dbPath,Constant.DATABASE_NAME);
        File parent=file.getParentFile();
        try{
            if(!parent.exists())
                parent.mkdirs();
            InputStream fi= c.getAssets().open(Constant.DATABASE_NAME);
            FileOutputStream fo=new FileOutputStream(file);
            byte[] buffer=new byte[1024];
            int len=0;
            while((len=fi.read(buffer))!=-1){
                fo.write(buffer,0,len);
            }
            fo.flush();
            fo.close();
            fi.close();
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(c);
            preferences.edit().putBoolean("isfirst",true).commit();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}