package com.hjy.wisdommedical.ui.shopping.person.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.JumpUtils;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.shopping.person.adapter.CheckLogisticsAdapter;
import com.hjy.wisdommedical.ui.shopping.person.adapter.LogisticsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 查看物流
 * Created by Stefan on 2018/11/7 11:14.
 */

public class CheckLogisticsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rv_Logistics)
    RecyclerView rv_Logistics;
    CheckLogisticsAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_check_logistics;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText("查看物流");
        setData();
        mAdapter = new CheckLogisticsAdapter(list);
        rv_Logistics.setLayoutManager(new LinearLayoutManager(mContext));
        rv_Logistics.setAdapter(mAdapter);
    }

    List<LogisticsBean> list;
    public void setData() {
        list = new ArrayList();
        list.add(new LogisticsBean("2018年11月7日","[武汉市]武汉中南代收点 已代收"));
        list.add(new LogisticsBean("2018年11月7日","[郑州市]郑州中转站 已发出"));
        list.add(new LogisticsBean("2018年11月7日","[郑州市]快件已到达 郑州中转站"));
        list.add(new LogisticsBean("2018年11月7日","[石家庄市] 石家庄新华区 已发出"));
        list.add(new LogisticsBean("2018年11月7日","[石家庄市]快件已到达 石家庄新华区"));
        list.add(new LogisticsBean("2018年11月7日","[北京市]快件已到达 北京大兴庞各庄"));
        list.add(new LogisticsBean("2018年11月7日","[北京市]北京中转站 已发出"));
        list.add(new LogisticsBean("2018年11月7日","[北京市]北京中转站 已发出"));
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;

        }
    }

}
