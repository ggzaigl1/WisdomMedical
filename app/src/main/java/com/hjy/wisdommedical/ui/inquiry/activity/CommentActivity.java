package com.hjy.wisdommedical.ui.inquiry.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.ListConsultBean;
import com.example.handsomelibrary.model.ListNoPageBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.hedgehog.ratingbar.RatingBar;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.inquiry.adapter.CommentAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 发表评价
 * Created by Stefan on 2018/8/8 11:36.
 */

public class CommentActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_docName)
    TextView tv_docName;
    @BindView(R.id.tv_titleName)
    TextView tv_titleName;
    @BindView(R.id.tv_hospitalName)
    TextView tv_hospitalName;
    @BindView(R.id.tv_departmentName)
    TextView tv_departmentName;
    @BindView(R.id.RatingBar)
    RatingBar mRatingBar;
    @BindView(R.id.et_feedback)
    EditText et_feedback;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_inputNum)
    TextView tv_inputNum;

    private ListConsultBean bean;
    CommentAdapter mAdapter;
    float score;
    ArrayList<String> mStrings = new ArrayList<>();


    @Override
    protected int getContentView() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setDepartment();
        init();
        tv_title.setText(getString(R.string.published_comment));
        mRatingBar.setStarEmptyDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_light));
        mRatingBar.setStarFillDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_fill));
        mRatingBar.setStarHalfDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_half));
        mRatingBar.setStar(0f);//分数
        mRatingBar.setOnRatingChangeListener(RatingCount -> score = RatingCount);
        et_feedback.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
        et_feedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_inputNum.setText(et_feedback.getText().toString().length() + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Bundle bundle = getIntent().getExtras();
        bean = (ListConsultBean) (bundle != null ? bundle.getSerializable("bean") : null);
        if (null != bean) {
            tv_docName.setText(bean.getAppDocEntityVO().getDocName());
            tv_titleName.setText(bean.getAppDocEntityVO().getTitleName());
            tv_hospitalName.setText(bean.getAppDocEntityVO().getHospicalName());
            tv_departmentName.setText(bean.getAppDocEntityVO().getDepartmentName());
        }
    }

    /**
     * 疾病名称条目
     */
    private void init() {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);//义项目在副轴轴上如何对齐
        layoutManager.setJustifyContent(JustifyContent.SPACE_AROUND); //justifyContent属性定义了项目在主轴上的对齐方式
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new CommentAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        //获取评价标签列表 选中状态
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ListNoPageBean listNoPageBean = mAdapter.getData().get(position);
                if (!listNoPageBean.isIselector()) { //未选择状态
                    listNoPageBean.setIselector(true);
                    String tagName = mAdapter.getData().get(position).getTagName();//获得数据源
                    if (!mStrings.contains(tagName)) { //增加到集合中
                        mStrings.add(tagName);
                    }
                } else {
                    listNoPageBean.setIselector(false);
                    String tagName = mAdapter.getData().get(position).getTagName();
                    if (mStrings.contains(tagName)) {
                        mStrings.remove(tagName);
                    }
                }
                mAdapter.notifyItemChanged(position);
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.bt_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.bt_comment:
                if (bean != null) {
                    if (et_feedback.getText().toString().equals("")) {
                        T.showShort(getString(R.string.published_comment_context));
                    } else {
                        getSaveToApp();
                    }
                }
                break;
        }
    }

    private void getSaveToApp() {
        StringBuilder stringBuffer = new StringBuilder();
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> prams = new ArrayMap<>();
        prams.put("consultId", bean.getId());
        prams.put("docNo", bean.getAppDocEntityVO().getDocNo());
        prams.put("userId", SpfUtils.getSpfSaveInt(Constant.userId));
        prams.put("serviceTypeId", bean.getServiceId());
        prams.put("consultDisease", bean.getDiseaseName());
        prams.put("sorce", score);
        prams.put("evaluationContent", et_feedback.getText().toString());
        for (int i = 0; i < mStrings.size(); i++) {
            if (i == mStrings.size() - 1) {
                stringBuffer.append(mStrings.get(i));
            } else {
                stringBuffer.append(mStrings.get(i) + ",");
            }
        }
        prams.put("evaluationTags", stringBuffer.toString());

        RxHttpUtils.createApi(ApiService.class)
                .saveToApp(prams)
                .throttleFirst(2, TimeUnit.SECONDS)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<String>() {
                    @Override
                    protected void onSuccess(String s) {
                        mKProgressHUD.dismiss();
                        T.showShort(getString(R.string.comment_succeed));
                        new Handler().postDelayed(() -> {
                            mContext.finish();
                        }, 1000);
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showShort(getString(R.string.comment_failure));
                    }
                });
    }

    /**
     * 去评价（获取评价标签列表）
     */
    private void setDepartment() {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .listNoPage()
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<List<ListNoPageBean>>() {
                    @Override
                    protected void onSuccess(List<ListNoPageBean> listNoPageBeans) {
                        if (null != listNoPageBeans) {
                            mAdapter.setNewData(listNoPageBeans);
                            mKProgressHUD.dismiss();
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });
    }
}
