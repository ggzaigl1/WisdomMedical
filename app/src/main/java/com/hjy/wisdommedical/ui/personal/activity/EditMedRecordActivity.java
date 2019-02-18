package com.hjy.wisdommedical.ui.personal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.inquiry.adapter.ImagePickerAdapter;
import com.hjy.wisdommedical.widget.dialog.NiceDialog;
import com.hjy.wisdommedical.widget.dialog.ViewConvertListener;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑病历
 * Created by Stefan on 2018/9/7 19:37.
 */
public class EditMedRecordActivity extends BaseActivity {

    @BindView(R.id.iamge_recycler_view)
    RecyclerView image_recycler_view;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.tv_limit)
    TextView tv_limit;
    @BindView(R.id.et_describe)
    EditText et_describe;

    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    ArrayList<ImageItem> images = null;
    private List<ImageItem> selImageList; //当前选择的所有图片
    private ImagePickerAdapter imageAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_edit_med_record;
    }

    //初始化图片选择
    private int maxImgCount = 3;

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText("编辑病历");
        tv_right.setText("保存");
        initImagePicker();
        initImageRecycler();
        et_describe.setFilters(new InputFilter[]{new InputFilter.LengthFilter(100)});
        et_describe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = et_describe.getText().toString().length();
                if(length<=100){
                    tv_limit.setText(length + "");
                }else {
                    T.showShort("输入超过字数限制！");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        GridLayoutManager manager = new GridLayoutManager(this, 4);
        image_recycler_view.setLayoutManager(manager);
        imageAdapter = new ImagePickerAdapter(EditMedRecordActivity.this, selImageList, maxImgCount);
        image_recycler_view.setHasFixedSize(true);
        image_recycler_view.setAdapter(imageAdapter);


        imageAdapter.setItemClickListener(position -> {
            if (position == imageAdapter.getImages().size()) {
                showSelectDialog();
            } else {
                Intent intentPreview = new Intent(EditMedRecordActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) imageAdapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
            }
        });
    }

    //选择图片弹框
    private void showSelectDialog() {
        NiceDialog.init().setLayoutId(R.layout.dialog_select_phono)
                .setConvertListener((ViewConvertListener) (holder, dialog) -> {
                    //拍照
                    holder.setOnClickListener(R.id.bt_photo, v -> {
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(1);
                        Intent intent = new Intent(EditMedRecordActivity.this, ImageGridActivity.class);
                        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                        startActivityForResult(intent, REQUEST_CODE_SELECT);
                        dialog.dismiss();
                    });
                    //相册
                    holder.setOnClickListener(R.id.bt_album, v -> {
                        //打开选择,本次允许选择的数量
                        ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                        Intent intent1 = new Intent(EditMedRecordActivity.this, ImageGridActivity.class);
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

    private void initImageRecycler() {
        selImageList = new ArrayList<>();

        GridLayoutManager manager = new GridLayoutManager(this, 4);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        image_recycler_view.setLayoutManager(manager);
        imageAdapter = new ImagePickerAdapter(EditMedRecordActivity.this, selImageList, maxImgCount);
        image_recycler_view.setHasFixedSize(true);
        image_recycler_view.setAdapter(imageAdapter);

        imageAdapter.setItemClickListener(position -> {
            if (position == imageAdapter.getImages().size()) {
                showSelectDialog();
            } else {
                Intent intentPreview = new Intent(EditMedRecordActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) imageAdapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
            }
        });
    }

    //图片选择配置 一般在application中配置 但是不确定其他位置是否还有使用情况且选择图片数量不一定
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制

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


    @OnClick({R.id.iv_back, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.tv_right:
//                if (TextUtils.isEmpty(et_feedback.getText().toString().trim())) {
//                    T.showShort(getString(R.string.feedback_null));
//                    return;
//                }
//                uploadImages("feedback", images);
                break;
        }
    }


}
