package com.hjy.wisdommedical.ui.inquiry.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.DiseaseBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.net.RetrofitManager;
import com.hjy.wisdommedical.ui.inquiry.adapter.ImagePickerAdapter;
import com.hjy.wisdommedical.ui.inquiry.adapter.ImageTextAdapter;
import com.hjy.wisdommedical.util.UpLoadUtils;
import com.hjy.wisdommedical.widget.dialog.NiceDialog;
import com.hjy.wisdommedical.widget.dialog.ViewConvertListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;

/**
 * Created by 初夏小溪 on 2018/7/2 0002.
 * 图文咨询
 */
public class ImageTextActivity extends BaseActivity {

    @BindView(R.id.id_editor_detail)
    EditText id_editor_detail;
    @BindView(R.id.id_editor_detail_font_count)
    TextView detail_font_count;

    @BindView(R.id.id_editor_detail_name)
    EditText id_editor_detail_name;
    @BindView(R.id.id_editor_detail_font_count_name)
    TextView detail_font_count_name;

    @BindView(R.id.ry_image_text)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.bt_Buy)
    Button bt_Buy;
    @BindView(R.id.iamge_recycler_view)
    RecyclerView image_recycler_view;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.text_people)
    TextView text_people;
    @BindView(R.id.tv_order)
    TextView tv_order;
    private int fontCount;
    private int fontCountName;
    private int visitMemberId;
    private ImageTextAdapter mAdapter;

    private List<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 3;
    private ImagePickerAdapter imageAdapter;

    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    @Override
    protected int getContentView() {
        return R.layout.activity_image_text;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.buy_image_advisory);
        init();
        initImagePicker();
        initImageRecycler();
        setDummyData();

        /**
         * 疾病名称 字数统计
         */
        id_editor_detail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fontCount = start + count;
                detail_font_count.setText(fontCount + "/10");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        /**
         * 疾病详情字数统计
         */
        id_editor_detail_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fontCountName = start + count;
                detail_font_count_name.setText(fontCountName + "/300");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        tv_order.setText("¥ 0");
    }


    /**
     * 疾病名称条目
     */
    private void init() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ImageTextAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_item_image_text:
                    String diseaseName = mAdapter.getData().get(position).getDiseaseName();
                    id_editor_detail.setText(diseaseName);
                    break;
            }
        });
    }

    /**
     * 疾病名称条目接口
     */
    private void setDummyData() {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RetrofitManager.createApi(ApiService.class)
                .getDiseaseBean(SpfUtils.getSpfSaveInt(Constant.userId))
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<DiseaseBean>() {
                    @Override
                    protected void onSuccess(DiseaseBean diseaseBean) {
                        if (diseaseBean != null) {
                            mAdapter.setNewData(diseaseBean.getListCommonDisease());
                            mKProgressHUD.dismiss();
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });
    }

    //图片选择配置 一般在application中配置 但是不确定其他位置是否还有使用情况且选择图片数量不一定
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制

    }

    //选择图片弹框
    private void showSelectDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_select_phono)
                .setConvertListener((ViewConvertListener) (holder, dialog) -> {
                    //拍照
                    holder.setOnClickListener(R.id.bt_photo, v -> {
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent = new Intent(ImageTextActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        dialog.dismiss();
                    });
                    //相册
                    holder.setOnClickListener(R.id.bt_album, v -> {
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent1 = new Intent(ImageTextActivity.this, ImageGridActivity.class);
                        startActivityForResult(intent1, REQUEST_CODE_SELECT);
                        dialog.dismiss();
                    });
                    //取消
                    holder.setOnClickListener(R.id.tv_cancel, v -> dialog.dismiss());
                })
                .setOutCancel(true)
                .setShowBottom(true)
                .setDimAmount(0.5f)
                .show(getSupportFragmentManager());
    }


    //初始化图片选择
    private void initImageRecycler() {
        selImageList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        image_recycler_view.setLayoutManager(manager);
        imageAdapter = new ImagePickerAdapter(ImageTextActivity.this, selImageList, maxImgCount);
        image_recycler_view.setHasFixedSize(true);
        image_recycler_view.setAdapter(imageAdapter);

        imageAdapter.setItemClickListener(position -> {
            if (position == imageAdapter.getImages().size()) {
                showSelectDialog();
            } else {
                Intent intentPreview = new Intent(ImageTextActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) imageAdapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
            }
        });
    }

    ArrayList<ImageItem> images = null;

    /**
     * 图片选择器 返回数据 和 就诊人选择返回数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    imageAdapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    imageAdapter.setImages(selImageList);
                }
            }
        } else if (resultCode == Constant.SELECT_USER) {
            String name = data.getStringExtra("name");
            visitMemberId = data.getIntExtra("visitMemberId", 0);
            text_people.setText(name);
        }
    }

    @OnClick({R.id.iv_back, R.id.bt_Buy, R.id.Ll_people_set})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_Buy:
                if (text_people.getText().toString().trim().equals("")) {
                    T.showShort(getString(R.string.user_null));
                    return;
                }
                if (id_editor_detail.getText().toString().trim().equals("")) {
                    T.showShort(getString(R.string.disease_cannot_name_empty));
                    return;
                }
                if (id_editor_detail_name.getText().toString().trim().equals("")) {
                    T.showShort(getString(R.string.disease_cannot_empty));
                    return;
                }
                uploadImages(images);
                break;
            //选择就诊人
            case R.id.Ll_people_set:
                Intent intent = new Intent(this, ChooseUseActivity.class);
                startActivityForResult(intent, Constant.SELECT_USER);
                break;
            case R.id.iv_back:
                this.finish();
                break;
            default:
                break;
        }
    }

    /**
     * 上传图片并提交用户反馈
     *
     * @param lists 文件列表
     */
    @SuppressLint("CheckResult")
    private void uploadImages(List<ImageItem> lists) {
        KProgressHUD.create(ImageTextActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
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
                        public List<File> apply(List<String> list) throws Exception {
                            return Luban.with(mContext)
                                    .load(list)
                                    .ignoreBy(100)
//                                    .setTargetDir(Environment.getDataDirectory().getPath())
                                    .get();
                        }
                    })
                    .map(UpLoadUtils::filesToMultipartBodyPart)
                    .flatMap(new Function<List<MultipartBody.Part>, ObservableSource<? extends String>>() {
                        @Override
                        public ObservableSource<? extends String> apply(List<MultipartBody.Part> files) throws Exception {
                            return RxHttpUtils.createApi(ApiService.class)
                                    .uploadPostFile(RequestBody.create(MediaType.parse("multipart/form-data"), "medicalRecords"), files)
                                    .compose(Transformer.switchSchedulers());
                        }
                    }).flatMap(this::getBuyService);
        } else {
            observable = getBuyService("");
        }
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String objectBaseBean) throws Exception {
                if (objectBaseBean != null) {
                    T.showShort(ImageTextActivity.this.getString(R.string.succeed));
                    JumpUtils.jump(mContext, MyAdviceActivity.class, null);
//                JumpUtils.jump(mContext, BuyActivity.class, null);
                    // TODO: 2018/9/4 0004  支付跳转
                    JumpUtils.exitActivity(mContext);
                    mKProgressHUD.dismiss();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mKProgressHUD.dismiss();
                T.showShort(getString(R.string.submit_failure));
            }
        });
    }

    /**
     * 购买服务（图文、语音、视频） 被观察者
     *
     * @param imgUrls
     * @return
     */
    private Observable<String> getBuyService(String imgUrls) {
        ArrayMap<String, Object> param = new ArrayMap<>();
        param.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        param.put("username", SpfUtils.getSpfSaveStr(Constant.username));
        param.put("docId", SpfUtils.getSpfSaveInt(Constant.docId));
        param.put("serviceId", 1);//服务类型ID
        param.put("visitMemberId", visitMemberId);//就诊人ID
        param.put("diseaseName", id_editor_detail.getText().toString().trim());//	疾病名称
        param.put("consultContent", id_editor_detail_name.getText().toString().trim());//病情描述
        param.put("image", imgUrls);//圖片
        param.put("reserveDate", "");//预约日期
        param.put("reserveTime", "");//预约时间段
        return RxHttpUtils.createApi(ApiService.class)
                .buyService(param)
                .compose(Transformer.switchSchedulers());
    }

}

