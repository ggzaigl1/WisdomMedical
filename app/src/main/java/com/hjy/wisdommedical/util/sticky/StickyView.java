package com.hjy.wisdommedical.util.sticky;

import android.view.View;

/**
 * 获取吸附View相关的信息
 * Created by cpf on 2018/1/16.
 */
public interface StickyView {

    /**
     * 是否是吸附view
     *
     * @param view
     * @return
     */
    boolean isStickyView(View view);

    /**
     * 得到吸附view的itemType
     *
     * @return
     */
    int getStickViewType();
}
