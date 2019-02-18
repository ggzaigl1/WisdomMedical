package com.hjy.wisdommedical.ui.shopping.trolley;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.ShopingCartBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.adapter.OnListener;
import com.hjy.wisdommedical.adapter.RvCommonAdapter;
import com.hjy.wisdommedical.adapter.ViewHolder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 购物车 列表 适配器
 * Created by QKun on 2018/10/29.
 */
public class ShoppingTrolleyAdapter extends RvCommonAdapter<ShopingCartBean.RowsBean> {

    private OnListener.OnModifyItemAmountListener modifyItemAmountListener;

    public ShoppingTrolleyAdapter(Context context, List<ShopingCartBean.RowsBean> datas) {
        super(context, R.layout.shopping_trolley_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, ShopingCartBean.RowsBean shopingCartBean, int position) {
        ShopingCartBean.RowsBean.ProductBean productBean = shopingCartBean.getProduct();
        Glide.with(mContext)
                .load(ApiService.BASE_PIC_URL + productBean.getPdSmallPic())
                .into((ImageView) holder.getView(R.id.iv_drug));
        holder.setText(R.id.tv_drug_induc, productBean.getPdName());
        holder.setText(R.id.tv_drug_money, "￥" + productBean.getPriceNow() + "");

        TextView tv_drug_num = holder.getView(R.id.tv_drug_num);
        tv_drug_num.setText(shopingCartBean.getAmount() + "");
        //数据 加
        holder.setOnClickListener(R.id.tv_trolley_add, v -> {
            int amount = shopingCartBean.getAmount() + 1;
            if (amount < 100) {
                listCart(shopingCartBean.getId(), amount);
                shopingCartBean.setAmount(amount);
                notifyItemChange(position);
                if (null != modifyItemAmountListener)
                    modifyItemAmountListener.onModifyAmount(position, true);
            } else {
                T.showLong("达到最大数量了");
            }
        });

        //数据 减
        holder.setOnClickListener(R.id.tv_drug_minus, v -> {
            int amount = shopingCartBean.getAmount() - 1;
            if (amount > 0) {
                listCart(shopingCartBean.getId(), amount);
                shopingCartBean.setAmount(amount);
                notifyItemChange(position);
                if (null != modifyItemAmountListener)
                    modifyItemAmountListener.onModifyAmount(position, false);
            } else {
                T.showLong("最少购买一件哦");
            }
        });

        // 多选按钮
        setCollectImg(holder, isItemChecked(position));
        holder.setOnClickListener(R.id.iv_check, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setItemChecked(position, !isItemChecked(position));
                notifyItemChange(position);
                if (null != changeItemListener) changeItemListener.onChange(position);
            }
        });

    }

    //设置 多选按钮是否选中
    private void setCollectImg(ViewHolder holder, boolean collect) {
        AppCompatImageView imgCollect = holder.getView(R.id.iv_check);
        if (collect) {//已收藏
            imgCollect.setImageResource(R.mipmap.icon_check_select);
        } else {
            imgCollect.setImageResource(R.mipmap.icon_check_normal);
        }
    }

    public void setModifyItemAmountListener(OnListener.OnModifyItemAmountListener modifyItemAmountListener) {
        this.modifyItemAmountListener = modifyItemAmountListener;
    }


    @SuppressLint("CheckResult")
    private void listCart(long cartId, int amount) {
        RxHttpUtils.createApi(ApiService.class)
                .updateAmount(cartId, amount)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<Object>>() {
                    @Override
                    public void accept(BaseBean<Object> listBaseBean) throws Exception {
                        T.showShort("操作成功");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        T.showShort("网络连接异常");
                    }
                });
    }
}
