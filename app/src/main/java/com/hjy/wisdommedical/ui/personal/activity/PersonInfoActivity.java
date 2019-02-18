package com.hjy.wisdommedical.ui.personal.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.UserInfo;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.GlideCircleTransform;
import com.example.handsomelibrary.utils.GlideRoundTransform;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.inquiry.activity.NewUserEditActivity;
import com.hjy.wisdommedical.util.UpLoadUtils;
import com.hjy.wisdommedical.widget.dialog.NiceDialog;
import com.hjy.wisdommedical.widget.dialog.ViewConvertListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;

/**
 * 个人中心 Activity
 * Created by Stefan on 2018/7/6 11:09.
 */
public class PersonInfoActivity extends BaseActivity {

    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.iv_header)
    ImageView iv_header;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_address)
    TextView tv_address;

    private String mTrim;

    @Override
    protected int getContentView() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.personInfo);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText(R.string.complete);
        getUserInfo();
    }

    /**
     * 查看个人资料
     */
    private void getUserInfo() {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .getUserInfo(SpfUtils.getSpfSaveInt(Constant.userId))
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<UserInfo>() {
                    @Override
                    protected void onSuccess(UserInfo userInfo) {
                        if (userInfo != null) {
                            mKProgressHUD.dismiss();
                            if (!TextUtils.isEmpty(userInfo.getNickname())) {
                                tv_name.setText(userInfo.getNickname());
                            }
                            if (!TextUtils.isEmpty(userInfo.getMobile())) {
                                tv_phone.setText(userInfo.getMobile());
                            }

                            if (!TextUtils.isEmpty(userInfo.getAddress())) {
                                tv_address.setText(userInfo.getAddress());
                            }
                            int type = userInfo.getSex();
                            String idType = "";
                            switch (type) {
                                case 0:
                                    idType = "女";
                                    break;
                                case 1:
                                    idType = "男";
                                    break;
                            }
                            if (!TextUtils.isEmpty(userInfo.getAddress())) {
                                tv_sex.setText(idType);
                            }
                            if (!TextUtils.isEmpty(userInfo.getPhotoUrl())) {
                                Glide.with(mContext)
                                        .load(ApiService.BASE_PIC_URL + userInfo.getPhotoUrl())
                                        .placeholder(R.mipmap.icon_doctor)
                                        .error(R.mipmap.icon_doctor)
                                        .transform(new CenterCrop(mContext), new GlideRoundTransform(mContext, 50))
                                        .crossFade(500) //标准的淡入淡出动画
                                        .into(iv_header);
                            }
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });
    }

    @OnClick({R.id.iv_back, R.id.rl_sex, R.id.rl_record,R.id.iv_header, R.id.tv_right, R.id.Ll_name, R.id.Ll_phone, R.id.Ll_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.rl_sex:
                SetSexChoose();
                break;
            case R.id.rl_record:
//               JumpUtils.jump(mContext,EleMedRecordActivity.class,null);
               T.showShort("暂无");
                break;
            case R.id.iv_header:
                SetPhotoChoose();
                break;
//            case R.id.Ll_name:
//                GetUser(Constant.NICKNAME);
//                break;
            case R.id.Ll_phone:
                GetUser(Constant.CLASS_PHONE);
                break;
            case R.id.Ll_address:
                GetUser(Constant.CLASS_ADDRESS);
                break;
            case R.id.tv_right:  //完成
                uploadImages("photo", images);
                break;
        }
    }

    /**
     * 上传图片并提交用户反馈
     *
     * @param type  图片类型	photo(头像)、feedback(反馈)、medicalRecords(病历)
     * @param lists 文件列表
     */
    @SuppressLint("CheckResult")
    private void uploadImages(String type, List<ImageItem> lists) {
        mKProgressHUD = KProgressHUD
                .create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(getString(R.string.load_in)).setCancellable(true)
                .setAnimationSpeed(2).setDimAmount(0.5f).show();

        Observable<String> observable;
        if (null != lists && lists.size() > 0) {
            observable = Observable.just(lists)
                    .observeOn(Schedulers.io())
                    .map(new Function<List<ImageItem>, List<String>>() {
                        @Override
                        public List<String> apply(List<ImageItem> imageItems) throws Exception {
                            List<String> imgPath = new ArrayList<>();
                            for (ImageItem imageItem : imageItems) {
                                imgPath.add(imageItem.getPath());
                            }
                            return imgPath;
                        }
                    }).map(new Function<List<String>, List<File>>() {
                        @Override
                        public List<File> apply(@NonNull List<String> list) throws Exception {
                            return Luban.with(mContext)
                                    .load(list)
                                    .ignoreBy(100)
//                                    .setTargetDir(Environment.getDataDirectory().getPath())
                                    .get();
                        }
                    })
                    .map(new Function<List<File>, List<MultipartBody.Part>>() {
                        @Override
                        public List<MultipartBody.Part> apply(List<File> fileList) throws Exception {
                            return UpLoadUtils.filesToMultipartBodyPart(fileList);
                        }
                    }).flatMap(new Function<List<MultipartBody.Part>, ObservableSource<String>>() {
                        @Override
                        public ObservableSource<String> apply(List<MultipartBody.Part> files) throws Exception {
                            return RxHttpUtils.createApi(ApiService.class)
                                    .uploadPostFile(RequestBody.create(MediaType.parse("multipart/form-data"), type), files)
                                    .compose(Transformer.switchSchedulers());
                        }
                    }).flatMap(new Function<String, ObservableSource<String>>() {
                        @Override
                        public ObservableSource<String> apply(String images) throws Exception {
                            return updateToApp(images);
                        }
                    });
        } else {
            observable = updateToApp("");
        }

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String objectBaseBean) throws Exception {
                if (objectBaseBean != null) {
                    T.showShort("提交成功");
                    JumpUtils.exitActivity(mContext);
                    mKProgressHUD.dismiss();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mKProgressHUD.dismiss();
                T.showShort(throwable.getMessage());
            }
        });
    }

    /**
     * 修改个人资料 被观察者
     *
     * @param imgUrls
     * @return
     */
    private Observable<String> updateToApp(String imgUrls) {
        int id = SpfUtils.getSpfSaveInt(Constant.userId);
        ArrayMap<String, Object> param = new ArrayMap<>();
        param.put("id", id);
        param.put("nickname", SpfUtils.getSpfSaveStr(Constant.username));
        if (tv_sex.getText().toString().trim().equals("女")) {
            param.put("sex", 0);
        } else {
            param.put("sex", 1);
        }
        param.put("email", "");
        param.put("mobile", tv_phone.getText().toString().trim());
        param.put("address", tv_address.getText().toString().trim());
        param.put("photoUrl", imgUrls);

        return RxHttpUtils.createApi(ApiService.class)
                .updateToApp(param)
                .compose(Transformer.switchSchedulers());
    }

    /************************************************************************/
    private void GetUser(int type) {
        String classPhone = tv_phone.getText().toString();//电话
        String classAddress = tv_address.getText().toString();//地址
        if (type == Constant.NICKNAME) {
            Intent intent = new Intent(this, NewUserEditActivity.class);
            intent.putExtra("type", type);
            startActivityForResult(intent, 1);
        } else if (type == Constant.CLASS_PHONE) {
            Intent intent = new Intent(this, NewUserEditActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("classPhone",classPhone);
            startActivityForResult(intent, 1);
        } else if (type == Constant.CLASS_ADDRESS) {
            Intent intent = new Intent(this, NewUserEditActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("classAddress",classAddress);
            startActivityForResult(intent, 1);
        }
    }

    private void SetSexChoose() {
        NiceDialog.init().setLayoutId(R.layout.dialog_choose_sex)
                .setConvertListener((ViewConvertListener) (holder, dialog) -> {
                    Button bt_photo = holder.getView(R.id.bt_photo);
                    Button bt_album = holder.getView(R.id.bt_album);
                    TextView tv_cancel = holder.getView(R.id.tv_cancel);
                    bt_photo.setText(R.string.man);
                    bt_album.setText(R.string.woman);
                    bt_photo.setOnClickListener(v -> {
                        tv_sex.setText(bt_photo.getText().toString());
                        dialog.dismiss();
                    });
                    bt_album.setOnClickListener(v -> {
                        tv_sex.setText(bt_album.getText().toString());
                        dialog.dismiss();
                    });
                    tv_cancel.setOnClickListener(v -> dialog.dismiss());

                }).setOutCancel(true).setShowBottom(true).setDimAmount(0.5f).show(getSupportFragmentManager());
    }

    private void SetPhotoChoose() {
        NiceDialog.init().setLayoutId(R.layout.dialog_choose_sex)
                .setConvertListener((ViewConvertListener) (holder, dialog) -> {
                    Button bt_photo = holder.getView(R.id.bt_photo);
                    Button bt_album = holder.getView(R.id.bt_album);
                    TextView tv_cancel = holder.getView(R.id.tv_cancel);
                    bt_photo.setText(R.string.photograph);
                    bt_album.setText(R.string.select_photo);
                    bt_photo.setOnClickListener(v -> {
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(1);
                        Intent intent = new Intent(PersonInfoActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        dialog.dismiss();
                    });
                    bt_album.setOnClickListener(v -> {
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(1);
                        Intent intent1 = new Intent(PersonInfoActivity.this, ImageGridActivity.class);
                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
                        dialog.dismiss();
                    });
                    tv_cancel.setOnClickListener(v -> {
                        dialog.dismiss();
                    });

                }).setOutCancel(true).setShowBottom(true).setDimAmount(0.5f).show(getSupportFragmentManager());
    }

    ArrayList<ImageItem> images = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //添加图片返回
        if (data != null && requestCode == REQUEST_CODE_SELECT) {
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            //添加图片返回
            if (images != null) {
                Glide.with(mContext)
                        .load(images.get(0).path)
                        .placeholder(R.drawable.ease_default_expression)
                        // .error(defaultImg)
                        .transform(new CenterCrop(mContext), new GlideCircleTransform(mContext))
                        .crossFade()
                        .into(iv_header);
            } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
                //预览图片返回
                if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                    images = (ArrayList<com.lzy.imagepicker.bean.ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                    if (images != null) {
                        Glide.with(mContext)
                                .load(images.get(0).path)
                                .placeholder(R.drawable.ease_default_expression)
                                // .error(defaultImg)
                                .transform(new CenterCrop(mContext), new GlideCircleTransform(mContext))
                                .crossFade()
                                .into(iv_header);
                    }
                }
            }
        }
        switch (resultCode) {
            case Constant.NICKNAME:
                mTrim = data.getStringExtra("trim");
                tv_name.setText(mTrim);
                break;
            case Constant.CLASS_PHONE:
                mTrim = data.getStringExtra("trim");
                tv_phone.setText(mTrim);
                break;
            case Constant.CLASS_ADDRESS:
                mTrim = data.getStringExtra("trim");
                tv_address.setText(mTrim);
                break;
        }
    }
}
