package com.lzy.weapon.pensenter;

import com.lzy.weapon.callback.JssyCallback;
import com.lzy.weapon.contract.JssyContract;
import com.lzy.weapon.model.Jssy;
import com.lzy.weapon.view.JssyFragment;

import java.util.List;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public class JssyPresenter implements JssyContract.Presenter {
    JssyContract.View view;
    private List<Jssy> jssyBeans;
    String tablename;

    public JssyPresenter(JssyContract.View view, String name) {
        this.view = view;
        this.tablename = name;
    }

    @Override
    public void getJssyInfo(int pagenum,int pagesize) {

        //执行任务
        JssyCallback jssyCallback = new JssyCallback(((JssyFragment) view).getContext());
        jssyBeans = jssyCallback.extcute(tablename,pagenum,pagesize);
        view.setJssyInfo(jssyBeans);
    }
}
