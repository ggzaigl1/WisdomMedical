package com.hjy.wisdommedical.ui.shopping.person.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.DrugOrderBean;
import com.example.handsomelibrary.model.PdEvaluationEntity;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hedgehog.ratingbar.RatingBar;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.shopping.person.adapter.OrderAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发表评价
 * Created by Stefan on 2018/11/2 10:58.
 */

public class ShopEvaluateActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.recycler_order)
    RecyclerView recycler_order;
    private OrderAdapter mAdapter;
    private DrugOrderBean.RowsBean rowsBean;
    EditText et_content;
    TextView tv_num;
    RatingBar mRatingBar;
    int score;
    List<PdEvaluationEntity> entityList = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_shop_evaluate;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText("发表评价");
        rowsBean = (DrugOrderBean.RowsBean) getIntent().getSerializableExtra("DrugOrderBean");
        initRv();
    }

    private void initRv() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.footer_evaluate, null, false);
        et_content = inflate.findViewById(R.id.et_content);//评价内容
        tv_num = inflate.findViewById(R.id.tv_num);//限制字数
        Button bt_release = inflate.findViewById(R.id.bt_release);
        //评分
        mRatingBar = inflate.findViewById(R.id.RatingBar);
        mRatingBar.setStarEmptyDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_light));
        mRatingBar.setStarFillDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_fill));
        mRatingBar.setStarHalfDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_half));
        mRatingBar.setStar(0f);//默认分数

        mRatingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                score = (int) RatingCount;
            }
        });

        recycler_order.setLayoutManager(new LinearLayoutManager(mContext));
        recycler_order.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        mAdapter = new OrderAdapter(new ArrayList<>());
        recycler_order.setAdapter(mAdapter);
        mAdapter.addFooterView(inflate);
        if (rowsBean != null) {
            mAdapter.setNewData(rowsBean.getListOrderDetails());
        }

        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_num.setText(String.valueOf(s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //提交评价
        bt_release.setOnClickListener(v -> {
            entityList.clear();
            if (et_content.getText().toString().length() == 0) {
                T.showShort("请输入评价内容!");
            } else {
                if (rowsBean != null) {
                    for (int i = 0; i < rowsBean.getListOrderDetails().size(); i++) {
                        entityList.add(new PdEvaluationEntity(
                                /**
                                 * 订单ID
                                 */
                                (long) rowsBean.getId(),
                                /**
                                 * 商品ID
                                 */
                                (long) rowsBean.getListOrderDetails().get(i).getGoodsId(),
                                /**
                                 * 用户ID
                                 */
                                (long) SpfUtils.getSpfSaveInt(Constant.userId),
                                /**
                                 * 评价内容
                                 */
                                et_content.getText().toString(),
                                /**
                                 * 打分
                                 */
                                score));
                    }
                }
                postComment();
            }
        });

    }

    private void postComment() {
        RxHttpUtils.createApi(ApiService.class)
                .postComment(entityList)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<String>() {
                    @Override
                    protected void onSuccess(String s) {
                        T.showShort("评价成功");
                        JumpUtils.exitActivity(mContext);
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showShort(errorMsg);
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
