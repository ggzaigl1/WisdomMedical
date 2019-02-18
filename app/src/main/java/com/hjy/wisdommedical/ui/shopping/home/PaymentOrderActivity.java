package com.hjy.wisdommedical.ui.shopping.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.AddressToListBean;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.PayShoppingListBean;
import com.example.handsomelibrary.model.ShopingCartBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.personal.activity.DispatchingActivity;
import com.hjy.wisdommedical.ui.shopping.home.adapter.PaymentOrderAdapter;
import com.hjy.wisdommedical.ui.shopping.person.activity.DrugOrderActivity;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 支付订单
 *
 * @author Stefan
 * @date 2018/10/23 14:58
 */
public class PaymentOrderActivity extends BaseActivity {
    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.iv_down)
    ImageView iv_down;
    //选择支付宝
    @BindView(R.id.cb_aliPay)
    CheckBox cb_aliPay;
    //选择微信
    @BindView(R.id.cb_weChat)
    CheckBox cb_weChat;
    @BindView(R.id.rl_WeChatPay)
    RelativeLayout rl_WeChatPay;
    @BindView(R.id.rv_orderList)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_pay)
    TextView tv_pay;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.iv_img)
    ImageView mIvImg;
    @BindView(R.id.tv_count)
    TextView mTvCount;
    @BindView(R.id.tv_pay_sum)
    TextView tv_pay_sum;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_num)
    TextView tv_num;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.Ll_buy)
    LinearLayout Ll_buy;

    PaymentOrderAdapter mAdapter;
    private int mType;
    private int mId;
    private AtomicInteger mAmount = new AtomicInteger();
    private int mPriceNow;
    private String mSumPrice;
    private String mCart;

    private BaseBean<ShopingCartBean> mBaseBean;

    @Override
    protected int getContentView() {
        return R.layout.activity_payment_order;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText("订单支付");
        mType = getIntent().getIntExtra("type", -1);
        mId = getIntent().getIntExtra("id", -1);//商品id
        mAmount.set(getIntent().getIntExtra("Amount", 1)); //商品数量
        mPriceNow = getIntent().getIntExtra("priceNow", -1);
        String pdSmallPic = getIntent().getStringExtra("pdSmallPic");
        String pdCom = getIntent().getStringExtra("pdCom");

        getConfirmOrder();
        setPayMethod();
        mAdapter = new PaymentOrderAdapter(new ArrayList<>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);

        if (mType == 3) {
            //购物车传值
            mBaseBean = (BaseBean<ShopingCartBean>) getIntent().getSerializableExtra("ShoppingCartArray");
            if (mBaseBean != null) {
                mSumPrice = getIntent().getStringExtra("sumPrice");
                mRecyclerView.setVisibility(View.VISIBLE);
                Ll_buy.setVisibility(View.GONE);
                tv_pay_sum.setText("￥" + mSumPrice);
                mTvMoney.setText("￥" + mSumPrice);
                mAdapter.setNewData(mBaseBean.getRows().getRows());
            }
        } else {
            mRecyclerView.setVisibility(View.GONE);
            Ll_buy.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(ApiService.BASE_PIC_URL + pdSmallPic).into(mIvImg);
            mTvCount.setText(pdCom);
            tv_num.setText("" + 1);
            tv_price.setText("￥" + mPriceNow);
            tv_pay_sum.setText("" + mPriceNow);
            mTvMoney.setText("￥" + mPriceNow);
        }
    }

    private void setPayMethod() {
        cb_aliPay.setOnClickListener(v -> {
            cb_weChat.setChecked(false);
            cb_aliPay.setChecked(true);
            if (cb_aliPay != null && cb_weChat != null) {
                if (cb_aliPay.isChecked()) {
                    cb_weChat.setChecked(false);
                }
            }
        });

        cb_weChat.setOnClickListener(v -> {
            cb_aliPay.setChecked(false);
            cb_weChat.setChecked(true);
            if (cb_aliPay != null && cb_weChat != null) {
                if (cb_weChat.isChecked()) {
                    cb_aliPay.setChecked(false);
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    @OnClick({R.id.iv_back, R.id.iv_down, R.id.iv_top, R.id.cb_aliPay, R.id.tv_pay, R.id.Ll_confirmOrder, R.id.tv_reduce, R.id.tv_plus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.iv_down:
                ivTop.setVisibility(View.VISIBLE);
                iv_down.setVisibility(View.GONE);
                rl_WeChatPay.setVisibility(View.VISIBLE);
                cb_aliPay.setVisibility(View.VISIBLE);
                cb_weChat.setVisibility(View.VISIBLE);
                cb_weChat.setChecked(false);
                break;
            case R.id.iv_top:
                rl_WeChatPay.setVisibility(View.GONE);
                ivTop.setVisibility(View.INVISIBLE);
                iv_down.setVisibility(View.VISIBLE);
                cb_weChat.setChecked(false);
                break;
            case R.id.tv_pay:
                if (mType == 3) {
                    if (cb_aliPay.isChecked()) {
                        getPayOrder();
                    } else if (cb_weChat.isChecked()) {
                        T.showShort("暂不支持微信购买！！");
                    } else {
                        T.showShort("请选择支付方式！！");
                    }
                } else {
                    if (cb_aliPay.isChecked()) {
                        getOrder();
                    } else if (cb_weChat.isChecked()) {
                        T.showShort("暂不支持微信购买！");
                    } else {
                        T.showShort("请选择支付方式！");
                    }
                }
                break;
            case R.id.Ll_confirmOrder:
                Intent intent = new Intent(this, DispatchingActivity.class);
                startActivityForResult(intent, Constant.SHIPPING_ADDRESS);
                break;
            //商品加
            case R.id.tv_plus:
                if (mAmount.get() < 99) {
                    mAmount.getAndIncrement();
                    tv_num.setText(mAmount.get() + "");
                    tv_pay_sum.setText("" + (mAmount.get() * mPriceNow));
                    mTvMoney.setText("￥" + (mAmount.get() * mPriceNow));
                    listCart(mId, mAmount.get());
                } else {
                    T.showLong("达到最大数量了");
                }
                break;
            //商品减
            case R.id.tv_reduce:
                if (mAmount.get() > 1) {
                    mAmount.getAndDecrement();
                    tv_num.setText(mAmount.get() + "");
                    tv_pay_sum.setText("" + (mAmount.get() * mPriceNow));
                    mTvMoney.setText("￥" + (mAmount.get() * mPriceNow));
                    listCart(mId, mAmount.get());
                } else {
                    T.showLong("最少购买一件哦");
                }
                break;
            default:
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constant.SHIPPING_ADDRESS) {
            AddressToListBean.RowsBean rowsBean = (AddressToListBean.RowsBean) data.getSerializableExtra("data");
            tv_name.setText(rowsBean.getConsigneeName());
            tv_phone.setText(rowsBean.getMobile());
            tv_address.setText(rowsBean.getProvince()
                    + rowsBean.getCity()
                    + rowsBean.getDistrict()
                    + rowsBean.getStreet());
        }
    }

    /**
     * 加载默认的收货地址
     */
    @SuppressLint("CheckResult")
    private void getConfirmOrder() {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .getConfirmOrder(SpfUtils.getSpfSaveInt(Constant.userId))
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<PayShoppingListBean>>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    protected void onSuccess(BaseBean<PayShoppingListBean> listBaseBean) {
                        mKProgressHUD.dismiss();
                        if (listBaseBean != null && null != listBaseBean.getRows()) {
                            tv_name.setText(listBaseBean.getRows().getConsigneeName());
                            tv_phone.setText(listBaseBean.getRows().getMobile());
                            tv_address.setText(listBaseBean.getRows().getProvince()
                                    + listBaseBean.getRows().getCity()
                                    + listBaseBean.getRows().getDistrict()
                                    + listBaseBean.getRows().getStreet());
                        } else {
                            tv_name.setText("暂无数据");
                            tv_phone.setText("");
                            tv_address.setText("");
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showShort(errorMsg);
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void listCart(long cartId, int amount) {
        RxHttpUtils.createApi(ApiService.class)
                .updateAmount(cartId, amount)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listBaseBean -> {
                }, throwable -> T.showShort("网络连接异常"));
    }

    /**
     * 1.从购物车下单
     */
    private void getPayOrder() {
        if (tv_phone.getText().toString().equals("")) {
            T.showShort("收货地址不能为空");
            return;
        }
        List<Integer> cartId = new ArrayList<>();
        ShopingCartBean shopingCartBean = mBaseBean.getRows();
        for (int i = 0; i < shopingCartBean.getRows().size(); i++) {
            cartId.add(shopingCartBean.getRows().get(i).getId());
            //去除list集合中括号
            mCart = Pattern.compile("\\b([\\w\\W])\\b").matcher(cartId.toString().substring(1, cartId.toString().length() - 1)).replaceAll("'$1'");
        }
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> params = new ArrayMap<>();
        params.put("receiptName", tv_name.getText().toString());//收件人姓名
        params.put("receiptPhone", tv_phone.getText().toString());//收件人电话
        params.put("receiptAddress", tv_address.getText().toString());//收货地址
        params.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        params.put("orderPay", mSumPrice);//订单总价
        params.put("goodsPay", mSumPrice);//商品总价
        params.put("logisticsPay", 0);//运费
        params.put("cartId", mCart);//购物车ID集合
        params.put("orderType", 1);//下单方式
        RxHttpUtils.createApi(ApiService.class)
                .getOrder(params)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<Object>() {
                    @Override
                    protected void onSuccess(Object o) {
                        mKProgressHUD.dismiss();
                        T.showShort("购买成功");
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Intent intent = new Intent(mContext, DrugOrderActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }.start();
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showShort(errorMsg);
                        mKProgressHUD.dismiss();
                    }
                });

    }

    /**
     * 2.立即购买下单
     */
    private void getOrder() {
        if (tv_phone.getText().toString().equals("")) {
            T.showShort("收货地址不能为空");
            return;
        }
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> params = new ArrayMap<>();
        params.put("receiptName", tv_name.getText().toString());//收件人姓名
        params.put("receiptPhone", tv_phone.getText().toString());//收件人电话
        params.put("receiptAddress", tv_address.getText().toString());//收货地址
        params.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        params.put("orderPay", tv_pay_sum.getText().toString());//订单总价
        params.put("goodsPay", tv_pay_sum.getText().toString());//商品总价
        params.put("logisticsPay", 0);//运费
        params.put("goodsId", mId);//商品id
        params.put("goodsCount", tv_num.getText().toString().trim());//购买数量
        params.put("goodsPrice", tv_pay_sum.getText().toString());//商品成交价
        params.put("orderType", 2);//下单方式
        RxHttpUtils.createApi(ApiService.class)
                .getOrder(params)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<Object>() {
                    @Override
                    protected void onSuccess(Object o) {
                        mKProgressHUD.dismiss();
                        T.showShort("购买成功");
                        Intent intent = new Intent(mContext, DrugOrderActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showShort(errorMsg);
                        mKProgressHUD.dismiss();
                    }
                });
    }

}
