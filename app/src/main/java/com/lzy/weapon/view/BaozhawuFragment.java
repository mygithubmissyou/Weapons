package com.lzy.weapon.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.weapon.R;
import com.lzy.weapon.adaptor.CustomAdaptor;
import com.lzy.weapon.adaptor.JssyAdaptor;
import com.lzy.weapon.contract.JssyContract;
import com.lzy.weapon.model.CustomBean;
import com.lzy.weapon.model.Jssy;

import java.util.List;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public class BaozhawuFragment extends Fragment implements JssyContract.View {
    JssyContract.Presenter mPresenter;
    private ProgressDialog dialog;
    private GridView listView;
    private TextView fragment_title;
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("正在获取数据");
        mPresenter.getJssyInfo();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dialog.dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_jssy, null);
        listView = view.findViewById(R.id.lv_jssy);
        fragment_title = view.findViewById(R.id.fragment_title);
        fragment_title.setText(title);
        return view;
    }

    @Override
    public void setPresenter(JssyContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setJssyInfo(List<Jssy> jssyBeans) {

    }

    @Override
    public void setCustomInfo(List<CustomBean> customBeans) {
        if (customBeans != null) {
            CustomAdaptor jssyAdaptor = new CustomAdaptor(customBeans, (JssyActivity) getActivity());
            listView.setAdapter(jssyAdaptor);
            jssyAdaptor.notifyDataSetChanged();
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showLoading() {
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity(), "出现错误,请稍后重试!", Toast.LENGTH_SHORT).show();
    }
}
