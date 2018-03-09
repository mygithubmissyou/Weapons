package com.lzy.weapon.adaptor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;

/**
 * Created by Adminstrator on 2018/3/9.
 */

public class MyViewPagerAdaptor extends FragmentPagerAdapter {
   List<Fragment> views;

    public MyViewPagerAdaptor(FragmentManager fm, List<Fragment> views) {
        super(fm);
        this.views = views;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return views.get(position).getTag();
//    }

    @Override
    public Fragment getItem(int position) {

        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }
}
