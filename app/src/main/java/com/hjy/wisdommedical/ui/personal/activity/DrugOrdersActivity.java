package com.hjy.wisdommedical.ui.personal.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.JumpUtils;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.personal.adapter.DrugOrderAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by QKun on 2018/7/10.
 * 药品订单
 */

public class DrugOrdersActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ArrayList list;

    @Override
    protected int getContentView() {
        return R.layout.activity_drug_orders;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setDummy();
        mTvTitle.setText(R.string.drug_orders);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DrugOrderAdapter adapter = new DrugOrderAdapter(list);
        mRecyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.iv_back})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
        }
    }

    private void setDummy() {
        list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            list.add("");
        }
    }
}
