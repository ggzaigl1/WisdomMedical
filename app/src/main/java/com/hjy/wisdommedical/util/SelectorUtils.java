package com.hjy.wisdommedical.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;

import com.example.handsomelibrary.application.BaseApplication;

/**
 * 代码生成选择器工具类 扩展TintUtils
 * Created by fangs on 2018/8/29 15:39.
 */
public class SelectorUtils {

    private SelectorUtils() {}

    /**
     * 根据传递的 drawable 和 颜色资源 生成选择器
     * @param Drawable
     * @param selectedColor
     * @param nomalColor
     * @return
     */
    public static Drawable getSelector(Drawable Drawable, @ColorRes int selectedColor, @ColorRes int nomalColor){

        int[][] states = new int[5][];
        states[0] = new int[]{android.R.attr.state_pressed};
        states[1] = new int[]{android.R.attr.state_selected};
        states[2] = new int[]{android.R.attr.state_checked};
        states[3] = new int[]{android.R.attr.state_activated};
        states[4] = new int[]{};

        Context ctx = BaseApplication.getAppContext();
        int color = ContextCompat.getColor(ctx, selectedColor);
        int[] colors = new int[]{color, color, color, color,
                ContextCompat.getColor(ctx, nomalColor)};

        return TintUtils.tintSelector(Drawable, colors, states);
    }
}
