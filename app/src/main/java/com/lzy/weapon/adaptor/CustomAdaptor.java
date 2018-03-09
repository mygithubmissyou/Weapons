package com.lzy.weapon.adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.weapon.R;
import com.lzy.weapon.model.CustomBean;
import com.lzy.weapon.model.Jssy;
import com.lzy.weapon.utils.ImageOptionUtils;
import com.lzy.weapon.view.DetailActivity;
import com.lzy.weapon.view.JssyActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public class CustomAdaptor extends BaseAdapter {
    private List<CustomBean> list;
    private JssyActivity activity;
    private ImageView imageView;
    private TextView textView;

    public CustomAdaptor(List<CustomBean> list, JssyActivity activity){
        this.list=list;
        this.activity=activity;
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
        View view=((LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.fragment_jssy_item,null);
        imageView = view.findViewById(R.id.jssy_item_img);
        textView = view.findViewById(R.id.jssy_item_tv);
        textView.setText(list.get(position).getTitle());
//        DisplayImageOptions options=new DisplayImageOptions.Builder().displayer(new SimpleBitmapDisplayer())
//                .showImageOnFail(R.drawable.def).showImageOnLoading(R.drawable.def).build();

        if(list.get(position).getImg()!=null&&!list.get(position).getImg().equals("")){

            ImageLoader.getInstance().displayImage(
                    list.get(position).getImg(),imageView, ImageOptionUtils.getWholeOptions());
        }else{
            imageView.setImageResource(R.drawable.def);
        }

        LinearLayout jssy_ll=view.findViewById(R.id.jssy_ll);
        jssy_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, DetailActivity.class);
                intent.putExtra("flag","jssy");
                intent.putExtra("title",list.get(position).getTitle());

                intent.putExtra("img",list.get(position).getImg());
                intent.putExtra("country",list.get(position).getCountry());
                intent.putExtra("pingfen",list.get(position).getPingfen());
                intent.putExtra("fenlei",list.get(position).getType());
                intent.putExtra("updatetime",list.get(position).getUpdate_time());
                intent.putExtra("imgcountry",list.get(position).getCountry_pic());
                intent.putExtra("desc",list.get(position).getDesc());
                activity.startActivity(intent);
            }
        });
        return view;
    }
}
