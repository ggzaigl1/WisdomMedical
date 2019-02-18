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
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
import com.hjy.wisdommedical.ui.inquiry.adapter.VoiceAdvisoryAdapter;
import com.hjy.wisdommedical.util.UpLoadUtils;
import com.hjy.wisdommedical.widget.GlideImagePickerLoader;
import com.hjy.wisdommedical.widget.dialog.BaseNiceDialog;
import com.hjy.wisdommedical.widget.dialog.NiceDialog;
import com.hjy.wisdommedical.widget.dialog.ViewConvertListener;
import com.hjy.wisdommedical.widget.dialog.ViewHolder;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;

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
 * 语音咨询
 */
public class VoiceAdvisoryActivity extends BaseActivity {

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
    @BindView(R.id.iamge_recycler_view)
    RecyclerView mIamgeRecyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.text_people)
    TextView text_people;
    @BindView(R.id.tv_data2String)
    TextView tv_data2String;
    @BindView(R.id.tv_order)
    TextView tv_order;

    private int fontCount;
    private int fontCountName;
    private int visitMemberId;
    private VoiceAdvisoryAdapter mAdapter;
    private String ReserveDate;
    private int ReserveTime;
    private List<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 3;
    private ImagePickerAdapter imageAdapter;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    @Override
    protected int getContentView() {
        return R.layout.activity_video_advisory;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.buy_voice_advisory);
        ReserveDate = getIntent().getStringExtra("reserveDate");//预约日期
        ReserveTime = getIntent().getIntExtra("reserveTime", 1);//预约时间段
        tv_data2String.setText(getString(R.string.voice_time) + ":" + ReserveDate + " " + Constant.time[ReserveTime]);
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
        mAdapter = new VoiceAdvisoryAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_item_image_text:
                        String diseaseName = mAdapter.getData().get(position).getDiseaseName();
                        id_editor_detail.setText(diseaseName);
                        break;
                }
            }
        });
    }

    //图片选择配置 一般在application中配置 但是不确定其他位置是否还有使用情况且选择图片数量不一定
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImagePickerLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    //初始化图片选择
    private void initImageRecycler() {
        selImageList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        mIamgeRecyclerView.setLayoutManager(manager);

        imageAdapter = new ImagePickerAdapter(VoiceAdvisoryActivity.this, selImageList, maxImgCount);

        mIamgeRecyclerView.setHasFixedSize(true);
        mIamgeRecyclerView.setAdapter(imageAdapter);

        imageAdapter.setItemClickListener(new ImagePickerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == imageAdapter.getImages().size()) {
                    showSelectDialog();
                } else {
                    Intent intentPreview = new Intent(VoiceAdvisoryActivity.this, ImagePreviewDelActivity.class);
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) imageAdapter.getImages());
                    intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                    intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                    startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                }
            }
        });
        imageAdapter.setItemLongClickListener(new ImagePickerAdapter.OnItemLongClickLister() {
            @Override
            public void onItemLongClick(int position) {
                T.showShort("长按了" + position);
            }
        });

    }

    //选择图片弹框
    private void showSelectDialog() {
        NiceDialog.init()
                .setLayoutId(R.layout.dialog_select_phono)
                .setConvertListener(new ViewConvertListener() {
                    @Override
                    public void convertView(ViewHolder holder, final BaseNiceDialog dialog) {
                        //拍照
                        holder.setOnClickListener(R.id.bt_photo, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(VoiceAdvisoryActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                dialog.dismiss();
                            }
                        });
                        //相册
                        holder.setOnClickListener(R.id.bt_album, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(VoiceAdvisoryActivity.this, ImageGridActivity.class);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                dialog.dismiss();
                            }
                        });
                        //取消
                        holder.setOnClickListener(R.id.tv_cancel, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setOutCancel(true)
                .setShowBottom(true)
                .setDimAmount(0.5f)
                .show(getSupportFragmentManager());
    }

    ArrayList<ImageItem> images = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<com.lzy.imagepicker.bean.ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    imageAdapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<com.lzy.imagepicker.bean.ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
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

    @OnClick({R.id.Ll_people_set, R.id.iv_back, R.id.bt_Buy})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Ll_people_set:
                Intent intent = new Intent(this, ChooseUseActivity.class);
                startActivityForResult(intent, Constant.SELECT_USER);
                break;
            case R.id.iv_back:
                this.finish();
                break;
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
                uploadImages("medicalRecords", images);

                break;
        }
    }

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

    /**
     * 上传图片并提交用户反馈
     *
     * @param type  图片类型	photo(头像)、feedback(反馈)、medicalRecords(病历)
     * @param lists 文件列表
     */
    @SuppressLint("CheckResult")
    private void uploadImages(String type, List<ImageItem> lists) {
        KProgressHUD.create(VoiceAdvisoryActivity.this)
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
                                    .uploadPostFile(RequestBody.create(MediaType.parse("multipart/form-data"), type), files)
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
                    T.showShort(VoiceAdvisoryActivity.this.getString(R.string.complete));
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 3);
                    JumpUtils.jump(mContext, MyAdviceActivity.class, bundle);
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
        param.put("serviceId", 3);//服务类型ID
        param.put("visitMemberId", visitMemberId);//就诊人ID
        param.put("diseaseName", id_editor_detail.getText().toString().trim());//	疾病名称
        param.put("consultContent", id_editor_detail_name.getText().toString().trim());//病情描述
        param.put("image", imgUrls);//圖片
        param.put("reserveDate", ReserveDate);//预约日期
        param.put("reserveTime", ReserveTime);//预约时间段
        return RxHttpUtils.createApi(ApiService.class)
                .buyService(param)
                .compose(Transformer.switchSchedulers());
    }
}
