package com.lzy.weapon.view;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.weapon.adaptor.JssyAdaptor;
import com.lzy.weapon.R;
import com.lzy.weapon.callback.JssyCallback;
import com.lzy.weapon.contract.JssyContract;
import com.lzy.weapon.model.CustomBean;
import com.lzy.weapon.model.Jssy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public class JssyFragment extends Fragment implements JssyContract.View {
    JssyContract.Presenter mPresenter;
    //    private ProgressDialog dialog;
    private ListView mListView;
    private TextView fragment_title;
    private JssyAdaptor jssyAdaptor;
    private int page = 1;
    private int pagesize = 10;
    private List<Jssy> firstJssyBeans;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.getJssyInfo(page,pagesize);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_jssy, null);
        fragment_title = view.findViewById(R.id.fragment_title);
        fragment_title.setText("军事常识");
        mListView = view.findViewById(R.id.mListView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefreshLayout);
        loadMoreView = getLayoutInflater().inflate(R.layout.load_more, null);
        loadMoreData();
        return view;
    }

    @Override
    public void setPresenter(JssyContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setJssyInfo(List<Jssy> jssyBeans) {
        if (jssyBeans != null) {
            firstJssyBeans = jssyBeans;
            jssyAdaptor = new JssyAdaptor(jssyBeans, (JssyActivity) getActivity());
            mListView.setAdapter(jssyAdaptor);
            jssyAdaptor.notifyDataSetChanged();
        }
    }

    @Override
    public void setCustomInfo(List<CustomBean> customBeans) {

    }


    private void loadMoreData() {

        loadMoreView.setVisibility(View.GONE);
        mListView.addFooterView(loadMoreView);
        mListView.setFooterDividersEnabled(false);

        //设置进度圈的大小;(这里面只有两个值SwipeRefreshLayout.LARGE和DEFAULT，后者是默认效果)
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        //设置进度圈的背景色。这里随便给他设置了一个颜色：浅绿色
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.CYAN);
        //设置进度动画的颜色。这里面最多可以指定四个颜色，我这也是随机设置的，大家知道怎么用就可以了
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark
                , android.R.color.holo_blue_dark
                , android.R.color.holo_red_dark
                , android.R.color.widget_edittext_dark);

//        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data);
//        mListView.setAdapter(adapter);

        //设置手势监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(REFRESH, 2000);
            }
        });
        //给listview设置一个滑动的监听
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            int visibleLastIndex = 0;    //最后的可视项索引
            int visibleItemCount;        // 当前窗口可见项总数

            //当滑动状态发生改变的时候执行
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    //当不滚动的时候
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        int itemsLastIndex = jssyAdaptor.getCount() - 1;    //数据集最后一项的索引
                        int lastIndex = itemsLastIndex + 1;                //加上底部的loadMoreView项
                        //判断是否是最底部
                        //if (view.getLastVisiblePosition() == (view.getCount()) - 1) { //或者
                        if (visibleLastIndex == lastIndex) {
                            loadMoreView.setVisibility(View.VISIBLE);
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //加载网络数据
                                    Message msg = new Message();
                                    msg.what = LOADMORE;
                                    msg.arg1 = visibleLastIndex - visibleItemCount + 1;
                                    mHandler.sendMessage(msg);
                                }
                            }, 2000);
                        }
                        break;
                }
            }

            //正在滑动的时候执行
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                this.visibleItemCount = visibleItemCount;
                visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
            }
        });

    }

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private static final int REFRESH = 0x01;
    private static final int LOADMORE = 0x02;
    private View loadMoreView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case REFRESH:
                    page=1;
                    getDatas();
                    mSwipeRefreshLayout.setRefreshing(false);
                    break;
                case LOADMORE:
                    page++;
                    getDatas();
                    if (mListView.isStackFromBottom()) {
                        mListView.setStackFromBottom(false);
                    }
                    mListView.setStackFromBottom(true);
                    break;
            }
        }
    };

    private void getDatas() {
        mPresenter.getJssyInfo(page,pagesize);
        JssyCallback jssyCallback = new JssyCallback(getActivity());
        List<Jssy> jssyBeans = jssyCallback.extcute("jssy", page,pagesize);
        firstJssyBeans.addAll(jssyBeans);
        JssyAdaptor jssyAdaptor = new JssyAdaptor(firstJssyBeans, (JssyActivity) getActivity());
        mListView.setAdapter(jssyAdaptor);
        jssyAdaptor.notifyDataSetChanged();
        loadMoreView.setVisibility(View.GONE);
    }
}
