package com.hjy.wisdommedical.ui.shopping.classify;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.ListClassBean;
import com.example.handsomelibrary.model.listSnClassBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.shopping.classify.adapter.ClassifyLeftAdapter;
import com.hjy.wisdommedical.ui.shopping.classify.adapter.ClassifyRightAdapter;
import com.hjy.wisdommedical.ui.shopping.home.GoodsListActivity;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 分类精选
 * Created by Stefan on 2018/10/25 10:30.
 */
public class ShoppingClassifyFragment extends BaseFragment {

    public static final String TAG = "ShoppingClassify";

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rv_leftList)
    RecyclerView rv_leftList;
    @BindView(R.id.rv_rightList)
    RecyclerView rv_rightList;
    @BindView(R.id.Ll_hand)
    LinearLayout Ll_hand;

    public int leftPos = 0;
    //  1,创建二维数组语句：int[][] array = new int[3][3];
    //  2,直接创建二维数组并赋值语句：int[][] array ={{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}} ;

    ClassifyLeftAdapter leftAdapter;
    ClassifyRightAdapter rightAdapter;

    public static ShoppingClassifyFragment newInstance(String parames) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, parames);
        ShoppingClassifyFragment fragment = new ShoppingClassifyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shopping_classify;
    }

    @Override
    protected void initData() {
        tv_title.setText("分类");
        initRvView();
        getListClass();
    }

    private void initRvView() {
        rv_leftList.setLayoutManager(new LinearLayoutManager(mContext));
        rv_rightList.setLayoutManager(new LinearLayoutManager(mContext));
        leftAdapter = new ClassifyLeftAdapter(new ArrayList<>());
        rv_leftList.setAdapter(leftAdapter);
        rightAdapter = new ClassifyRightAdapter(new ArrayList<>());
        rv_rightList.setAdapter(rightAdapter);
        rightAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.activity_advice_error, Ll_hand, false));
        leftAdapter.setOnItemChildClickListener((adapter, view1, position) -> {
            switch (view1.getId()) {
                case R.id.rb_left:
                    getListSnClass(String.valueOf(leftAdapter.getData().get(position).getId()));
                    leftPos = position;
                    leftAdapter.setCheckPosition(position);
                    leftAdapter.notifyDataSetChanged();
                    break;
            }
        });

        rightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("pdCf", leftAdapter.getData().get(leftPos).getId());//父类id
                bundle.putInt("pdSubCf", rightAdapter.getData().get(position).getId());//子类id
                JumpUtils.jump(mContext, GoodsListActivity.class, bundle);
            }
        });
    }

    //获取分类精选列表
    public void getListClass() {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .getListClass()
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<ListClassBean>() {
                    @Override
                    protected void onSuccess(ListClassBean listClassBean) {
                        if (listClassBean != null) {
                            mKProgressHUD.dismiss();
                            leftAdapter.setNewData(listClassBean.getListPtClass());
                            getListSnClass(String.valueOf(leftAdapter.getData().get(0).getId()));
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });

    }

    private void getListSnClass(String ptId) {
        RxHttpUtils.createApi(ApiService.class)
                .getListSnClass(ptId)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<List<listSnClassBean>>() {
                    @Override
                    protected void onSuccess(List<listSnClassBean> listSnClassBean) {
                        if (listSnClassBean != null) {
                            rightAdapter.setNewData(listSnClassBean);
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {

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
