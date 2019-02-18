package com.hjy.wisdommedical.ui.inquiry.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.healthManagementInfoBean;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.google.gson.Gson;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.entity.JsonBean;
import com.hjy.wisdommedical.net.RetrofitManager;
import com.hjy.wisdommedical.util.GetJsonDataUtil;
import com.hjy.wisdommedical.widget.BaseWheelView;
import com.hjy.wisdommedical.widget.dialog.BaseNiceDialog;
import com.hjy.wisdommedical.widget.dialog.NiceDialog;
import com.hjy.wisdommedical.widget.dialog.ViewConvertListener;
import com.hjy.wisdommedical.widget.dialog.ViewHolder;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/7/3 0003.
 * 增加就诊人
 */

public class AddUseActivity extends BaseActivity {

    private final String[] No = new String[]{"身份证", "护照", "驾驶证"};
    private final String[] SEX = new String[]{"男", "女"};
    private final String[] isMarried = new String[]{"未婚", "已婚"};
    private final String[] RELATIONSHIP = new String[]{"配偶", "本人", "子女", "父母", "其他"};

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private boolean isLoaded = false;
    private TimePickerView pvTime;
    private String mTrim;

    @BindView(R.id.tv_add_user_type)
    TextView mAdd_User_Type;
    @BindView(R.id.tv_sex)
    TextView mAdd_Sex;
    @BindView(R.id.tv_relationship)
    TextView mAdd_Relationship;
    @BindView(R.id.tv_isMarried)
    TextView tv_isMarried;
    //证件号码控件
    @BindView(R.id.tv_add_user_no)
    TextView mTextView_No;
    //姓名控件
    @BindView(R.id.tv_add_user_name)
    TextView mTextView_Name;
    //手机号码控件
    @BindView(R.id.tv_add_user_phone)
    TextView mTextView_Phone;
    @BindView(R.id.tv_address)
    TextView mTextView_Address;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_Date)
    TextView mTextView_Date;
    @BindView(R.id.tv_city)
    TextView mTextView_city;
    @BindView(R.id.cb_check)
    CheckBox mCheckBox;
    private String mProvinceStr;
    private String mCityStr;
    private String mDistrictStr;
    private int isDefault = 0;
    private int mType;
    private int mId; //家庭成员id

    @Override
    protected int getContentView() {
        return R.layout.activity_add_user;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initTimePicker();

        mId = getIntent().getExtras().getInt("id", 0);
        mType = getIntent().getExtras().getInt("type", 0);
        if (mType == 1) {
            tv_title.setText(R.string.update_patient);
            healthManagementInfo(mId);
        } else {
            tv_title.setText(R.string.add_patient);
        }


        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isDefault = 1;
                } else {
                    isDefault = 0;
                }
            }
        });
    }

    /**
     * 回显就诊人数据
     *
     * @param id
     */
    private void healthManagementInfo(int id) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RetrofitManager.createApi(ApiService.class)
                .healthManagementInfo(id)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<healthManagementInfoBean>() {
                    @Override
                    protected void onSuccess(healthManagementInfoBean bean) {
                        mKProgressHUD.dismiss();
                        mTextView_Name.setText(bean.getMemberName());
                        if (bean.getIdType() == 0) {
                            mAdd_User_Type.setText("身份证");
                        } else if (bean.getIdType() == 1) {
                            mAdd_User_Type.setText("护照");
                        } else if (bean.getIdType() == 2) {
                            mAdd_User_Type.setText("驾驶证");
                        }
                        mTextView_No.setText(bean.getIdNumber());

                        if (bean.getGender() == 0) {
                            mAdd_Sex.setText("女");
                        } else if (bean.getGender() == 1) {
                            mAdd_Sex.setText("男");
                        }

                        mTextView_Date.setText(bean.getBirthday());
                        mTextView_Phone.setText(bean.getMobile());

                        if (bean.getIsMarried() == 0) {
                            tv_isMarried.setText("已婚");
                        } else if (bean.getIsMarried() == 1) {
                            tv_isMarried.setText("未婚");
                        }

                        if (bean.getRelation() == 0) {
                            mAdd_Relationship.setText("本人");
                        } else if (bean.getRelation() == 1) {
                            mAdd_Relationship.setText("配偶");
                        } else if (bean.getRelation() == 2) {
                            mAdd_Relationship.setText("子女");
                        } else if (bean.getRelation() == 3) {
                            mAdd_Relationship.setText("父母");
                        } else if (bean.getRelation() == 4) {
                            mAdd_Relationship.setText("其他");
                        }
                        mTextView_city.setText(bean.getProvince() + bean.getCity() + bean.getDistrict());
                        mTextView_Address.setText(bean.getStreet());


//                        mProvinceStr = options1Items.get(options1).getPickerViewText();
//                        mCityStr = options2Items.get(options1).get(options2);
//                        mDistrictStr = options3Items.get(options1).get(options2).get(options3);
                        mProvinceStr = bean.getProvince();
                        mCityStr = bean.getCity();
                        mDistrictStr = bean.getDistrict();

                        if (bean.getIsDefault() == 0) {
                            mCheckBox.setChecked(false);
                        } else {
                            mCheckBox.setChecked(true);
                        }

                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Constant.PERSON_NAME:
                mTrim = data.getExtras().getString("trim");
                mTextView_Name.setText(mTrim);
                break;
            case Constant.CLASS_NUMBER:
                mTrim = data.getExtras().getString("trim");
                mTextView_No.setText(mTrim);
                break;
            case Constant.PHONE:
                mTrim = data.getExtras().getString("trim");
                mTextView_Phone.setText(mTrim);
                break;
            case Constant.CLASS_ADDRESS:
                mTrim = data.getExtras().getString("trim");
                mTextView_Address.setText(mTrim);
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
//                        Toast.makeText(AddUseActivity.this, "开始解析数据", Toast.LENGTH_SHORT).show();
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
                case MSG_LOAD_SUCCESS:
//                    Toast.makeText(AddUseActivity.this, "解析成功", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;
                case MSG_LOAD_FAILED:
                    Toast.makeText(AddUseActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    @OnClick({R.id.iv_back, R.id.bt_add_user, R.id.Ll_type_no, R.id.Ll_sex, R.id.Ll_isMarried, R.id.Ll_relationship, R.id.Ll_date, R.id.Ll_city
            , R.id.Ll_phone, R.id.Ll_user, R.id.Ll_No, R.id.Rl_location})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            //证件选择
            case R.id.Ll_type_no:
                GetPapersNo();
                break;
            //性别
            case R.id.Ll_sex:
                GetSex();
                break;

            //婚史
            case R.id.Ll_isMarried:
                GetMarried();
                break;

            //关系
            case R.id.Ll_relationship:
                GetRelationship();
                break;
            //出生日期
            case R.id.Ll_date:
                GetDate();
                break;
            //所在城市
            case R.id.Ll_city:
                if (isLoaded) {
                    GetCity();
                } else {
                    mHandler.sendEmptyMessage(MSG_LOAD_DATA);
                }
                break;
            //就診人
            case R.id.Ll_user:
                GetUser(Constant.PERSON_NAME);
                break;
            //身份證號碼
            case R.id.Ll_No:
                GetUser(Constant.CLASS_NUMBER);
                break;
            //手機號碼
            case R.id.Ll_phone:
                GetUser(Constant.PHONE);
                break;
            //地址
            case R.id.Rl_location:
                GetUser(Constant.CLASS_ADDRESS);
                break;
            //確定
            case R.id.bt_add_user:
                //保存新增就诊人
                saveHealthManagement();
                break;
            default:
                break;
        }
    }

    //保存新增就诊人
    private void saveHealthManagement() {
        if (mTextView_Name.getText().equals("")) {
            T.showShort(getString(R.string.user_null));
            return;
        } else if (mAdd_User_Type.getText().equals("")) {
            T.showShort(getString(R.string.no_type_null));
            return;
        } else if (mTextView_No.getText().equals("")) {
            T.showShort(getString(R.string.no_number_null));
            return;
        } else if (mAdd_Sex.getText().equals("")) {
            T.showShort(getString(R.string.sex_null));
            return;
        } else if (mTextView_Date.getText().equals("")) {
            T.showShort(getString(R.string.date_null));
            return;
        } else if (mTextView_Phone.getText().equals("")) {
            T.showShort(getString(R.string.phone_null));
            return;
        } else if (mAdd_Relationship.getText().equals("")) {
            T.showShort(getString(R.string.relation_null));
            return;
        } else if (tv_isMarried.getText().equals("")) {
            T.showShort(getString(R.string.tv_isMarried_null));
            return;
        } else if (TextUtils.isEmpty(mProvinceStr)) {
            T.showShort(getString(R.string.city_null));
            return;
        } else if (mTextView_Address.getText().toString().trim().equals("")) {
            T.showShort(getString(R.string.detailed_address_null));
            return;
        }
        if (mType == 0) { //新增
            mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
            ArrayMap<String, Object> requestData = getRequestData();
            RetrofitManager.createApi(ApiService.class)
                    .healthManagementSave(requestData)
                    .compose(Transformer.switchSchedulers())
                    .subscribe(new CommonObserver<Object>() {
                        @Override
                        protected void onSuccess(Object o) {
                            mKProgressHUD.dismiss();
                            T.showShort("新增就诊人成功！");
                            JumpUtils.exitActivity(AddUseActivity.this);
                        }

                        @Override
                        protected void onError(String errorMsg) {
                            T.showShort(errorMsg);
                            mKProgressHUD.dismiss();
                        }
                    });
        } else {//更新
            mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
            ArrayMap<String, Object> requestData = getRequestData();
            RetrofitManager.createApi(ApiService.class)
                    .healthManagementUpdate(requestData)
                    .compose(Transformer.switchSchedulers())
                    .subscribe(new CommonObserver<Object>() {
                        @Override
                        protected void onSuccess(Object o) {
                            mKProgressHUD.dismiss();
                            T.showShort("更新就诊人成功！");
                            JumpUtils.exitActivity(AddUseActivity.this);
                        }

                        @Override
                        protected void onError(String errorMsg) {
                            T.showShort(errorMsg);
                            mKProgressHUD.dismiss();
                        }
                    });
        }

    }


    private ArrayMap<String, Object> getRequestData() {
        //只要求有cusid
        ArrayMap<String, Object> objectMap = new ArrayMap<>();
        if (mType == 0) {
            objectMap.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        } else {
            objectMap.put("id", mId);
        }

        if (!TextUtils.isEmpty(mTextView_Name.getText().toString().trim())) {
            objectMap.put("memberName", mTextView_Name.getText().toString().trim());
        }
        if (!TextUtils.isEmpty(mAdd_User_Type.getText().toString().trim())) {
            int type = 0;
            String idType = mAdd_User_Type.getText().toString().trim();
            switch (idType) {
                case "身份证":
                    type = 0;
                    break;
                case "护照":
                    type = 1;
                    break;
                case "驾驶证":
                    type = 2;
                    break;
            }
            objectMap.put("idType", type);
        }
        if (!TextUtils.isEmpty(mTextView_No.getText().toString().trim())) {
            String trim = mTextView_No.getText().toString().trim();
            objectMap.put("idNumber", trim);
        }
        if (!TextUtils.isEmpty(mAdd_Sex.getText().toString().trim())) {
            int type = 0;
            String idType = mAdd_Sex.getText().toString().trim();
            switch (idType) {
                case "女":
                    type = 0;
                    break;
                case "男":
                    type = 1;
                    break;
            }
            objectMap.put("gender", type);
        }
        if (!TextUtils.isEmpty(mTextView_Date.getText().toString().trim())) {
            String trim = mTextView_Date.getText().toString().trim();
            objectMap.put("birthday", trim);
        }
        //手机号
        if (!TextUtils.isEmpty(mTextView_Phone.getText().toString().trim())) {
            objectMap.put("mobile", mTextView_Phone.getText().toString().trim());
        }
        //婚史
        if (!TextUtils.isEmpty(tv_isMarried.getText().toString().trim())) {
            String trim = tv_isMarried.getText().toString().trim();
            int type = 0;
            switch (trim) {
                case "已婚":
                    type = 0;
                    break;
                case "未婚":
                    type = 1;
                    break;
            }
            objectMap.put("isMarried", type);
        }
        //关系
        if (!TextUtils.isEmpty(mAdd_Relationship.getText().toString().trim())) {
            int type = 0;
            String idType = mAdd_Relationship.getText().toString().trim();
            switch (idType) {
                case "本人":
                    type = 0;
                    break;
                case "配偶":
                    type = 1;
                    break;
                case "子女":
                    type = 2;
                    break;
                case "父母":
                    type = 3;
                    break;
                case "其他":
                    type = 4;
                    break;
            }
            objectMap.put("relation", type);
        }
        //所在城市
        if (!TextUtils.isEmpty(mProvinceStr)) {
            objectMap.put("province", mProvinceStr);
        }
        if (!TextUtils.isEmpty(mCityStr)) {
            objectMap.put("city", mCityStr);
        }
        if (!TextUtils.isEmpty(mDistrictStr)) {
            objectMap.put("district", mDistrictStr);
        }
        if (!TextUtils.isEmpty(mTextView_Address.getText().toString().trim())) {
            objectMap.put("street", mTextView_Address.getText().toString().trim());
        }
        // 是否是默认就诊人
        objectMap.put("isDefault", isDefault);

        return objectMap;
    }

    private void GetUser(int type) {
        if (type == Constant.PERSON_NAME) {
            Intent intent = new Intent(this, NewUserEditActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("mTextView_Name", mTextView_Name.getText().toString().trim());
            startActivityForResult(intent, 1);
        } else if (type == Constant.CLASS_NUMBER) {
            Intent intent = new Intent(this, NewUserEditActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("mTextView_No", mTextView_No.getText().toString());
            startActivityForResult(intent, 1);
        } else if (type == Constant.PHONE) {
            Intent intent = new Intent(this, NewUserEditActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("mTextView_Phone", mTextView_Phone.getText().toString());
            startActivityForResult(intent, 1);
        } else if (type == Constant.CLASS_ADDRESS) {
            Intent intent = new Intent(this, NewUserEditActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("classAddress", mTextView_Address.getText().toString());
            startActivityForResult(intent, 1);
        }
    }

    //证件选择
    private void GetPapersNo() {
        NiceDialog.init().setLayoutId(R.layout.dialog_add_user).setConvertListener(new ViewConvertListener() {
            @Override
            public void convertView(ViewHolder holder, BaseNiceDialog dialog) {
                BaseWheelView baseWheelView = holder.getView(R.id.wv_dialog_add_user);
                baseWheelView.setOffset(2);
                baseWheelView.setItems(Arrays.asList(No));
                baseWheelView.setSeletion(0);
                //关闭
                holder.setOnClickListener(R.id.iv_dialog_add_user, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //确定
                holder.setOnClickListener(R.id.bt_dialog_add_user, v -> {
                    String selectItem = baseWheelView.getSeletedItem();
                    mAdd_User_Type.setText(selectItem);
                    dialog.dismiss();
                });
            }
        }).setOutCancel(false).setShowBottom(true).setDimAmount(0.5f).show(getSupportFragmentManager());
    }

    //性别 选择
    private void GetSex() {
        NiceDialog.init().setLayoutId(R.layout.dialog_add_user).setConvertListener((ViewConvertListener) (holder, dialog) -> {
            BaseWheelView baseWheelView = holder.getView(R.id.wv_dialog_add_user);
            baseWheelView.setOffset(2);
            baseWheelView.setItems(Arrays.asList(SEX));
            baseWheelView.setSeletion(0);
            //关闭
            holder.setOnClickListener(R.id.iv_dialog_add_user, v -> dialog.dismiss());
            //确定
            holder.setOnClickListener(R.id.bt_dialog_add_user, v -> {
                String selectItem = baseWheelView.getSeletedItem();
                mAdd_Sex.setText(selectItem);
                dialog.dismiss();
            });
        }).setOutCancel(false).setShowBottom(true).setDimAmount(0.5f).show(getSupportFragmentManager());
    }

    //婚史 选择
    private void GetMarried() {
        NiceDialog.init().setLayoutId(R.layout.dialog_add_user).setConvertListener((ViewConvertListener) (holder, dialog) -> {
            BaseWheelView baseWheelView = holder.getView(R.id.wv_dialog_add_user);
            baseWheelView.setOffset(2);
            baseWheelView.setItems(Arrays.asList(isMarried));
            baseWheelView.setSeletion(0);
            //关闭
            holder.setOnClickListener(R.id.iv_dialog_add_user, v -> dialog.dismiss());
            //确定
            holder.setOnClickListener(R.id.bt_dialog_add_user, v -> {
                String selectItem = baseWheelView.getSeletedItem();
                tv_isMarried.setText(selectItem);
                dialog.dismiss();
            });
        }).setOutCancel(false).setShowBottom(true).setDimAmount(0.5f).show(getSupportFragmentManager());
    }

    //关系 选择
    private void GetRelationship() {
        NiceDialog.init().setLayoutId(R.layout.dialog_add_user).setConvertListener((ViewConvertListener) (holder, dialog) -> {
            BaseWheelView baseWheelView = holder.getView(R.id.wv_dialog_add_user);
            baseWheelView.setOffset(2);
            baseWheelView.setItems(Arrays.asList(RELATIONSHIP));
            baseWheelView.setSeletion(0);
            //关闭
            holder.setOnClickListener(R.id.iv_dialog_add_user, v -> dialog.dismiss());
            //确定
            holder.setOnClickListener(R.id.bt_dialog_add_user, v -> {
                String selectItem = baseWheelView.getSeletedItem();
                mAdd_Relationship.setText(selectItem);
                dialog.dismiss();
            });
        }).setOutCancel(false).setShowBottom(true).setDimAmount(0.5f).show(getSupportFragmentManager());
    }

    //所在城市 选择
    private void GetCity() {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String city = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                mTextView_city.setText(city);
                mProvinceStr = options1Items.get(options1).getPickerViewText();
                mCityStr = options2Items.get(options1).get(options2);
                mDistrictStr = options3Items.get(options1).get(options2).get(options3);
            }
        })
                .setTitleText(getString(R.string.city_select))
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    /*******************************出生日期*****************************************/

    //出生日期 选择
    private void GetDate() {
        if (pvTime != null) {
            pvTime.show();
        }
    }

    private void initTimePicker() {//Dialog 模式下，在底部弹出
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mTextView_Date.setText(getTime(date));
                Log.i("pvTime", "onTimeSelect");
            }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
            @Override
            public void onTimeSelectChanged(Date date) {
                Log.i("pvTime", "onTimeSelectChanged");
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isDialog(true)
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /*******************************出生日期END*****************************************/

    /*******************************城市選擇*****************************************/
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    /*******************************城市選擇END*****************************************/
}
