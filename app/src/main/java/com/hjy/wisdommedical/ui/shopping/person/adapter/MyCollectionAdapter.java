package com.hjy.wisdommedical.ui.shopping.person.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.model.BaseListBean;
import com.example.handsomelibrary.model.DrugOrderBean;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 2018/11/1.
 * 我的收藏
 */
public class MyCollectionAdapter extends BaseQuickAdapter<BaseListBean.RowsBean, BaseViewHolder> {

    public MyCollectionAdapter(@Nullable List<BaseListBean.RowsBean> data) {
        super(R.layout.item_my_collection, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseListBean.RowsBean item) {
        helper.setText(R.id.tv_count, item.getPdCom())
                .setText(R.id.tv_price, item.getPriceNow() + "");
//                .setText(R.id.tv_goodsCount, "×" + item.getIsExchange());
        Glide.with(mContext).load(ApiService.BASE_PIC_URL + item.getPdSmallPic()).into((ImageView) helper.getView(R.id.iv_img));
        helper.addOnClickListener(R.id.Ll_child);
    }
}
