package com.hjy.wisdommedical.ui.shopping.home;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.BaseListBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.view.popupwindow.CommonPopupWindow;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.shopping.home.adapter.GoodsListAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 商品列表
 * Created by Stefan on 2018/10/18 11:03.
 */

public class GoodsListActivity extends BaseActivity {

    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.tv_orderByPrice)
    TextView tv_orderByPrice;
    @BindView(R.id.tv_orderBySale)
    TextView tv_orderBySale;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.rv_goodList)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.ll_tv_dept)
    LinearLayout ll_tv_dept;
    @BindView(R.id.Ll_health)
    LinearLayout Ll_health;

    GoodsListAdapter mAdapter;
    int mPageNo = 1;
    private PopupWindow mPopupWindow;
    private int mPdCf;//父类id
    private int mPdSubCf;//子类id

    @Override
    protected int getContentView() {
        return R.layout.activity_goods_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPdCf = getIntent().getIntExtra("pdCf", -1);
        mPdSubCf = getIntent().getIntExtra("pdSubCf", -1);
        setRecycler();
        getConfirmOrder(mPageNo, "", mPdCf, mPdSubCf);
        initRefresh();
        setSelect(tv_orderBySale, getResources().getDrawable(R.drawable.svg_sales));
        setSelect(tv_orderByPrice, getResources().getDrawable(R.drawable.svg_sort));
    }

    private void setRecycler() {
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

    @Override
    @OnClick({R.id.iv_back, R.id.tv_search, R.id.tv_orderBySale, R.id.tv_orderByPrice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.tv_search:
                JumpUtils.jump(mContext, SearchActivity.class, null);
                break;
            //销量
            case R.id.tv_orderBySale:
                setSelect(tv_orderBySale, getResources().getDrawable(R.drawable.svg_sales_select));
                setSelect(tv_orderByPrice, getResources().getDrawable(R.drawable.svg_sort));
                tv_orderBySale.setTextColor(ContextCompat.getColor(mContext, R.color.statusBarColor));
                tv_orderByPrice.setTextColor(ContextCompat.getColor(mContext, R.color.txtSuperColor));
                getConfirmOrder(mPageNo, "", mPdCf, mPdSubCf);
                break;
            //价格
            case R.id.tv_orderByPrice:
                showPopupWindow();
                break;
        }
    }

    /**
     * 颜色选中
     *
     * @param textView
     */
    private void setSelect(TextView textView, Drawable drawableLeft) {
        textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
        textView.setCompoundDrawablePadding(4);
    }

    private void showPopupWindow() {
        mPopupWindow = new CommonPopupWindow.Builder(mContext)
                .setView(R.layout.good_popu_list)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimTop)
                .setViewOnclickListener((view1, layoutResId) -> {
                    Button bt_up = view1.findViewById(R.id.bt_up);
                    Button bt_down = view1.findViewById(R.id.bt_down);
                    Button bt_def = view1.findViewById(R.id.bt_def);
                    bt_def.setOnClickListener(v -> {
                        setSelect(tv_orderByPrice, getResources().getDrawable(R.drawable.svg_sort_select));
                        setSelect(tv_orderBySale, getResources().getDrawable(R.drawable.svg_sales));
                        tv_orderBySale.setTextColor(ContextCompat.getColor(mContext, R.color.txtSuperColor));
                        tv_orderByPrice.setTextColor(ContextCompat.getColor(mContext, R.color.statusBarColor));
                        tv_orderByPrice.setText(bt_def.getText().toString());
                        getConfirmOrder(mPageNo, "", mPdCf, mPdSubCf);
                        mPopupWindow.dismiss();
                    });
                    bt_up.setOnClickListener(v -> {
                        setSelect(tv_orderByPrice, getResources().getDrawable(R.drawable.svg_sort_select));
                        setSelect(tv_orderBySale, getResources().getDrawable(R.drawable.svg_sales));
                        tv_orderBySale.setTextColor(ContextCompat.getColor(mContext, R.color.txtSuperColor));
                        tv_orderByPrice.setTextColor(ContextCompat.getColor(mContext, R.color.statusBarColor));
                        tv_orderByPrice.setText(bt_up.getText().toString());
                        getConfirmOrder(mPageNo, "0", mPdCf, mPdSubCf);
                        mPopupWindow.dismiss();
                    });
                    bt_down.setOnClickListener(v -> {
                        setSelect(tv_orderByPrice, getResources().getDrawable(R.drawable.svg_sort_select));
                        setSelect(tv_orderBySale, getResources().getDrawable(R.drawable.svg_sales));
                        tv_orderBySale.setTextColor(ContextCompat.getColor(mContext, R.color.txtSuperColor));
                        tv_orderByPrice.setTextColor(ContextCompat.getColor(mContext, R.color.statusBarColor));
                        tv_orderByPrice.setText(bt_down.getText().toString());
                        getConfirmOrder(mPageNo, "1", mPdCf, mPdSubCf);
                        mPopupWindow.dismiss();
                    });
                }).create();

        //得到button的左上角坐标
        int[] positions = new int[2];
        ll_tv_dept.getLocationOnScreen(positions);
        mPopupWindow.showAtLocation(mContext.findViewById(android.R.id.content),
                Gravity.NO_GRAVITY,
                positions[0] + (ll_tv_dept.getWidth() - mPopupWindow.getWidth()) / 2,
                positions[1] + ll_tv_dept.getHeight());
    }

    /**
     * 商品列表
     */
    @SuppressLint("CheckResult")
    private void getConfirmOrder(int mPageNo, String orderByPrice, int pdCf, int pdSubCf) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> objectMap = new ArrayMap<>();
        objectMap.put("pageNumber", mPageNo);
        objectMap.put("pageSize", 20);
        objectMap.put("orderBySale", 1);
        objectMap.put("orderByPrice", orderByPrice);
        objectMap.put("pdCf", pdCf);
        objectMap.put("pdSubCf", pdSubCf);
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
                getConfirmOrder(mPageNo, "", mPdCf, mPdSubCf);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 1;
                getConfirmOrder(mPageNo, "", mPdCf, mPdSubCf);
                setSelect(tv_orderBySale, getResources().getDrawable(R.drawable.svg_sales));
                setSelect(tv_orderByPrice, getResources().getDrawable(R.drawable.svg_sort));
                tv_orderBySale.setTextColor(ContextCompat.getColor(mContext, R.color.txtSuperColor));
                tv_orderByPrice.setText("价格排序");
                tv_orderByPrice.setTextColor(ContextCompat.getColor(mContext, R.color.txtSuperColor));
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
}
