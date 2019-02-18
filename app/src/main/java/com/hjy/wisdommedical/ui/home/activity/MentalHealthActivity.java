package com.hjy.wisdommedical.ui.home.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.MentalHealthBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.GsonUtils;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.home.adapter.MentalHealthTitleAdapter;
import com.hjy.wisdommedical.util.sticky.FlexboxStickyDecoration;
import com.hjy.wisdommedical.util.sticky.MentalHealthStickyBean;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 心理健康
 * Created by QKun on 2018/9/10.
 */
public class MentalHealthActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTextView;
    @BindView(R.id.rlv_mental_title)
    RecyclerView mRecyclerView_Title;
    MentalHealthTitleAdapter mAdapterTitle;

    @Override
    protected int getContentView() {
        return R.layout.activity_mental_health;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mTextView.setText(R.string.mental_health);
        initViewTitle();
        getUpdateHistory();
    }

    private void initViewTitle() {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.SPACE_BETWEEN);

        mRecyclerView_Title.setLayoutManager(layoutManager);
        mRecyclerView_Title.addItemDecoration(new FlexboxStickyDecoration());
        mAdapterTitle = new MentalHealthTitleAdapter(this, new ArrayList<>());
        mRecyclerView_Title.setAdapter(mAdapterTitle);
    }

    @Override
    @OnClick({R.id.iv_back, R.id.bt_modify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.bt_modify:
                updateExamination();
                break;
        }
    }

    /**
     * 获取测试题
     */
    private void getUpdateHistory() {
        mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .GetTopic(1, getIntent().getIntExtra("Id", 0))
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<MentalHealthBean>>() {
                    @Override
                    protected void onSuccess(BaseBean<MentalHealthBean> mentalHealthBeanBaseBean) {
                        getData(mentalHealthBeanBaseBean);
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });
    }


    private void updateExamination() {
        Map<String, Object> answers = new HashMap<>();

        List<MentalHealthStickyBean> data = mAdapterTitle.getmDatas();
        SparseBooleanArray mSelectedPositions = mAdapterTitle.getmSelectedPositions();
        int score = 0;
        for (int i = 0; i < mSelectedPositions.size(); i++){
            if (mSelectedPositions.valueAt(i)){
                MentalHealthStickyBean bean = data.get(mSelectedPositions.keyAt(i));
                score += bean.getScore();

                answers.put(bean.getId() + "", bean.getSmalId() + "");
            }
        }

        if (answers.size() < mAdapterTitle.getTitleCount()){
            T.showLong("请填写完整健康评估题目");
            return;
        }

        mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> params = new ArrayMap<>();
        params.put("type", 1);
        params.put("visitMemberId", getIntent().getIntExtra("Id", 0));
        params.put("score", score);
        params.put("answers", GsonUtils.mapToJsonStr(answers));
        RxHttpUtils.createApi(ApiService.class)
                .saveResult(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<Object>() {
                    @Override
                    protected void onSuccess(Object o) {
                        mKProgressHUD.dismiss();
                        T.showShort(getString(R.string.succeed));
                        JumpUtils.exitActivity(mContext);
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showShort(errorMsg);
                    }
                });
    }

    //解析获取的数据
    private void getData(BaseBean<MentalHealthBean> mentalHealthBeanBaseBean){
        int titleCount = 0;
        int smalCount = 0;
        List<MentalHealthStickyBean> data = new ArrayList<>();

        String answers = mentalHealthBeanBaseBean.getRows().getAnswers();
        Map<String, String> answerArray = null;
        if (!TextUtils.isEmpty(answers)) {
            answerArray = (Map<String, String>) GsonUtils.JSONToObject(answers, Map.class);
        }

        List<MentalHealthBean.ListQuestionBean> list = mentalHealthBeanBaseBean.getRows().getListQuestion();
        for (MentalHealthBean.ListQuestionBean listQuestionBean : list){
            //吸附view
            data.add(new MentalHealthStickyBean(listQuestionBean.getId(),
                    listQuestionBean.getType(),
                    listQuestionBean.getName()));
            titleCount++;

            List<MentalHealthBean.ListQuestionBean.ListOptionBean> listOption = listQuestionBean.getListOption();
            int i = 0;
            for (; i < listOption.size(); i++){
                MentalHealthBean.ListQuestionBean.ListOptionBean optionBean = listOption.get(i);
                //子view
                boolean isSelect = false;
                if (null != answerArray && answerArray.containsKey(listQuestionBean.getId() + "") &&
                        answerArray.get(listQuestionBean.getId() + "").equals(optionBean.getId() + "")) {
                    isSelect = true;
                }

                data.add(new MentalHealthStickyBean(listQuestionBean.getId(),
                        optionBean.getId(),
                        optionBean.getName(),
                        optionBean.getScore(),
                        isSelect));
            }

            if (i > smalCount) smalCount = i;
        }

        mAdapterTitle.setSmalCount(smalCount);
        mAdapterTitle.setTitleCount(titleCount);
        mAdapterTitle.setmDatas(data);
        mAdapterTitle.notifyDataSetChanged();
    }
}
