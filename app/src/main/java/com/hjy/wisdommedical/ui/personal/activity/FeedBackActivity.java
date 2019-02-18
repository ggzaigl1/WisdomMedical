package com.hjy.wisdommedical.ui.personal.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.inquiry.adapter.ImagePickerAdapter;
import com.hjy.wisdommedical.util.UpLoadUtils;
import com.hjy.wisdommedical.widget.dialog.NiceDialog;
import com.hjy.wisdommedical.widget.dialog.ViewConvertListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;

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
 * 意见反馈 Activity
 * Created by Stefan on 2018/7/9 14:58.
 */

public class FeedBackActivity extends BaseActivity {
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    @BindView(R.id.iamge_recycler_view)
    RecyclerView image_recycler_view;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.et_feedback)
    EditText et_feedback;
    @BindView(R.id.tv_inputNum)
    TextView tv_inputNum;

    ArrayList<ImageItem> images = null;
    private List<ImageItem> selImageList; //当前选择的所有图片
    private ImagePickerAdapter imageAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.feedback);
        initImagePicker();
        initImageRecycler();
        et_feedback.setFilters(new InputFilter[]{new InputFilter.LengthFilter(200)});
        et_feedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_inputNum.setText(et_feedback.getText().toString().length() + "");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @OnClick({R.id.iv_back, R.id.bt_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.bt_commit:
                if (TextUtils.isEmpty(et_feedback.getText().toString().trim())) {
                    T.showShort(getString(R.string.feedback_null));
                    return;
                }
                uploadImages("feedback", images);
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
                    .map(imageItems -> {
                        List<String> imgPath = new ArrayList<>();
                        for (ImageItem imageItem : imageItems) {
                            imgPath.add(imageItem.getPath());
                        }
                        return imgPath;
                    }).map(list -> Luban.with(mContext)
                            .load(list)
                            .ignoreBy(100)
//                                    .setTargetDir(Environment.getDataDirectory().getPath())
                            .get())
                    .map(fileList -> UpLoadUtils.filesToMultipartBodyPart(fileList))
                    .flatMap((Function<List<MultipartBody.Part>, ObservableSource<String>>) files -> RxHttpUtils.createApi(ApiService.class)
                            .uploadPostFile(RequestBody.create(MediaType.parse("multipart/form-data"), type), files)
                            .compose(Transformer.switchSchedulers()))
                    .flatMap((Function<String, ObservableSource<String>>) images -> getFeedBack(images));
        } else {
            observable = getFeedBack("");
        }

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String objectBaseBean) throws Exception {
                if (objectBaseBean != null) {
                    T.showShort(getString(R.string.feedback_successful));
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
     * 获取 用户反馈 被观察者
     *
     * @param imgUrls
     * @return
     */
    private Observable<String> getFeedBack(String imgUrls) {
        ArrayMap<String, Object> param = new ArrayMap<>();
        param.put("imageUrl", imgUrls);
        param.put("content", et_feedback.getText().toString().trim());

        return RxHttpUtils.createApi(ApiService.class)
                .feedback(param)
                .compose(Transformer.switchSchedulers());
    }

    //选择图片弹框
    private void showSelectDialog() {
        NiceDialog.init().setLayoutId(R.layout.dialog_select_phono)
                .setConvertListener((ViewConvertListener) (holder, dialog) -> {
                    //拍照
                    holder.setOnClickListener(R.id.bt_photo, v -> {
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(1);
                        Intent intent = new Intent(FeedBackActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        dialog.dismiss();
                    });
                    //相册
                    holder.setOnClickListener(R.id.bt_album, v -> {
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent1 = new Intent(FeedBackActivity.this, ImageGridActivity.class);
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

    //图片选择配置 一般在application中配置 但是不确定其他位置是否还有使用情况且选择图片数量不一定
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制

    }

    //初始化图片选择
    private int maxImgCount = 9;

    private void initImageRecycler() {
        selImageList = new ArrayList<>();

        GridLayoutManager manager = new GridLayoutManager(this, 4);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        image_recycler_view.setLayoutManager(manager);
        imageAdapter = new ImagePickerAdapter(FeedBackActivity.this, selImageList, maxImgCount);
        image_recycler_view.setHasFixedSize(true);
        image_recycler_view.setAdapter(imageAdapter);

        imageAdapter.setItemClickListener(position -> {
            if (position == imageAdapter.getImages().size()) {
                showSelectDialog();
            } else {
                Intent intentPreview = new Intent(FeedBackActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) imageAdapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
            }
        });
    }

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
                images = (ArrayList<com.lzy.imagepicker.bean.ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    imageAdapter.setImages(selImageList);
                }
            }
        }
    }
}
