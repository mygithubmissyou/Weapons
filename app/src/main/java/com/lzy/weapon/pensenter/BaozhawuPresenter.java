package com.lzy.weapon.pensenter;

import com.lzy.weapon.callback.BaozhawuCallback;
import com.lzy.weapon.callback.JssyCallback;
import com.lzy.weapon.contract.JssyContract;
import com.lzy.weapon.model.CustomBean;
import com.lzy.weapon.model.Jssy;
import com.lzy.weapon.view.BaozhawuFragment;
import com.lzy.weapon.view.JssyFragment;

import java.util.List;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public class BaozhawuPresenter implements JssyContract.Presenter {
    JssyContract.View view;
    private List<CustomBean> jssyBeans;
    String tablename;

    public BaozhawuPresenter(JssyContract.View view,String tablename) {
        this.view = view;
        this.tablename=tablename;
    }


    @Override
    public void getJssyInfo() {
        if (view.isActive()) {
            view.showLoading();
        }
        //执行任务
//        JssyCallback jssyCallback = JssyCallback.getInstance();
        BaozhawuCallback jssyCallback =new BaozhawuCallback(((BaozhawuFragment)view).getContext());
        jssyBeans = jssyCallback.extcute(tablename);
        view.setCustomInfo(jssyBeans);
        if (view.isActive()) {
            view.hideLoading();
        }
    }
}
