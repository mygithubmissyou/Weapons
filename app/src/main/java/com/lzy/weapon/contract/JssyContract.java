package com.lzy.weapon.contract;

import com.lzy.weapon.model.CustomBean;
import com.lzy.weapon.model.Jssy;

import com.lzy.weapon.view.BaseView;

import java.util.List;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public interface JssyContract {
    interface Presenter {
        void getJssyInfo();
    }

    interface View extends BaseView<Presenter> {
        void setJssyInfo(List<Jssy> jssyBeans);
        void setCustomInfo(List<CustomBean> customBeans);

        boolean isActive();

        void showLoading();

        void hideLoading();

        void showError();
    }
}
