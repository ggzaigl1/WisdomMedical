package com.hjy.wisdommedical.ui.shopping.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.BaseListBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.shopping.home.adapter.GoodsListAdapter;
import com.hjy.wisdommedical.util.KeyBoardUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 搜索
 * Created by Stefan on 2018/10/19 10:32.
 */

public class SearchActivity extends BaseActivity {
    @BindView(R.id.Ll_health)
    LinearLayout Ll_health;
    @BindView(R.id.fl_tags)
    TagFlowLayout fl_tags;
    @BindView(R.id.rv_searchHistory)
    RecyclerView rv_searchHistory;
    //搜索条目
    @BindView(R.id.rv_searchHistory_heat)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_hotTitle)
    TextView tv_hotTitle;
    @BindView(R.id.tv_hisTitle)
    TextView tv_hisTitle;
    @BindView(R.id.tv_clear)
    TextView tv_clear;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.refreshLayout_heat)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.et_search_heat)
    EditText et_search_heat;

    //    SearchHistoryAdapter searchAdapter;
    GoodsListAdapter mAdapter;
    int mPageNo = 1;

    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //搜索
        et_search_heat.setOnEditorActionListener((v, actionId, event) -> {
            if (et_search_heat.getText().toString().equals("")) {
                T.showShort("搜索内容不能空");
                return true;
            } else {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    getConfirmOrder(mPageNo, et_search_heat.getText().toString());
                    mKProgressHUD.dismiss();
                    initView();
                    mRefreshLayout.setEnableRefresh(true);//启用刷新
                    mRefreshLayout.setEnableLoadMore(true);//启用加载
                    KeyBoardUtils.closeKeyBoard(mContext);
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (et_search_heat.getText().toString().equals("")) {
            mRefreshLayout.setEnableRefresh(false);//禁用刷新
            mRefreshLayout.setEnableLoadMore(false);//禁用加载
        } else {
            initRefresh();
        }
    }

    private void initView() {
        //搜索列表
        mAdapter = new GoodsListAdapter(new ArrayList<>());
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.activity_advice_error, Ll_health, false));
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            BaseListBean.RowsBean rowsBean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putInt("id", rowsBean.getId());
            bundle.putInt("priceNow", rowsBean.getPriceNow());
            bundle.putString("pdSmallPic", rowsBean.getPdSmallPic());
            bundle.putString("pdCom", rowsBean.getPdCom());
            JumpUtils.jump(mContext, GoodsDetailActivity.class, bundle);
        });
    }
    //搜索历史
//    private void setHisSearchResult(int position) {
//        String txt = tagData.get(position);
//        et_search.setText(txt);
//        et_search.setSelection(txt.length());
//        tv_hotTitle.setVisibility(View.GONE);
//        fl_tags.setVisibility(View.GONE);
//        tv_hisTitle.setVisibility(View.GONE);
//        tv_clear.setVisibility(View.GONE);
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
//        goodsAdapter.setNewData(new ArrayList<>());
//        rv_searchHistory.setLayoutManager(new GridLayoutManager(mContext, 1));
//        rv_searchHistory.setAdapter(goodsAdapter);
//        refreshLayout.autoRefresh();
//        T.showShort(txt);
//    }

//    private void setTagFlow() {
//        fl_tags.setAdapter(new TagAdapter<String>(setTagData()) {
//            @Override
//            public View getView(FlowLayout parent, int position, String s) {
//                View inflate = LayoutInflater.from(mContext).inflate(R.layout.tags_tv, fl_tags, false);
//                TextView tv_tags = inflate.findViewById(R.id.tv_tags);
//                tv_tags.setText(s);
//                return inflate;
//            }
//        });
//        fl_tags.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                setHotSearchResult(position);
//                return true;
//            }
//        });
//    }


    @OnClick({R.id.iv_back, R.id.tv_clear, R.id.iv_action_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                //JumpUtils.exitActivity(mContext);
                break;
            case R.id.iv_action_search:
                if (et_search_heat.getText().toString().equals("")) {
                    T.showShort("搜索内容不能空");
                    return;
                } else {
                    getConfirmOrder(mPageNo, et_search_heat.getText().toString());
                    mKProgressHUD.dismiss();
                    initView();
                    mRefreshLayout.setEnableRefresh(true);//启用刷新
                    mRefreshLayout.setEnableLoadMore(true);//启用加载
                    KeyBoardUtils.closeKeyBoard(mContext);
                }
                break;
//            case R.id.tv_clear:
//                searchAdapter.setNewData(new ArrayList<>());
//                break;
        }
    }

    /**
     * 分页加载数据
     */
    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPageNo += 1;
                getConfirmOrder(mPageNo, et_search_heat.getText().toString());
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 1;
                getConfirmOrder(mPageNo, et_search_heat.getText().toString());
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
        }
        if (mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadMore();
        }
    }

    /**
     * 商品列表
     */
    @SuppressLint("CheckResult")
    private void getConfirmOrder(int mPageNo, String keyword) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> objectMap = new ArrayMap<>();
        objectMap.put("keyword", keyword);
        objectMap.put("pageNumber", mPageNo);
        objectMap.put("pageSize", 20);
        RxHttpUtils.createApi(ApiService.class)
                .listProduct(objectMap)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<BaseListBean>>() {
                    @Override
                    protected void onSuccess(BaseBean<BaseListBean> listBaseBean) {
                        if (null != listBaseBean.getRows().getRows()) {
                            mKProgressHUD.dismiss();
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(listBaseBean.getRows().getRows());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.addData(listBaseBean.getRows().getRows());
//                                mAdapter.setNewData(listBaseBean.getRows().getRows());
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(listBaseBean.getRows().getRows());
                            }
                        } else {
                            if (mRefreshLayout.isRefreshing()) {
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mRefreshLayout.finishLoadMore();
                            }
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });
    }

}
