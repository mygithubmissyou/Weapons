package com.lzy.weapon.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/5.
 */

public class PermissionUtil {
    public static int PERMISSION_ALL = 1;

    public static void requestAllPermissions(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查gps是否开启
//            LocationManager locationmanager = (LocationManager) activity.getSystemService(LOCATION_SERVICE);
//            if (!locationmanager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
//
//                Toast.makeText(activity, "请打开网络定位", Toast.LENGTH_SHORT).show();
//            }
//            if (!locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER))
//                Toast.makeText(activity, "请打开GPS定位", Toast.LENGTH_SHORT).show();

            List<String> permissionlist = new ArrayList<>();
//            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                permissionlist.add(Manifest.permission.ACCESS_COARSE_LOCATION);
//            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                permissionlist.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionlist.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
//            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//                permissionlist.add(Manifest.permission.RECORD_AUDIO);
//            }
//            if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_SETTINGS) != PackageManager.PERMISSION_GRANTED) {
//                permissionlist.add(Manifest.permission.WRITE_SETTINGS);
//            }
            if (!permissionlist.isEmpty()) {
                String[] permissionArray = permissionlist.toArray(new String[permissionlist.size()]);
                ActivityCompat.requestPermissions(activity, permissionArray, PERMISSION_ALL);
            }
        }
    }
}
