package com.lzy.weapon.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.weapon.R;
import com.lzy.weapon.utils.Constant;
import com.lzy.weapon.utils.ImageOptionUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detaile_tv_title)
    TextView detaileTvTitle;
    @BindView(R.id.detaile_iv_img)
    ImageView detaileIvImg;
    @BindView(R.id.detaile_tv_country)
    TextView detaileTvCountry;
    @BindView(R.id.detail_iv_country)
    ImageView detailIvCountry;
    @BindView(R.id.detaile_tv_pngfen)
    TextView detaileTvPngfen;
    @BindView(R.id.detaile_tv_fenlei)
    TextView detaileTvFenlei;
    @BindView(R.id.detaile_tv_updatetime)
    TextView detaileTvUpdatetime;
    @BindView(R.id.detaile_tv_desc)
    TextView detaileTvDesc;
    private Unbinder unbinder;
    @BindView(R.id.menu_back_left_btn)
    ImageButton menu_back_left_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatail);
        unbinder = ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            if (!intent.getStringExtra("flag").equals("jssy")) {
                detaileTvCountry.setText(intent.getStringExtra("country"));
                detaileTvPngfen.setText(intent.getStringExtra("pingfen"));
                detaileTvFenlei.setText(intent.getStringExtra("fenlei"));
                detaileTvUpdatetime.setText(intent.getStringExtra("updatetime"));
                ImageLoader.getInstance().displayImage(intent.getStringExtra("imgcountry"), detailIvCountry, ImageOptionUtils.getWholeOptions());

            }
            detaileTvTitle.setText(intent.getStringExtra("title"));
            detaileTvDesc.setText(intent.getStringExtra("desc"));
            ImageLoader.getInstance().displayImage(intent.getStringExtra("img"), detaileIvImg, ImageOptionUtils.getWholeOptions());

        }
    }

    @OnClick({R.id.menu_back_left_btn})
    void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.menu_back_left_btn:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
