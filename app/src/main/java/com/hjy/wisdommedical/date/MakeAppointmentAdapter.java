package com.hjy.wisdommedical.date;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjy.wisdommedical.R;
import com.example.handsomelibrary.model.MakeBean;
import com.hjy.wisdommedical.adapter.MultiCommonAdapter;
import com.hjy.wisdommedical.adapter.MultiTypeSupport;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.util.TimeUtils;
import com.hjy.wisdommedical.util.TintUtils;
import com.hjy.wisdommedical.adapter.ViewHolder;

import java.util.List;

/**
 * 预约时间适配器
 * Created by fangs on 2018/7/3.
 */
public class MakeAppointmentAdapter extends MultiCommonAdapter<MakeBean> {

    public static final int funType = 1001;//标题
    public static final int timeTitle = 1002;//标题
    private RecyclerView mRv;
    private int mSelectedPos = 0;//实现单选  保存当前选中的position

    public int getmSelectedPos() {
        return mSelectedPos;
    }

    public MakeAppointmentAdapter(Context context, List<MakeBean> datas, RecyclerView rv) {
        super(context, datas, new MultiTypeSupport<MakeBean>(){
            @Override
            public int getLayoutId(int itemType) {
                if (itemType == funType){
                    return R.layout.item_maketitle;
                } else if (itemType == timeTitle){
                    return R.layout.item_time_title;
                } else {
                    return R.layout.item_makeappointment;
                }
            }

            @Override
            public int getItemViewType(int position, MakeBean t) {
                if (position > 0 && position < 8) {
                    return funType;
                } else if(position % 8 == 0){
                    return timeTitle;
                } else {
                    return -1;
                }
            }
        });
        this.mRv = rv;
    }

    @Override
    public void convert(ViewHolder holder, MakeBean makeBean, int position) {
        if (position > 0 && position < 8) {
            TextView tvTitle = holder.getView(R.id.tvTitle);
            String date = makeBean.getDate();
            long timeLong = TimeUtils.timeString2long(date, "yyyy-MM-dd");
            if (MakeAppointmentActivity.isSameDay(System.currentTimeMillis(), timeLong)) {
                Spannable sp = new SpannableString("今\n天");
                sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.txtSecondColor)),
                        0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                tvTitle.setText(sp);
            } else {
                String day = TimeUtils.Long2DataString(timeLong, "dd");
                String week = TimeUtils.Long2DataString(timeLong, "E").substring(1);
                String text = week + "\n" + day;

                Spannable sp = new SpannableString(text);
                sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.txtLight)),
                        0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                sp.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.txtSecondColor)),
                        1, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                tvTitle.setText(sp);
            }
        } else if (position % 8 == 0) {
            if (position == 0) holder.setText(R.id.tvTimer, "");
            else holder.setText(R.id.tvTimer, Constant.time[makeBean.getTime()]);
        } else {
            ImageView imgContent = holder.getView(R.id.imgContent);
            int makeStatus = makeBean.getStatus();

            Drawable selector = getSelector(R.drawable.svg_make, R.drawable.svg_rectangle);
            if (mSelectedPos == 0 && makeStatus == 0) {
                imgContent.setBackground(null);
            } else if (makeStatus == 2){
                imgContent.setBackground(selector);
                imgContent.setSelected(true);
            } else {
                imgContent.setBackground(selector);

                boolean isSelect = makeStatus > 1;
                imgContent.setSelected(isSelect);
                //单选
                imgContent.setOnClickListener(v -> {
                    ViewHolder couponVH = (ViewHolder) mRv.findViewHolderForLayoutPosition(mSelectedPos);
                    if (couponVH != null) {//还在屏幕里
                        ImageView img = couponVH.getView(R.id.imgContent);
                        if (null != img)img.setSelected(false);
                    }else {//add by 2016 11 22 for 一些极端情况，holder被缓存在Recycler的cacheView里，
                        //此时拿不到ViewHolder，但是也不会回调onBindViewHolder方法。所以add一个异常处理
                        notifyItemChanged(mSelectedPos);
                    }

                    mDatas.get(mSelectedPos).setStatus(1);//不管在不在屏幕里 都需要改变数据
                    //设置新Item的勾选状态
                    mSelectedPos = position;
                    mDatas.get(mSelectedPos).setStatus(3);
                    imgContent.setSelected(true);
                });
            }

        }
    }


    public static StateListDrawable getStateListDrawable(Drawable[] drawables, int[][] states) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < drawables.length; i++) {
            int[] state = states[i];
            Drawable drawable = drawables[i];
            stateListDrawable.addState(state, drawable);
        }
        return stateListDrawable;
    }

    /**
     * 获取 指定 ID的 drawable，生成的 选择器
     * @param draId1
     * @param draId2
     * @return
     */
    public static Drawable getSelector(@DrawableRes int draId1, @DrawableRes int draId2){

        int[][] states = new int[2][];
//        states[0] = new int[]{android.R.attr.state_pressed};
        states[0] = new int[]{android.R.attr.state_selected};
//        states[2] = new int[]{android.R.attr.state_checked};
        states[1] = new int[]{};

        Drawable[] drawables = new Drawable[2];
        drawables[0] = TintUtils.getDrawable(draId1,1);
        drawables[1] = TintUtils.getDrawable(draId2,1);

        StateListDrawable stateListDrawable = getStateListDrawable(drawables, states);

        return stateListDrawable;
    }
}
