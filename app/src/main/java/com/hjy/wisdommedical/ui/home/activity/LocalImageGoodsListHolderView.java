package com.hjy.wisdommedical.ui.home.activity;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.utils.GlideRoundTransform;
import com.hjy.wisdommedical.entity.BannerBean;

/**
 * 本地图片Holder 商城
 * Created by fangs on 2017/7/6.
 */
public class LocalImageGoodsListHolderView implements Holder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {

        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        Glide.with(context)
                .load(ApiService.BASE_PIC_URL + data)
                .transform(new CenterCrop(context), new GlideRoundTransform(context, 20))
                .into(imageView);
    }
}
