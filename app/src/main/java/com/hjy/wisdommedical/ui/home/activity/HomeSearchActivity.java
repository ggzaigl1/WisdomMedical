package com.hjy.wisdommedical.ui.home.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.ListToAppBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.L;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.date.MakeAppointmentActivity;
import com.hjy.wisdommedical.ui.home.adapter.HomeSearchAdapter;
import com.hjy.wisdommedical.ui.inquiry.activity.DoctorInfoActivity;
import com.hjy.wisdommedical.ui.inquiry.activity.ImageTextActivity;
import com.hjy.wisdommedical.util.KeyBoardUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/9/1 0001.
 * 首页搜索
 */
public class HomeSearchActivity extends BaseActivity {

    @BindView(R.id.rv_home_search)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.Ll_search)
    LinearLayout Ll_search;
    @BindView(R.id.edit_search)
    EditText edit_search;

    HomeSearchAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_home_search;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.home_search);
        initView();

        edit_search.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                // 先隐藏键盘
                String mSearch = edit_search.getText().toString().trim();
                setListToApp(mSearch, 100, 100);
                KeyBoardUtils.closeKeyBoard(mContext);
                return true;
            }
            return false;
        });
    }


    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new HomeSearchAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_advice_error, Ll_search, false);
        mAdapter.setEmptyView(view);
        mAdapter.setOnItemClickListener((adapter, view12, position) -> {
            ListToAppBean.RowsBean rowsBean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            SpfUtils.saveIntToSpf(Constant.docId, rowsBean.getId());
            SpfUtils.saveStrToSpf(Constant.docNo, rowsBean.getDocNo());
            JumpUtils.jump(mContext, DoctorInfoActivity.class, bundle);
        });
        mAdapter.setOnItemChildClickListener((adapter, view1, position) -> {
            switch (view1.getId()) {
                case R.id.tv_it:
                    JumpUtils.jump(mContext, ImageTextActivity.class, null);
                    break;
                case R.id.tv_vd:
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constant.VIDEO, 1);
                    JumpUtils.jump(mContext, MakeAppointmentActivity.class, bundle);
                    break;
            }
        });
    }

    /**
     * 医生条目接口
     *
     * @param keyword
     * @param departmentId
     * @param sortId
     */
    private void setListToApp(String keyword, int departmentId, int sortId) {
        Map<String, Object> prams = new HashMap<>();
        if (departmentId != 100) {
            prams.put("departmentId", departmentId);
        }
        if (departmentId != 100) {
            prams.put("sort", sortId);
        }
        prams.put("keyword", keyword);
        RxHttpUtils.createApi(ApiService.class)
                .getListToApp(prams)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<ListToAppBean>() {
                    @Override
                    protected void onSuccess(ListToAppBean bean) {
                        if (null != bean || bean.getRows().size() != 0) {
                            if (bean.getRows().size() == 0) {
                                T.showShort(R.string.error_advice_null);
                            }
                            mAdapter.setNewData(bean.getRows());
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        L.e(errorMsg);
                    }
                });
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
