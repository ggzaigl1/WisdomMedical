package com.hjy.wisdommedical.ui.home.adapter;

import android.content.Context;
import android.widget.RadioButton;

import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.adapter.MultiCommonAdapter;
import com.hjy.wisdommedical.adapter.MultiTypeSupport;
import com.hjy.wisdommedical.adapter.ViewHolder;
import com.hjy.wisdommedical.util.Constant;
import com.hjy.wisdommedical.util.sticky.MentalHealthStickyBean;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/9/11 0011.
 * 心理健康title adapter
 */
public class MentalHealthTitleAdapter extends MultiCommonAdapter<MentalHealthStickyBean> {

    private int titleCount = 0;//题目数量
    private int count = 0;//题目答案 最大数量（如：一个问题有 A、B、C、D 四个选项）

    public MentalHealthTitleAdapter(Context context, List<MentalHealthStickyBean> datas) {
        super(context, datas, new MultiTypeSupport<MentalHealthStickyBean>() {
            @Override
            public int getLayoutId(int itemType) {
                return itemType == Constant.StickyType ?
                        R.layout.item_mental_health_title : R.layout.item_mental_health_context;
            }

            @Override
            public int getItemViewType(int position, MentalHealthStickyBean bookmark) {
                return bookmark.getItemType();
            }
        });
    }

    @Override
    public synchronized void convert(ViewHolder holder, MentalHealthStickyBean bookmark, int position) {
        if (bookmark.getItemType() == Constant.StickyType){
            holder.setText(R.id.tv_title, bookmark.getName());
        } else {
            RadioButton radioButton = holder.getView(R.id.cb_mental_health_list_item);
            if (null == radioButton) return;

            radioButton.setText(bookmark.getName());
            radioButton.setChecked(bookmark.isSelect);//设置是否选中
            setItemChecked(position, bookmark.isSelect);

            radioButton.setOnClickListener(v -> {
                bookmark.setSelect(true);
                notifyItemChanged(position);
                setItemChecked(position, true);

                int id = bookmark.getId();
                int posiT = 0;//最小 位置
                if (position >= count) {
                    posiT = position - count;
                }

                for (int i = 0; i < count * 2; i++) {
                    int posi;//循环位置
                    if (posiT + i >= getItemCount()) {
                        posi = getItemCount() - 1;
                    } else {
                        posi = posiT + i;
                    }

                    MentalHealthStickyBean bean = mDatas.get(posi);
                    if (bean.getId() == id && position != posi) {//说明是一组
                        bean.setSelect(false);
                        notifyItemChanged(posi);
                    }
                }
            });
        }
    }

    public void setSmalCount(int smalCount) {
        this.count = smalCount;
    }

    public int getTitleCount() {
        return titleCount;
    }

    public void setTitleCount(int titleCount) {
        this.titleCount = titleCount;
    }
}
