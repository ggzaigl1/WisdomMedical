package com.hjy.wisdommedical.date;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.ArrayMap;
import android.view.View;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.MakeBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.L;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.adapter.GridItemDecoration;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.inquiry.activity.VideoAdvisoryActivity;
import com.hjy.wisdommedical.ui.inquiry.activity.VoiceAdvisoryActivity;
import com.hjy.wisdommedical.util.TimeUtils;
import com.hjy.wisdommedical.util.TintUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 预约时间
 * Created by fangs on 2018/7/3.
 */
public class MakeAppointmentActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.rvMake)
    RecyclerView rvMake;
    MakeAppointmentAdapter mMakeAppointmentAdapter;
    @BindView(R.id.tvWeek)
    TextView tvWeek;
    @BindView(R.id.tvMakeSuccess)
    TextView tvMakeSuccess;
    @BindView(R.id.tvCanMakeAppointment)
    TextView tvCanMakeAppointment;

    int mVoice;
    int mVideo;
    long time = 0L;
    String start, end;

    @Override
    protected int getContentView() {
        return R.layout.activity_make_appointment;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(getString(R.string.appointment_time));
        mVoice = getIntent().getExtras().getInt(Constant.VOICE);
        mVideo = getIntent().getExtras().getInt(Constant.VIDEO);
        TintUtils.setTxtIconLocal(tvCanMakeAppointment, TintUtils.getDrawable(R.drawable.svg_rectangle, 1), 0);
        TintUtils.setTxtIconLocal(tvMakeSuccess, TintUtils.getDrawable(R.drawable.svg_make, 1), 0);
        initRv();
        time = System.currentTimeMillis();
        setTimeTitle(dateToWeek(time, true));
    }

    private void setTimeTitle(List<Date> days) {
        start = TimeUtils.Data2String(days.get(0), "yyyy-MM-dd");
        end = TimeUtils.Data2String(days.get(6), "yyyy-MM-dd");
        Spannable sp = new SpannableString(start + getString(R.string.to) + end);
//        sp.setSpan(new AbsoluteSizeSpan(20, true), 0, 15, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.txtSecondColor)),
                0, 10, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.txtSecondColor)),
                11, 21, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tvWeek.setText(sp);
        getListConsult();
    }

    @OnClick({R.id.iv_back, R.id.imgLastWeek, R.id.imgNextWeek, R.id.tvConfirm})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.imgLastWeek:
                time -= 7 * 24 * 3600000;
                setTimeTitle(dateToWeek(time, true));
                break;
            case R.id.imgNextWeek:
                time += 7 * 24 * 3600000;
                setTimeTitle(dateToWeek(time, true));
                break;
            case R.id.tvConfirm:
                MakeBean bean = mMakeAppointmentAdapter.getmDatas().get(mMakeAppointmentAdapter.getmSelectedPos());
                String reserveDate = bean.getDate();
                Bundle bundle = new Bundle();
                bundle.putString("reserveDate", reserveDate);
                bundle.putInt("reserveTime", bean.getTime());
                //根据类型进入不同的界面  视频 == 1和 语音 ==2

                if (TextUtils.isEmpty(reserveDate)){
                    T.showShort("请选择预约时间");
                    return;
                }
                if (mVoice == 2) {
                    //创建意图对象
                    JumpUtils.jump(MakeAppointmentActivity.this, VoiceAdvisoryActivity.class, bundle);
                    JumpUtils.exitActivity(mContext);
                } else if (mVideo == 1) {
                    JumpUtils.jump(MakeAppointmentActivity.this, VideoAdvisoryActivity.class, bundle);
                    JumpUtils.exitActivity(mContext);
                }
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void getListConsult() {
        ArrayMap<String, Object> prams = new ArrayMap<>();
        prams.put("docId", SpfUtils.getSpfSaveInt(Constant.docId));
        prams.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        prams.put("startDate", start);
        prams.put("endDate", end);
        RxHttpUtils.createApi(ApiService.class)
                .getScheduleListToApp(prams)
                .compose(Transformer.switchSchedulers())
                .subscribe(new Consumer<List<MakeBean>>() {
                    @Override
                    public void accept(List<MakeBean> listToAppBean) throws Exception {
                        mMakeAppointmentAdapter.setmDatas(setData(listToAppBean));
                        mMakeAppointmentAdapter.notifyDataSetChanged();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        T.showShort(throwable.getMessage());
                    }
                });
    }

    private void initRv() {
        rvMake.addItemDecoration(new GridItemDecoration.Builder()
                .setColumn(8)
                .create(this));
        GridLayoutManager manager = new GridLayoutManager(this, 10);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position % 8 == 0) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        rvMake.setLayoutManager(manager);
        mMakeAppointmentAdapter = new MakeAppointmentAdapter(this, new ArrayList<>(), rvMake);
        rvMake.setAdapter(mMakeAppointmentAdapter);
    }

    /**
     * 根据 后台返回数据生成 列表数据
     *
     * @param listToAppBean
     * @return
     */
    private List<MakeBean> setData(List<MakeBean> listToAppBean) {
        List<Date> days = dateToWeek(time, true);//一周的时间集合
        List<MakeBean> data = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            if (i == 0) {
                data.add(new MakeBean());//表格 第一格
            } else {
                data.add(new MakeBean(TimeUtils.Data2String(days.get(i - 1), "yyyy-MM-dd")));//表格第一行 （一周的标题）
            }
        }
        data.addAll(listToAppBean);
        return data;
    }

    /**
     * 获取指定时间戳 所在的一周的时间集合
     *
     * @param time
     * @param isToDayStart 用于 返回的时间集合是否 以指定的时间戳为第一天
     * @return
     */
    public static List<Date> dateToWeek(long time, boolean isToDayStart) {
        L.e("今天的日期: " + TimeUtils.Long2DataString(time, "yyyy-MM-dd EEE"));
        //今天零点零分零秒的毫秒数
        long zero = time - TimeZone.getDefault().getRawOffset();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(zero);
        int d = calendar.get(Calendar.DAY_OF_WEEK);
        long startTime = isToDayStart ? calendar.getTimeInMillis() : calendar.getTimeInMillis() - d * 24 * 3600000;
        Date mDate;
        List<Date> list = new ArrayList<>();
        for (int a = 1; a < 8; a++) {
            mDate = new Date();
            if (isToDayStart) mDate.setTime(startTime + (a - 1) * 24 * 3600000);
            else mDate.setTime(startTime + a * 24 * 3600000);

            list.add(a - 1, mDate);
        }
        return list;
    }

    /**
     * 两个时间是不是同一天
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean isSameDay(long time1, long time2) {
        String date1 = TimeUtils.Long2DataString(time1, "yyyy-MM-dd");
        String date2 = TimeUtils.Long2DataString(time2, "yyyy-MM-dd");

        return date1.equals(date2);
    }
}
