package com.hjy.wisdommedical.ui.inquiry.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.DoctorInfoBean;
import com.example.handsomelibrary.model.FollowDocBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hedgehog.ratingbar.RatingBar;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.date.MakeAppointmentActivity;
import com.hjy.wisdommedical.ui.inquiry.adapter.DoctorInfoAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 医生简介 Activity
 * Created by Stefan on 2018/6/29 16:10.
 */
public class DoctorInfoActivity extends BaseActivity {

    @BindView(R.id.rv_evaluate)
    RecyclerView rv_evaluate;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private TextView tv_goodAt;
    private TextView tv_brief;
    private TextView tv_book_brief;
    private TextView tv_image_text;
    private TextView tv_video;
    private TextView tv_voice;
    private TextView tv_follow, tv_name, tv_titleName, tv_hospitalName, tv_departmentName, tv_diagnoseCount;
    private RatingBar mRatingBar;
    private DoctorInfoAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_doctor_info;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.doctor_info);
        getInfoToAppDoc();
        rv_evaluate.setLayoutManager(new LinearLayoutManager(mContext));
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_header_doctor_info, null);
        tv_follow = inflate.findViewById(R.id.tv_follow);
        tv_name = inflate.findViewById(R.id.tv_name);
        tv_titleName = inflate.findViewById(R.id.tv_titleName);
        tv_hospitalName = inflate.findViewById(R.id.tv_hospitalName);
        tv_departmentName = inflate.findViewById(R.id.tv_departmentName);
        tv_diagnoseCount = inflate.findViewById(R.id.tv_diagnoseCount);
        mRatingBar = inflate.findViewById(R.id.RatingBar);
        tv_goodAt = inflate.findViewById(R.id.tv_goodAt);
        tv_brief = inflate.findViewById(R.id.tv_brief);
        tv_book_brief = inflate.findViewById(R.id.tv_book_brief);
        tv_image_text = inflate.findViewById(R.id.tv_image_text);
        tv_video = inflate.findViewById(R.id.tv_video);
        tv_voice = inflate.findViewById(R.id.tv_voice);
        RelativeLayout Rl_commentCount = inflate.findViewById(R.id.Rl_commentCount);
        //关注
        tv_follow.setOnClickListener(v -> {
            String follow = tv_follow.getText().toString();
            if (follow.equals(getString(R.string.add_attention))) {
                getFollowDoc();
                tv_follow.invalidate();
            } else {
                getUnFollowDoc();
            }
        });

        Rl_commentCount.setOnClickListener(v -> JumpUtils.jump(mContext, EvaluationDoctor.class, null));

        jump();//咨询跳转

        mAdapter = new DoctorInfoAdapter(new ArrayList<>());
        rv_evaluate.setAdapter(mAdapter);
        mAdapter.addHeaderView(inflate);

    }

    /**
     * 取消关注
     */
    private void getUnFollowDoc() {
        Map<String, Object> prams = new HashMap<>();
        prams.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        prams.put("docId", SpfUtils.getSpfSaveInt(Constant.docId));
        RxHttpUtils.createApi(ApiService.class)
                .getUnFollowDoc(prams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<FollowDocBean>() {
                    @Override
                    protected void onSuccess(FollowDocBean followDocBean) {
                        tv_follow.setText(getString(R.string.add_attention));
                        T.showShort(getString(R.string.cancel_attention));
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showShort(errorMsg + getString(R.string.cancel_attention_failure));
                    }
                });
    }

    /**
     * 关注
     */
    private void getFollowDoc() {
        Map<String, Object> prams = new HashMap<>();
        prams.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        prams.put("docId", SpfUtils.getSpfSaveInt(Constant.docId));
        RxHttpUtils.createApi(ApiService.class)
                .getFollowDoc(prams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<FollowDocBean>() {
                    @Override
                    protected void onSuccess(FollowDocBean obj) {
                        tv_follow.setText(getString(R.string.followed));
                        T.showShort(R.string.attention_succeed);
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showShort(errorMsg + getString(R.string.attention_failure));
                    }
                });
    }

    /**
     * 医生详情
     */
    private void getInfoToAppDoc() {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        Map<String, Object> prams = new HashMap<>();
        prams.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        prams.put("id", SpfUtils.getSpfSaveInt(Constant.docId));
        RxHttpUtils.createApi(ApiService.class)
                .getInfoToAppDoc(prams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<DoctorInfoBean>>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    protected void onSuccess(BaseBean<DoctorInfoBean> doctorInfoBeanBaseBean) {
                        if (doctorInfoBeanBaseBean != null) {
                            if (doctorInfoBeanBaseBean.getRows() != null) {
                                mAdapter.setNewData(doctorInfoBeanBaseBean.getRows().getListEvaluationCusDoc());
                                tv_book_brief.setText(doctorInfoBeanBaseBean.getRows().getSpecialty());//医生介绍
                                tv_name.setText(doctorInfoBeanBaseBean.getRows().getDocName());//医生姓名
                                tv_diagnoseCount.setText(doctorInfoBeanBaseBean.getRows().getDiagnoseCount() + getString(R.string.patients));//患者数量
                                tv_titleName.setText(doctorInfoBeanBaseBean.getRows().getTitleName());//职称
                                tv_hospitalName.setText(doctorInfoBeanBaseBean.getRows().getHospicalName());//医院名字
                                tv_departmentName.setText(doctorInfoBeanBaseBean.getRows().getDepartmentName());//科室名字

                                mRatingBar.setStarEmptyDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_light));
                                mRatingBar.setStarFillDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_fill));
                                mRatingBar.setStarHalfDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_half));
                                mRatingBar.setStar((float) doctorInfoBeanBaseBean.getRows().getScore());//分数
                                tv_goodAt.setOnClickListener(v -> {
                                    tv_goodAt.setBackgroundResource(R.drawable.shape_comm_bg_c);
                                    tv_goodAt.setTextColor(getResources().getColor(R.color.white));
                                    tv_brief.setBackgroundResource(R.drawable.select_no_bg);
                                    tv_brief.setTextColor(getResources().getColor(R.color.Unselected));
                                    tv_book_brief.setText(doctorInfoBeanBaseBean.getRows().getSpecialty());
                                });
                                tv_brief.setOnClickListener(v -> {
                                    tv_goodAt.setBackgroundResource(R.drawable.select_no_bg);
                                    tv_goodAt.setTextColor(getResources().getColor(R.color.Unselected));
                                    tv_brief.setBackgroundResource(R.drawable.shape_comm_bg_c);
                                    tv_brief.setTextColor(getResources().getColor(R.color.white));
                                    tv_book_brief.setText(doctorInfoBeanBaseBean.getRows().getDocInfo());
                                });

                                if (doctorInfoBeanBaseBean.getRows().getIsFollow() == 1) {
                                    tv_follow.setText(getString(R.string.followed));
                                } else {
                                    tv_follow.setText(getString(R.string.add_attention));
                                }
                            }
                            mKProgressHUD.dismiss();
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });
    }


    private void jump() {
        /**
         * 图文咨询
         */
        tv_image_text.setOnClickListener(v -> JumpUtils.jump(mContext, ImageTextActivity.class, null));
        /**
         * 视频咨询
         */
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.VIDEO, 1);
        tv_video.setOnClickListener(v -> JumpUtils.jump(mContext, MakeAppointmentActivity.class, bundle));
        /**
         * 语音咨询
         */
        Bundle bundle1 = new Bundle();
        bundle1.putInt(Constant.VOICE, 2);
        tv_voice.setOnClickListener(v -> JumpUtils.jump(mContext, MakeAppointmentActivity.class, bundle1));

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
