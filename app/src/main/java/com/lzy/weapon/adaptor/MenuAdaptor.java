package com.lzy.weapon.adaptor;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lzy.weapon.R;
import com.lzy.weapon.model.Fenlei;
import com.lzy.weapon.view.JssyActivity;

import java.util.List;

/**
 * Created by Adminstrator on 2018/3/9.
 */

public class MenuAdaptor extends BaseAdapter {
    List<Fenlei> list;
    JssyActivity activity;

    public MenuAdaptor(List<Fenlei> list, JssyActivity activity) {
        this.list = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = View.inflate(activity, R.layout.slidingmenu_item, null);
        TextView tv_title = view.findViewById(R.id.menu_item_tv);
        tv_title.setText(list.get(position).getName());
        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.mSlidingMenu.toggle();
                switch (position) {
                    case 0:
                        activity.viewPager.setCurrentItem(1);
                        break;
                    case 1:
                        activity.viewPager.setCurrentItem(3);
                        break;
                    case 2:
                        activity.viewPager.setCurrentItem(4);
                        break;
                    case 3:
                        activity.viewPager.setCurrentItem(2);
                        break;
                    case 4:
                        activity.viewPager.setCurrentItem(5);
                        break;
                    case 5:
                        activity.viewPager.setCurrentItem(6);
                        break;
                    case 6:
                        activity.viewPager.setCurrentItem(7);
                        break;
                    case 7:
                        activity.viewPager.setCurrentItem(8);
                        break;
                    case 8:
                        activity.viewPager.setCurrentItem(0);
                        break;


                }
            }
        });
        return view;
    }
}
