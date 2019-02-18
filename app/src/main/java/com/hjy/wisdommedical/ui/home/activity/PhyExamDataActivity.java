package com.hjy.wisdommedical.ui.home.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.InfoExaminationBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 体检数据
 * Created by Stefan on 2018/9/11 9:49.
 */

public class PhyExamDataActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.et_height)
    EditText et_height;
    @BindView(R.id.et_weight)
    EditText et_weight;
    @BindView(R.id.et_waist)
    EditText et_waist;//腰围
    @BindView(R.id.et_heartDate)
    EditText et_heartDate;//心率
    @BindView(R.id.et_highPressure)
    EditText et_highPressure;//收缩压(高压)
    @BindView(R.id.et_lowPressure)
    EditText et_lowPressure;//舒张压(低压)
    @BindView(R.id.et_bloodSugar)
    EditText et_bloodSugar;//空腹血糖(GLU)
    @BindView(R.id.et_totalCholesterol)
    EditText et_totalCholesterol;//总胆固醇(TC)
    @BindView(R.id.et_triglyceride)
    EditText et_triglyceride;//甘油三酯(TG)

    private String height = "";
    private String weight = "";
    private String waist = "";
    private String heartDate = "";
    private String highPressure = "";
    private String lowPressure = "";
    private String bloodSugar = "";
    private String totalCholesterol = "";
    private String triglyceride = "";
    private long mId = 0;

    @Override
    protected int getContentView() {
        return R.layout.activity_phy_exam_data;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.phy_exam_data);
        mId = getIntent().getIntExtra("Id", 0);
        getInfoExamination();
        getEdit();
    }

    private void getInfoExamination() {
        RxHttpUtils.createApi(ApiService.class)
                .infoExamination(mId)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<InfoExaminationBean>() {
                    @Override
                    protected void onSuccess(InfoExaminationBean infoExaminationBean) {
                        if (infoExaminationBean != null) {
                            setExamData(infoExaminationBean);
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {

                    }
                });
    }

    private void setExamData(InfoExaminationBean infoExaminationBean) {
        if (infoExaminationBean.getHeight() != null) {
            et_height.setText(infoExaminationBean.getHeight().toString());
        }
        if ((infoExaminationBean.getWeight() != null && infoExaminationBean.getWeight().equals("0.0"))) {//后台默认数据 老杜要求处理一下 不背锅！
            et_height.setText("");
        }
        if (infoExaminationBean.getWeight() != null) {
            et_weight.setText(infoExaminationBean.getWeight().toString());
        }
        if (infoExaminationBean.getWeight() != null && infoExaminationBean.getWeight().equals("0.0")) {
            et_weight.setText("");
        }
        if (infoExaminationBean.getWaistline() != null) {
            et_waist.setText(infoExaminationBean.getWaistline().toString());
        }
        if (infoExaminationBean.getHeartRate() != null) {
            et_heartDate.setText(infoExaminationBean.getHeartRate().toString());
        }
        if (infoExaminationBean.getSystolicPressure() != null) {
            et_highPressure.setText(infoExaminationBean.getSystolicPressure().toString());
        }
        if (infoExaminationBean.getDiastolicPressure() != null) {
            et_lowPressure.setText(infoExaminationBean.getDiastolicPressure().toString());
        }
        if (infoExaminationBean.getFastingBloodGlucose() != null) {
            et_bloodSugar.setText(infoExaminationBean.getFastingBloodGlucose().toString());
        }
        if (infoExaminationBean.getCholesterol() != null) {
            et_totalCholesterol.setText(infoExaminationBean.getCholesterol().toString());
        }
        if (infoExaminationBean.getTriglyceride() != null) {
            et_triglyceride.setText(infoExaminationBean.getTriglyceride().toString());
        }
    }

    private void getEdit() {
        et_height.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                height = et_height.getText().toString();
                et_height.setSelection(height.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_weight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                weight = et_weight.getText().toString();
                et_weight.setSelection(weight.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_waist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                waist = et_waist.getText().toString();
                et_waist.setSelection(waist.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_heartDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                heartDate = et_heartDate.getText().toString();
                et_heartDate.setSelection(heartDate.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_highPressure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                highPressure = et_highPressure.getText().toString();
                et_highPressure.setSelection(highPressure.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_lowPressure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lowPressure = et_lowPressure.getText().toString();
                et_lowPressure.setSelection(lowPressure.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_bloodSugar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bloodSugar = et_bloodSugar.getText().toString();
                et_bloodSugar.setSelection(bloodSugar.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_totalCholesterol.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                totalCholesterol = et_totalCholesterol.getText().toString();
                et_totalCholesterol.setSelection(totalCholesterol.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        et_triglyceride.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                triglyceride = et_triglyceride.getText().toString();
                et_triglyceride.setSelection(triglyceride.length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.bt_save, R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_save:
                updateExamination();
                break;
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
        }
    }

    private void updateExamination() {
        mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> params = new ArrayMap<>();
        params.put("height", height);
        params.put("weight", weight);
        params.put("waistline", waist);
        params.put("heartRate", heartDate);
        params.put("systolicPressure", highPressure);
        params.put("diastolicPressure", lowPressure);
        params.put("fastingBloodGlucose", bloodSugar);
        params.put("cholesterol", totalCholesterol);
        params.put("triglyceride", triglyceride);
        params.put("visitMemberId", mId);
        RxHttpUtils.createApi(ApiService.class)
                .updateExamination(params)
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
}
